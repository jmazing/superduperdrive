package com.udacity.jwdnd.course1.cloudstorage.model;

public class UserNote {
    
    private Integer noteid;
    private String notetitle;
    private String notedescriptions;
    private Integer userid;

    public UserNote(Integer noteid, String notetitle, String notedescriptions, Integer userid) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notedescriptions = notedescriptions;
        this.userid = userid;
    }

    public Integer getNoteid() {
        return noteid;
    }
    
    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }
    
    public String getNotetitle() {
        return notetitle;
    }
    
    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }
    
    public String getNotedescriptions() {
        return notedescriptions;
    }
    
    public void setNotedescriptions(String notedescriptions) {
        this.notedescriptions = notedescriptions;
    }
    
    public Integer getUserid() {
        return userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    
}
