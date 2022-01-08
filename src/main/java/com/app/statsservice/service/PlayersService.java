package com.app.statsservice.service;

import com.app.statsservice.dto.AddPlayerRequest;
import com.app.statsservice.model.entities.DocumentType;
import com.app.statsservice.model.entities.Player;
import com.app.statsservice.model.entities.Team;
import com.app.statsservice.model.request.PlayerInformation;
import com.app.statsservice.model.response.PlayerResponse;
import com.app.statsservice.model.response.PlayersByTeamResponse;
import com.app.statsservice.repository.DocumentTypeRepository;
import com.app.statsservice.repository.PlayersRepository;
import com.app.statsservice.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayersService {

  @Autowired
  private PlayersRepository playersRepository;

  @Autowired
  private TeamsRepository teamsRepository;

  @Autowired
  private DocumentTypeRepository documentTypeRepository;

  private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  public PlayersService(PlayersRepository playersRepository,
                        TeamsRepository teamsRepository,
                        DocumentTypeRepository documentTypeRepository) {
    this.playersRepository = playersRepository;
    this.teamsRepository = teamsRepository;
    this.documentTypeRepository = documentTypeRepository;
  }

  public PlayersByTeamResponse findPlayersByTeam(Long teamId) {
    //TODO: Add error handling and null check for repository calls.
    List<Player> playerList = playersRepository.findPlayersByTeamId(teamId).get();
    PlayersByTeamResponse response = new PlayersByTeamResponse();
    List<PlayerResponse> playersResponseList = new ArrayList<>();
    for(Player player : playerList) {
      playersResponseList.add(buildPlayerResponse(player));
    }
    response.setPlayerList(playersResponseList);
    return response;
  }

  public Object addPlayersToTeam(AddPlayerRequest playerRequest) {
    List<Player> playersToSave = new ArrayList<>();
    Team playerTeam = this.getTeamById(playerRequest.getTeamId());
    for(PlayerInformation playerInformation : playerRequest.getPlayerInformationList()) {
      DocumentType documentType = this.getDocumentTypeById(playerInformation.getDocumentTypeId());
      playersToSave.add(this.buildPlayer(playerInformation, playerTeam, documentType));
    }

    //TODO: Should we create a response object?
    return playersRepository.saveAll(playersToSave);
  }

  private PlayerResponse buildPlayerResponse(Player player) {
    PlayerResponse playerResponse = new PlayerResponse();
    playerResponse.setPlayerName(player.getName());
    playerResponse.setDateOfBirth(dateFormat.format(player.getDateOfBirth()));
    playerResponse.setDocumentNumber(player.getDocumentNumber());
    playerResponse.setStatus(player.getStatus());
    return playerResponse;
  }

  private Team getTeamById(Long teamId) {
    //TODO: Add null check here
    return teamsRepository.findById(teamId).get();
  }

  private DocumentType getDocumentTypeById(Long documentTypeId) {
    //TODO: Add null check here
    return documentTypeRepository.findById(documentTypeId).get();
  }

  private Player buildPlayer(PlayerInformation playerInformation, Team playerTeam, DocumentType documentType) {
    Player player = new Player();
    player.setName(playerInformation.getPlayerName());
    player.setDocumentNumber(playerInformation.getDocumentNumber());
    player.setDateOfBirth(playerInformation.getDateOfBirth());
    player.setTeam(playerTeam);
    player.setDocumentType(documentType);
    player.setStatus("ACTIVE");
    return player;
  }
}
