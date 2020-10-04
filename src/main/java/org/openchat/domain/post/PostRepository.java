package org.openchat.domain.post;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PostRepository {
    List<Post> posts = new LinkedList<>();

    public void add(Post post) {
        posts.add(0, post);
    }

    public List<Post> postBy(String userId) {
        return posts.stream()
                .filter(post -> userId.equalsIgnoreCase(post.userId()))
                .collect(toList());
    }
}
