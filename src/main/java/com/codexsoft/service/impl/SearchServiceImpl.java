package com.codexsoft.service.impl;

import com.codexsoft.model.Comment;
import com.codexsoft.model.Subproblem;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;
import com.codexsoft.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Greenjerk
 * Date: 30.01.14
 * Time: 15:32
 */

@Transactional
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    CommentService commentService;
    @Autowired
    TaskService taskService;
    @Autowired
    SubproblemService subproblemService;
    @Autowired
    UserService userService;

    @Override
    public Set<Task> search(String term) {
        Set<Task> taskSet = new HashSet<Task>();

        for (Comment comment : commentService.search(term)) {
            taskSet.add(comment.getTask());
        }

        for (Subproblem subproblem : subproblemService.search(term)) {
            taskSet.add(subproblem.getTask());
        }

        for (Task task : taskService.search(term)) {
            taskSet.add(task);
        }

        return taskSet;
    }
}
