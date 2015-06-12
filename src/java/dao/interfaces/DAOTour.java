/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.Tour;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOTour {
    void create(Tour tour);
    void update(Tour tour);
    void delete(Tour tour);
    Tour find(int id);
    List<Tour> findAll();
    List<Tour> findAllByType(String tourType);
}
