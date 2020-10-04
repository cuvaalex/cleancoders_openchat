package org.openchat.domain.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openchat.infrastructure.builders.PostBuilder.aPost;


@RunWith(MockitoJUnitRunner.class)
public class PostRepositoryShould {

    private static final Post ALICE_POST_1 = aPost()
            .setText("ALICE POST 1").build();
    private static final Post ALICE_POST_2 = aPost()
            .setUserId(ALICE_POST_1.userId())
            .setText("ALICE POST 2").build();
    private static final Post CHARLIE_POST_1 = aPost()
            .setText("CHARLIE POST 1").build();
    private PostRepository postRepository;

    @Before
    public void before() {
        postRepository = new PostRepository();
    }
    @Test
    public void return_posts_for_give_user_in_reverse_chronological_order() {
        postRepository.add(ALICE_POST_1);
        postRepository.add(CHARLIE_POST_1);
        postRepository.add(ALICE_POST_2);

        List<Post> result = postRepository.postBy(ALICE_POST_1.userId());

        assertThat(result).containsExactly(ALICE_POST_2, ALICE_POST_1);
    }
}
