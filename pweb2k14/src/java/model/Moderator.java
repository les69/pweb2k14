/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author les
 */
public class Moderator {
    private int id_user;
    private boolean _active;

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * @return the _active
     */
    public boolean isActive() {
        return _active;
    }

    /**
     * @param _active the _active to set
     */
    public void setActive(boolean _active) {
        this._active = _active;
    }
    
}
