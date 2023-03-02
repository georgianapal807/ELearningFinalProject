package Finalproject.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseCreateDto {

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @NotBlank(message = "Please write a short description for this course!")
    private String shortDescription;

    @NotBlank(message = "Please set a category here!")
    private String category;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
