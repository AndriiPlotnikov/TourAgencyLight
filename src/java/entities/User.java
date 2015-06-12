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
public class User{
    private int id;
    private String name;
    private String role;
    private String password;
    private int discount;
    
    public User(int id, String name, String password, String role, int discount) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
