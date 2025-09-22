public class MiniVan extends CommercialVehicle {
    private boolean covered;

    public MiniVan(String make, String model, int year,
                   String carries, boolean covered, double price, int invQuantity) {
        super(make, model, year, carries, price, invQuantity);
        this.covered = covered;
    }

    public String toString(){
        String result = "";
        if(covered) result += "Covered ";
        return result +=  "Minivan: " + super.toString();
    }


}
