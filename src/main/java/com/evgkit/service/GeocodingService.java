package com.evgkit.service;

import com.evgkit.service.dto.geocoding.GeocodingResult;

public interface GeocodingService {
    GeocodingResult findBySearchTerm(String q);
}