package com.example.application.Model;

/**
 * The Blog class represents a blog post.
 */
public class Blog {
    private String id;
    private String title;
    private String content;
    private String des;
    private String image;
    private String author;
    private String updatedAt;
    private int status;

    /**
     * Constructs a Blog object with the specified parameters.
     *
     * @param id        the ID of the blog
     * @param title     the title of the blog
     * @param content   the content of the blog
     * @param des       the description of the blog
     * @param image     the image URL of the blog
     * @param status    the status of the blog
     * @param author    the author of the blog
     * @param updatedAt the last updated date of the blog
     */
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

    /**
     * Constructs a Blog object with the specified parameters.
     *
     * @param title   the title of the blog
     * @param content the content of the blog
     * @param des     the description of the blog
     * @param image   the image URL of the blog
     */
    public Blog(String title, String content, String des, String image) {
        this.title = title;
        this.content = content;
        this.des = des;
        this.image = image;
    }

    /**
     * Returns the ID of the blog.
     *
     * @return the ID of the blog
     */
    public String getBlogId() {
        return id;
    }

    /**
     * Returns the author of the blog.
     *
     * @return the author of the blog
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the last updated date of the blog.
     *
     * @return the last updated date of the blog
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Returns the title of the blog.
     *
     * @return the title of the blog
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the content of the blog.
     *
     * @return the content of the blog
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the image URL of the blog.
     *
     * @return the image URL of the blog
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the ID of the blog.
     *
     * @return the ID of the blog
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the description of the blog.
     *
     * @return the description of the blog
     */
    public String getDes() {
        return des;
    }

    /**
     * Returns the status of the blog.
     *
     * @return the status of the blog
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the ID of the blog.
     *
     * @param id the ID of the blog
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the description of the blog.
     *
     * @param des the description of the blog
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * Sets the status of the blog.
     *
     * @param status the status of the blog
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Sets the title of the blog.
     *
     * @param title the title of the blog
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the content of the blog.
     *
     * @param content the content of the blog
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the image URL of the blog.
     *
     * @param image the image URL of the blog
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Sets the author of the blog.
     *
     * @param author the author of the blog
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the last updated date of the blog.
     *
     * @param updatedAt the last updated date of the blog
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
