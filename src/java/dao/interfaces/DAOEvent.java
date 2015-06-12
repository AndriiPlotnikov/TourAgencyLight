/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.Event;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOEvent{
    void create(Event event);
    void update(Event event);
    void delete(Event event);
    Event find(int id);
    List<Event> findAll();
}
