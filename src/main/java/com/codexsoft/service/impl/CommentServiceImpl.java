package com.codexsoft.service.impl;

import com.codexsoft.dao.CommentDao;
import com.codexsoft.form.CommentForm;
import com.codexsoft.model.Comment;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;
import com.codexsoft.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentServiceImpl extends GenericManagerImpl<Comment, Long>
        implements CommentService {


    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        super(commentDao);
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(Task task, CommentForm commentForm, User author) {

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setComment(commentForm.getComment());
        comment.setUser(author);
        commentDao.save(comment);
    }

}
