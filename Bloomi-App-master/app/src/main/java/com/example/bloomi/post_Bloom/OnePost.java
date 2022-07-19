package com.example.bloomi.post_Bloom;

public class OnePost {
    private int idpost;

    private String content;
    private int likes,comment;
    private String image;
    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public OnePost(){}
    public OnePost(String content, int likes,int comment) {
        this.content = content;
        this.likes = likes;
        this.comment=comment;
    }

    public OnePost(String content, int likes, int comment,String image) {
        this.content = content;
        this.image = image;
        this.likes = likes;
        this.comment = comment;
    }
    public OnePost(int idpost, String content, int likes, int comment) {
        this.idpost = idpost;
        this.content = content;
        this.likes = likes;
        this.comment = comment;
    }

    public OnePost(String content) {
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
