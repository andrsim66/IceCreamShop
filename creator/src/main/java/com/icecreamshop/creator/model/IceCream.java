package com.icecreamshop.creator.model;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("weight", weight);
        result.put("color", color);
        result.put("flavor", flavor);
        result.put("temperature", temperature);
        return result;
    }
}
