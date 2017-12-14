package com.hongon.solarmvvmdemo.converter;

import java.util.Date;

/**
 * Created by CoCO on 2017/12/14.
 */

 class ConverterListItem {
    private Integer ID;
    private String  Name;
    private Date    LastReportTime;

    public Date getLastReportTime() {
        return LastReportTime;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setLastReportTime(Date lastReportTime) {
        LastReportTime = lastReportTime;
    }

    public void setName(String name) {
        Name = name;
    }
}
