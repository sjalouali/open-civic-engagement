package com.oce.app.web.rest;

import com.oce.app.OpenCivicEngagementApp;
import com.oce.app.domain.CommentOce;
import com.oce.app.domain.User;
import com.oce.app.domain.ActionService;
import com.oce.app.repository.CommentOceRepository;
import com.oce.app.service.CommentOceService;
import com.oce.app.service.dto.CommentOceDTO;
import com.oce.app.service.mapper.CommentOceMapper;
import com.oce.app.service.dto.CommentOceCriteria;
import com.oce.app.service.CommentOceQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CommentOceResource} REST controller.
 */
@SpringBootTest(classes = OpenCivicEngagementApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CommentOceResourceIT {

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_COMMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COMMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final UUID DEFAULT_UUID_OCE = UUID.randomUUID();
    private static final UUID UPDATED_UUID_OCE = UUID.randomUUID();

    private static final UUID DEFAULT_UUID_ORGANISATION = UUID.randomUUID();
    private static final UUID UPDATED_UUID_ORGANISATION = UUID.randomUUID();

    private static final UUID DEFAULT_UUID_ENTITY = UUID.randomUUID();
    private static final UUID UPDATED_UUID_ENTITY = UUID.randomUUID();

    private static final Boolean DEFAULT_ARCHIVED = false;
    private static final Boolean UPDATED_ARCHIVED = true;

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    @Autowired
    private CommentOceRepository commentOceRepository;

    @Autowired
    private CommentOceMapper commentOceMapper;

    @Autowired
    private CommentOceService commentOceService;

    @Autowired
    private CommentOceQueryService commentOceQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommentOceMockMvc;

    private CommentOce commentOce;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommentOce createEntity(EntityManager em) {
        CommentOce commentOce = new CommentOce()
            .content(DEFAULT_CONTENT)
            .commentDate(DEFAULT_COMMENT_DATE)
            .uuidOce(DEFAULT_UUID_OCE)
            .uuidOrganisation(DEFAULT_UUID_ORGANISATION)
            .uuidEntity(DEFAULT_UUID_ENTITY)
            .archived(DEFAULT_ARCHIVED)
            .deleted(DEFAULT_DELETED);
        return commentOce;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommentOce createUpdatedEntity(EntityManager em) {
        CommentOce commentOce = new CommentOce()
            .content(UPDATED_CONTENT)
            .commentDate(UPDATED_COMMENT_DATE)
            .uuidOce(UPDATED_UUID_OCE)
            .uuidOrganisation(UPDATED_UUID_ORGANISATION)
            .uuidEntity(UPDATED_UUID_ENTITY)
            .archived(UPDATED_ARCHIVED)
            .deleted(UPDATED_DELETED);
        return commentOce;
    }

    @BeforeEach
    public void initTest() {
        commentOce = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommentOce() throws Exception {
        int databaseSizeBeforeCreate = commentOceRepository.findAll().size();

        // Create the CommentOce
        CommentOceDTO commentOceDTO = commentOceMapper.toDto(commentOce);
        restCommentOceMockMvc.perform(post("/api/comment-oces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentOceDTO)))
            .andExpect(status().isCreated());

        // Validate the CommentOce in the database
        List<CommentOce> commentOceList = commentOceRepository.findAll();
        assertThat(commentOceList).hasSize(databaseSizeBeforeCreate + 1);
        CommentOce testCommentOce = commentOceList.get(commentOceList.size() - 1);
        assertThat(testCommentOce.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testCommentOce.getCommentDate()).isEqualTo(DEFAULT_COMMENT_DATE);
        assertThat(testCommentOce.getUuidOce()).isEqualTo(DEFAULT_UUID_OCE);
        assertThat(testCommentOce.getUuidOrganisation()).isEqualTo(DEFAULT_UUID_ORGANISATION);
        assertThat(testCommentOce.getUuidEntity()).isEqualTo(DEFAULT_UUID_ENTITY);
        assertThat(testCommentOce.isArchived()).isEqualTo(DEFAULT_ARCHIVED);
        assertThat(testCommentOce.isDeleted()).isEqualTo(DEFAULT_DELETED);
    }

    @Test
    @Transactional
    public void createCommentOceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentOceRepository.findAll().size();

        // Create the CommentOce with an existing ID
        commentOce.setId(1L);
        CommentOceDTO commentOceDTO = commentOceMapper.toDto(commentOce);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentOceMockMvc.perform(post("/api/comment-oces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentOceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentOce in the database
        List<CommentOce> commentOceList = commentOceRepository.findAll();
        assertThat(commentOceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommentOces() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList
        restCommentOceMockMvc.perform(get("/api/comment-oces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commentOce.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].commentDate").value(hasItem(DEFAULT_COMMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].uuidOce").value(hasItem(DEFAULT_UUID_OCE.toString())))
            .andExpect(jsonPath("$.[*].uuidOrganisation").value(hasItem(DEFAULT_UUID_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].uuidEntity").value(hasItem(DEFAULT_UUID_ENTITY.toString())))
            .andExpect(jsonPath("$.[*].archived").value(hasItem(DEFAULT_ARCHIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCommentOce() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get the commentOce
        restCommentOceMockMvc.perform(get("/api/comment-oces/{id}", commentOce.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commentOce.getId().intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.commentDate").value(DEFAULT_COMMENT_DATE.toString()))
            .andExpect(jsonPath("$.uuidOce").value(DEFAULT_UUID_OCE.toString()))
            .andExpect(jsonPath("$.uuidOrganisation").value(DEFAULT_UUID_ORGANISATION.toString()))
            .andExpect(jsonPath("$.uuidEntity").value(DEFAULT_UUID_ENTITY.toString()))
            .andExpect(jsonPath("$.archived").value(DEFAULT_ARCHIVED.booleanValue()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
    }


    @Test
    @Transactional
    public void getCommentOcesByIdFiltering() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        Long id = commentOce.getId();

        defaultCommentOceShouldBeFound("id.equals=" + id);
        defaultCommentOceShouldNotBeFound("id.notEquals=" + id);

        defaultCommentOceShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCommentOceShouldNotBeFound("id.greaterThan=" + id);

        defaultCommentOceShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCommentOceShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCommentOcesByCommentDateIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where commentDate equals to DEFAULT_COMMENT_DATE
        defaultCommentOceShouldBeFound("commentDate.equals=" + DEFAULT_COMMENT_DATE);

        // Get all the commentOceList where commentDate equals to UPDATED_COMMENT_DATE
        defaultCommentOceShouldNotBeFound("commentDate.equals=" + UPDATED_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByCommentDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where commentDate not equals to DEFAULT_COMMENT_DATE
        defaultCommentOceShouldNotBeFound("commentDate.notEquals=" + DEFAULT_COMMENT_DATE);

        // Get all the commentOceList where commentDate not equals to UPDATED_COMMENT_DATE
        defaultCommentOceShouldBeFound("commentDate.notEquals=" + UPDATED_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByCommentDateIsInShouldWork() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where commentDate in DEFAULT_COMMENT_DATE or UPDATED_COMMENT_DATE
        defaultCommentOceShouldBeFound("commentDate.in=" + DEFAULT_COMMENT_DATE + "," + UPDATED_COMMENT_DATE);

        // Get all the commentOceList where commentDate equals to UPDATED_COMMENT_DATE
        defaultCommentOceShouldNotBeFound("commentDate.in=" + UPDATED_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByCommentDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where commentDate is not null
        defaultCommentOceShouldBeFound("commentDate.specified=true");

        // Get all the commentOceList where commentDate is null
        defaultCommentOceShouldNotBeFound("commentDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOceIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOce equals to DEFAULT_UUID_OCE
        defaultCommentOceShouldBeFound("uuidOce.equals=" + DEFAULT_UUID_OCE);

        // Get all the commentOceList where uuidOce equals to UPDATED_UUID_OCE
        defaultCommentOceShouldNotBeFound("uuidOce.equals=" + UPDATED_UUID_OCE);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOce not equals to DEFAULT_UUID_OCE
        defaultCommentOceShouldNotBeFound("uuidOce.notEquals=" + DEFAULT_UUID_OCE);

        // Get all the commentOceList where uuidOce not equals to UPDATED_UUID_OCE
        defaultCommentOceShouldBeFound("uuidOce.notEquals=" + UPDATED_UUID_OCE);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOceIsInShouldWork() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOce in DEFAULT_UUID_OCE or UPDATED_UUID_OCE
        defaultCommentOceShouldBeFound("uuidOce.in=" + DEFAULT_UUID_OCE + "," + UPDATED_UUID_OCE);

        // Get all the commentOceList where uuidOce equals to UPDATED_UUID_OCE
        defaultCommentOceShouldNotBeFound("uuidOce.in=" + UPDATED_UUID_OCE);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOceIsNullOrNotNull() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOce is not null
        defaultCommentOceShouldBeFound("uuidOce.specified=true");

        // Get all the commentOceList where uuidOce is null
        defaultCommentOceShouldNotBeFound("uuidOce.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOrganisationIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOrganisation equals to DEFAULT_UUID_ORGANISATION
        defaultCommentOceShouldBeFound("uuidOrganisation.equals=" + DEFAULT_UUID_ORGANISATION);

        // Get all the commentOceList where uuidOrganisation equals to UPDATED_UUID_ORGANISATION
        defaultCommentOceShouldNotBeFound("uuidOrganisation.equals=" + UPDATED_UUID_ORGANISATION);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOrganisationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOrganisation not equals to DEFAULT_UUID_ORGANISATION
        defaultCommentOceShouldNotBeFound("uuidOrganisation.notEquals=" + DEFAULT_UUID_ORGANISATION);

        // Get all the commentOceList where uuidOrganisation not equals to UPDATED_UUID_ORGANISATION
        defaultCommentOceShouldBeFound("uuidOrganisation.notEquals=" + UPDATED_UUID_ORGANISATION);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOrganisationIsInShouldWork() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOrganisation in DEFAULT_UUID_ORGANISATION or UPDATED_UUID_ORGANISATION
        defaultCommentOceShouldBeFound("uuidOrganisation.in=" + DEFAULT_UUID_ORGANISATION + "," + UPDATED_UUID_ORGANISATION);

        // Get all the commentOceList where uuidOrganisation equals to UPDATED_UUID_ORGANISATION
        defaultCommentOceShouldNotBeFound("uuidOrganisation.in=" + UPDATED_UUID_ORGANISATION);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidOrganisationIsNullOrNotNull() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidOrganisation is not null
        defaultCommentOceShouldBeFound("uuidOrganisation.specified=true");

        // Get all the commentOceList where uuidOrganisation is null
        defaultCommentOceShouldNotBeFound("uuidOrganisation.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidEntityIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidEntity equals to DEFAULT_UUID_ENTITY
        defaultCommentOceShouldBeFound("uuidEntity.equals=" + DEFAULT_UUID_ENTITY);

        // Get all the commentOceList where uuidEntity equals to UPDATED_UUID_ENTITY
        defaultCommentOceShouldNotBeFound("uuidEntity.equals=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidEntityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidEntity not equals to DEFAULT_UUID_ENTITY
        defaultCommentOceShouldNotBeFound("uuidEntity.notEquals=" + DEFAULT_UUID_ENTITY);

        // Get all the commentOceList where uuidEntity not equals to UPDATED_UUID_ENTITY
        defaultCommentOceShouldBeFound("uuidEntity.notEquals=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidEntityIsInShouldWork() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidEntity in DEFAULT_UUID_ENTITY or UPDATED_UUID_ENTITY
        defaultCommentOceShouldBeFound("uuidEntity.in=" + DEFAULT_UUID_ENTITY + "," + UPDATED_UUID_ENTITY);

        // Get all the commentOceList where uuidEntity equals to UPDATED_UUID_ENTITY
        defaultCommentOceShouldNotBeFound("uuidEntity.in=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUuidEntityIsNullOrNotNull() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where uuidEntity is not null
        defaultCommentOceShouldBeFound("uuidEntity.specified=true");

        // Get all the commentOceList where uuidEntity is null
        defaultCommentOceShouldNotBeFound("uuidEntity.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommentOcesByArchivedIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where archived equals to DEFAULT_ARCHIVED
        defaultCommentOceShouldBeFound("archived.equals=" + DEFAULT_ARCHIVED);

        // Get all the commentOceList where archived equals to UPDATED_ARCHIVED
        defaultCommentOceShouldNotBeFound("archived.equals=" + UPDATED_ARCHIVED);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByArchivedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where archived not equals to DEFAULT_ARCHIVED
        defaultCommentOceShouldNotBeFound("archived.notEquals=" + DEFAULT_ARCHIVED);

        // Get all the commentOceList where archived not equals to UPDATED_ARCHIVED
        defaultCommentOceShouldBeFound("archived.notEquals=" + UPDATED_ARCHIVED);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByArchivedIsInShouldWork() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where archived in DEFAULT_ARCHIVED or UPDATED_ARCHIVED
        defaultCommentOceShouldBeFound("archived.in=" + DEFAULT_ARCHIVED + "," + UPDATED_ARCHIVED);

        // Get all the commentOceList where archived equals to UPDATED_ARCHIVED
        defaultCommentOceShouldNotBeFound("archived.in=" + UPDATED_ARCHIVED);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByArchivedIsNullOrNotNull() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where archived is not null
        defaultCommentOceShouldBeFound("archived.specified=true");

        // Get all the commentOceList where archived is null
        defaultCommentOceShouldNotBeFound("archived.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommentOcesByDeletedIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where deleted equals to DEFAULT_DELETED
        defaultCommentOceShouldBeFound("deleted.equals=" + DEFAULT_DELETED);

        // Get all the commentOceList where deleted equals to UPDATED_DELETED
        defaultCommentOceShouldNotBeFound("deleted.equals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByDeletedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where deleted not equals to DEFAULT_DELETED
        defaultCommentOceShouldNotBeFound("deleted.notEquals=" + DEFAULT_DELETED);

        // Get all the commentOceList where deleted not equals to UPDATED_DELETED
        defaultCommentOceShouldBeFound("deleted.notEquals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByDeletedIsInShouldWork() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where deleted in DEFAULT_DELETED or UPDATED_DELETED
        defaultCommentOceShouldBeFound("deleted.in=" + DEFAULT_DELETED + "," + UPDATED_DELETED);

        // Get all the commentOceList where deleted equals to UPDATED_DELETED
        defaultCommentOceShouldNotBeFound("deleted.in=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllCommentOcesByDeletedIsNullOrNotNull() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        // Get all the commentOceList where deleted is not null
        defaultCommentOceShouldBeFound("deleted.specified=true");

        // Get all the commentOceList where deleted is null
        defaultCommentOceShouldNotBeFound("deleted.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommentOcesByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        commentOce.setUser(user);
        commentOceRepository.saveAndFlush(commentOce);
        Long userId = user.getId();

        // Get all the commentOceList where user equals to userId
        defaultCommentOceShouldBeFound("userId.equals=" + userId);

        // Get all the commentOceList where user equals to userId + 1
        defaultCommentOceShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllCommentOcesByActionServiceIsEqualToSomething() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);
        ActionService actionService = ActionServiceResourceIT.createEntity(em);
        em.persist(actionService);
        em.flush();
        commentOce.setActionService(actionService);
        commentOceRepository.saveAndFlush(commentOce);
        Long actionServiceId = actionService.getId();

        // Get all the commentOceList where actionService equals to actionServiceId
        defaultCommentOceShouldBeFound("actionServiceId.equals=" + actionServiceId);

        // Get all the commentOceList where actionService equals to actionServiceId + 1
        defaultCommentOceShouldNotBeFound("actionServiceId.equals=" + (actionServiceId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCommentOceShouldBeFound(String filter) throws Exception {
        restCommentOceMockMvc.perform(get("/api/comment-oces?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commentOce.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].commentDate").value(hasItem(DEFAULT_COMMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].uuidOce").value(hasItem(DEFAULT_UUID_OCE.toString())))
            .andExpect(jsonPath("$.[*].uuidOrganisation").value(hasItem(DEFAULT_UUID_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].uuidEntity").value(hasItem(DEFAULT_UUID_ENTITY.toString())))
            .andExpect(jsonPath("$.[*].archived").value(hasItem(DEFAULT_ARCHIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));

        // Check, that the count call also returns 1
        restCommentOceMockMvc.perform(get("/api/comment-oces/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCommentOceShouldNotBeFound(String filter) throws Exception {
        restCommentOceMockMvc.perform(get("/api/comment-oces?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCommentOceMockMvc.perform(get("/api/comment-oces/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCommentOce() throws Exception {
        // Get the commentOce
        restCommentOceMockMvc.perform(get("/api/comment-oces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommentOce() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        int databaseSizeBeforeUpdate = commentOceRepository.findAll().size();

        // Update the commentOce
        CommentOce updatedCommentOce = commentOceRepository.findById(commentOce.getId()).get();
        // Disconnect from session so that the updates on updatedCommentOce are not directly saved in db
        em.detach(updatedCommentOce);
        updatedCommentOce
            .content(UPDATED_CONTENT)
            .commentDate(UPDATED_COMMENT_DATE)
            .uuidOce(UPDATED_UUID_OCE)
            .uuidOrganisation(UPDATED_UUID_ORGANISATION)
            .uuidEntity(UPDATED_UUID_ENTITY)
            .archived(UPDATED_ARCHIVED)
            .deleted(UPDATED_DELETED);
        CommentOceDTO commentOceDTO = commentOceMapper.toDto(updatedCommentOce);

        restCommentOceMockMvc.perform(put("/api/comment-oces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentOceDTO)))
            .andExpect(status().isOk());

        // Validate the CommentOce in the database
        List<CommentOce> commentOceList = commentOceRepository.findAll();
        assertThat(commentOceList).hasSize(databaseSizeBeforeUpdate);
        CommentOce testCommentOce = commentOceList.get(commentOceList.size() - 1);
        assertThat(testCommentOce.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testCommentOce.getCommentDate()).isEqualTo(UPDATED_COMMENT_DATE);
        assertThat(testCommentOce.getUuidOce()).isEqualTo(UPDATED_UUID_OCE);
        assertThat(testCommentOce.getUuidOrganisation()).isEqualTo(UPDATED_UUID_ORGANISATION);
        assertThat(testCommentOce.getUuidEntity()).isEqualTo(UPDATED_UUID_ENTITY);
        assertThat(testCommentOce.isArchived()).isEqualTo(UPDATED_ARCHIVED);
        assertThat(testCommentOce.isDeleted()).isEqualTo(UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingCommentOce() throws Exception {
        int databaseSizeBeforeUpdate = commentOceRepository.findAll().size();

        // Create the CommentOce
        CommentOceDTO commentOceDTO = commentOceMapper.toDto(commentOce);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommentOceMockMvc.perform(put("/api/comment-oces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentOceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentOce in the database
        List<CommentOce> commentOceList = commentOceRepository.findAll();
        assertThat(commentOceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommentOce() throws Exception {
        // Initialize the database
        commentOceRepository.saveAndFlush(commentOce);

        int databaseSizeBeforeDelete = commentOceRepository.findAll().size();

        // Delete the commentOce
        restCommentOceMockMvc.perform(delete("/api/comment-oces/{id}", commentOce.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommentOce> commentOceList = commentOceRepository.findAll();
        assertThat(commentOceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
