package com.hfplatinum.persistence;
package com.qa.newguinea.main.persistance;

public class Manager {
    private int managerid;
    private String managername;
    private int userId;

    public Manager(int managerid, String managername, int userId) {
        super();
        this.managerid = managerid;
        this.managername = managername;
        this.setUserId(userId);
    }

    public Manager(String managername, int userId) {
        super();
        this.managername = managername;
        this.setUserId(userId);
    }

    public int getManagerid() {
        return managerid;
    }
    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }
    public String getManagername() {
        return managername;
    }
    public void setManagername(String managername) {
        this.managername = managername;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}

