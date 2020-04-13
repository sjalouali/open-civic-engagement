package com.oce.app.service.mapper;


import com.oce.app.domain.*;
import com.oce.app.service.dto.CommentOceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommentOce} and its DTO {@link CommentOceDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ActionServiceMapper.class})
public interface CommentOceMapper extends EntityMapper<CommentOceDTO, CommentOce> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "actionService.id", target = "actionServiceId")
    CommentOceDTO toDto(CommentOce commentOce);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "actionServiceId", target = "actionService")
    CommentOce toEntity(CommentOceDTO commentOceDTO);

    default CommentOce fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommentOce commentOce = new CommentOce();
        commentOce.setId(id);
        return commentOce;
    }
}
