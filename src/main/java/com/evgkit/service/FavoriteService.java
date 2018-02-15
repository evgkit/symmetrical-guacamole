package com.evgkit.service;

import com.evgkit.domain.Favorite;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findAll();

    Favorite findById(Long id) throws FavoriteNotFoundException;

    void save(Favorite favorite) throws FavoriteSaveException;

    void delete(Long id) throws FavoriteDeleteException;
}