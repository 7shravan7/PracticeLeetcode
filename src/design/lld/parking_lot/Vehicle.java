package design.lld.parking_lot;

public class Vehicle {

    private String regNumber;

    private VehicleCategory vehicleCategory;

    private String color;

    public Vehicle(String regNumber, VehicleCategory vehicleCategory, String color) {
        this.regNumber = regNumber;
        this.vehicleCategory = vehicleCategory;
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public String getColor() {
        return color;
    }
}
