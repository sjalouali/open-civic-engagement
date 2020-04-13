package com.oce.app.service.impl;

import com.oce.app.service.OrganisationService;
import com.oce.app.domain.Organisation;
import com.oce.app.repository.OrganisationRepository;
import com.oce.app.service.dto.OrganisationDTO;
import com.oce.app.service.mapper.OrganisationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Organisation}.
 */
@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {

    private final Logger log = LoggerFactory.getLogger(OrganisationServiceImpl.class);

    private final OrganisationRepository organisationRepository;

    private final OrganisationMapper organisationMapper;

    public OrganisationServiceImpl(OrganisationRepository organisationRepository, OrganisationMapper organisationMapper) {
        this.organisationRepository = organisationRepository;
        this.organisationMapper = organisationMapper;
    }

    /**
     * Save a organisation.
     *
     * @param organisationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrganisationDTO save(OrganisationDTO organisationDTO) {
        log.debug("Request to save Organisation : {}", organisationDTO);
        Organisation organisation = organisationMapper.toEntity(organisationDTO);
        organisation = organisationRepository.save(organisation);
        return organisationMapper.toDto(organisation);
    }

    /**
     * Get all the organisations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrganisationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Organisations");
        return organisationRepository.findAll(pageable)
            .map(organisationMapper::toDto);
    }

    /**
     * Get one organisation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganisationDTO> findOne(Long id) {
        log.debug("Request to get Organisation : {}", id);
        return organisationRepository.findById(id)
            .map(organisationMapper::toDto);
    }

    /**
     * Delete the organisation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organisation : {}", id);
        organisationRepository.deleteById(id);
    }
}
