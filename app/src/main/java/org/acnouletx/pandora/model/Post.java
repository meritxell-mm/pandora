package org.acnouletx.pandora.model;

import org.acnouletx.pandora.model.utils.PojoUtils;

/**
 * Created by txelly on 24/08/17.
 */

public class Post {

    private String id;
    private Member member;
    private PojoUtils.POST_TYPE postType;
    private long timestamp;
    private String title;
    private String contentDesc;
    private boolean isReaded;
    private double[] location;

    //FEATURE
    private boolean isPublic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public PojoUtils.POST_TYPE getPostType() {
        return postType;
    }

    public void setPostType(PojoUtils.POST_TYPE postType) {
        this.postType = postType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
