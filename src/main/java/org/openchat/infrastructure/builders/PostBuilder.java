package org.openchat.infrastructure.builders;

import org.openchat.domain.post.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostBuilder {
    private String postId = UUID.randomUUID().toString();
    private String userId = UUID.randomUUID().toString();
    private String text = "New post";
    private LocalDateTime dateTime = LocalDateTime.now();

    public static PostBuilder aPost() {return new PostBuilder();}

    public PostBuilder setPostId(String postId) {
        this.postId = postId;
        return this;
    }

    public PostBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public PostBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public PostBuilder setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Post build() {
        return new Post(postId, userId, text, dateTime);
    }
}
