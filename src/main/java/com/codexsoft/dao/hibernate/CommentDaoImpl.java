package com.codexsoft.dao.hibernate;

import com.codexsoft.dao.CommentDao;
import com.codexsoft.model.Comment;
import org.springframework.stereotype.Repository;

/**
 * User: Greenjerk
 * Date: 27.01.14
 * Time: 11:40
 */

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment, Long> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

}
