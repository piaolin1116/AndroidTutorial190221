package com.example.a.photogallery.model;

public class GalleryItem {

    private String mCaption;
    private String mid;
    private String mUrl;

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return "GalleryItem{" +
                "mCaption='" + mCaption + '\'' +
                ", mid='" + mid + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}
