package com.example.bloomi.post_Bloom;

public class OnePost {
    private String content;
    private String image;
    private int likes,comment;

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
