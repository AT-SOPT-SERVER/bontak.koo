package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostCreateResponse;
import org.sopt.dto.PostDetailResponse;
import org.sopt.dto.PostListResponse;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;


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


    public PostListResponse getAllPosts() {
        return PostListResponse.of(postRepository.findAll());
    }

    public PostDetailResponse getPost(final Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return PostDetailResponse.of(post.getId(), post.getTitle());
    }
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