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
public class TourPurchase {
    private int id;
    private Tour tour;
    private User client;
    private int price;
    private int discount;
    private String status;

    public TourPurchase(User client, Tour tour, int price, int discount, String status) {
        this.client = client;
        this.tour = tour;
        this.price = price;
        this.discount = discount;
        this.status = status;
    }
    
    public TourPurchase(int id, User client, Tour tour, int price, int discount, String status) {
        this.id = id;
        this.client = client;
        this.tour = tour;
        this.price = price;
        this.discount = discount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    
    
}
