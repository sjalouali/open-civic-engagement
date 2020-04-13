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

import com.oce.app.domain.ActionService;
import com.oce.app.domain.*; // for static metamodels
import com.oce.app.repository.ActionServiceRepository;
import com.oce.app.service.dto.ActionServiceCriteria;
import com.oce.app.service.dto.ActionServiceDTO;
import com.oce.app.service.mapper.ActionServiceMapper;

/**
 * Service for executing complex queries for {@link ActionService} entities in the database.
 * The main input is a {@link ActionServiceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActionServiceDTO} or a {@link Page} of {@link ActionServiceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActionServiceQueryService extends QueryService<ActionService> {

    private final Logger log = LoggerFactory.getLogger(ActionServiceQueryService.class);

    private final ActionServiceRepository actionServiceRepository;

    private final ActionServiceMapper actionServiceMapper;

    public ActionServiceQueryService(ActionServiceRepository actionServiceRepository, ActionServiceMapper actionServiceMapper) {
        this.actionServiceRepository = actionServiceRepository;
        this.actionServiceMapper = actionServiceMapper;
    }

    /**
     * Return a {@link List} of {@link ActionServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActionServiceDTO> findByCriteria(ActionServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ActionService> specification = createSpecification(criteria);
        return actionServiceMapper.toDto(actionServiceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActionServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActionServiceDTO> findByCriteria(ActionServiceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ActionService> specification = createSpecification(criteria);
        return actionServiceRepository.findAll(specification, page)
            .map(actionServiceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActionServiceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ActionService> specification = createSpecification(criteria);
        return actionServiceRepository.count(specification);
    }

    /**
     * Function to convert {@link ActionServiceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ActionService> createSpecification(ActionServiceCriteria criteria) {
        Specification<ActionService> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ActionService_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ActionService_.name));
            }
            if (criteria.getNature() != null) {
                specification = specification.and(buildSpecification(criteria.getNature(), ActionService_.nature));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), ActionService_.amount));
            }
            if (criteria.getBeneficiaryNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBeneficiaryNumber(), ActionService_.beneficiaryNumber));
            }
            if (criteria.getVolunteerNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVolunteerNumber(), ActionService_.volunteerNumber));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), ActionService_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), ActionService_.lastName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), ActionService_.email));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), ActionService_.phoneNumber));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), ActionService_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), ActionService_.endDate));
            }
            if (criteria.getLabelAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLabelAdresse(), ActionService_.labelAdresse));
            }
            if (criteria.getStreetNumberAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStreetNumberAdresse(), ActionService_.streetNumberAdresse));
            }
            if (criteria.getRouteAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRouteAdresse(), ActionService_.routeAdresse));
            }
            if (criteria.getLocality() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocality(), ActionService_.locality));
            }
            if (criteria.getCounty() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCounty(), ActionService_.county));
            }
            if (criteria.getCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountry(), ActionService_.country));
            }
            if (criteria.getPostalCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostalCode(), ActionService_.postalCode));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLatitude(), ActionService_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLongitude(), ActionService_.longitude));
            }
            if (criteria.getTypeServiceId() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeServiceId(),
                    root -> root.join(ActionService_.typeService, JoinType.LEFT).get(TypeService_.id)));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(ActionService_.users, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getOrganisationId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganisationId(),
                    root -> root.join(ActionService_.organisation, JoinType.LEFT).get(Organisation_.id)));
            }
        }
        return specification;
    }
}
