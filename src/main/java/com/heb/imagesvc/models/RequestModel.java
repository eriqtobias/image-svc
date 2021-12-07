package com.heb.imagesvc.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestModel {

    String imageURL;
    String label;
    boolean objectDetection;

    public RequestModel(@JsonProperty("imageURL") String imageURL, @JsonProperty(value = "label", required = false) String label, @JsonProperty("objectDetection") boolean objectDetection) {
        this.imageURL = imageURL;
        this.label = label;
        this.objectDetection = objectDetection;

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean getObjectDetection() {
        return objectDetection;
    }

    public void setObjectDetection(boolean objectDetection) {
        this.objectDetection = objectDetection;
    }
}
