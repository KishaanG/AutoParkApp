public abstract class Item {
    private double price;
    private int invQuantity;
    private int soldQuantity;

    public Item(double price, int invQuantity){
        this.price = price;
        this.invQuantity = invQuantity;
        this.soldQuantity = 0;
    }

    public void setSoldQuantity(int _soldQuantity)
    {
        soldQuantity = _soldQuantity;
    }
    public void setInvQuantity(int _invQuantity)
    {
        invQuantity = _invQuantity;
    }

    //Returns the total revenue (price * amount) if at least 'amount' items are in stock
    //Return 0 otherwise (i.e., no sale is completed)
    public double sellUnits(int amount){
        if( amount > 0 && invQuantity >= amount){
            invQuantity -= amount;
            soldQuantity += amount;
            return amount * price;
        }
        return 0;
    }

    public double getPrice(){return price;}
    public int getInvQuantity(){return invQuantity;}
    public int getSoldQuantity(){return soldQuantity;}
}
