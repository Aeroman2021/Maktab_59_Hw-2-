package hw16.q2;

import java.util.Objects;

public abstract class Vehicle implements Cloneable {
    private String brand;
    private String model;
    private String color;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    @Override
    protected Object clone() {
        Object cloneObject = null;
        try {
            cloneObject = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return brand.equals(vehicle.brand) &&
                model.equals(vehicle.model) &&
                color.equals(vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color);
    }
}
