package com.voufinal.event.model;

import com.voufinal.event.entity.Event;
import com.voufinal.event.entity.Item;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EventItemId implements Serializable {
    private Event event;

    private Item item;

    // equals() and hashCode()
}