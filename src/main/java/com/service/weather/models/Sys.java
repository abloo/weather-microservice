package com.service.weather.models;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Sys {
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
