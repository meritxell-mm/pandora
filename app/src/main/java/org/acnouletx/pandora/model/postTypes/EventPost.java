package org.acnouletx.pandora.model.postTypes;

import org.acnouletx.pandora.model.Member;
import org.acnouletx.pandora.model.Post;
import org.acnouletx.pandora.model.utils.PojoUtils;

import java.util.HashMap;

/**
 * Created by txelly on 24/08/17.
 */

public class EventPost extends Post {

    private String eventDesc;
    private long date;
    private boolean isInvite;
    private HashMap<Member, PojoUtils.INVITE_RESPONSE> goingMembers;
    private boolean isDoodle;

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isInvite() {
        return isInvite;
    }

    public void setInvite(boolean invite) {
        isInvite = invite;
    }

    public HashMap<Member, PojoUtils.INVITE_RESPONSE> getGoingMembers() {
        return goingMembers;
    }

    public void setGoingMembers(HashMap<Member, PojoUtils.INVITE_RESPONSE> goingMembers) {
        this.goingMembers = goingMembers;
    }

    public boolean isDoodle() {
        return isDoodle;
    }

    public void setDoodle(boolean doodle) {
        isDoodle = doodle;
    }
}
