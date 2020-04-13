package com.oce.app.service.mapper;


import com.oce.app.domain.*;
import com.oce.app.service.dto.TypeServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeService} and its DTO {@link TypeServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeServiceMapper extends EntityMapper<TypeServiceDTO, TypeService> {



    default TypeService fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeService typeService = new TypeService();
        typeService.setId(id);
        return typeService;
    }
}
