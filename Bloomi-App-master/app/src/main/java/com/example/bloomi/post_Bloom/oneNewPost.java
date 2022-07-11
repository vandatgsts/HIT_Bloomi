package com.example.bloomi.post_Bloom;

public class oneNewPost {
    int accountId;
    String content;
    String Url_image;
    int displayMode=1;

    public int getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(int displayMode) {
        this.displayMode = displayMode;
    }

    public oneNewPost(int accountId, String content, String url_image, int displayMode) {
        this.accountId = accountId;
        this.content = content;
        Url_image = url_image;
        this.displayMode = displayMode;
    }

    public oneNewPost() {
    }

    public oneNewPost(int accountId, String content, String url_image) {
        this.accountId = accountId;
        this.content = content;
        Url_image = url_image;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl_image() {
        return Url_image;
    }

    public void setUrl_image(String url_image) {
        Url_image = url_image;
    }
}
