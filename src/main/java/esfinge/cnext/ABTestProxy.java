package esfinge.cnext;

import esfinge.cnext.factories.Metrics;
import esfinge.cnext.factories.Selector;
import esfinge.cnext.metricscolector.MetricsCollector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ABTestProxy<R> implements InvocationHandler {


    private Selector selector;
    private Class[] implementations;

    private MetricsCollector metricsCollectorChain;

    public ABTestProxy() {
    }



    protected Selector getSelector() {
        return selector;
    }

    protected void setSelector(Selector selector) {
        this.selector = selector;
    }

    protected Class[] getImplementations() {
        return implementations.clone();
    }

    protected void setImplementations(Class[] implementations) {
        this.implementations = implementations;
    }

    public MetricsCollector getMetricsCollectorChain() {
        return metricsCollectorChain;
    }

    public void setMetricsCollectorChain(MetricsCollector metricsCollectorChain) {
        this.metricsCollectorChain = metricsCollectorChain;
    }

    @Override
    public R invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class implementation = selector.select(implementations,args,method);

        R methodResult = (R) getMetricsCollectorChain().collect(implementation,selector, method, args);

        return methodResult;
    }
}
