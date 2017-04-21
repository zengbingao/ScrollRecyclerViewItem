package com.robin.SlideItem;

/**
 * Created by robin on 2016/3/2.
 */
public class MyModel {

    private String left;
    private String content;
    private String right;

    public MyModel(String left, String content, String right) {
        this.left = left;
        this.content = content;
        this.right = right;
    }

    public String getContent() {
        return content;
    }

    public String getRight() {
        return right;
    }

    public String getLeft() {
        return left;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
