package org.openchat.domain.post;

import org.openchat.domain.users.IdGenerator;

public class PostService {
    private LanguageService languageService;
    private IdGenerator idGenerator;
    private Clock clock;
    private PostRepository postRepository;

    public PostService(LanguageService languageService, IdGenerator idGenerator, Clock clock, PostRepository postRepository) {
        this.languageService = languageService;
        this.idGenerator = idGenerator;
        this.clock = clock;

        this.postRepository = postRepository;
    }

    public Post createPost(String userId, String postText) throws InapropriateLanguageException {
        validate(postText);
        Post post = new Post(idGenerator.next(), userId, postText, clock.now());
        postRepository.add(post);
        return post;
    }

    private void validate(String postText) throws InapropriateLanguageException {
        if (languageService.isInapropriate(postText)){
            throw new InapropriateLanguageException();
        }
    }

    public LanguageService languageService() {
        return languageService;
    }

    public IdGenerator idGenerator() {
        return idGenerator;
    }

    public Clock clock() {
        return clock;
    }

    public PostRepository postRepository() {
        return postRepository;
    }
}
