package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
