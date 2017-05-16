package ru.sbpau.mit.elements;

/**
 * Author - Эдгар
 * Date - 14.05.2017, 13:49
 */
public class CamSnippet {
    private final String brand;
    private final Double price;

    public CamSnippet(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }
}
