package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public boolean createPost(String title) {
        if (!title.trim().isEmpty() && isTitleUnique(title)) {
            return postService.createPost(title);
        } else {
            return false;
        }
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        return postService.updatePostTitle(updateId, newTitle);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public boolean isTitleUnique(String title) {
        for (Post post : getAllPosts()) {
            if (post.getTitle().equals(title)) {
                return false;
            }
        }
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }

    public void saveToFile() {
        postService.saveToFile();
    }

    public void loadFromFile() {
        postService.loadFromFile();
    }
}
