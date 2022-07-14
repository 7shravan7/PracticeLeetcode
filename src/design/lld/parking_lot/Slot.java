package design.lld.parking_lot;

import java.util.UUID;

public class Slot {

    private String id;

    private int slotNumber;

    private boolean isEmpty;

    private Vehicle vehicle;

    public Slot(int slotNumber) {
        id = UUID.randomUUID().toString();
        this.slotNumber = slotNumber;
        isEmpty = true;
        vehicle = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void addVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        isEmpty = false;
    }

    public Vehicle removeVehicle() {
        if(isEmpty){
            return null;
        }
        Vehicle removedVehicle = vehicle;
        this.vehicle = null;
        isEmpty = true;
        return removedVehicle;
    }

}
