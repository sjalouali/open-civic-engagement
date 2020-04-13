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

import com.oce.app.domain.TypeService;
import com.oce.app.domain.*; // for static metamodels
import com.oce.app.repository.TypeServiceRepository;
import com.oce.app.service.dto.TypeServiceCriteria;
import com.oce.app.service.dto.TypeServiceDTO;
import com.oce.app.service.mapper.TypeServiceMapper;

/**
 * Service for executing complex queries for {@link TypeService} entities in the database.
 * The main input is a {@link TypeServiceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TypeServiceDTO} or a {@link Page} of {@link TypeServiceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeServiceQueryService extends QueryService<TypeService> {

    private final Logger log = LoggerFactory.getLogger(TypeServiceQueryService.class);

    private final TypeServiceRepository typeServiceRepository;

    private final TypeServiceMapper typeServiceMapper;

    public TypeServiceQueryService(TypeServiceRepository typeServiceRepository, TypeServiceMapper typeServiceMapper) {
        this.typeServiceRepository = typeServiceRepository;
        this.typeServiceMapper = typeServiceMapper;
    }

    /**
     * Return a {@link List} of {@link TypeServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeServiceDTO> findByCriteria(TypeServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeService> specification = createSpecification(criteria);
        return typeServiceMapper.toDto(typeServiceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TypeServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeServiceDTO> findByCriteria(TypeServiceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeService> specification = createSpecification(criteria);
        return typeServiceRepository.findAll(specification, page)
            .map(typeServiceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeServiceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeService> specification = createSpecification(criteria);
        return typeServiceRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeServiceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TypeService> createSpecification(TypeServiceCriteria criteria) {
        Specification<TypeService> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TypeService_.id));
            }
            if (criteria.getNameShort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameShort(), TypeService_.nameShort));
            }
            if (criteria.getNameLong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameLong(), TypeService_.nameLong));
            }
            if (criteria.getTypeServiceColor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeServiceColor(), TypeService_.typeServiceColor));
            }
        }
        return specification;
    }
}
