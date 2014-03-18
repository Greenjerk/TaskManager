package com.codexsoft.model;

import com.codexsoft.util.TimeStamped;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity implements Serializable, TimeStamped {

    @Version
    Long version;

    @Column(name = "created_date", nullable = false, updatable = false)
    Date createdDate;

    @Column(name = "last_updated", nullable = false)
    Date lastUpdated;

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = this.lastUpdated = createdDate;
    }

    @Override
    public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

