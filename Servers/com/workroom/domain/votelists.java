package com.workroom.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class votelists {
	@NotNull(message="��Ϊ��")
    private Integer matchid;
	@NotNull(message="��Ϊ��")
    private Integer battleid;
	@NotNull(message="��Ϊ��")
    private Integer playerid;
	
    private Integer userid;
	@NotNull(message="��Ϊ��")
    private Integer ticketbol;

    public Integer getMatchid() {
        return matchid;
    }

    public void setMatchid(Integer matchid) {
        this.matchid = matchid;
    }

    public Integer getBattleid() {
        return battleid;
    }

    public void setBattleid(Integer battleid) {
        this.battleid = battleid;
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTicketbol() {
        return ticketbol;
    }

    public void setTicketbol(Integer ticketbol) {
        this.ticketbol = ticketbol;
    }
}