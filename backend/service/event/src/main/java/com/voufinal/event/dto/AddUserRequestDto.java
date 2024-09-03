package com.voufinal.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.voufinal.event.model.NotificationInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddUserRequestDto {
    private NotificationInfo notification;
    private List<String> userIds;
}
