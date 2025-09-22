import java.util.Objects;

public abstract class Vehicle extends Item {
    private String make;
    private String model;
    private int year;


    public Vehicle(String make, String model, int year,double price, int quantity){
        super(price, quantity);
        this.make = make;
        this.model = model;
        this.year = year;
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
        Vehicle other = (Vehicle) obj;
        return Double.compare(this.getPrice(), other.getPrice()) == 0 &&
                Objects.equals(this.make, other.getMake()) &&
                Objects.equals(this.model, other.getModel()) &&
                this.year == other.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), make, model, year);
    }

    public String getMake(){return make;}
    public String getModel(){return model;}
    public int getYear(){return year;}
 }
