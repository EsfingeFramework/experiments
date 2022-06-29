package user.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingCart implements PlaceOrder {

    private List<Item> items;
    private User client;
    private static double totalCost=0;

    ShoppingCart(User client){
        this.client = client;
        this.items = new ArrayList<>();
    }

    @Override
    public void placeOrder(int orderID, Item item, User user) {
       if(user.getBalance()>= totalCost){
           System.out.println("order sent!");
       }
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> addItem( Item item){
        this.items.add(item);
        this.totalCost+= item.getPrice();
        return this.items;
    }

    public User getClient() {
        return client;
    }

}
