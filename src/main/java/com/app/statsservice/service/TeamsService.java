package com.app.statsservice.service;

import com.app.statsservice.dto.AddTeamRequest;
import com.app.statsservice.exception.TeamNameAlreadyExistsException;
import com.app.statsservice.model.entities.Team;
import com.app.statsservice.model.entities.User;
import com.app.statsservice.model.response.TeamOwner;
import com.app.statsservice.model.response.UserTeam;
import com.app.statsservice.repository.TeamsRepository;
import com.app.statsservice.repository.UserRepository;
import com.app.statsservice.service.response.TeamsResponse;
import com.app.statsservice.service.response.UserTeamsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TeamsService {

  @Autowired
  private final TeamsRepository teamsRepository;

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final AuthService authService;

  private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  public TeamsService(TeamsRepository teamsRepository, AuthService authService, UserRepository userRepository) {
    this.teamsRepository = teamsRepository;
    this.authService = authService;
    this.userRepository = userRepository;
  }
  
  public List<TeamsResponse> getTeams() {

    List<Team> teams = teamsRepository.findAll();
    List<TeamsResponse> teamsResponse = new ArrayList<>();
    for (Team team: teams) {
      teamsResponse.add(this.addTeam(team));
    }
    return teamsResponse;
  }

  public UserTeam saveTeam(AddTeamRequest request) {
    //TODO: Create subscription record after creating the team!
    Team team = new Team();
    team.setName(request.getTeamName());
    team.setDateCreated(request.getDateCreated());
    team.setFoundationDate(request.getFoundationDate());
    team.setStatus("ACTIVE");
    if (request.getTeamOwner() != null) {
      User user = this.userRepository.findByUsername(request.getTeamOwner().getUsername()).get();
      if(this.teamsRepository.findTeamByNameAndUserId(request.getTeamName(), user.getId()).isPresent()) {
        throw new TeamNameAlreadyExistsException("El nombre de equipo ya existe");
      } else {
        request.getTeamOwner().setId(user.getId());
        request.getTeamOwner().setEmail(user.getEmail());
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
    response.setTeamOwner(buildTeamOwner(team));
    return response;
  }

  public UserTeamsResponse getTeamsByUserId(String username) {
    List<Long> teamIds = new ArrayList<>();
    List<UserTeam> userTeams = new ArrayList<>();
    UserTeamsResponse userTeamsResponse = new UserTeamsResponse();

    Long userId = authService.findUserIdByUsername(username);
    if (userId != null) {
      Optional<List<Object[]>> userSubscriptions = teamsRepository.findTeamsSubscriptions(userId);
      Map<String, String> userTeamRoles = new HashMap<>();
      if(userSubscriptions.isPresent()) {
        for(Object[] subs: userSubscriptions.get()) {
          userTeamRoles.put(subs[0].toString(), subs[1].toString());
          teamIds.add((Long) subs[0]);
        }
      }
      Optional<List<Team>> teamsList = teamsRepository.findTeamByIdIn(teamIds);
      if(teamsList.isPresent()) {
        for(Team team : teamsList.get()) {
          userTeams.add(buildUserTeam(team));
        }
      }
      for(UserTeam userTeam: userTeams) {
        userTeam.setRole(userTeamRoles.get(userTeam.getId().toString()));
      }

    }

    userTeamsResponse.setTeamsList(userTeams);
    return userTeamsResponse;
  }

  private UserTeam buildUserTeam(Team team) {
    /* TODO:
    * Need to find a way to set the user permission for each team
    */
    UserTeam userTeam = new UserTeam();
    userTeam.setId(team.getId());
    userTeam.setName(team.getName());
    userTeam.setStatus(team.getStatus());
    userTeam.setDateCreated(dateFormat.format(team.getDateCreated()));
    userTeam.setFoundationDate(dateFormat.format(team.getDateCreated()));
    return userTeam;
  }

  private TeamOwner buildTeamOwner(Team team) {
    TeamOwner teamOwner = new TeamOwner();
    teamOwner.setEmail(team.getUser().getEmail());
    teamOwner.setId(team.getUser().getId());
    teamOwner.setUsername(team.getUser().getUsername());
    return teamOwner;
  }
}
