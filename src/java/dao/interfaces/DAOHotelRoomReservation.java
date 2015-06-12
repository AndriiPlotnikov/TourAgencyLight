/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.HotelRoom;
import entities.HotelRoomReservation;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOHotelRoomReservation {
    void create(HotelRoomReservation room);
    void update(HotelRoomReservation room);
    void delete(HotelRoomReservation room);
    HotelRoomReservation find(int id);
    List<HotelRoomReservation> findAll();
    List<HotelRoomReservation> findAllByRoom(int id);
}
