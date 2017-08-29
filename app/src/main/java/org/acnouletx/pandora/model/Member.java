package org.acnouletx.pandora.model;

import org.acnouletx.pandora.model.utils.PojoUtils;

import java.util.UUID;

/**
 * Created by txelly on 24/08/17.
 */

public class Member {

    private String id;
    private String name;
    private String userId;
    private String thumb;
    private String pic;
    private int totalFriends;
    private int totalCreatedBoxes;
    private int totalInvitedBoxes;
    private int totalPosts;
    private PojoUtils.MOOD mood;
    private PojoUtils.IS_FRIEND isFriend;

    public Member(String username) {
        id= getUserId();
        name=username;
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
}
