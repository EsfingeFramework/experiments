package esfinge.cnext.metric;

import esfinge.cnext.factories.Selector;
import esfinge.cnext.metricscolector.BaseAroundMetricsCollector;
import esfinge.cnext.metricscolector.MetricsCollector;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class TimeMetricsGenerator extends BaseAroundMetricsCollector {

    private Instant startExecution;

    public TimeMetricsGenerator(MetricsCollector next) {
        super(next);
    }

    @Override
    public void collectException(Class selectedImplementation, Selector selector, Method method, Object[] args, Throwable targetException) throws Throwable {
        MetricResult mr = extractMetricResult(method, selector.getClass().getSimpleName(),selectedImplementation.getSimpleName(),targetException.getMessage()) ;
        getMetricRecorder().save(mr);
    }

    @Override
    public void collectAfter(Class selectedImplementation, Selector selector, Method method, Object[] args, Object returned) throws Throwable {
        Duration duration = Duration.between(startExecution, Instant.now());
        String result = duration.toMillis() / 1000.0d + " seconds";
        MetricResult mr = extractMetricResult(method,selector.getClass().getSimpleName(), selectedImplementation.getSimpleName(),result);
        getMetricRecorder().save(mr);
    }

    @Override
    public void collectBefore(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable {
        startExecution = Instant.now();
    }



}
