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

The entry point for the framework is the AB Test Proxy, which should implement the same interface of the alternative implementations. The component uses instances from **MetricsCollector** and  **MetricsRecorder** that are framework hot-spots. 

- **MetricsCollector** can be extended to implement new measurements.
- **MetricsRecorder** allows the application to store this data using its preferred approach, like on its database or exporting the data to another tool. 
- **Selector** is responsible for implementing the approach used to choose between the alternative implementations, being extensible to allow domain-specific criteria to be used.

The framework model defines the definition of measurements to be collected in the A/B tests through metadata (measurements like execution time or memory usage).
The framework provides an extension point to allow the implementation of new metrics through the definition of new types of metadata. 


### Simple Example

#### Scenario:
**Two different implementations of hash generation, where each implementation requires a different amount of time to create the hash.**

#### First step:
**Define an interface to abstract the interaction with the alternative implementations.**

The interface ImplementationInterface will be created.
 
```
@AverageExecutionTime
public interface ImplementationInterface {
    void calculateHash(); //method we want to test between implementations
}
```

The interface abstracts the behavior for our implementations that will create the hash. The interface receives annotations to configure the metrics, in this case @TimeMetrics will collect the execution time measurement for each implementation. 
(@TimeMetrics is a predefiend extension-point of the MetricsCollector hotspot)


#### Last step: 
**Create the factory method responsible to instantiate the proxy that performs the A/B tests.**

```
public class Main {

    public static void main(String[] args) throws Exception {

        ImplementationInterface tests = (ImplementationInterface) createOrganizer();
        for (int i=0 ; i<20; i++){
            tests.calculateHash();
        }

    }

    private static Object createOrganizer() throws Exception {
        ABTestBuilder<ImplementationInterface, Integer> abTestBuilder
                = new ABTestBuilder<ImplementationInterface, Integer>();

        Class[] classes = new Class[2];
        classes[0]= md5Hash.class;
        classes[1]= shaHash.class;

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

#### Result
**The collected average execution times of the different implementations are visible in the MetricResult when executing the A/B test.**

```
* Implementation 1 was selected
2022-05-14 18:45:12 - MetricResult{metric=TimeMetrics, selector=SelectorRandom, implementation=md5Hash, method=calculateHash, result=0.252 seconds}
** Implementation 2 was selected
2022-05-14 18:45:12 - MetricResult{metric=TimeMetrics, selector=SelectorRandom, implementation=shaHash, method=calculateHash, result=0.433 seconds}
* Implementation 1 was selected
2022-05-14 18:45:12 - MetricResult{metric=TimeMetrics, selector=SelectorRandom, implementation=md5Hash, method=calculateHash, result=0.295 seconds}
* Implementation 1 was selected
2022-05-14 18:45:13 - MetricResult{metric=TimeMetrics, selector=SelectorRandom, implementation=md5Hash, method=calculateHash, result=0.213 seconds}
** Implementation 2 was selected
2022-05-14 18:45:13 - MetricResult{metric=TimeMetrics, selector=SelectorRandom, implementation=shaHash, method=calculateHash, result=0.465 seconds}
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
- **MetricsCollector** is an interface for classes that capture metrics and @MetricsGenerator is an annotation to
  specify the metric implementation.
  This annotation is currently used by the @MemoryMetrics
  and @TimeMetrics annotations which respectively indicate the MemoryMetricsGenerator
  and TimeMetricsGenerator classes. The
  class MetricResult represents in a general way the
  collected value for a metric. It can be specialized to store
  more specific information
  Example for the metrics extension point


### Extension of hotspots examples


### MetricsCollector

**Extending the MetricsCollector hotspot to collect the value of an order in the method parameters.**
**(method of an order where one parameter represents the orderID of the order)**

*The Annotation OrderValue and the class OrderValueMetricsGenerator will be created*

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@MetricsGenerator(OrderValueMetricsGenerator.class)
public @interface OrderValue {
}
```

@OrderValue is an annotation that uses @MetricsGenerator to specify the class that implements the collection of the metrics.
OrderValueMetricsGenerator is the class we have to create and contains the logic to capture metrics.

```
public class OrderValueMetricsGenerator extends BaseAroundMetricsCollector {
    
    public OrderValueMetricsGenerator(MetricsCollector next) {
        super(next);
    }

    @Override
    public void collectException(Class selectedImplementation, Selector selector, Method method, Object[] args, Throwable targetException) throws Throwable {
        MetricResult metricResult = extractMetricResult(method,selector.getClass().getSimpleName(),selectedImplementation.getSimpleName(),targetException.getMessage());
        getMetricRecorder().save(metricResult);
    }

    @Override
    public void collectAfter(Class selectedImplementation, Selector selector, Method method, Object[] args, Object returned) throws Throwable {

    }

    @Override
    public void collectBefore(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable {
        Integer result = (Integer) args[0];
        MetricResult metricResult = extractMetricResult(method,selector.getClass().getSimpleName(),selectedImplementation.getSimpleName(),result.toString());
        getMetricRecorder().save(metricResult);
    }
}
```

The class OrderValueMetricsGenerator extends BaseAroundMetricsCollector which is used for the chain of responsability of the MetricsColletor hotspot.
To collect the wanted metrics we have to implement all of the abstract methods of the super class:

- collectBefore -> collects metrics before the invokation of the method
- collectAfter -> collects the metrics after the invokation of the method
- collectException -> collects the metrics if any exception was thrown during the invokation of the method

Inside the extended methods we create a MetricsResult that we fill with the method extractMetricResult which is extended by the BaseAroundMetricsCollector and will generate
a MetricsResult with the provided information: method, selector, selectedImplementation and result.
After creating the MetricResult we use the MetricsRecorder from BaseAroundMetricsCollector with the function getMetricRecorder and save the metricsResult inside of it.

In this example we collect the orderValue metrics by simply passing the first argument (orderID) of the invokation to the MetricRecorder.

### MetricRecorder

**Extending the MetricRecorder hotspot to store the metrics into a database.**

//wagner case study in the paper - example required

### Selector

**Extending the Selector hotspot that uses information about the method parameters to select a specific implementation for the AB-test**
**(for example - implementation B will only be selected if one of the parameters contains the company: *company-B*)**

*The Annotation targetParameter and the class CompanySelector will be created*

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface targetParameter {
}
```

```
@OrderValue
public interface ImplementationInterface {
    void placeOrder(int orderID, Item item , @targetParameter User user);
}
```

We use the targetParameter annotation to specify in the implementations which of the parameters will contain the information about the company.
The company information is used to select specific implementations for specific companies:

```
public class CompanySelector implements Selector {
    @Override
    public Class select(Class[] implementations, Object[] args,Method m) {

        Parameter[] parameters = m.getParameters();
        int targetParameterPosition=0 ;
        for(int i =0; i< parameters.length; i++){
            if (parameters[i].isAnnotationPresent(targetParameter.class)){
                targetParameterPosition= i;
            }
        }

        if(((User) args[targetParameterPosition]).getCompanyName().equals("Loacker")){
            return implementations[0];
            //for the company Loacker only the first implementation will be selected
        }else{
            return new SelectorRandom().select(implementations,args,m);
        }
    }
}
```

Here the extension CompanySelector is created that uses metadata to find the parameter with the **targetParameter** annotation.
From the targetParameter we extract the company information and for the company ***Loacker*** we specifically only select the first implementation for the AB-Tests.

Bellow a snippet of the results when we run the Framework with this new Selector:

```
* Implementation 1 was selected with user working in company: Loacker
2022-05-24 22:01:35 - MetricResult{metric=OrderValueMetrics, selector=CompanySelector, implementation=Implementation1, method=placeOrder, result=194}
* Implementation 1 was selected with user working in company: Loacker
2022-05-24 22:01:35 - MetricResult{metric=OrderValueMetrics, selector=CompanySelector, implementation=Implementation1, method=placeOrder, result=109}
* Implementation 1 was selected with user working in company: Facebook
2022-05-24 22:01:35 - MetricResult{metric=OrderValueMetrics, selector=CompanySelector, implementation=Implementation2, method=placeOrder, result=102}
* Implementation 1 was selected with user working in company: Loacker
2022-05-24 22:01:35 - MetricResult{metric=OrderValueMetrics, selector=CompanySelector, implementation=Implementation1, method=placeOrder, result=81}
* Implementation 1 was selected with user working in company: McDonalds
2022-05-24 22:01:36 - MetricResult{metric=OrderValueMetrics, selector=CompanySelector, implementation=Implementation1, method=placeOrder, result=146}
* Implementation 1 was selected with user working in company: McDonalds
2022-05-24 22:01:36 - MetricResult{metric=OrderValueMetrics, selector=CompanySelector, implementation=Implementation2, method=placeOrder, result=140}
```