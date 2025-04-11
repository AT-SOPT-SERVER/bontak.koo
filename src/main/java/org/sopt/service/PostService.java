package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public boolean createPost(String title) {
        boolean flag = true;

        if (!this.getAllPosts().isEmpty()) {
            LocalDateTime latestPostTime = getAllPosts().get(getAllPosts().size() - 1).getTime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(latestPostTime, now);
            if (duration.toMinutes() < 3) {
                flag = false;
            }
        }

        if (flag) {
            Post post = new Post(title);
            postRepository.save(post);
            return true;
        } else {
            return false;
        }


    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        return postRepository.updatePostTitle(updateId, newTitle);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }

    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/java/org/sopt/assets/Post.txt"));

            for (Post post : getAllPosts()) {
                writer.write(post.getTitle() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFromFile() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/main/java/org/sopt/assets/Post.txt"));


            String title;
            while ((title = reader.readLine()) != null) {
                Post post = new Post(title);
                postRepository.save(post);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
