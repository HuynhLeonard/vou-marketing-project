package com.voufinal.event.model;

import com.voufinal.event.entity.Event;
import com.voufinal.event.entity.Game;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EventGameId implements Serializable {
    private Event event;

    private Long game_id;

    // equals() and hashCode()
}