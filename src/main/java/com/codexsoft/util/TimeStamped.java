package com.codexsoft.util;

import java.util.Date;

public interface TimeStamped {
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);
    public Date getLastUpdated();
    public void setLastUpdated(Date lastUpdated);
}