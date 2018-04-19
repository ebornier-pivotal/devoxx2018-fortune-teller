package io.spring.cloud.samples.fortuneteller.fortuneservice.domain;


import org.springframework.data.annotation.Id;

public class Fortune {

    @Id
    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return "Thursday : " + text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
