/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.TourPurchase;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOTourPurchase {
    void create(TourPurchase tourPurchase);
    void update(TourPurchase tourPurchase);
    void delete(TourPurchase tourPurchase);
    TourPurchase find(int id);
    List<TourPurchase> findAll();
}
