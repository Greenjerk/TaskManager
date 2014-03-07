package com.codexsoft.model;

import com.codexsoft.util.TimeStamped;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
class BaseEntity implements Serializable, TimeStamped {

    @Version
    Long version;

    @Column(name = "createdDate", nullable = false, updatable = false)
    Date createdDate;

    @Column(name = "lastUpdated", nullable = false)
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

