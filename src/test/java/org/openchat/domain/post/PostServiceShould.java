package org.openchat.domain.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openchat.domain.users.IdGenerator;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class PostServiceShould {

    private static final String POST_USERID = UUID.randomUUID().toString();
    private static final String POST_TEXT = "this is a post text";
    private static final LocalDateTime POST_DATETIME = LocalDateTime.now();
    private static final String POST_ID = UUID.randomUUID().toString();
    private static final Post POST = new Post(POST_ID, POST_USERID, POST_TEXT, POST_DATETIME);

    private PostService postService;

    @Mock PostRepository postRepository;
    @Mock IdGenerator idGenerator;
    @Mock Clock clock;
    @Mock LanguageService languageService;

    @Before
    public void before() {
        postService = new PostService(languageService, idGenerator, clock, postRepository);
    }
    @Test
    public void create_post() throws InapropriateLanguageException {
        given(languageService.isInapropriate(POST_TEXT)).willReturn(false);
        given(idGenerator.next()).willReturn(POST_ID);
        given(clock.now()).willReturn(POST_DATETIME);
        Post result = postService.createPost(POST_USERID, POST_TEXT);

        verify(postRepository).add(POST);
        assertThat(result).isEqualTo(POST);
    }

    @Test(expected = InapropriateLanguageException.class)
    public void throw_an_inapropriate_language_exception_if_post_contain_inapropriate_word() throws InapropriateLanguageException {
        given(languageService.isInapropriate(POST_TEXT)).willReturn(true);

        postService.createPost(POST_USERID, POST_TEXT);
    }
}
