package org.openchat.domain.post;

import org.openchat.domain.users.IdGenerator;

import java.util.List;

import static org.openchat.infrastructure.builders.PostBuilder.aPost;

public class PostService {
    private final LanguageService languageService;
    private final IdGenerator idGenerator;
    private final Clock clock;
    private final PostRepository postRepository;

    public PostService(LanguageService languageService, IdGenerator idGenerator, Clock clock, PostRepository postRepository) {
        this.languageService = languageService;
        this.idGenerator = idGenerator;
        this.clock = clock;

        this.postRepository = postRepository;
    }

    public Post createPost(String userId, String postText) throws InapropriateLanguageException {
        validate(postText);
        Post post = aPost()
                .setPostId(idGenerator.next())
                .setUserId(userId)
                .setText(postText)
                .setDateTime(clock.now())
                .build();
        postRepository.add(post);
        return post;
    }

    private void validate(String postText) throws InapropriateLanguageException {
        if (languageService.isInappropriate(postText)){
            throw new InapropriateLanguageException();
        }
    }

    public List<Post> postBy(String userId) {
        return postRepository.postBy(userId);
    }
}
