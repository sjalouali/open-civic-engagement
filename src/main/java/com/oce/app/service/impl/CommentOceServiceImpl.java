package com.oce.app.service.impl;

import com.oce.app.service.CommentOceService;
import com.oce.app.domain.CommentOce;
import com.oce.app.repository.CommentOceRepository;
import com.oce.app.service.dto.CommentOceDTO;
import com.oce.app.service.mapper.CommentOceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommentOce}.
 */
@Service
@Transactional
public class CommentOceServiceImpl implements CommentOceService {

    private final Logger log = LoggerFactory.getLogger(CommentOceServiceImpl.class);

    private final CommentOceRepository commentOceRepository;

    private final CommentOceMapper commentOceMapper;

    public CommentOceServiceImpl(CommentOceRepository commentOceRepository, CommentOceMapper commentOceMapper) {
        this.commentOceRepository = commentOceRepository;
        this.commentOceMapper = commentOceMapper;
    }

    /**
     * Save a commentOce.
     *
     * @param commentOceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommentOceDTO save(CommentOceDTO commentOceDTO) {
        log.debug("Request to save CommentOce : {}", commentOceDTO);
        CommentOce commentOce = commentOceMapper.toEntity(commentOceDTO);
        commentOce = commentOceRepository.save(commentOce);
        return commentOceMapper.toDto(commentOce);
    }

    /**
     * Get all the commentOces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommentOceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommentOces");
        return commentOceRepository.findAll(pageable)
            .map(commentOceMapper::toDto);
    }

    /**
     * Get one commentOce by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommentOceDTO> findOne(Long id) {
        log.debug("Request to get CommentOce : {}", id);
        return commentOceRepository.findById(id)
            .map(commentOceMapper::toDto);
    }

    /**
     * Delete the commentOce by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommentOce : {}", id);
        commentOceRepository.deleteById(id);
    }
}
