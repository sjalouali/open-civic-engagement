package com.oce.app.service;

import com.oce.app.service.dto.CommentOceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.oce.app.domain.CommentOce}.
 */
public interface CommentOceService {

    /**
     * Save a commentOce.
     *
     * @param commentOceDTO the entity to save.
     * @return the persisted entity.
     */
    CommentOceDTO save(CommentOceDTO commentOceDTO);

    /**
     * Get all the commentOces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommentOceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" commentOce.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentOceDTO> findOne(Long id);

    /**
     * Delete the "id" commentOce.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
