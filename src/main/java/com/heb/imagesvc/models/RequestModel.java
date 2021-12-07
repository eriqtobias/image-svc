package com.heb.imagesvc.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestModel {

    String imageURL;

    public RequestModel(@JsonProperty("imageURL") String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
