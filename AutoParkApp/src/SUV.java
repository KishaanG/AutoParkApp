public class SUV extends PersonalVehicle {
    private boolean allWheelDrive;

    public SUV(String make, String model, String color, int year,
               boolean allWheelDrive, double price, int invQuantity){
        super(make, model, color, year, price, invQuantity);
        this.allWheelDrive = allWheelDrive;
    }

    public String toString(){
        String result = "";
        if(allWheelDrive) result = "AWD ";
        return result += "SUV: " + super.toString();
    }


}
