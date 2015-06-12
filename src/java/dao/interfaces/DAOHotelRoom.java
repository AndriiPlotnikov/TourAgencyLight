/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.HotelRoom;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOHotelRoom {
    void create(HotelRoom room);
    void update(HotelRoom room);
    void delete(HotelRoom room);
    HotelRoom find(int id);
    List<HotelRoom> findAll();
    List<HotelRoom> findAllbyHotel(int hotelId);
}
