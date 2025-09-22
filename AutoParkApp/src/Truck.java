public class Truck extends CommercialVehicle {
    private boolean heavyDuty;

    public Truck(String make, String model, int year,
                 String carries, boolean heavyDuty, double price, int invQuantity) {
        super(make, model, year, carries, price, invQuantity);
        this.heavyDuty = heavyDuty;
    }

    public String toString(){
        String result = "";
        if(heavyDuty) result += "Heavy-duty ";
        return result += "Truck: " + super.toString();
    }

}
