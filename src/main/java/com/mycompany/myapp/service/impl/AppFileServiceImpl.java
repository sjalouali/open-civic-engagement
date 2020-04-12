package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AppFileService;
import com.mycompany.myapp.domain.AppFile;
import com.mycompany.myapp.repository.AppFileRepository;
import com.mycompany.myapp.service.dto.AppFileDTO;
import com.mycompany.myapp.service.mapper.AppFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AppFile}.
 */
@Service
@Transactional
public class AppFileServiceImpl implements AppFileService {

    private final Logger log = LoggerFactory.getLogger(AppFileServiceImpl.class);

    private final AppFileRepository appFileRepository;

    private final AppFileMapper appFileMapper;

    public AppFileServiceImpl(AppFileRepository appFileRepository, AppFileMapper appFileMapper) {
        this.appFileRepository = appFileRepository;
        this.appFileMapper = appFileMapper;
    }

    /**
     * Save a appFile.
     *
     * @param appFileDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AppFileDTO save(AppFileDTO appFileDTO) {
        log.debug("Request to save AppFile : {}", appFileDTO);
        AppFile appFile = appFileMapper.toEntity(appFileDTO);
        appFile = appFileRepository.save(appFile);
        return appFileMapper.toDto(appFile);
    }

    /**
     * Get all the appFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppFiles");
        return appFileRepository.findAll(pageable)
            .map(appFileMapper::toDto);
    }

    /**
     * Get one appFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AppFileDTO> findOne(Long id) {
        log.debug("Request to get AppFile : {}", id);
        return appFileRepository.findById(id)
            .map(appFileMapper::toDto);
    }

    /**
     * Delete the appFile by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppFile : {}", id);
        appFileRepository.deleteById(id);
    }
}
