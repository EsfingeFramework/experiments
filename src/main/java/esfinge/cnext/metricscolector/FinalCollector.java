package esfinge.cnext.metricscolector;

import esfinge.cnext.factories.MetricRecorder;
import esfinge.cnext.factories.Selector;

import java.lang.reflect.Method;

public class FinalCollector implements MetricsCollector{

    private MetricRecorder metricRecorder;

    @Override
    public MetricRecorder getMetricRecorder() {
        return this.metricRecorder;
    }

    @Override
    public void setMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
    }

    @Override
    public Object collect(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable {
        Object impl = selectedImplementation.getConstructor().newInstance();
        Object methodResult = method.invoke(impl, args);
        return methodResult;
    }
}
