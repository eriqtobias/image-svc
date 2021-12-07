package com.heb.imagesvc.models;

import java.sql.ResultSet;

public class ResponseModel {

    String label;
    String imageId;
    String url;
    String detectedObjects;

    public ResponseModel(String label, String imageId, String url, String detectedObjects) {
        this.label = label;
        this.imageId = imageId;
        this.url = url;
        this.detectedObjects = detectedObjects;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDetectedObjects() {
        return detectedObjects;
    }

    public void setDetectedObjects(String detectedObjects) {
        this.detectedObjects = detectedObjects;
    }

}
