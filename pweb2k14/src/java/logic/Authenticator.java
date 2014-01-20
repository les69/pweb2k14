/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import model.DbHelper;
import model.User;

/**
 *
 * @author lorenzo
 */

public class Authenticator {
    public User authenticate(String username, String password, DbHelper dbh) {
        //TODO connessione a DB e controllo di username e password
        User utente = dbh.getUser(username);
        if ((utente.getPassword().equals(password))) {            
            return utente;
        } else {
            return null;
        }
    }
}
