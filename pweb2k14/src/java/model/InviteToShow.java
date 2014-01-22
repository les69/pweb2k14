/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;

/**
 *
 * @author Lorenzo
 */
public class InviteToShow {
    private int _idGroup;
    private int _idUser;
    private Date _inviteDate;
    private String _groupName;

    /**
     * @return the _idGroup
     */
    public int getIdGroup() {
        return _idGroup;
    }

    /**
     * @param _idGroup the _idGroup to set
     */
    public void setIdGroup(int _idGroup) {
        this._idGroup = _idGroup;
    }

    /**
     * @return the _idUser
     */
    public int getIdUser() {
        return _idUser;
    }

    /**
     * @param _idUser the _idUser to set
     */
    public void setIdUser(int _idUser) {
        this._idUser = _idUser;
    }

    /**
     * @return the _inviteDate
     */
    public Date getInviteDate() {
        return _inviteDate;
    }

    /**
     * @param _inviteDate the _inviteDate to set
     */
    public void setInviteDate(Date _inviteDate) {
        this._inviteDate = _inviteDate;
    }
    
    /**
     * @return the _groupName
     */
    public String getGroupName() {
        return _groupName;
    }

    /**
     * @param _groupName the _groupName to set
     */
    public void setGroupName(String _groupName) {
        this._groupName = _groupName;
    }
    
    public static InviteToShow ITSfromInvite(Invite invito, String grpName)
    {
        InviteToShow its = new InviteToShow();
        its.setGroupName(grpName);
        its.setIdGroup(invito.getIdGroup());
        its.setIdUser(invito.getIdUser());
        its.setInviteDate(invito.getInviteDate());
        return its;
    }
}
