package esfinge.cnext.metric;

import esfinge.cnext.factories.Selector;
import esfinge.cnext.metricscolector.BaseAroundMetricsCollector;
import esfinge.cnext.metricscolector.MetricsCollector;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoryMetricsGenerator extends BaseAroundMetricsCollector {

    private long usedMemoryBefore;

    public MemoryMetricsGenerator(MetricsCollector next) {
        super(next);
    }

    @Override
    public void collectException(Class selectedImplementation, Selector selector, Method method, Object[] args, Throwable targetException) throws Throwable {
        MetricResult mr = extractMetricResult(method, selector.getClass().getSimpleName(), selectedImplementation.getSimpleName(),targetException.getMessage());
        getMetricRecorder().save(mr);
    }

    @Override
    public void collectAfter(Class selectedImplementation, Selector selector, Method method, Object[] args, Object returned) throws Throwable {
        long usedMemoryAfter = 0;
        try {
            usedMemoryAfter = getSettledUsedMemory();
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        String result = usedMemoryAfter - usedMemoryBefore + " bytes";
        MetricResult mr = extractMetricResult(method, selector.getClass().getSimpleName(), selectedImplementation.getSimpleName(),result);
        getMetricRecorder().save(mr);
    }

    @Override
    public void collectBefore(Class selectedImplementation, Selector selector, Method method, Object[] args) throws Throwable {
        try {
            usedMemoryBefore = getSettledUsedMemory();
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }



    private long getGcCount() {
        long sum = 0;
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = b.getCollectionCount();
            if (count != -1) {
                sum += count;
            }
        }
        return sum;
    }

    private long getReallyUsedMemory() {
        long before = getGcCount();
        do {
            System.gc();
        } while (getGcCount() == before);
        return getCurrentlyUsedMemory();
    }

    private long getSettledUsedMemory() throws InterruptedException {
        long m;
        long m2 = getReallyUsedMemory();
        do {
            Thread.sleep(567);
            m = m2;
            m2 = getReallyUsedMemory();
        } while (m2 < getReallyUsedMemory());
        return m;
    }

    private long getCurrentlyUsedMemory() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
    }

}
