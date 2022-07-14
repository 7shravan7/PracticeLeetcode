package design.lld.parking_lot;

import java.util.UUID;

public enum ParkingLotType{

    TWO_WHEELER {
        @Override
        public double getPrice() {
            return 0.5;
        }
    },

    SMALL{
        @Override
        public double getPrice() {
            return 0.8;
        }
    },

    MEDIUM{
        @Override
        public double getPrice() {
            return 1.0;
        }
    },

    LARGE{
        @Override
        public double getPrice() {
            return 1.2;
        }
    };


    public abstract double getPrice();

}
