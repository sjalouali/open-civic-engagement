package com.oce.app.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.oce.app.domain.Organisation;
import com.oce.app.domain.*; // for static metamodels
import com.oce.app.repository.OrganisationRepository;
import com.oce.app.service.dto.OrganisationCriteria;
import com.oce.app.service.dto.OrganisationDTO;
import com.oce.app.service.mapper.OrganisationMapper;

/**
 * Service for executing complex queries for {@link Organisation} entities in the database.
 * The main input is a {@link OrganisationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrganisationDTO} or a {@link Page} of {@link OrganisationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrganisationQueryService extends QueryService<Organisation> {

    private final Logger log = LoggerFactory.getLogger(OrganisationQueryService.class);

    private final OrganisationRepository organisationRepository;

    private final OrganisationMapper organisationMapper;

    public OrganisationQueryService(OrganisationRepository organisationRepository, OrganisationMapper organisationMapper) {
        this.organisationRepository = organisationRepository;
        this.organisationMapper = organisationMapper;
    }

    /**
     * Return a {@link List} of {@link OrganisationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrganisationDTO> findByCriteria(OrganisationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Organisation> specification = createSpecification(criteria);
        return organisationMapper.toDto(organisationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrganisationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganisationDTO> findByCriteria(OrganisationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Organisation> specification = createSpecification(criteria);
        return organisationRepository.findAll(specification, page)
            .map(organisationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrganisationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Organisation> specification = createSpecification(criteria);
        return organisationRepository.count(specification);
    }

    /**
     * Function to convert {@link OrganisationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Organisation> createSpecification(OrganisationCriteria criteria) {
        Specification<Organisation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Organisation_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildSpecification(criteria.getUuid(), Organisation_.uuid));
            }
            if (criteria.getUuidEntity() != null) {
                specification = specification.and(buildSpecification(criteria.getUuidEntity(), Organisation_.uuidEntity));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Organisation_.name));
            }
            if (criteria.getLegalId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLegalId(), Organisation_.legalId));
            }
            if (criteria.getAccreditation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccreditation(), Organisation_.accreditation));
            }
            if (criteria.getLegalStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getLegalStatus(), Organisation_.legalStatus));
            }
            if (criteria.getActionServiceId() != null) {
                specification = specification.and(buildSpecification(criteria.getActionServiceId(),
                    root -> root.join(Organisation_.actionServices, JoinType.LEFT).get(ActionService_.id)));
            }
        }
        return specification;
    }
}
