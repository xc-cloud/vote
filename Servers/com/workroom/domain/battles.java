package com.workroom.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class battles {
	
    private Integer battleid;
    
    @NotNull(message="不为空")
    private Integer matchid;
    
    @NotNull(message="不为空")
    private Integer playerid;
    
    @NotNull(message="不为空")
    private Integer tickettruecount;
    
    @NotNull(message="不为空")
    private Integer ticketfalsecount;
    
    @NotNull(message="不为空")
    private Double needscorecount;
    
    @NotNull(message="不为空")
    private Integer matchtype;
    
    @NotNull(message="不为空")
    private Integer battleflag;
    
    @NotEmpty(message="不为空")
    private String songname;
    
    
    public Integer getBattleid() {
        return battleid;
    }

    public void setBattleid(Integer battleid) {
        this.battleid = battleid;
    }

    public Integer getMatchid() {
        return matchid;
    }

    public void setMatchid(Integer matchid) {
        this.matchid = matchid;
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public Integer getTickettruecount() {
        return tickettruecount;
    }

    public void setTickettruecount(Integer tickettruecount) {
        this.tickettruecount = tickettruecount;
    }

    public Integer getTicketfalsecount() {
        return ticketfalsecount;
    }

    public void setTicketfalsecount(Integer ticketfalsecount) {
        this.ticketfalsecount = ticketfalsecount;
    }

    public Double getNeedscorecount() {
        return needscorecount;
    }

    public void setNeedscorecount(Double needscorecount) {
        this.needscorecount = needscorecount;
    }

    public Integer getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(Integer matchtype) {
        this.matchtype = matchtype;
    }

    public Integer getBattleflag() {
        return battleflag;
    }

    public void setBattleflag(Integer battleflag) {
        this.battleflag = battleflag;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname == null ? null : songname.trim();
    }
}