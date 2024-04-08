package ie.atu;
import java.util.ArrayList;
public class ShoppingCart {
    private  ArrayList<String> devices;
    private ArrayList<Double> prices;

    public ShoppingCart(){
        devices = new ArrayList<>();
        prices = new ArrayList<>();
    }
    public void addDevice(String device, double price){ //initializes the selected items and their prices when a ShoppingCart object is created.
        devices.add(device);
        prices.add(price);
    }
    public double totalCost(){
        double total =0;
        for (Double price : prices){
            total += price;
        }
        return total;
    }

    public void displayCart(){
        System.out.println("Devices in your cart: ");
        for(int i =0; i< devices.size(); i++){
            System.out.println((i+1) + ". " + devices.get(i) + " $" + prices.get(i));
        }
        System.out.println("Total :$" + totalCost());
    }
}
