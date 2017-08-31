package org.acnouletx.pandora.utils;

/**
 * Created by txelly on 28/08/17.
 */

public interface Constants {

    //INTENT KEYS
    String USER = "user";

    /**
     * DB TABLES
     **/
    String USERS = "users";

    /**
     * DB KEYS
     **/
    //USER
    String ID = "id";
    String NAME = "name";
    String USERNAME = "username";
    String USER_ID = "userId";
    String THUMB = "thumb";
    String PIC = "pic";
    String EMAIL = "email";
    String TOTAL_FRIENDS = "totalFriends";
    String TOTAL_CREATED_BOXES = "totalCreatedBoxes";
    String TOTAL_INVITED_BOXES = "totalInvitedBoxes";
    String TOTAL_POSTS = "totalPosts";
    String MOOD = "mood";
    String IS_FRIEND = "isFriend";
    String PASSWORD_TOKEN = "passwordToken";
    String PUSH_TOKEN = "pushToken";
    String BOXES = "boxes";


    //REQUEST CODES
    int RC_SIGN_IN_GOOGLE = 0;
}
