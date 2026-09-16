package com.cognifyz.internship.model;

public class TechSkill {
    private String title;
    private String category;
    private String icon;

    public TechSkill(String title, String category, String icon) {
        this.title = title;
        this.category = category;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getIcon() {
        return icon;
    }
}
