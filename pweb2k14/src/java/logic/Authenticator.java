/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import model.User;

/**
 *
 * @author lorenzo
 */
public class Authenticator {
    public User authenticate(String username, String password) {
        //TODO connessione a DB e controllo di username e password
        if (("utonto".equalsIgnoreCase(username))
                && ("password".equals(password))) {
            User toRet = new User();
            toRet.setUsername("utonto");
            toRet.setId(0);
            toRet.setAvatar("bananaro.png");
            return toRet;
        } else {
            return null;
        }
    }
}
