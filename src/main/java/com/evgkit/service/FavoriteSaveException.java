package com.evgkit.service;

public class FavoriteSaveException extends RuntimeException {
    public FavoriteSaveException() {
        super("Could not save favorite.");
    }
}
