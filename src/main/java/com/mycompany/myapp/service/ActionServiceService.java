package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ActionServiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ActionService}.
 */
public interface ActionServiceService {

    /**
     * Save a actionService.
     *
     * @param actionServiceDTO the entity to save.
     * @return the persisted entity.
     */
    ActionServiceDTO save(ActionServiceDTO actionServiceDTO);

    /**
     * Get all the actionServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActionServiceDTO> findAll(Pageable pageable);

    /**
     * Get all the actionServices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ActionServiceDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" actionService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActionServiceDTO> findOne(Long id);

    /**
     * Delete the "id" actionService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
