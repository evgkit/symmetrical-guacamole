package com.evgkit.service.dto.geocoding;

import com.evgkit.service.dto.Dto;

public class Geometry extends Dto {
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
