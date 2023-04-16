package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    
    private int noteid;
    private String notetitle;
    private String notedescriptions;
    private int userid;
    
    public int getNoteid() {
        return noteid;
    }
    
    public void setNoteid(int noteid) {
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
    
    public int getUserid() {
        return userid;
    }
    
    public void setUserid(int userid) {
        this.userid = userid;
    }

    
}
