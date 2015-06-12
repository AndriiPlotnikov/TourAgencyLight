/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.Hotel;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOHotel {
    void create(Hotel hotel);
    void update(Hotel hotel);
    void delete(Hotel hotel);
    Hotel find(int id);
    List<Hotel> findAll();
}
