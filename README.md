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

### Framework model


![alt text](https://github.com/SimonGschnell/experiments/blob/master/diagram.png?raw=true)
*Figure 1*


**Figure 1** is a graphical representation of the model. The fire icon marked elements represent
hot-spots, the ones that the framework should model as extension points.

The entry point for the framework is the AB Test Proxy, which should implement the same interface of the alternative implementations. The component uses instances from **Metrics** and  **Metrics Recorder** that are framework hot-spots. 
The **Metrics** can be extended to implement new measurements.
The **Metrics Recorder** allows the application to store this data using its preferred approach, like on its database or exporting the data to another tool. 
The **Selector** is responsible for implementing the approach used to choose between the alternative implementations, being extensible to allow domain-specific criteria to be used.

The framework model defines the definition of measurements to be collected in the A/B tests through metadata (measurements like execution time or memory usage).
The framework provides an extension point to allow the implementation of new metrics through the definition of new types of metadata. 


### Reference implementation for this framework model for the java language

Scenario:
Two different implementations of printing to the console, where each implementation requires a different amount of time to finish execution.

First step:
define an interface to abstract the interaction with the alternative implementations. 
 

![alt text](https://github.com/SimonGschnell/experiments/blob/master/abstract%20interface.PNG?raw=true)

*Listing 1.*


The interface in listing 1 abstracts the behavior for our implementations that will print on the console. The interface receives annotations to configure the metrics, in this case @AverageExecutionTime will collect the execution time measurement for each implementation.


![alt text](https://github.com/SimonGschnell/experiments/blob/master/annotation.PNG?raw=true)
*Listing 2.*


@AverageExecutionTime is an annotation that uses @MetricsGenerator to specify the class that implements the collection of the execution time metrics.

For this example we want to record the individuall execution times of the implementations and print the average execution time of each implementation at the end.
This requires us to create an extension point for our MetricRecorder hotspot:


![alt text](https://github.com/SimonGschnell/experiments/blob/master/MetricsRecorder.PNG?raw=true)
*Listing 3.*


In the extension point MetricRecorderAveragePrinter we check from which implementation the collected metric is coming from and then persist the result in a corresponding list. At the end of testing we can call the getAverageWaitingTime() function to see the average of both implementations.

last step: 
Create the factory method responsible to instantiate the proxy that performs the A/B tests.


![alt text](https://github.com/SimonGschnell/experiments/blob/master/builder.PNG?raw=true)
*Listing 4.*


In Listing 4, the builder is used to configure the selector and the class responsible for handling the metrics. We used MetricRecorderAveragePrinter which is an implementation of MetricRecorder, to persist the metrics.


To illustrate how the application became decoupled from the A/B test 


![alt text](https://github.com/SimonGschnell/experiments/blob/master/main.PNG?raw=true)
*Listing 5.*


Listing 5. Shows how a Implementation instance is created and used. The implementations
Also do not need any reference to the framework. When the A/B test is not needed anymore, only the factory needs to be changed to return the chosen implementation. 

### Internal structure


![alt text](https://github.com/SimonGschnell/experiments/blob/master/model.PNG?raw=true)

*Figure 2.*


Figure 2 shows the framework’s architecture with its main
classes, highlighting the extension points (hotspots) with a fire
icon. Below, the description of the main classes/interfaces from the
framework structure:
 
• **ABTest** is the framework entry point that orchestrates
the other components to perform the A/B test. This class
behaves as a dynamic proxy that assumes the target
interface and uses the Selector to choose between
available implementations. This class also reads the metadata from the interface to retrieve the classes responsible for collecting the metrics.
• **ABTestBuilder** implements the Builder pattern and is
responsible for the creation of the ABTest instance. The API chains the
method calls to configure how the created proxy should
perform the A/B test.
• **Selector** is an interface with an abstract method for
selecting which implementation should be used in each
method call. 
• **MetricRecorder**** is an interface providing a method
for handling the metric values collected by implementations of a MetricsGenerator. Usually, implementations of this class store the metric values to be used
for analysis later. 
• **Metrics** is an interface for classes that capture metrics and @MetricsGenerator is an annotation to
associate an annotation to the metric implementation.
This annotation is currently used by the @MemoryMetrics
and @PerformanceMetrics annotations which respectively indicate the MemoryMetricsGenerator
and PerformanceMetricsGenerator classes. The
class MetricResult represents in a general way the
collected value for a metric. It can be specialized to store
more specific information
Example for the metrics extension point




