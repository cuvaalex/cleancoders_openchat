package org.openchat.domain.post;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Post {
    private final String postId;
    private final String userId;
    private final String text;
    private LocalDateTime dateTime;

    public Post(String postId, String userId, String text, LocalDateTime dateTime) {

        this.postId = postId;
        this.userId = userId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String postId() {
        return postId;
    }

    public String userId() {
        return userId;
    }

    public String text() {
        return text;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
