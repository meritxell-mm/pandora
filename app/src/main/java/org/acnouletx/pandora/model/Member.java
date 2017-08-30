package org.acnouletx.pandora.model;

import com.google.firebase.database.DataSnapshot;

import org.acnouletx.pandora.model.utils.PojoUtils;
import org.acnouletx.pandora.utils.Constants;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by txelly on 24/08/17.
 */

public class Member implements Serializable, Constants{

    private String id;
    private String name;
    private String userId;
    private String thumb;
    private String pic;
    private String email;
    private int totalFriends;
    private int totalCreatedBoxes;
    private int totalInvitedBoxes;
    private int totalPosts;
    private PojoUtils.MOOD mood;
    private PojoUtils.IS_FRIEND isFriend;

    public Member(String email) {
        id= getUserId();
        this.email= email;
    }

    public Member(DataSnapshot value) {

        id=(String) value.child(ID).getValue();
        name=(String) value.child(NAME).getValue();
        userId=(String) value.child(USER_ID).getValue();
        thumb=(String) value.child(THUMB).getValue();
        pic=(String) value.child(PIC).getValue();
        email=(String) value.child(EMAIL).getValue();
        totalFriends = ((Long) value.child(TOTAL_FRIENDS).getValue()).intValue();
        totalCreatedBoxes = ((Long) value.child(TOTAL_CREATED_BOXES).getValue()).intValue();
        totalInvitedBoxes = ((Long) value.child(TOTAL_INVITED_BOXES).getValue()).intValue();
        totalPosts = ((Long) value.child(TOTAL_POSTS).getValue()).intValue();
        mood=(PojoUtils.MOOD) value.child(MOOD).getValue();
        isFriend=(PojoUtils.IS_FRIEND) value.child(IS_FRIEND).getValue();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        if(userId==null){
            userId = UUID.randomUUID().toString();
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getTotalFriends() {
        return totalFriends;
    }

    public void setTotalFriends(int totalFriends) {
        this.totalFriends = totalFriends;
    }

    public int getTotalCreatedBoxes() {
        return totalCreatedBoxes;
    }

    public void setTotalCreatedBoxes(int totalCreatedBoxes) {
        this.totalCreatedBoxes = totalCreatedBoxes;
    }

    public int getTotalInvitedBoxes() {
        return totalInvitedBoxes;
    }

    public void setTotalInvitedBoxes(int totalInvitedBoxes) {
        this.totalInvitedBoxes = totalInvitedBoxes;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public PojoUtils.IS_FRIEND getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(PojoUtils.IS_FRIEND isFriend) {
        this.isFriend = isFriend;
    }

    public PojoUtils.MOOD getMood() {
        return mood;
    }

    public void setMood(PojoUtils.MOOD mood) {
        this.mood = mood;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
