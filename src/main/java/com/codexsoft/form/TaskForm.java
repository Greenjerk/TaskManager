package com.codexsoft.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskForm {

    @Size(min = 3, max = 64, message = "{validation.size}")
    @NotEmpty(message = "{validation.not_empty}")
    private String title;

    @Size(min = 3, max = 400, message = "{validation.size}")
    @NotEmpty(message = "{validation.not_empty}")
    private String description;

    @NotNull
    private String users;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
