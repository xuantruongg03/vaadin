package com.example.application.Model;

public class Blog {
    private String id;
    private String title;
    private String content;
    private String des;
    private String image;
    private String author;
    private String updatedAt;
    private int status;

    public Blog(String id, String title, String content, String des, String image, int status, String author,
            String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.des = des;
        this.image = image;
        this.status = status;
        this.author = author;
        this.updatedAt = updatedAt;
    }

    public Blog(String title, String content, String des, String image) {
        this.title = title;
        this.content = content;
        this.des = des;
        this.image = image;
    }

    public String getBlogId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getDes() {
        return des;
    }

    public int getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
