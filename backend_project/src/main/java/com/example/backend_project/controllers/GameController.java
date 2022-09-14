package com.example.backend_project.controllers;

import com.example.backend_project.models.Battle;
import com.example.backend_project.models.Game;
import com.example.backend_project.models.Player;
import com.example.backend_project.models.Reply;
import com.example.backend_project.services.BattleService;
import com.example.backend_project.services.GameService;
import com.example.backend_project.services.MonsterService;
import com.example.backend_project.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    BattleService battleService;

    @Autowired
    MonsterService monsterService;

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> games;
        games = gameService.getGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping(value = "/combat")
    public ResponseEntity<Reply> battleOutcome(@RequestParam long playerId,@RequestParam long battleId){
        Player player = playerService.getPlayerById(playerId).get();
        Battle battle = battleService.getBattleById(battleId).get();
        return new ResponseEntity<>(battleService.combatOutcome(player,battle), HttpStatus.OK);
    }

    @


}