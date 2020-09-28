package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.post.InapropriateLanguageException;
import org.openchat.domain.post.Post;
import org.openchat.domain.post.PostService;
import org.openchat.infrastructure.json.PostJson;
import spark.Request;
import spark.Response;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;

public class PostAPI {
    private PostService postService;

    public PostAPI(PostService postService) {

        this.postService = postService;
    }

    public String createPost(Request request, Response response) throws InapropriateLanguageException {
        String userId = request.params("userId");
        String text = postTextFrom(request);
        Post post = postService.createPost(userId, text);
        response.type("application/json");
        response.status(CREATED_201);
        return PostJson.toJson(post);
    }

    private String postTextFrom(Request request) {
        JsonObject json = Json.parse(request.body()).asObject();
        return json.getString("body", "");
    }

}
