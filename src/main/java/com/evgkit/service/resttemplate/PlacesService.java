package com.evgkit.service.resttemplate;

import com.evgkit.service.dto.geocoding.PlacesResult;

public interface PlacesService {
    PlacesResult findByPlaceId(String placeId);
}
