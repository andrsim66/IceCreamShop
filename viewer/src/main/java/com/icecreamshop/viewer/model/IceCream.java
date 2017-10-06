package com.icecreamshop.viewer.model;

import java.util.Objects;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class IceCream {

    private int weight;
    private int temperature;

    private String name;
    private String color;
    private String flavor;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof IceCream)) {
            return false;
        }

        IceCream iceCream = (IceCream) o;

        return temperature == iceCream.getTemperature() &&
                Objects.equals(name, iceCream.getName()) &&
                Objects.equals(color, iceCream.getColor()) &&
                Objects.equals(flavor, iceCream.getFlavor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, name, color, flavor);
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "weight=" + weight +
                ", temperature=" + temperature +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", flavor='" + flavor + '\'' +
                '}';
    }
}
