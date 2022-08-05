package com.example.mehndidesignapp.Models;

public class ImageModel {
    private String imgName;
    private String imgThumbnail;
    private String imgUrl;
    private String mostViewImage;

    public ImageModel() {
    }

    public ImageModel(String imgName, String imgThumbnail, String imgUrl, String mostViewImage) {
        this.imgName = imgName;
        this.imgThumbnail = imgThumbnail;
        this.imgUrl = imgUrl;
        this.mostViewImage = mostViewImage;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgThumbnail() {
        return imgThumbnail;
    }

    public void setImgThumbnail(String imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMostViewImage() {
        return mostViewImage;
    }

    public void setMostViewImage(String mostViewImage) {
        this.mostViewImage = mostViewImage;
    }
}
