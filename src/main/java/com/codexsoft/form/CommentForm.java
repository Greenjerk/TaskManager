package com.codexsoft.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class CommentForm {

    @Size(min = 1, max = 255, message = "{validation.size}")
    @NotEmpty(message = "{validation.not_empty}")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
