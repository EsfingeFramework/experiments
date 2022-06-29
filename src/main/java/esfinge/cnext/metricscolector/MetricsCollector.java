package esfinge.cnext.metricscolector;

import esfinge.cnext.factories.MetricRecorder;
import esfinge.cnext.factories.Selector;
import esfinge.cnext.metric.MetricResult;

import java.lang.reflect.Method;

public interface MetricsCollector {
    MetricRecorder getMetricRecorder();
    void setMetricRecorder(MetricRecorder metricRecorder);
    Object collect(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable;

    default MetricResult extractMetricResult(Method method, String selectorName, String implementation, String result) {
        String metric = getClass().getSimpleName().replace("Generator", "");
        return new MetricResult(metric, selectorName, implementation, method.getName(), result);
    }
}
