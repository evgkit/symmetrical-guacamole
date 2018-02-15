package com.evgkit.service;

import com.evgkit.service.dto.geocoding.Location;
import com.evgkit.service.dto.weather.Weather;

public interface WeatherService {
    Weather findByLocation(Location location);
}