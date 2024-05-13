package com.example.retrofit;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TeamResponse {
    @SerializedName("teams")
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
