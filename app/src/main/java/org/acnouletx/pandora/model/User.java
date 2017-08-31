package org.acnouletx.pandora.model;

import com.google.firebase.database.DataSnapshot;

import org.acnouletx.pandora.utils.Constants;

import java.util.ArrayList;

/**
 * Created by txelly on 24/08/17.
 */

public class User extends Member{

    private String passwordToken;
    private String pushToken;
    private ArrayList<Box> boxes;

    public User(String email, String token) {
        super(email);
        passwordToken=token;
        boxes = new ArrayList<>(); //TODO
    }

    public User(DataSnapshot value) {
        super(value);
        passwordToken=(String) value.child(PASSWORD_TOKEN).getValue();
        pushToken = (String) value.child(PUSH_TOKEN).getValue();
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }
}
