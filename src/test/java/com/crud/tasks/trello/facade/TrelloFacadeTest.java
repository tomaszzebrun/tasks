package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

//import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloList =
                Stream.of(new TrelloListDto("1", "test_list", false))
                .collect(Collectors.toList());
        List<TrelloBoardDto> trelloBoards =
                Stream.of(new TrelloBoardDto("1", "test", trelloList))
                .collect(Collectors.toList());
        List<TrelloList> mappedTrelloLists =
                Stream.of(new TrelloList("1", "test_list", false))
                .collect(Collectors.toList());
        List<TrelloBoard> mappedTrelloBoards =
                Stream.of(new TrelloBoard("1", "test", mappedTrelloLists))
                .collect(Collectors.toList());

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloList =
                Stream.of(new TrelloListDto("1", "test_list", false))
                .collect(Collectors.toList());
        List<TrelloBoardDto> trelloBoards =
                Stream.of(new TrelloBoardDto("1", "test", trelloList))
                .collect(Collectors.toList());
        List<TrelloList> mappedTrelloLists =
                Stream.of(new TrelloList("1", "test_list", false))
                .collect(Collectors.toList());
        List<TrelloBoard> mappedTrelloBoards =
                Stream.of(new TrelloBoard("1", "test", mappedTrelloLists))
                .collect(Collectors.toList());

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto -> {
                    assertThat(trelloBoardDto.getId()).isEqualTo("1");
                    assertThat(trelloBoardDto.getName()).isEqualTo("test");

                    trelloBoardDto.getLists().forEach(trelloListDto -> {
                                assertThat(trelloListDto.getId()).isEqualTo("1");
                                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                                assertThat(trelloListDto.isClosed()).isFalse();
                    });
        });

    }

}
