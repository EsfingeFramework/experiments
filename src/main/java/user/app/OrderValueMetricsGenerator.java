package user.app;

import esfinge.cnext.factories.Selector;
import esfinge.cnext.metric.MetricResult;
import esfinge.cnext.metricscolector.BaseAroundMetricsCollector;
import esfinge.cnext.metricscolector.MetricsCollector;

import java.lang.reflect.Method;

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
        Integer result = (Integer) args[0];
        MetricResult metricResult = extractMetricResult(method,selector.getClass().getSimpleName(),selectedImplementation.getSimpleName(),result.toString());
        getMetricRecorder().save(metricResult);
    }

    @Override
    public void collectBefore(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable {

    }
}
