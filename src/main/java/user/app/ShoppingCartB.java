package user.app;

import java.util.Random;

public class ShoppingCartB extends ShoppingCart implements PlaceOrder {


    ShoppingCartB(User client) {
        super(client);
    }

    @Override
    public void placeOrder(int orderID, Item item, User user) {
        try{
            Thread.sleep(new Random().nextInt(100)+200);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        System.out.println("* Implementation 1 was selected with user working in company: "+user.getCompanyName());


    }
}
