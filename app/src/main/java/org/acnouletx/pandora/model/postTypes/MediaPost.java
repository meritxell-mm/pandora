package org.acnouletx.pandora.model.postTypes;

import org.acnouletx.pandora.model.Post;

/**
 * Created by txelly on 24/08/17.
 */

public class MediaPost extends Post {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
