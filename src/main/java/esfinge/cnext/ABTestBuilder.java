package esfinge.cnext;

import esfinge.cnext.factories.MetricRecorder;
import esfinge.cnext.metric.MetricRecorderLogger;
import esfinge.cnext.factories.Metrics;
import esfinge.cnext.annotations.MetricsGenerator;
import esfinge.cnext.factories.Selector;
import esfinge.cnext.metricscolector.FinalCollector;
import esfinge.cnext.metricscolector.MetricsCollector;
import esfinge.cnext.selector.SelectorRandom;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ABTestBuilder<T, R> {

    private Class<T> proxyClass;
    private Selector selector;
    private MetricRecorder metricRecorder;
    private Class[] implementations;

    public ABTestBuilder() {
    }

    public ABTestBuilder createFor(Class<T> proxyClass) {
        this.proxyClass = proxyClass;
        return this;
    }

    public ABTestBuilder withSelector(Selector selector) {
        this.selector = selector;
        return this;
    }

    public ABTestBuilder withMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
        return this;
    }

    public ABTestBuilder withImplementations(Class[] implementations) {
        this.implementations = implementations;
        return this;
    }

    public ABTest<T, R> build() throws Exception {
        ABTest<T, R> abTest = new ABTest<>();

        if (selector == null) {
            selector = new SelectorRandom(); //default
        }

        if (metricRecorder == null) {
            metricRecorder = new MetricRecorderLogger(); //default
        }

        ABTestProxy<R> proxy = new ABTestProxy();

        MetricsCollector collectorChain = new FinalCollector();
        collectorChain.setMetricRecorder(metricRecorder);
        for (Annotation an : proxyClass.getAnnotations()) {
            Class<?> anType = an.annotationType();
            if (anType.isAnnotationPresent(MetricsGenerator.class)) {
                MetricsGenerator fi = anType.getAnnotation(MetricsGenerator.class);
                Class<? extends MetricsCollector> c = fi.value();
                Constructor<? extends MetricsCollector> constr = c.getConstructor(MetricsCollector.class);
                MetricsCollector metricCollector = constr.newInstance(collectorChain);
                metricCollector.setMetricRecorder(metricRecorder);
                collectorChain = metricCollector;
            }
        }
        proxy.setMetricsCollectorChain(collectorChain);
        proxy.setSelector(selector);
        proxy.setImplementations(implementations);

        T proxyInstance = (T) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{proxyClass}, proxy);
        abTest.setProxy(proxyInstance);

        return abTest;
    }
}
