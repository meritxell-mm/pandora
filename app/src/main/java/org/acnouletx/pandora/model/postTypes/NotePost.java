package org.acnouletx.pandora.model.postTypes;

import org.acnouletx.pandora.model.Post;

/**
 * Created by txelly on 24/08/17.
 */

public class NotePost extends Post {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
