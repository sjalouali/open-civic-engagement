package com.oce.app.web.rest;

import com.oce.app.service.CommentOceService;
import com.oce.app.web.rest.errors.BadRequestAlertException;
import com.oce.app.service.dto.CommentOceDTO;
import com.oce.app.service.dto.CommentOceCriteria;
import com.oce.app.service.CommentOceQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.oce.app.domain.CommentOce}.
 */
@RestController
@RequestMapping("/api")
public class CommentOceResource {

    private final Logger log = LoggerFactory.getLogger(CommentOceResource.class);

    private static final String ENTITY_NAME = "commentOce";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommentOceService commentOceService;

    private final CommentOceQueryService commentOceQueryService;

    public CommentOceResource(CommentOceService commentOceService, CommentOceQueryService commentOceQueryService) {
        this.commentOceService = commentOceService;
        this.commentOceQueryService = commentOceQueryService;
    }

    /**
     * {@code POST  /comment-oces} : Create a new commentOce.
     *
     * @param commentOceDTO the commentOceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commentOceDTO, or with status {@code 400 (Bad Request)} if the commentOce has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comment-oces")
    public ResponseEntity<CommentOceDTO> createCommentOce(@RequestBody CommentOceDTO commentOceDTO) throws URISyntaxException {
        log.debug("REST request to save CommentOce : {}", commentOceDTO);
        if (commentOceDTO.getId() != null) {
            throw new BadRequestAlertException("A new commentOce cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommentOceDTO result = commentOceService.save(commentOceDTO);
        return ResponseEntity.created(new URI("/api/comment-oces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comment-oces} : Updates an existing commentOce.
     *
     * @param commentOceDTO the commentOceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commentOceDTO,
     * or with status {@code 400 (Bad Request)} if the commentOceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commentOceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comment-oces")
    public ResponseEntity<CommentOceDTO> updateCommentOce(@RequestBody CommentOceDTO commentOceDTO) throws URISyntaxException {
        log.debug("REST request to update CommentOce : {}", commentOceDTO);
        if (commentOceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommentOceDTO result = commentOceService.save(commentOceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commentOceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comment-oces} : get all the commentOces.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commentOces in body.
     */
    @GetMapping("/comment-oces")
    public ResponseEntity<List<CommentOceDTO>> getAllCommentOces(CommentOceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CommentOces by criteria: {}", criteria);
        Page<CommentOceDTO> page = commentOceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comment-oces/count} : count all the commentOces.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/comment-oces/count")
    public ResponseEntity<Long> countCommentOces(CommentOceCriteria criteria) {
        log.debug("REST request to count CommentOces by criteria: {}", criteria);
        return ResponseEntity.ok().body(commentOceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /comment-oces/:id} : get the "id" commentOce.
     *
     * @param id the id of the commentOceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commentOceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comment-oces/{id}")
    public ResponseEntity<CommentOceDTO> getCommentOce(@PathVariable Long id) {
        log.debug("REST request to get CommentOce : {}", id);
        Optional<CommentOceDTO> commentOceDTO = commentOceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commentOceDTO);
    }

    /**
     * {@code DELETE  /comment-oces/:id} : delete the "id" commentOce.
     *
     * @param id the id of the commentOceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comment-oces/{id}")
    public ResponseEntity<Void> deleteCommentOce(@PathVariable Long id) {
        log.debug("REST request to delete CommentOce : {}", id);
        commentOceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
