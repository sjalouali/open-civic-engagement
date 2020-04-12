package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ActionServiceService;
import com.mycompany.myapp.domain.ActionService;
import com.mycompany.myapp.repository.ActionServiceRepository;
import com.mycompany.myapp.service.dto.ActionServiceDTO;
import com.mycompany.myapp.service.mapper.ActionServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ActionService}.
 */
@Service
@Transactional
public class ActionServiceServiceImpl implements ActionServiceService {

    private final Logger log = LoggerFactory.getLogger(ActionServiceServiceImpl.class);

    private final ActionServiceRepository actionServiceRepository;

    private final ActionServiceMapper actionServiceMapper;

    public ActionServiceServiceImpl(ActionServiceRepository actionServiceRepository, ActionServiceMapper actionServiceMapper) {
        this.actionServiceRepository = actionServiceRepository;
        this.actionServiceMapper = actionServiceMapper;
    }

    /**
     * Save a actionService.
     *
     * @param actionServiceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ActionServiceDTO save(ActionServiceDTO actionServiceDTO) {
        log.debug("Request to save ActionService : {}", actionServiceDTO);
        ActionService actionService = actionServiceMapper.toEntity(actionServiceDTO);
        actionService = actionServiceRepository.save(actionService);
        return actionServiceMapper.toDto(actionService);
    }

    /**
     * Get all the actionServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActionServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActionServices");
        return actionServiceRepository.findAll(pageable)
            .map(actionServiceMapper::toDto);
    }

    /**
     * Get all the actionServices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ActionServiceDTO> findAllWithEagerRelationships(Pageable pageable) {
        return actionServiceRepository.findAllWithEagerRelationships(pageable).map(actionServiceMapper::toDto);
    }

    /**
     * Get one actionService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActionServiceDTO> findOne(Long id) {
        log.debug("Request to get ActionService : {}", id);
        return actionServiceRepository.findOneWithEagerRelationships(id)
            .map(actionServiceMapper::toDto);
    }

    /**
     * Delete the actionService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActionService : {}", id);
        actionServiceRepository.deleteById(id);
    }
}
