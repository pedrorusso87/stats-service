package com.app.statsservice.service;

import com.app.statsservice.dto.AddTeamRequest;
import com.app.statsservice.model.entities.Team;
import com.app.statsservice.model.entities.User;
import com.app.statsservice.model.response.TeamOwner;
import com.app.statsservice.repository.TeamsRepository;
import com.app.statsservice.service.response.TeamsResponse;
import com.app.statsservice.service.response.UserTeamsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TeamsService {

  @Autowired
  private TeamsRepository teamsRepository;

  public TeamsService(TeamsRepository teamsRepository) {
    this.teamsRepository = teamsRepository;
  }
  
  public List<TeamsResponse> getTeams() {

    List<Team> teams = teamsRepository.findAll();
    List<TeamsResponse> teamsResponse = new ArrayList<>();
    teamsResponse.add(this.addTeam(teams.get(0)));
    return teamsResponse;
  }

  public Team saveTeam(AddTeamRequest request) {
    Team team = new Team();
    team.setName(request.getTeamName());
    team.setDateCreated(request.getDateCreated());
    team.setFoundationDate(request.getFoundationDate());
    team.setStatus("ACTIVE");
    if (request.getTeamOwner() != null) {
      team.setUser(getOwnerData(request.getTeamOwner()));
    } else {
      team.setUser(null);
    }
    return teamsRepository.save(team);
  }

  private TeamsResponse addTeam(Team team) {
    TeamsResponse response = new TeamsResponse();
    response.setTeamId(team.getId());
    response.setTeamName(team.getName());
    TeamOwner teamOwner = new TeamOwner();
    teamOwner.setEmail(team.getUser().getEmail());
    teamOwner.setId(team.getUser().getId());
    teamOwner.setName(team.getUser().getUsername());
    response.setTeamOwner(teamOwner);
    return response;
  }

  private User getOwnerData(User requestUser) {
    User user = new User(requestUser.getId(), requestUser.getUsername());
    return user;
  }

  public UserTeamsResponse getTeamsByUserId(Long userId) {
    List<Team> userTeams = new ArrayList<>();
    UserTeamsResponse response = new UserTeamsResponse();

    // TODO: should this field even exist? If so, could it be username instead of userId?
    response.setUserId(userId);

    Optional<List<Team>> teamsList = teamsRepository.findTeamsByUserId(userId);
    if(!teamsList.isEmpty()) {
      userTeams = teamsList.get();
    }
    response.setTeamsList(userTeams);
    return response;
  }
}
