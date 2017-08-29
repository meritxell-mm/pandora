package org.acnouletx.pandora.model.utils;

/**
 * Created by txelly on 24/08/17.
 */

public interface PojoUtils {

    enum IS_FRIEND{
        NOT_FRIEND,
        FRIEND,
        ITS_ME
    }

    enum MOOD{
        HAPPY,
        NEUTRAL,
        SAD,
        EXCITED,
        SURPRISED,
        ANGRY,
        HUNGRY,
        SICK,
        BORED,
        ROMANTIC
    }

    enum POST_TYPE{
        LOCATION,
        PHOTO,
        VIDEO,
        NOTE,
        LETTER,
        EVENT
    }

    enum INVITE_RESPONSE{
        UNKNOWN,
        GOING,
        NOT_GOING
    }
}
