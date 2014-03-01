package com.codexsoft.service.impl;

import com.codexsoft.dao.SubproblemDao;
import com.codexsoft.form.SubproblemForm;
import com.codexsoft.model.Subproblem;
import com.codexsoft.model.Task;
import com.codexsoft.service.SubproblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SubproblemServiceImpl extends GenericManagerImpl<Subproblem, Long> implements SubproblemService {

    private SubproblemDao subproblemDao;

    @Autowired
    public SubproblemServiceImpl(SubproblemDao subproblemDao) {
        super(subproblemDao);
        this.subproblemDao = subproblemDao;
    }

    @Override
    public void addSubproblem(SubproblemForm form, Task task) {
        Subproblem subproblem = new Subproblem();
        subproblem.setTitle(form.getTitle());
        subproblem.setContent(form.getContent());
        subproblem.setTask(task);
        subproblemDao.save(subproblem);
    }
}
