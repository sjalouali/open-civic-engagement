package com.mycompany.myapp.service;

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

import com.mycompany.myapp.domain.AppFile;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.AppFileRepository;
import com.mycompany.myapp.service.dto.AppFileCriteria;
import com.mycompany.myapp.service.dto.AppFileDTO;
import com.mycompany.myapp.service.mapper.AppFileMapper;

/**
 * Service for executing complex queries for {@link AppFile} entities in the database.
 * The main input is a {@link AppFileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AppFileDTO} or a {@link Page} of {@link AppFileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AppFileQueryService extends QueryService<AppFile> {

    private final Logger log = LoggerFactory.getLogger(AppFileQueryService.class);

    private final AppFileRepository appFileRepository;

    private final AppFileMapper appFileMapper;

    public AppFileQueryService(AppFileRepository appFileRepository, AppFileMapper appFileMapper) {
        this.appFileRepository = appFileRepository;
        this.appFileMapper = appFileMapper;
    }

    /**
     * Return a {@link List} of {@link AppFileDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AppFileDTO> findByCriteria(AppFileCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AppFile> specification = createSpecification(criteria);
        return appFileMapper.toDto(appFileRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AppFileDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AppFileDTO> findByCriteria(AppFileCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AppFile> specification = createSpecification(criteria);
        return appFileRepository.findAll(specification, page)
            .map(appFileMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AppFileCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AppFile> specification = createSpecification(criteria);
        return appFileRepository.count(specification);
    }

    /**
     * Function to convert {@link AppFileCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AppFile> createSpecification(AppFileCriteria criteria) {
        Specification<AppFile> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AppFile_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), AppFile_.name));
            }
            if (criteria.getPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPath(), AppFile_.path));
            }
            if (criteria.getFileSize() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileSize(), AppFile_.fileSize));
            }
            if (criteria.getExtention() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExtention(), AppFile_.extention));
            }
            if (criteria.getCommentId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommentId(),
                    root -> root.join(AppFile_.comment, JoinType.LEFT).get(Comment_.id)));
            }
            if (criteria.getActionServiceId() != null) {
                specification = specification.and(buildSpecification(criteria.getActionServiceId(),
                    root -> root.join(AppFile_.actionService, JoinType.LEFT).get(ActionService_.id)));
            }
        }
        return specification;
    }
}
