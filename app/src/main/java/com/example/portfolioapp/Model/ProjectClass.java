package com.example.portfolioapp.Model;

public class ProjectClass {
    String Image, Name, Description;

    public ProjectClass(String image, String name, String description) {
        Image = image;
        Name = name;
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
