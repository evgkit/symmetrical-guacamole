package com.evgkit.web.controller;

import com.evgkit.domain.Favorite;
import com.evgkit.service.FavoriteNotFoundException;
import com.evgkit.service.FavoriteService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private FavoriteController controller;

    @Mock
    private FavoriteService service;

    @Before
    public void setup() {
        // MockitoAnnotations.initMocks(this); // Initialize according to annotations
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void index_ShouldIncludeFavoritesInModel() throws Exception {
        // Arrange the mock behavior
        List<Favorite> favoriteList = Arrays.asList(
                new Favorite.FavoriteBuilder(1L).withAddress("Chicago").withPlaceId("chicago1").build(),
                new Favorite.FavoriteBuilder(2L).withAddress("Omaha").withPlaceId("omaha1").build()
        );
        Mockito.when(service.findAll()).thenReturn(favoriteList);

        // Act (perform the MVC request) and Assert results
        mockMvc.perform(get("/favorites"))
            .andExpect(status().isOk())
            .andExpect(view().name("favorite/index"))
            .andExpect(model().attribute("favorites", favoriteList));
        Mockito.verify(service).findAll();
    }

    @Test
    public void add_ShouldRedirectToNewFavorite() throws Exception {
        // Arrange the mock behavior
        Mockito.doAnswer(invocation -> {
            Favorite f = (Favorite) invocation.getArguments()[0];
            f.setId(1L);
            return null;
        }).when(service).save(Mockito.any(Favorite.class));

        // Act (perform the MVC request) and Assert results
        mockMvc.perform(post("/favorites")
            .param("formattedAddress", "chicago, il")
            .param("placeId", "windycity")
        ).andExpect(redirectedUrl("/favorites/1"));
        Mockito.verify(service).save(Mockito.any(Favorite.class));
    }

    @Test
    public void detail_ShouldErrorOnNotFound() throws Exception {
        // Arrange the mock behavior
        Mockito.when(service.findById(1L)).thenThrow(FavoriteNotFoundException.class);

        // Act (perform the MVC request) and Assert results
        mockMvc.perform(get("/favorites/1"))
            .andExpect(view().name("error"))
            .andExpect(model().attribute("ex", Matchers.instanceOf(FavoriteNotFoundException.class)));

        Mockito.verify(service).findById(1L);
    }
}