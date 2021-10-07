package com.crud.tasks.controller;


import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

/*
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
        });
*/

        System.out.println(Optional.ofNullable(trelloBoards.stream()
                            .filter(dto -> !dto.getId().isEmpty() && !dto.getName().isEmpty())
                            .filter(dto -> dto.getName().contains("Kodilla"))
                            .map(dto -> dto.getId() + " " + dto.getName())
                            .collect(Collectors.joining("\n")))
                            .filter(s -> s.length() > 0)
                            .orElse("Nie znaleziono")
        );

    }
}
