package com.evgkit.service;

import com.evgkit.dao.FavoriteDao;
import com.evgkit.domain.Favorite;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class FavoriteServiceTest {
    @Mock
    private FavoriteDao dao;

    @InjectMocks
    private FavoriteService service = new FavoriteServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo() throws Exception {
        List<Favorite> favoriteList = Arrays.asList(
            new Favorite(),
            new Favorite()
        );
        Mockito.when(dao.findAll()).thenReturn(favoriteList);

        assertEquals("findAll should return two favorites", 2, service.findAll().size());
        Mockito.verify(dao).findAll();
    }

    @Test
    public void findById_ShouldReturnOne() throws Exception {
        Mockito.when(dao.findOne(1L)).thenReturn(new Favorite());
        assertThat(
            service.findById(1L),
            Matchers.instanceOf(Favorite.class)
        );
        Mockito.verify(dao).findOne(1L);
    }

    @Test(expected = FavoriteNotFoundException.class)
    public void findById_ShouldThrowFavoriteNotFoundException() throws Exception {
        Mockito.when(dao.findOne(1L)).thenReturn(null);
        service.findById(1L);
        Mockito.verify(dao).findOne(1L);
    }
}