package com.voufinal.event.model;

import com.voufinal.event.entity.Event;
import com.voufinal.event.entity.Brand;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EventBrandId implements Serializable {
    private Event event;

    private String brand_id;

    // equals() and hashCode()
}