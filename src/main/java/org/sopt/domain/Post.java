package org.sopt.domain;

import org.sopt.util.IDGenerator;

import java.time.LocalDateTime;

public class Post {
    private final int id;
    private String title;
    private final LocalDateTime time;

    public Post(String title) {
        this.id = IDGenerator.getId();
        this.setTitle(title);
        this.time = LocalDateTime.now();
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title.length() > 30) {
            this.title = title.substring(0, 30);
        } else {
            this.title = title;

        }
    }


    public LocalDateTime getTime() {
        return this.time;
    }
}
