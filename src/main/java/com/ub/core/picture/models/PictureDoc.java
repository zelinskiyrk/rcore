package com.ub.core.picture.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class PictureDoc {
    private ObjectId id;

    private Map<String, PictureSize> sizes = new HashMap<String, PictureSize>();
    private String url = "";
    private String fileName;
    private String contentType;
    private Boolean isFileInDb = true;
    private ObjectId originFileId;
    private String originFilePath;

    private String color;

    public void addSize(PictureSize pictureSize){
        sizes.put(pictureSize.getStringSize(),pictureSize);
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Map<String, PictureSize> getSizes() {
        return sizes;
    }

    public void setSizes(Map<String, PictureSize> sizes) {
        this.sizes = sizes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsFileInDb() {
        return isFileInDb;
    }

    public void setIsFileInDb(Boolean isFileInDb) {
        this.isFileInDb = isFileInDb;
    }

    public ObjectId getOriginFileId() {
        return originFileId;
    }

    public void setOriginFileId(ObjectId originFileId) {
        this.originFileId = originFileId;
    }

    public String getOriginFilePath() {
        return originFilePath;
    }

    public void setOriginFilePath(String originFilePath) {
        this.originFilePath = originFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}