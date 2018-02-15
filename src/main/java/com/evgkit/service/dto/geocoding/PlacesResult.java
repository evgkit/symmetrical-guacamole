package com.evgkit.service.dto.geocoding;

import com.evgkit.service.dto.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlacesResult extends Dto {
    @JsonProperty("place_id")
    private String placeId;

    private Geometry geometry;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}