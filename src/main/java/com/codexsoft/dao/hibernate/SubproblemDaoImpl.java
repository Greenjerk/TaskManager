package com.codexsoft.dao.hibernate;

import com.codexsoft.dao.SubproblemDao;
import com.codexsoft.model.Subproblem;
import org.springframework.stereotype.Repository;

@Repository
public class SubproblemDaoImpl extends GenericDaoImpl<Subproblem, Long> implements SubproblemDao {

    public SubproblemDaoImpl() {
        super(Subproblem.class);
    }

}
