package com.nasa.rover.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    private Long id;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;

    public Photo(final Long id, final String imgSrc, final String earthDate) {
        this.id = id;
        this.imgSrc = imgSrc;
        this.earthDate = earthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(final String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(final String earthDate) {
        this.earthDate = earthDate;
    }
}
