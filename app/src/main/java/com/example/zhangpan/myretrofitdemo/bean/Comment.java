package com.example.zhangpan.myretrofitdemo.bean;

/**
 * Created by zhangpan on 16/8/22.
 */
public class Comment {
    String url;
    long id;
    User user;
    String created_at;
    String updated_at;
    String body;

    public Comment() {

    }

    public Comment(String url, long id, User user, String created_at, String updated_at, String body) {
        this.url = url;
        this.id = id;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
