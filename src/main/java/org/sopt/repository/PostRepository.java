package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        for (Post post : postList) {
            if (post.getId() == updateId) {
                post.setTitle(newTitle);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> searchedPosts = new ArrayList<>();
        for (Post post : postList) {
            if (post.getTitle().contains(keyword)) {
                searchedPosts.add(post);
            }
        }
        return searchedPosts;
    }
}
