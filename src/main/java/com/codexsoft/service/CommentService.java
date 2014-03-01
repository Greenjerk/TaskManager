package com.codexsoft.service;

import com.codexsoft.form.CommentForm;
import com.codexsoft.model.Comment;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;

public interface CommentService extends GenericManager<Comment, Long> {

    public void addComment(Task task, CommentForm commentForm, User author);

}
