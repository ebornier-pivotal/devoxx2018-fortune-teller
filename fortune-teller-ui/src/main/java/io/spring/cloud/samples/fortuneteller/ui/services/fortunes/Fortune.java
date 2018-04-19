package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

public class Fortune {
    private String id;
    private String text;

    public Fortune() {
    }

    public Fortune(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
