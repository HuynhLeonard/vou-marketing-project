package com.voufinal.gameservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Option {
    String id;
    String key;
    String value;

    @JsonCreator
    public Option(@JsonProperty("id") String id,
                  @JsonProperty("key") String key,
                  @JsonProperty("value") String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    // Getters
    public String getId() { return id; }
    public String getKey() { return key; }
    public String getValue() { return value; }

    @Override
    public String toString() {
        return "Option{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
