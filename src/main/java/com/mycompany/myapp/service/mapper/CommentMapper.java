package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "user.id", target = "userId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "userId", target = "user")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
