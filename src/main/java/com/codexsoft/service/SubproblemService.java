package com.codexsoft.service;

import com.codexsoft.form.SubproblemForm;
import com.codexsoft.model.Subproblem;
import com.codexsoft.model.Task;

public interface SubproblemService extends GenericManager<Subproblem, Long> {

    public void addSubproblem(SubproblemForm form, Task task);

}
