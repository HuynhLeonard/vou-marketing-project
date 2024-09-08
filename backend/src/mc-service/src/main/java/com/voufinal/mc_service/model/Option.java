package com.voufinal.mc_service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
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

    @Override
    public String toString() {
        return "Option{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
