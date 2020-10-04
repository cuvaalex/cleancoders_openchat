package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.post.InapropriateLanguageException;
import org.openchat.domain.post.Post;
import org.openchat.domain.post.PostService;
import org.openchat.infrastructure.json.PostJson;
import spark.Request;
import spark.Response;

import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.eclipse.jetty.http.HttpStatus.*;

public class PostAPI {
    private PostService postService;

    public PostAPI(PostService postService) {

        this.postService = postService;
    }

    public String createPost(Request request, Response response) {
        String userId = request.params("userId");
        String text = postTextFrom(request);
        try {
            return prepareOKResponse(response, userId, text);
        }catch (InapropriateLanguageException e){
            return prepareErrorResponse(response);
        }

    }

    public String postByUser(Request request, Response response) {
        String userId = request.params("userId");
        List<Post> posts = postService.postBy(userId);
        response.status(OK_200);
        response.type("application/json");
        return PostJson.toJson(posts);
    }

    private String prepareErrorResponse(Response response) {
        response.status(BAD_REQUEST_400);
        return "Post contains inappropriate language.";
    }

    private String prepareOKResponse(Response response, String userId, String text) throws InapropriateLanguageException {
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
