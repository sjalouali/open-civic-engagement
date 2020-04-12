package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrganisationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organisation} and its DTO {@link OrganisationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganisationMapper extends EntityMapper<OrganisationDTO, Organisation> {


    @Mapping(target = "actionServices", ignore = true)
    @Mapping(target = "removeActionService", ignore = true)
    Organisation toEntity(OrganisationDTO organisationDTO);

    default Organisation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organisation organisation = new Organisation();
        organisation.setId(id);
        return organisation;
    }
}
