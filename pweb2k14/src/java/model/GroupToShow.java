/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author lorenzo
 */
public class GroupToShow extends Group{
    
    private int _postCount;
    private int _participantCount;

    public int getPostCount() {
        return _postCount;
    }

    public void setPostCount(int _postCount) {
        this._postCount = _postCount;
    }

    public int getParticipantCount() {
        return _participantCount;
    }

    public void setParticipantCount(int _participantCount) {
        this._participantCount = _participantCount;
    }
    
}
