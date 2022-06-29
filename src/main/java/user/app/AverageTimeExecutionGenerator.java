package user.app;

import esfinge.cnext.factories.MetricRecorder;
import esfinge.cnext.factories.Metrics;
import esfinge.cnext.metric.MetricResult;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AverageTimeExecutionGenerator implements Metrics {
    private MetricRecorder metricRecorder;
    private Instant startExecution;
    private List<Double> averageExecutionImpl1 = new ArrayList<>();
    private List<Double> averageExecutionImpl2 = new ArrayList<>();

    private double averageExecutionTime(List<Double> list){
        double result=0;
        for(double time : list){
            result+=time;
        }
        return result / (double)list.size();
    }

    @Override
    public MetricRecorder getMetricRecorder() {
        return this.metricRecorder;
    }

    @Override
    public void setMetricRecorder(MetricRecorder metricRecorder) {
        this.metricRecorder = metricRecorder;
    }

    @Override
    public void startRecording(Method method) throws Exception {
        startExecution = Instant.now();
    }

    @Override
    public void finishRecording(Method method, String selectorName, String implementation) throws Exception {
        Duration duration = Duration.between(startExecution, Instant.now());
        String result;
        if(implementation.equals("Implementation1")){
            averageExecutionImpl1.add(duration.toMillis() / 1000.0d);
            result = averageExecutionTime(averageExecutionImpl1)+"" ;
        }else {
            averageExecutionImpl2.add(duration.toMillis() / 1000.0d);
            result = averageExecutionTime(averageExecutionImpl2)+"" ;
        }

        MetricResult mr = extractMetricResult(method, selectorName, implementation, result);
        metricRecorder.save(mr);
    }


}
