package com.workroom.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class players {
    private Integer playerid;
    @NotEmpty(message="不为空")
    private String playertel;
    @NotEmpty(message="不为空")
    private String playername;
    @NotEmpty(message="不为空")
    private String playerimage;
    @NotEmpty(message="不为空")
    private String playertext;

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public String getPlayertel() {
        return playertel;
    }

    public void setPlayertel(String playertel) {
        this.playertel = playertel == null ? null : playertel.trim();
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername == null ? null : playername.trim();
    }

    public String getPlayerimage() {
        return playerimage;
    }

    public void setPlayerimage(String playerimage) {
        this.playerimage = playerimage == null ? null : playerimage.trim();
    }

    public String getPlayertext() {
        return playertext;
    }

    public void setPlayertext(String playertext) {
        this.playertext = playertext == null ? null : playertext.trim();
    }
}