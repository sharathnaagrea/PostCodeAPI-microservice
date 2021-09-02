package com.postcode.au.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PostCodeRecordDto {

    @NotEmpty(message = "Postcode is required.")
    @Pattern(regexp = "([0-9]{4})", message = "The Postcode is invalid.")
    private String postCode;

    @NotEmpty(message = "Suburb is required.")
    private String suburb;

    @NotEmpty(message = "State is required.")
    private String state;

    public PostCodeRecordDto() {
        super();
    }

    public PostCodeRecordDto(String postCode, String suburb, String state) {
        super();
        this.postCode = postCode;
        this.suburb = suburb;
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
