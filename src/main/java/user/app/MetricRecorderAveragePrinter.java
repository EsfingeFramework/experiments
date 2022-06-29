package user.app;

import esfinge.cnext.factories.MetricRecorder;
import esfinge.cnext.metric.MetricResult;

import java.util.ArrayList;
import java.util.List;

public class MetricRecorderAveragePrinter implements MetricRecorder {

    static List<Double> implementation1List = new ArrayList<>();
    static List<Double> implementation2List = new ArrayList<>();

    @Override
    public void save(MetricResult metricResult) {
        if(metricResult.getImplementation().equals("Implementation1")){
            implementation1List.add(Double.parseDouble(metricResult.getResult()));
        }else{
            implementation2List.add(Double.parseDouble(metricResult.getResult()));
        }
    }

    public static void getAverageWaitingTimes(){
        System.out.println("implementaiton1 average waiting-time : "+calculateAverage(implementation1List));
        System.out.println("implementaiton2 average waiting-time : "+calculateAverage(implementation2List));

    }

    private static double calculateAverage(List<Double> list){
        double sum =0;
        for(double value: list){
         sum+=value;
        }

        if(list.size()>0){
            return sum/(double)list.size();
        }else{
            return 0;
        }
    }
}
