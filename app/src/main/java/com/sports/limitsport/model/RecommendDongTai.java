package com.sports.limitsport.model;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class RecommendDongTai {
    private int id;
    private String imgUrl;
    private int resourceType;
    private String thumbnailUrl;
    private String vedioImgUrl;
    private String vedioThumbnailUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVedioImgUrl() {
        return vedioImgUrl;
    }

    public void setVedioImgUrl(String vedioImgUrl) {
        this.vedioImgUrl = vedioImgUrl;
    }

    public String getVedioThumbnailUrl() {
        return vedioThumbnailUrl;
    }

    public void setVedioThumbnailUrl(String vedioThumbnailUrl) {
        this.vedioThumbnailUrl = vedioThumbnailUrl;
    }
}
