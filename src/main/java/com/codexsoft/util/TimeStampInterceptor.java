package com.codexsoft.util;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.EmptyInterceptor;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

public class TimeStampInterceptor extends EmptyInterceptor {

    @Override
    public boolean onFlushDirty(java.lang.Object entity, java.io.Serializable id, java.lang.Object[] currentState, java.lang.Object[] previousState, java.lang.String[] propertyNames, org.hibernate.type.Type[] types) {
        if (entity instanceof TimeStamped) {
            int indexOf = ArrayUtils.indexOf(propertyNames, "lastUpdated");
            currentState[indexOf] = new Date();
            return true;
        }
        return false;
    }

    @Override
    public boolean onSave(java.lang.Object entity, java.io.Serializable id, java.lang.Object[] state, java.lang.String[] propertyNames, org.hibernate.type.Type[] types) {
        if (entity instanceof TimeStamped) {
            Date date = new Date();
            int indexOfCD = ArrayUtils.indexOf(propertyNames, "createdDate");
            state[indexOfCD] = date;
            int indexOfLU = ArrayUtils.indexOf(propertyNames, "lastUpdated");
            state[indexOfLU] = date;
            return true;
        }
        return false;
    }
}