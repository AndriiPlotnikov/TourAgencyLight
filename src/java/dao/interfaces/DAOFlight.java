/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.Flight;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOFlight {
    void create(Flight flight);
    void update(Flight flight);
    void delete(Flight flight);
    Flight find(int id);
    List<Flight> findAll();
    List<Flight> findAllAvailable();
}
