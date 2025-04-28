package com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateCourseRequest {

    @NotBlank(message = "Course name is required")
    private String name;

    private String description;

    @NotBlank(message = "Course type is required")
    private String type; // theory or practical

    @NotNull(message = "Course duration is required")
    private Integer duration; // in days

    private List<String> topics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}

