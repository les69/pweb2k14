/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import helpers.ServletHelperClass;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author les
 */
public class User {
    private int _id;
    private String _password;
    private String _username;
    private String _avatar;
    private Timestamp _lastLogin;
    private String _email;
    private boolean _ismoderator;
    private String _formatDate;

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * @param _password the _password to set
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     * @return the _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @param _username the _username to set
     */
    public void setUsername(String _username) {
        this._username = _username;
    }

    /**
     * @return the _avatar
     */
    public String getAvatar() {
        return _avatar;
    }

    /**
     * @param _avatar the _avatar to set
     */
    public void setAvatar(String _avatar) {
        this._avatar = _avatar;
    }

    /**
     * @return the _lastLogin
     */
    public Timestamp getLastLogin() {
        return _lastLogin;
    }

    /**
     * @param _lastLogin the _lastLogin to set
     */
    public void setLastLogin(Timestamp _lastLogin) {
        this._lastLogin = _lastLogin;
    }

    /**
     * @return the _ismoderator
     */
    public boolean isIsmoderator() {
        return _ismoderator;
    }

    /**
     * @return the _email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * @param _email the _email to set
     */
    public void setEmail(String _email) {
        this._email = _email;
    }

    /**
     * @param _ismoderator the _ismoderator to set
     */
    public void setIsmoderator(boolean _ismoderator) {
        this._ismoderator = _ismoderator;
    }
    public String getFormatDate()
    {
        return ServletHelperClass.formatDate(_lastLogin);
    }
    public void setAnonymous()
    {
        //This is only to allow the page to be redirected correctly
        this.setId(-1);
        this.setUsername("Anonymous");
        this.setIsmoderator(false);
        this.setLastLogin(new java.sql.Timestamp(0));
        this.setAvatar("");
        this.setPassword("");
    }
    
}