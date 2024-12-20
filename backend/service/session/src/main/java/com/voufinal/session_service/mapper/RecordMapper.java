package com.voufinal.session_service.mapper;
import com.voufinal.session_service.dto.RecordDto;
import com.voufinal.session_service.dto.quizgame.QuizRecordDto;
import com.voufinal.session_service.dto.shakinggame.ShakingRecordDto;
import com.voufinal.session_service.entity.RecordEntity;
import com.voufinal.session_service.entity.quizgame.QuizRecordEntity;
import com.voufinal.session_service.entity.shakinggame.ShakingRecordEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    QuizRecordDto toQuizRecordDto(QuizRecordEntity quizRecordEntity);

    QuizRecordEntity toQuizRecordEntity(QuizRecordDto quizRecordDto);

    List<QuizRecordDto> toQuizRecordDtoList(List<QuizRecordEntity> quizRecordEntities);

    List<QuizRecordEntity> toQuizRecordEntityList(List<QuizRecordDto> quizRecordDtos);

    ShakingRecordDto toShakingRecordDto(ShakingRecordEntity quizRecordEntity);

    ShakingRecordEntity toShakingRecordEntity(ShakingRecordDto quizRecordDto);

    List<ShakingRecordDto> toShakingRecordDtoList(List<ShakingRecordEntity> quizRecordEntities);

    List<ShakingRecordEntity> toShakingRecordEntityList(List<ShakingRecordDto> quizRecordDtos);

    default RecordDto mapRecordEntityToRecordDto(RecordEntity entity) {
        if (entity instanceof QuizRecordEntity) {
            return toQuizRecordDto((QuizRecordEntity) entity);
        } else if (entity instanceof ShakingRecordEntity) {
            return toShakingRecordDto((ShakingRecordEntity) entity);
        }
        // Handle the default case or throw an exception
        throw new RuntimeException("Failed to map record entity to record dto");
    }

    default RecordEntity mapRecordDtoToRecordEntity(RecordDto dto) {
        if (dto instanceof QuizRecordDto) {
            return toQuizRecordEntity((QuizRecordDto) dto);
        } else if (dto instanceof ShakingRecordDto) {
            return toShakingRecordEntity((ShakingRecordDto) dto);
        }
        // Handle the default case or throw an exception
        throw new RuntimeException("Failed to map record dto to record entity");
    }

}
