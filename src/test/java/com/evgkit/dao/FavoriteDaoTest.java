package com.evgkit.dao;

import com.evgkit.Application;
import com.evgkit.domain.Favorite;
import com.evgkit.domain.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@DatabaseSetup("classpath:favorites.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class FavoriteDaoTest {
    @Autowired
    private FavoriteDao dao;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setId(1L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));
    }

    @Test
    public void findAll_ShouldReturnTwoFavorites() {
        assertThat(dao.findAll(), hasSize(3));
    }

    @Test
    public void saveForCurrentUser_ShouldPersistEntity() {
        String placeId = "Checl";

        Favorite favorite = new Favorite.FavoriteBuilder().withPlaceId(placeId).build();
        dao.saveForCurrentUser(favorite);

        assertThat(dao.findByPlaceId(placeId), notNullValue(Favorite.class));
    }
}