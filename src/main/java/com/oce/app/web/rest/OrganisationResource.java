package com.oce.app.web.rest;

import com.oce.app.service.OrganisationService;
import com.oce.app.web.rest.errors.BadRequestAlertException;
import com.oce.app.service.dto.OrganisationDTO;
import com.oce.app.service.dto.OrganisationCriteria;
import com.oce.app.service.OrganisationQueryService;

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
 * REST controller for managing {@link com.oce.app.domain.Organisation}.
 */
@RestController
@RequestMapping("/api")
public class OrganisationResource {

    private final Logger log = LoggerFactory.getLogger(OrganisationResource.class);

    private static final String ENTITY_NAME = "organisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganisationService organisationService;

    private final OrganisationQueryService organisationQueryService;

    public OrganisationResource(OrganisationService organisationService, OrganisationQueryService organisationQueryService) {
        this.organisationService = organisationService;
        this.organisationQueryService = organisationQueryService;
    }

    /**
     * {@code POST  /organisations} : Create a new organisation.
     *
     * @param organisationDTO the organisationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organisationDTO, or with status {@code 400 (Bad Request)} if the organisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organisations")
    public ResponseEntity<OrganisationDTO> createOrganisation(@Valid @RequestBody OrganisationDTO organisationDTO) throws URISyntaxException {
        log.debug("REST request to save Organisation : {}", organisationDTO);
        if (organisationDTO.getId() != null) {
            throw new BadRequestAlertException("A new organisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganisationDTO result = organisationService.save(organisationDTO);
        return ResponseEntity.created(new URI("/api/organisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organisations} : Updates an existing organisation.
     *
     * @param organisationDTO the organisationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organisationDTO,
     * or with status {@code 400 (Bad Request)} if the organisationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organisationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organisations")
    public ResponseEntity<OrganisationDTO> updateOrganisation(@Valid @RequestBody OrganisationDTO organisationDTO) throws URISyntaxException {
        log.debug("REST request to update Organisation : {}", organisationDTO);
        if (organisationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganisationDTO result = organisationService.save(organisationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organisationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organisations} : get all the organisations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisations in body.
     */
    @GetMapping("/organisations")
    public ResponseEntity<List<OrganisationDTO>> getAllOrganisations(OrganisationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Organisations by criteria: {}", criteria);
        Page<OrganisationDTO> page = organisationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /organisations/count} : count all the organisations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/organisations/count")
    public ResponseEntity<Long> countOrganisations(OrganisationCriteria criteria) {
        log.debug("REST request to count Organisations by criteria: {}", criteria);
        return ResponseEntity.ok().body(organisationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /organisations/:id} : get the "id" organisation.
     *
     * @param id the id of the organisationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organisationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organisations/{id}")
    public ResponseEntity<OrganisationDTO> getOrganisation(@PathVariable Long id) {
        log.debug("REST request to get Organisation : {}", id);
        Optional<OrganisationDTO> organisationDTO = organisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organisationDTO);
    }

    /**
     * {@code DELETE  /organisations/:id} : delete the "id" organisation.
     *
     * @param id the id of the organisationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organisations/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        log.debug("REST request to delete Organisation : {}", id);
        organisationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
