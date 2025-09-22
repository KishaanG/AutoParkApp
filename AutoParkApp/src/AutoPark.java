import java.util.*;

public class AutoPark {
    private static final int MAX_ITEMS = 10;
    private static int totalItem;

    private String name;
    private static double revenue;
    private static Item[] items;
    private static Map<Item, Integer> cart;
    private static int numSales;

    public Map<Item, Integer> getCart()
    {
        return cart;
    }

    public void setRevenue(double _revenue)
    {
        revenue = _revenue;
    }

    public double getRevenue(){return revenue;}

    public AutoPark(String name) {
        this.name = name;
        revenue = 0;
        items = new Item[MAX_ITEMS];
        totalItem = 0;
        cart = new LinkedHashMap<Item, Integer>();
        numSales = 0;
    }

    public void setNumSales(int _numSales)
    {
        numSales = _numSales;
    }

    public int getNumSales(){return numSales;}

    public double getCurrentCart() {
        double currentCart = 0.0;
        for (Item key : cart.keySet())
        { // Gets the sum of all the prices in the cart
            currentCart += key.getPrice() * cart.get(key);
        }
        return currentCart;
    }

    public List<String> getObservableCart()
    { // Creates a list of strings with the number of each type of Item
        List<String> observableCart = new ArrayList<String>();
        for (Item key : cart.keySet())
        {
            // Uses cart map to create strings displaying the amount of each Item in the cart
            observableCart.add(cart.get(key) + "x " + key.toString());
        }
        return observableCart;
    }

    public boolean addAnItem(Item item) {
        if (totalItem < MAX_ITEMS) {
            items[totalItem] = item;
            totalItem++;
            return true;
        }
        return false;
    }

    public List<Item> getXPopularItems(int x)
    {
        List<Item> popularItems = new ArrayList<Item>();
        if (x >= totalItem) {x = totalItem;}
        for (int i=0; i<x; i++)
        {
            Item mostPurchased = null;
            for (Item item : items) {
                if (!popularItems.contains(item)) {
                    mostPurchased = item; // Gets the first item in the iterator order that's not already in the list
                }
            }
            for (Item item : items)
            {
                if ((mostPurchased.getSoldQuantity() < item.getSoldQuantity()) && !popularItems.contains(item))
                { // Gets the next most sold item
                    mostPurchased = item;
                }
            }
            popularItems.add(mostPurchased);
        }
        return popularItems;
    }

    public String getName() {
        return name;
    }

    public int getTotalItem()
    {
        return totalItem;
    }

    public Item[] getItems()
    {
        return items;
    }

    public static AutoPark createPark() {
        AutoPark park = new AutoPark("VroomVille Vehicle Haven");
        Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park.addAnItem(s1);
        park.addAnItem(s2);
        SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true,32000,10);
        SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false,21000,10);
        park.addAnItem(suv1);
        park.addAnItem(suv2);
        Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods",true,28000,10);
        Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment",false,30000,10);
        park.addAnItem(t1);
        park.addAnItem(t2);
        MiniVan v1 = new MiniVan("Ford", "Transit", 2020, "goods",true,22000,10);
        MiniVan v2 = new MiniVan("Ram", "ProMaster", 2019, "equipment",false,19000,10);
        park.addAnItem(v1);
        park.addAnItem(v2);
        Tire tire1 = new Tire(10,30,true,390, 20);
        Tire tire2 = new Tire(12,35,false,320,20);
        park.addAnItem(tire1);
        park.addAnItem(tire2);
        cart.clear();
        numSales = 0;
        revenue = 0;
        return park;
    }
}




