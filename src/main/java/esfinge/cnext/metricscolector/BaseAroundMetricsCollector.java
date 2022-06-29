package esfinge.cnext.metricscolector;

import esfinge.cnext.factories.MetricRecorder;
import esfinge.cnext.factories.Selector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseAroundMetricsCollector implements esfinge.cnext.metricscolector.MetricsCollector {

    private MetricsCollector next;
    protected MetricRecorder recorder;

    public BaseAroundMetricsCollector(MetricsCollector next){
        this.next = next;
    }

    @Override
    public MetricRecorder getMetricRecorder() {
        return recorder;
    }

    @Override
    public void setMetricRecorder(MetricRecorder metricRecorder) {
        recorder = metricRecorder;
    }

    @Override
    public Object collect(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable {

        collectBefore(selectedImplementation,selector, method, args);
        Object returned;

        try {
            returned = next.collect(selectedImplementation,selector, method, args);
            collectAfter(selectedImplementation,selector, method, args, returned);
        } catch (InvocationTargetException exc) {
            collectException(selectedImplementation,selector, method, args, exc.getTargetException());
            throw exc;
        }

        return returned;
    }

    public abstract void collectException(Class selectedImplementation,Selector selector, Method method, Object[] args, Throwable targetException) throws Throwable;

    public abstract void collectAfter(Class selectedImplementation,Selector selector, Method method, Object[] args, Object returned) throws Throwable;

    public abstract void collectBefore(Class selectedImplementation,Selector selector, Method method, Object[] args) throws Throwable;

}
