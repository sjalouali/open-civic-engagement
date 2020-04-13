package com.oce.app.web.rest;

import com.oce.app.service.AppFileService;
import com.oce.app.web.rest.errors.BadRequestAlertException;
import com.oce.app.service.dto.AppFileDTO;
import com.oce.app.service.dto.AppFileCriteria;
import com.oce.app.service.AppFileQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.oce.app.domain.AppFile}.
 */
@RestController
@RequestMapping("/api")
public class AppFileResource {

    private final Logger log = LoggerFactory.getLogger(AppFileResource.class);

    private static final String ENTITY_NAME = "appFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppFileService appFileService;

    private final AppFileQueryService appFileQueryService;

    public AppFileResource(AppFileService appFileService, AppFileQueryService appFileQueryService) {
        this.appFileService = appFileService;
        this.appFileQueryService = appFileQueryService;
    }

    /**
     * {@code POST  /app-files} : Create a new appFile.
     *
     * @param appFileDTO the appFileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appFileDTO, or with status {@code 400 (Bad Request)} if the appFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-files")
    public ResponseEntity<AppFileDTO> createAppFile(@Valid @RequestBody AppFileDTO appFileDTO) throws URISyntaxException {
        log.debug("REST request to save AppFile : {}", appFileDTO);
        if (appFileDTO.getId() != null) {
            throw new BadRequestAlertException("A new appFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppFileDTO result = appFileService.save(appFileDTO);
        return ResponseEntity.created(new URI("/api/app-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-files} : Updates an existing appFile.
     *
     * @param appFileDTO the appFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appFileDTO,
     * or with status {@code 400 (Bad Request)} if the appFileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-files")
    public ResponseEntity<AppFileDTO> updateAppFile(@Valid @RequestBody AppFileDTO appFileDTO) throws URISyntaxException {
        log.debug("REST request to update AppFile : {}", appFileDTO);
        if (appFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppFileDTO result = appFileService.save(appFileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appFileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /app-files} : get all the appFiles.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appFiles in body.
     */
    @GetMapping("/app-files")
    public ResponseEntity<List<AppFileDTO>> getAllAppFiles(AppFileCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AppFiles by criteria: {}", criteria);
        Page<AppFileDTO> page = appFileQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /app-files/count} : count all the appFiles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/app-files/count")
    public ResponseEntity<Long> countAppFiles(AppFileCriteria criteria) {
        log.debug("REST request to count AppFiles by criteria: {}", criteria);
        return ResponseEntity.ok().body(appFileQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /app-files/:id} : get the "id" appFile.
     *
     * @param id the id of the appFileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appFileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-files/{id}")
    public ResponseEntity<AppFileDTO> getAppFile(@PathVariable Long id) {
        log.debug("REST request to get AppFile : {}", id);
        Optional<AppFileDTO> appFileDTO = appFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appFileDTO);
    }

    /**
     * {@code DELETE  /app-files/:id} : delete the "id" appFile.
     *
     * @param id the id of the appFileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-files/{id}")
    public ResponseEntity<Void> deleteAppFile(@PathVariable Long id) {
        log.debug("REST request to delete AppFile : {}", id);
        appFileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
