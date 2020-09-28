package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openchat.domain.post.InapropriateLanguageException;
import org.openchat.domain.post.Post;
import org.openchat.domain.post.PostService;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostAPIShould {
    private static final String USER_ID = UUID.randomUUID().toString();
    private static final String POST_TEXT = "some post text";
    private static final String POST_ID = UUID.randomUUID().toString();
    private static final LocalDateTime DATETIME = LocalDateTime.of(2018,1,10,14,30);
    private static final Post POST = new Post(POST_ID, USER_ID, POST_TEXT, DATETIME);
    private PostAPI postAPI;
    @Mock private PostService postService;
    @Mock private Request request;
    @Mock private Response response;

    @Before
    public void before() {
        postAPI = new PostAPI(postService);
    }


    @Test
    public void return_a_json_representation_of_a_newly_created_post() throws InapropriateLanguageException {
        given(request.params("userId")).willReturn(USER_ID);
        given(request.body()).willReturn(jsonContaining(POST_TEXT));
        given(postService.createPost(USER_ID, POST_TEXT)).willReturn(POST);

        String result = postAPI.createPost(request, response);

        verify(response).status(201);
        verify(response).type("application/json");
        assertThat(result).isEqualTo(jsonContaining(POST));
    }

    @Test
    public void
            create_a_post() throws InapropriateLanguageException {
        given(request.params("userId")).willReturn(USER_ID);
        given(request.body()).willReturn(jsonContaining(POST_TEXT));
        given(postService.createPost(USER_ID, POST_TEXT)).willReturn(POST);

        postAPI.createPost(request, response);

        verify(postService).createPost(USER_ID, POST_TEXT);
    }


    private String jsonContaining(Post post) {
        return new JsonObject()
                .add("postId", post.postId())
                .add("userId", post.userId())
                .add("text", post.text())
                .add("dateTime", "2018-01-10T14:30:00Z")
                .toString();
    }

    private String jsonContaining(String postText) {
        return new JsonObject().add("body", postText).toString();
    }


}
