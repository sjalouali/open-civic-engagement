package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AppFileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppFile} and its DTO {@link AppFileDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommentMapper.class, ActionServiceMapper.class})
public interface AppFileMapper extends EntityMapper<AppFileDTO, AppFile> {

    @Mapping(source = "comment.id", target = "commentId")
    @Mapping(source = "actionService.id", target = "actionServiceId")
    AppFileDTO toDto(AppFile appFile);

    @Mapping(source = "commentId", target = "comment")
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
