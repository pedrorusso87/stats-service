package com.app.statsservice.service;

import com.app.statsservice.model.entities.Player;
import com.app.statsservice.model.response.PlayerResponse;
import com.app.statsservice.model.response.PlayersByTeamResponse;
import com.app.statsservice.repository.PlayersRepository;
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

  private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  public PlayersService(PlayersRepository playersRepository) {
    this.playersRepository = playersRepository;
  }

  public PlayersByTeamResponse findPlayersByTeam(Long teamId) {
    List<Player> playerList = playersRepository.findPlayersByTeamId(teamId).get();
    PlayersByTeamResponse response = new PlayersByTeamResponse();
    List<PlayerResponse> playersResponseList = new ArrayList<>();
    for(Player player : playerList) {
      playersResponseList.add(buildPlayer(player));
    }
    response.setPlayerList(playersResponseList);
    return response;
  }

  private PlayerResponse buildPlayer(Player player) {
    PlayerResponse playerResponse = new PlayerResponse();
    playerResponse.setPlayerName(player.getName());
    playerResponse.setDateOfBirth(dateFormat.format(player.getDateOfBirth()));
    playerResponse.setDocumentNumber(player.getDocumentNumber());
    playerResponse.setStatus(player.getStatus());
    return playerResponse;
  }
}
