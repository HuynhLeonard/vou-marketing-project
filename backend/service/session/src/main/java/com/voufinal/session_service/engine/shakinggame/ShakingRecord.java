package com.voufinal.session_service.engine.shakinggame;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.voufinal.session_service.engine.Record;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ShakingRecord extends Record implements Serializable {
    private int turns;
}
