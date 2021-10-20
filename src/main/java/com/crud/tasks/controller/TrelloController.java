package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloService trelloService;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto>  getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
/*
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
        });
*/
/*
        System.out.println(Optional.ofNullable(trelloBoards.stream()
                            .filter(dto -> Objects.nonNull(dto.getId()) && Objects.nonNull(dto.getName()))
                            .filter(dto -> dto.getName().contains("Kodilla"))
                            .map(dto -> dto.getId() + " " + dto.getName())
                            .collect(Collectors.joining("\n")))
                            .filter(s -> s.length() > 0)
                            .orElse("Nie znaleziono")
        );
        System.out.println();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList -> {
                System.out.println(
                        trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()
                );
            });
        });
*/
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}
