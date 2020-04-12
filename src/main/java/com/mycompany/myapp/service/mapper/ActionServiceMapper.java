package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ActionServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ActionService} and its DTO {@link ActionServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeServiceMapper.class, UserMapper.class, OrganisationMapper.class})
public interface ActionServiceMapper extends EntityMapper<ActionServiceDTO, ActionService> {

    @Mapping(source = "typeService.id", target = "typeServiceId")
    @Mapping(source = "organisation.id", target = "organisationId")
    ActionServiceDTO toDto(ActionService actionService);

    @Mapping(source = "typeServiceId", target = "typeService")
    @Mapping(target = "removeUser", ignore = true)
    @Mapping(source = "organisationId", target = "organisation")
    ActionService toEntity(ActionServiceDTO actionServiceDTO);

    default ActionService fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActionService actionService = new ActionService();
        actionService.setId(id);
        return actionService;
    }
}
