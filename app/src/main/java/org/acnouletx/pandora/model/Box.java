package org.acnouletx.pandora.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by txelly on 24/08/17.
 */

public class Box implements Serializable{

    private String id;
    private String name;
    private ArrayList<Member> members;
    private ArrayList<Post> lastPosts;

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

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Post> getLastPosts() {
        return lastPosts;
    }

    public void setLastPosts(ArrayList<Post> lastPosts) {
        this.lastPosts = lastPosts;
    }
}
