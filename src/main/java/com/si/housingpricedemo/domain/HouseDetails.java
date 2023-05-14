package com.si.housingpricedemo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HouseDetails(
        @JsonProperty("Area") int area,
        @JsonProperty("Room") int room,
        @JsonProperty("Lon") float lon,
        @JsonProperty("Lat") float lat,
        @JsonProperty("ZipCode") int zipCode,
        @JsonProperty("AreaCode") String areaCode,
        @JsonProperty("isCityCenter") boolean isCityCenter) {
}
