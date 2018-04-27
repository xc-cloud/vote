package com.workroom.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class matchs {
    private Integer matchid;
    @NotEmpty(message="不为空")
    private String matchname;
    @NotEmpty(message="不为空")
    private String matchaddress;
    @NotEmpty(message="不为空")
    private String matchtime;
    @NotEmpty(message="不为空")
    private String matchinfo;

    public Integer getMatchid() {
        return matchid;
    }

    public void setMatchid(Integer matchid) {
        this.matchid = matchid;
    }

    public String getMatchname() {
        return matchname;
    }

    public void setMatchname(String matchname) {
        this.matchname = matchname == null ? null : matchname.trim();
    }

    public String getMatchaddress() {
        return matchaddress;
    }

    public void setMatchaddress(String matchaddress) {
        this.matchaddress = matchaddress == null ? null : matchaddress.trim();
    }

    public String getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(String matchtime) {
        this.matchtime = matchtime;
    }

    public String getMatchinfo() {
        return matchinfo;
    }

    public void setMatchinfo(String matchinfo) {
        this.matchinfo = matchinfo == null ? null : matchinfo.trim();
    }
}