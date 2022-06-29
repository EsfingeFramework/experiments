package user.app;

import java.util.Random;

public class ShoppingCartA extends ShoppingCart implements PlaceOrder {


    ShoppingCartA(User client) {
        super(client);
    }

    @Override
    public void placeOrder(int orderID, Item item, User user) {
        try{
            Thread.sleep(new Random().nextInt(100)+400);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        System.out.println("** Implementation 2 was selected with user working in company: "+user.getCompanyName());

    }
}
