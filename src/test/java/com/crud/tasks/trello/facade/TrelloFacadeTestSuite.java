package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrelloFacadeTestSuite {

    @Autowired
    private TrelloFacade trelloFacade;

    @Test
    public void testTrelloFacadeFetchTrelloBoards() {
        //Given

        //When
        List<TrelloBoardDto> trelloBoards = trelloFacade.fetchTrelloBoards();

        /*        for (TrelloBoardDto trelloBoardDto : trelloBoards) {
            System.out.println(trelloBoardDto.getName());
        }*/

        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("Kodilla Application", trelloBoards.get(0).getName());
    }

    @Test
    public void testTrelloFacadeCreateCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Task testowy next",
                "Opis testowy",
                "",
                "612efa3cf20acb0b4e0f20e2");

        //Then
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);

        //When
        assertEquals("Task testowy next", createdTrelloCardDto.getName());
    }

}
