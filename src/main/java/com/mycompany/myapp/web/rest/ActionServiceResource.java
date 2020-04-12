package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ActionServiceService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ActionServiceDTO;
import com.mycompany.myapp.service.dto.ActionServiceCriteria;
import com.mycompany.myapp.service.ActionServiceQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ActionService}.
 */
@RestController
@RequestMapping("/api")
public class ActionServiceResource {

    private final Logger log = LoggerFactory.getLogger(ActionServiceResource.class);

    private static final String ENTITY_NAME = "actionService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActionServiceService actionServiceService;

    private final ActionServiceQueryService actionServiceQueryService;

    public ActionServiceResource(ActionServiceService actionServiceService, ActionServiceQueryService actionServiceQueryService) {
        this.actionServiceService = actionServiceService;
        this.actionServiceQueryService = actionServiceQueryService;
    }

    /**
     * {@code POST  /action-services} : Create a new actionService.
     *
     * @param actionServiceDTO the actionServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new actionServiceDTO, or with status {@code 400 (Bad Request)} if the actionService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/action-services")
    public ResponseEntity<ActionServiceDTO> createActionService(@Valid @RequestBody ActionServiceDTO actionServiceDTO) throws URISyntaxException {
        log.debug("REST request to save ActionService : {}", actionServiceDTO);
        if (actionServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new actionService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActionServiceDTO result = actionServiceService.save(actionServiceDTO);
        return ResponseEntity.created(new URI("/api/action-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /action-services} : Updates an existing actionService.
     *
     * @param actionServiceDTO the actionServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actionServiceDTO,
     * or with status {@code 400 (Bad Request)} if the actionServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the actionServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/action-services")
    public ResponseEntity<ActionServiceDTO> updateActionService(@Valid @RequestBody ActionServiceDTO actionServiceDTO) throws URISyntaxException {
        log.debug("REST request to update ActionService : {}", actionServiceDTO);
        if (actionServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActionServiceDTO result = actionServiceService.save(actionServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, actionServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /action-services} : get all the actionServices.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of actionServices in body.
     */
    @GetMapping("/action-services")
    public ResponseEntity<List<ActionServiceDTO>> getAllActionServices(ActionServiceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ActionServices by criteria: {}", criteria);
        Page<ActionServiceDTO> page = actionServiceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /action-services/count} : count all the actionServices.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/action-services/count")
    public ResponseEntity<Long> countActionServices(ActionServiceCriteria criteria) {
        log.debug("REST request to count ActionServices by criteria: {}", criteria);
        return ResponseEntity.ok().body(actionServiceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /action-services/:id} : get the "id" actionService.
     *
     * @param id the id of the actionServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actionServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/action-services/{id}")
    public ResponseEntity<ActionServiceDTO> getActionService(@PathVariable Long id) {
        log.debug("REST request to get ActionService : {}", id);
        Optional<ActionServiceDTO> actionServiceDTO = actionServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actionServiceDTO);
    }

    /**
     * {@code DELETE  /action-services/:id} : delete the "id" actionService.
     *
     * @param id the id of the actionServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/action-services/{id}")
    public ResponseEntity<Void> deleteActionService(@PathVariable Long id) {
        log.debug("REST request to delete ActionService : {}", id);
        actionServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
