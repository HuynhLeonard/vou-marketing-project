package com.voufinal.session_service.mapper;

import com.voufinal.session_service.dto.SessionDto;
import com.voufinal.session_service.entity.SessionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RecordMapper.class})
public interface SessionMapper {
    SessionEntity toSession(SessionDto sessionDto);

    SessionDto toSessionDto(SessionEntity sessionEntity);
}
