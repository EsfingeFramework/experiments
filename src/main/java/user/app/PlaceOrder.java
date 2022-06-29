package user.app;


import esfinge.cnext.annotations.MemoryMetrics;
import esfinge.cnext.annotations.TimeMetrics;

@OrderValue
public interface PlaceOrder {
    void placeOrder(int orderID, Item item , @targetParameter User user);
}
