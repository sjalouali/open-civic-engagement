package com.oce.app.service;

import com.oce.app.service.dto.AppFileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.oce.app.domain.AppFile}.
 */
public interface AppFileService {

    /**
     * Save a appFile.
     *
     * @param appFileDTO the entity to save.
     * @return the persisted entity.
     */
    AppFileDTO save(AppFileDTO appFileDTO);

    /**
     * Get all the appFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppFileDTO> findAll(Pageable pageable);

    /**
     * Get the "id" appFile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppFileDTO> findOne(Long id);

    /**
     * Delete the "id" appFile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
