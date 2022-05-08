# experiments

## Esfinge CNEXT (esfinge - CoN-tinuous EXperimentation for Tests)

A framework model to supprt A/B Tests at the class and component level

A/B tests are spread in the industry but companies usually rely on in-house 
software platforms which are typically implemented at the application level,
such as using different branches in the code repository.This increases technical debt and maintenance effort

**CNEXT** is a framework that introduces experiments at class and component level
in applications without coupling this concern to business rules in order to not waste resources and 
avoid modifications of the infrastructure.

The framework uses a dynamic proxy to select between two or more implementations implementing the same interface, using annotations on the interface to configure the metrics to be collected. 
Additionally it provides an extension point allowing the inclusion of new kinds of measrement and their persistence according to the application requirements.

Besides the proxy creation, no further reference for the A/B tests is needed neither in the implementations or in the code invoking them.

### General view


![alt text](https://github.com/SimonGschnell/experiments/blob/master/diagram.png?raw=true)


This is a graphical representation of the model. The fire icon marked elements represent
hot-spots, the ones that the framework should model as extension points.

The entry point for the framework is the AB Test Proxy, which should implement the same interface of the alternative implementations. The component uses instances from **Metrics** and  **Metrics Recorder** that are framework hot-spots. 

- **Metrics** can be extended to implement new measurements.
- **Metrics Recorder** allows the application to store this data using its preferred approach, like on its database or exporting the data to another tool. 
- **Selector** is responsible for implementing the approach used to choose between the alternative implementations, being extensible to allow domain-specific criteria to be used.

The framework model defines the definition of measurements to be collected in the A/B tests through metadata (measurements like execution time or memory usage).
The framework provides an extension point to allow the implementation of new metrics through the definition of new types of metadata. 


### Simple Example

#### Scenario:
**Two different implementations of printing to the console, where each implementation requires a different amount of time to finish execution.**

#### First step:
**Define an interface to abstract the interaction with the alternative implementations.**
The interface ImplementationInterface will be created.
 
```
@AverageExecutionTime
public interface ImplementationInterface {
    void printSomething();
}
```

The interface abstracts the behavior for our implementations that will print on the console. The interface receives annotations to configure the metrics, in this case @AverageExecutionTime will collect the average execution time measurement for each implementation. 
(@AverageExecutionTime is a custom extension-point of the Metrics hotspot)

#### Second step:
**Define how the average execution time of the implementations should be measured**
The Annotation AverageExecutionTime and the class AverageExecutionTimeGenerator will be created.

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@MetricsGenerator(AverageExecutionTimeGenerator.class)
public @interface AverageExecutionTime {
}
```

@AverageExecutionTime is an annotation that uses @MetricsGenerator to specify the class that implements the collection of the average execution time metrics.
AverageExecutionTimeGenerator is the class we have to create and will contain the logic to capture the average execution time.

```
public class AverageExecutionTimeGenerator implements Metrics {
    private MetricRecorder metricRecorder;
    private Instant startExecution;
    private List<Double> averageExecutionImpl1 = new ArrayList<>();
    private List<Double> averageExecutionImpl2 = new ArrayList<>();

    private double averageExecutionTime(List<Double> list){
        double result=0;
        for(double time : list){
            result+=time;
        }
        return result / (double)list.size();
    }

    @Override
    public MetricRecorder getMetricRecorder() {
        return this.metricRecorder;
    }

    @Override
    public void setMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
    }

    @Override
    public void startRecording(Method method) throws Exception {
        startExecution = Instant.now();
    }

    @Override
    public void finishRecording(Method method, String selectorName, String implementation) throws Exception {
        Duration duration = Duration.between(startExecution, Instant.now());
        String result;
        if(implementation.equals("Implementation1")){
            averageExecutionImpl1.add(duration.toMillis() / 1000.0d);
            result = averageExecutionTime(averageExecutionImpl1)+"" ;
        }else {
            averageExecutionImpl2.add(duration.toMillis() / 1000.0d);
            result = averageExecutionTime(averageExecutionImpl2)+"" ;
        }

        MetricResult mr = extractMetricResult(method, selectorName, implementation, result);
        metricRecorder.save(mr);
    }
}
```

In this example AverageExecutionTime is based on the predefined TimeMetricsGenerator class. Instead of collecting the execution time of each run we collect the average execution time. We realized this using 2 different lists, we use to store the execution times of the different implementations. The MetricsRecorder will then receive the calculated average execution time of the corresponding implementation. 

#### Last step: 
**Create the factory method responsible to instantiate the proxy that performs the A/B tests.**

```
public class Main {

    public static void main(String[] args) throws Exception {

        ImplementationInterface menuOrg = (ImplementationInterface) createOrganizer();
        for (int i=0 ; i<20; i++){
            menuOrg.printSomething();
        }

    }

    private static Object createOrganizer() throws Exception {
        ABTestBuilder<ImplementationInterface, Integer> abTestBuilder
                = new ABTestBuilder<ImplementationInterface, Integer>();

        Class[] classes = new Class[2];
        classes[0]= Implementation1.class;
        classes[1]= Implementation2.class;

        ABTest abTest = abTestBuilder.
                createFor(ImplementationInterface.class).
                withSelector(new SelectorRandom()).
                withMetricRecorder(new MetricRecorderLogger()).
                withImplementations(classes).build();

        return abTest.getProxy();
    }
}
```

The method createOrganizer is used to create the proxy that is responsible to perform the A/B tests with our implementations. For this we use The ABTestBuilder to configure the Selector, the MetricsRecorder (both predefined extension-points for this example) and the implementations that we want to test. 

To illustrate how the application became decoupled from the A/B test :
The Main class shows how a Implementation instance is created and used. The implementations also do not need any
reference to the framework. When the A/B test is not needed anymore, only the factory needs to be changed to return the chosen implementation. 

#### result
**The collected average execution times of the different implementations are visible in the MetricResult when executing the A/B test.**

```
** Implementation 2 was selected
2022-05-08 22:08:52 - MetricResult{metric=AverageExecutionTime, selector=SelectorRandom, implementation=Implementation2, method=printSomething, result=0.498}
* Implementation 1 was selected
2022-05-08 22:08:53 - MetricResult{metric=AverageExecutionTime, selector=SelectorRandom, implementation=Implementation1, method=printSomething, result=0.295}
* Implementation 1 was selected
2022-05-08 22:08:53 - MetricResult{metric=AverageExecutionTime, selector=SelectorRandom, implementation=Implementation1, method=printSomething, result=0.2955}
* Implementation 1 was selected
2022-05-08 22:08:53 - MetricResult{metric=AverageExecutionTime, selector=SelectorRandom, implementation=Implementation1, method=printSomething, result=0.2776666666666667}
** Implementation 2 was selected
2022-05-08 22:08:54 - MetricResult{metric=AverageExecutionTime, selector=SelectorRandom, implementation=Implementation2, method=printSomething, result=0.4505}
```


### Internal structure


![alt text](https://github.com/SimonGschnell/experiments/blob/master/model.PNG?raw=true)


The picture shows the framework’s architecture with its main classes, highlighting the extension points (hotspots) with a fire icon.
Below, the description of the main classes/interfaces from the framework structure:
 
- **ABTest** is the framework entry point that orchestrates
  the other components to perform the A/B test. This class
  behaves as a dynamic proxy that assumes the target
  interface and uses the Selector to choose between
  available implementations. This class also reads the metadata from the interface to retrieve the classes responsible for collecting the metrics.

- **ABTestBuilder** implements the Builder pattern and is
  responsible for the creation of the ABTest instance. The API chains the
  method calls to configure how the created proxy should
  perform the A/B test.
- **Selector** is an interface with an abstract method for
  selecting which implementation should be used in each
  method call. 
- **MetricRecorder** is an interface providing a method
  for handling the metric values collected by implementations of a MetricsGenerator. Usually, implementations of this class store the metric values to be used
  for analysis later. 
- **Metrics** is an interface for classes that capture metrics and @MetricsGenerator is an annotation to
  associate an annotation to the metric implementation.
  This annotation is currently used by the @MemoryMetrics
  and @PerformanceMetrics annotations which respectively indicate the MemoryMetricsGenerator
  and PerformanceMetricsGenerator classes. The
  class MetricResult represents in a general way the
  collected value for a metric. It can be specialized to store
  more specific information
  Example for the metrics extension point




