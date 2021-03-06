package com.cony.es.entity;


import com.cony.es.config.ESearchTypeColumn;

/**
 * Created by wangk-p on 2017/11/9.
 */
public class Article {

    public static final String DEFAULT_TEMPLATE = "frontViewArticle";

    private static final long serialVersionUID = 1L;

    @ESearchTypeColumn
    private String id;
    @ESearchTypeColumn
    private Integer hits;
    @ESearchTypeColumn
    private String beginDateString;

    @ESearchTypeColumn
    private String title;       // 标题
    private String link;  // 外部链接
    private String color;     // 标题颜色（red：红色；green：绿色；blue：蓝色；yellow：黄色；orange：橙色）
    private String image;    // 文章图片
    @ESearchTypeColumn
    private String keywords;//关键字

    @ESearchTypeColumn
    private String description;//描述、摘要

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getBeginDateString() {
        return beginDateString;
    }

    public void setBeginDateString(String beginDateString) {
        this.beginDateString = beginDateString;
    }
}