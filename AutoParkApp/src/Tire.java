import java.util.Objects;

public class Tire extends Item {
    int wheelDiameter;
    int sectionWidth;
    boolean passengerTire;

    public Tire(int wheelDiameter, int sectionWidth, boolean passengerTire,double price, int invQuantity){
        super(price, invQuantity);
        this.wheelDiameter = wheelDiameter;
        this.sectionWidth = sectionWidth;
        this.passengerTire = passengerTire;
    }

    @Override
    public boolean equals(Object obj) {
        // Check for the same reference
        if (this == obj)
            return true;
        // Check that the object is non-null and of the same exact class
        if (obj == null || getClass() != obj.getClass())
            return false;
        // Cast and compare fields
        Tire other = (Tire) obj;
        return Double.compare(this.getPrice(), other.getPrice()) == 0 &&
                this.sectionWidth == other.sectionWidth &&
                this.wheelDiameter == other.wheelDiameter &&
                this.passengerTire == other.passengerTire;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), sectionWidth, wheelDiameter, passengerTire);
    }

    public String toString(){
        String result = "";
        if(passengerTire) result += "Passenger Tire";
        else result = "Front Tire";
        return result + " with " + wheelDiameter + "\" wheel diameter " + sectionWidth +
                " mm. section width, price $" + String.format("%,.2f",getPrice()) + " each ("
                + getInvQuantity() +" in stock, " + getSoldQuantity() + " sold).";
    }
}
