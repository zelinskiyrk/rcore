package com.ub.core.menu.views;

import org.bson.types.ObjectId;

import java.util.List;

public class MenuView {

    protected ObjectId id;
    protected String name;
    protected String url;
    protected List<MenuView> child;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuView> getChild() {
        return child;
    }

    public void setChild(List<MenuView> child) {
        this.child = child;
    }
}