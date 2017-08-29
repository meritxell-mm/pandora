package org.acnouletx.pandora.model;

import java.util.ArrayList;

/**
 * Created by txelly on 24/08/17.
 */

public class User extends Member {

    private String passwordToken;
    private String pushToken;
    private ArrayList<Box> boxes;

    public User(String username) {
        super(username);
    }
}
