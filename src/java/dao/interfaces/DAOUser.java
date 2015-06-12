/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import entities.User;
import java.util.List;

/**
 *
 * @author andre
 */
public interface DAOUser {
    User authorize(String login, String password);
    User find(int id);
    List<User> findAll();
    void update(User user);
    void create(User user);
}
