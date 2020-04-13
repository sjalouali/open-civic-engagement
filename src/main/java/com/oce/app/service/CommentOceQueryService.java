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

import com.oce.app.domain.CommentOce;
import com.oce.app.domain.*; // for static metamodels
import com.oce.app.repository.CommentOceRepository;
import com.oce.app.service.dto.CommentOceCriteria;
import com.oce.app.service.dto.CommentOceDTO;
import com.oce.app.service.mapper.CommentOceMapper;

/**
 * Service for executing complex queries for {@link CommentOce} entities in the database.
 * The main input is a {@link CommentOceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CommentOceDTO} or a {@link Page} of {@link CommentOceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CommentOceQueryService extends QueryService<CommentOce> {

    private final Logger log = LoggerFactory.getLogger(CommentOceQueryService.class);

    private final CommentOceRepository commentOceRepository;

    private final CommentOceMapper commentOceMapper;

    public CommentOceQueryService(CommentOceRepository commentOceRepository, CommentOceMapper commentOceMapper) {
        this.commentOceRepository = commentOceRepository;
        this.commentOceMapper = commentOceMapper;
    }

    /**
     * Return a {@link List} of {@link CommentOceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommentOceDTO> findByCriteria(CommentOceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CommentOce> specification = createSpecification(criteria);
        return commentOceMapper.toDto(commentOceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommentOceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommentOceDTO> findByCriteria(CommentOceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CommentOce> specification = createSpecification(criteria);
        return commentOceRepository.findAll(specification, page)
            .map(commentOceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommentOceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CommentOce> specification = createSpecification(criteria);
        return commentOceRepository.count(specification);
    }

    /**
     * Function to convert {@link CommentOceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CommentOce> createSpecification(CommentOceCriteria criteria) {
        Specification<CommentOce> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CommentOce_.id));
            }
            if (criteria.getCommentDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommentDate(), CommentOce_.commentDate));
            }
            if (criteria.getUuidOce() != null) {
                specification = specification.and(buildSpecification(criteria.getUuidOce(), CommentOce_.uuidOce));
            }
            if (criteria.getUuidOrganisation() != null) {
                specification = specification.and(buildSpecification(criteria.getUuidOrganisation(), CommentOce_.uuidOrganisation));
            }
            if (criteria.getUuidEntity() != null) {
                specification = specification.and(buildSpecification(criteria.getUuidEntity(), CommentOce_.uuidEntity));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), CommentOce_.archived));
            }
            if (criteria.getDeleted() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleted(), CommentOce_.deleted));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(CommentOce_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getActionServiceId() != null) {
                specification = specification.and(buildSpecification(criteria.getActionServiceId(),
                    root -> root.join(CommentOce_.actionService, JoinType.LEFT).get(ActionService_.id)));
            }
        }
        return specification;
    }
}
