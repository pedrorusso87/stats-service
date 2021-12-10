package com.app.statsservice.service;

import com.app.statsservice.dto.AddTeamRequest;
import com.app.statsservice.exception.TeamNameAlreadyExistsException;
import com.app.statsservice.model.entities.Team;
import com.app.statsservice.model.entities.User;
import com.app.statsservice.model.response.TeamOwner;
import com.app.statsservice.model.response.UserTeam;
import com.app.statsservice.repository.TeamsRepository;
import com.app.statsservice.service.response.TeamsResponse;
import com.app.statsservice.service.response.UserTeamsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {

  @Autowired
  private final TeamsRepository teamsRepository;

  @Autowired
  private final AuthService authService;

  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public TeamsService(TeamsRepository teamsRepository, AuthService authService) {
    this.teamsRepository = teamsRepository;
    this.authService = authService;
  }
  
  public List<TeamsResponse> getTeams() {

    List<Team> teams = teamsRepository.findAll();
    List<TeamsResponse> teamsResponse = new ArrayList<>();
    teamsResponse.add(this.addTeam(teams.get(0)));
    return teamsResponse;
  }

  public UserTeam saveTeam(AddTeamRequest request) {
    Team team = new Team();
    team.setName(request.getTeamName());
    team.setDateCreated(request.getDateCreated());
    team.setFoundationDate(request.getFoundationDate());
    team.setStatus("ACTIVE");
    if (request.getTeamOwner() != null) {
      if(this.teamsRepository.findTeamByNameAndUserId(request.getTeamName(), request.getTeamOwner().getId()).isPresent()) {
        throw new TeamNameAlreadyExistsException("El nombre de equipo ya existe");
      } else {
        team.setUser(request.getTeamOwner());
      }
    } else {
      team.setUser(null);
    }
    Team savedTeam = teamsRepository.save(team);
    return buildUserTeam(savedTeam);
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

  public UserTeamsResponse getTeamsByUserId(String username) {
    List<Team> teams = new ArrayList<>();
    List<UserTeam> userTeams = new ArrayList<>();
    UserTeamsResponse response = new UserTeamsResponse();
    // TODO: should this field even exist?
    response.setUsername(username);

    Long userId = authService.findUserIdByUsername(username);
    if (userId != null) {
      Optional<List<Team>> teamsList = teamsRepository.findTeamsByUserId(userId);
      if(!teamsList.isEmpty()) {
        teams = teamsList.get();
      }
    }
    for (Team team: teams) {
      userTeams.add(buildUserTeam(team));
    }
    response.setTeamsList(userTeams);
    return response;
  }

  private UserTeam buildUserTeam(Team team) {
    UserTeam userTeam = new UserTeam();
    userTeam.setId(team.getId());
    userTeam.setName(team.getName());
    userTeam.setStatus(team.getStatus());
    userTeam.setDateCreated(dateFormat.format(team.getDateCreated()));
    userTeam.setFoundationDate(dateFormat.format(team.getDateCreated()));
    if (team.getUser() != null) {
      TeamOwner owner = new TeamOwner();
      owner.setEmail(team.getUser().getEmail());
      owner.setId(team.getUser().getId());
      owner.setName(team.getUser().getUsername());
      userTeam.setTeamOwner(owner);
    }
    return userTeam;
  }
}
