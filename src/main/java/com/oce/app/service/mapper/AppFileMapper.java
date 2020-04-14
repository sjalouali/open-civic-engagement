package com.oce.app.service.mapper;


import com.oce.app.domain.*;
import com.oce.app.service.dto.AppFileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppFile} and its DTO {@link AppFileDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommentOceMapper.class, ActionServiceMapper.class})
public interface AppFileMapper extends EntityMapper<AppFileDTO, AppFile> {

    @Mapping(source = "commentOce.id", target = "commentOceId")
    @Mapping(source = "actionService.id", target = "actionServiceId")
    AppFileDTO toDto(AppFile appFile);

    @Mapping(source = "commentOceId", target = "commentOce")
    @Mapping(source = "actionServiceId", target = "actionService")
    AppFile toEntity(AppFileDTO appFileDTO);

    default AppFile fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppFile appFile = new AppFile();
        appFile.setId(id);
        return appFile;
    }
}
