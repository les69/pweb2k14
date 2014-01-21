/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Timestamp;


/**
 *
 * @author les
 */
public class Group {
    private Integer _id;
    private String _name;
    private boolean _active;
    private int _owner;
    private boolean _public;
    private Timestamp _last_activity;

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
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
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

    /**
     * @return the _owner
     */
    public int getOwner() {
        return _owner;
    }

    /**
     * @param _owner the _owner to set
     */
    public void setOwner(Integer _owner) {
        this._owner = _owner;
    }

    /**
     * @return the _public
     */
    public boolean isPublic() {
        return _public;
    }

    /**
     * @param _public the _public to set
     */
    public void setPublic(boolean _public) {
        this._public = _public;
    }

    /**
     * @return the _last_activity
     */
    public Timestamp getLast_activity() {
        return _last_activity;
    }

    /**
     * @param _last_activity the _last_activity to set
     */
    public void setLast_activity(Timestamp _last_activity) {
        this._last_activity = _last_activity;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this ==  obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Group g = (Group) obj;
        if(this.getId() == g.getId())
            return true;
        return false;
    }

    

    
}
