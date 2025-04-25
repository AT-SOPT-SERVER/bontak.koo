package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.req.PostCreateRequest;
import org.sopt.dto.res.PostCreateResponse;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostCreateResponse createPost(PostCreateRequest PostCreateRequest) {
        Post post = new Post(PostCreateRequest.title());
        postRepository.save(post);

        return PostCreateResponse.from(post);
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

//    public Post getPostById(int id) {
//        return postRepository.findPostById(id);
//    }
//
//    public boolean updatePostTitle(int updateId, String newTitle) {
//        return postRepository.updatePostTitle(updateId, newTitle);
//    }
//
//    public boolean deletePostById(int id) {
//        return postRepository.delete(id);
//    }
//
//    public List<Post> searchPostsByKeyword(String keyword) {
//        return postRepository.searchPostsByKeyword(keyword);
//    }
}
