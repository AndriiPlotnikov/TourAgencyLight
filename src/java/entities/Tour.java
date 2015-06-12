/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author andre
 */
public class Tour {
    private int id;
    private String name;
    private Flight departure;
    private Flight arrival;
    private String type;
    private int price;
    private int minPrice;
    private int placesLeft;
    private boolean available;
    private String description;

    public Tour(String name, Flight departure, Flight arrival, String type, int price, int minPrice, int placesLeft, boolean available, String description) {
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.type = type;
        this.price = price;
        this.minPrice = minPrice;
        this.placesLeft = placesLeft;
        this.available = available;
        this.description = description;
    }
    
    public Tour(int id, String name, Flight departure, Flight arrival, String type, int price, int minPrice, int placesLeft, boolean available, String description) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.type = type;
        this.price = price;
        this.minPrice = minPrice;
        this.placesLeft = placesLeft;
        this.available = available;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flight getDeparture() {
        return departure;
    }

    public void setDeparture(Flight departure) {
        this.departure = departure;
    }

    public Flight getArrival() {
        return arrival;
    }

    public void setArrival(Flight arrival) {
        this.arrival = arrival;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(int placesLeft) {
        this.placesLeft = placesLeft;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean isAvailable) {
        this.available = isAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
