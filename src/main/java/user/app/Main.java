package user.app;
import esfinge.cnext.ABTest;
import esfinge.cnext.ABTestBuilder;
import esfinge.cnext.metric.MetricRecorderLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        PlaceOrder menuOrg = (PlaceOrder) createOrganizer();
        for (int i=0 ; i<20; i++){

            List<String> companies = new ArrayList<>();
            companies.add("Loacker");
            companies.add("Facebook");
            companies.add("McDonalds");
            menuOrg.placeOrder(new Random().nextInt(200),new Item("itemName",25.8), new User(companies.get(new Random().nextInt(companies.size())),"Peter",55,200));
        }

    }

    private static Object createOrganizer() throws Exception {
        ABTestBuilder<PlaceOrder, Integer> abTestBuilder
                = new ABTestBuilder<PlaceOrder, Integer>();

        Class[] classes = new Class[2];
        classes[0]= ShoppingCartB.class;
        classes[1]= ShoppingCartA.class;

        ABTest abTest = abTestBuilder.
                createFor(PlaceOrder.class).
                withSelector(new CompanySelector()).
                withMetricRecorder(new MetricRecorderLogger()).
                withImplementations(classes).build();

        return abTest.getProxy();
    }
}
