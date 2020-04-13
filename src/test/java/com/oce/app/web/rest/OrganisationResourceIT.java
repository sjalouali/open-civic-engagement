package com.oce.app.web.rest;

import com.oce.app.OpenCivicEngagementApp;
import com.oce.app.domain.Organisation;
import com.oce.app.domain.ActionService;
import com.oce.app.repository.OrganisationRepository;
import com.oce.app.service.OrganisationService;
import com.oce.app.service.dto.OrganisationDTO;
import com.oce.app.service.mapper.OrganisationMapper;
import com.oce.app.service.dto.OrganisationCriteria;
import com.oce.app.service.OrganisationQueryService;

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
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.oce.app.domain.enumeration.LegalStatus;
/**
 * Integration tests for the {@link OrganisationResource} REST controller.
 */
@SpringBootTest(classes = OpenCivicEngagementApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrganisationResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final UUID DEFAULT_UUID_ENTITY = UUID.randomUUID();
    private static final UUID UPDATED_UUID_ENTITY = UUID.randomUUID();

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCREDITATION = "AAAAAAAAAA";
    private static final String UPDATED_ACCREDITATION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_INFORMATION = "BBBBBBBBBB";

    private static final LegalStatus DEFAULT_LEGAL_STATUS = LegalStatus.NON_PROFIT_ORGANIZATION;
    private static final LegalStatus UPDATED_LEGAL_STATUS = LegalStatus.PUBLIC_ORGANIZATION;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private OrganisationMapper organisationMapper;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private OrganisationQueryService organisationQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganisationMockMvc;

    private Organisation organisation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organisation createEntity(EntityManager em) {
        Organisation organisation = new Organisation()
            .uuid(DEFAULT_UUID)
            .uuidEntity(DEFAULT_UUID_ENTITY)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .legalId(DEFAULT_LEGAL_ID)
            .accreditation(DEFAULT_ACCREDITATION)
            .additionalInformation(DEFAULT_ADDITIONAL_INFORMATION)
            .legalStatus(DEFAULT_LEGAL_STATUS);
        return organisation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organisation createUpdatedEntity(EntityManager em) {
        Organisation organisation = new Organisation()
            .uuid(UPDATED_UUID)
            .uuidEntity(UPDATED_UUID_ENTITY)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .legalId(UPDATED_LEGAL_ID)
            .accreditation(UPDATED_ACCREDITATION)
            .additionalInformation(UPDATED_ADDITIONAL_INFORMATION)
            .legalStatus(UPDATED_LEGAL_STATUS);
        return organisation;
    }

    @BeforeEach
    public void initTest() {
        organisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganisation() throws Exception {
        int databaseSizeBeforeCreate = organisationRepository.findAll().size();

        // Create the Organisation
        OrganisationDTO organisationDTO = organisationMapper.toDto(organisation);
        restOrganisationMockMvc.perform(post("/api/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organisationDTO)))
            .andExpect(status().isCreated());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeCreate + 1);
        Organisation testOrganisation = organisationList.get(organisationList.size() - 1);
        assertThat(testOrganisation.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testOrganisation.getUuidEntity()).isEqualTo(DEFAULT_UUID_ENTITY);
        assertThat(testOrganisation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrganisation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrganisation.getLegalId()).isEqualTo(DEFAULT_LEGAL_ID);
        assertThat(testOrganisation.getAccreditation()).isEqualTo(DEFAULT_ACCREDITATION);
        assertThat(testOrganisation.getAdditionalInformation()).isEqualTo(DEFAULT_ADDITIONAL_INFORMATION);
        assertThat(testOrganisation.getLegalStatus()).isEqualTo(DEFAULT_LEGAL_STATUS);
    }

    @Test
    @Transactional
    public void createOrganisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organisationRepository.findAll().size();

        // Create the Organisation with an existing ID
        organisation.setId(1L);
        OrganisationDTO organisationDTO = organisationMapper.toDto(organisation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganisationMockMvc.perform(post("/api/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organisationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organisationRepository.findAll().size();
        // set the field null
        organisation.setName(null);

        // Create the Organisation, which fails.
        OrganisationDTO organisationDTO = organisationMapper.toDto(organisation);

        restOrganisationMockMvc.perform(post("/api/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organisationDTO)))
            .andExpect(status().isBadRequest());

        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLegalStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = organisationRepository.findAll().size();
        // set the field null
        organisation.setLegalStatus(null);

        // Create the Organisation, which fails.
        OrganisationDTO organisationDTO = organisationMapper.toDto(organisation);

        restOrganisationMockMvc.perform(post("/api/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organisationDTO)))
            .andExpect(status().isBadRequest());

        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganisations() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList
        restOrganisationMockMvc.perform(get("/api/organisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].uuidEntity").value(hasItem(DEFAULT_UUID_ENTITY.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].legalId").value(hasItem(DEFAULT_LEGAL_ID)))
            .andExpect(jsonPath("$.[*].accreditation").value(hasItem(DEFAULT_ACCREDITATION)))
            .andExpect(jsonPath("$.[*].additionalInformation").value(hasItem(DEFAULT_ADDITIONAL_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].legalStatus").value(hasItem(DEFAULT_LEGAL_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getOrganisation() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get the organisation
        restOrganisationMockMvc.perform(get("/api/organisations/{id}", organisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organisation.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.uuidEntity").value(DEFAULT_UUID_ENTITY.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.legalId").value(DEFAULT_LEGAL_ID))
            .andExpect(jsonPath("$.accreditation").value(DEFAULT_ACCREDITATION))
            .andExpect(jsonPath("$.additionalInformation").value(DEFAULT_ADDITIONAL_INFORMATION.toString()))
            .andExpect(jsonPath("$.legalStatus").value(DEFAULT_LEGAL_STATUS.toString()));
    }


    @Test
    @Transactional
    public void getOrganisationsByIdFiltering() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        Long id = organisation.getId();

        defaultOrganisationShouldBeFound("id.equals=" + id);
        defaultOrganisationShouldNotBeFound("id.notEquals=" + id);

        defaultOrganisationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOrganisationShouldNotBeFound("id.greaterThan=" + id);

        defaultOrganisationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOrganisationShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOrganisationsByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuid equals to DEFAULT_UUID
        defaultOrganisationShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the organisationList where uuid equals to UPDATED_UUID
        defaultOrganisationShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuid not equals to DEFAULT_UUID
        defaultOrganisationShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

        // Get all the organisationList where uuid not equals to UPDATED_UUID
        defaultOrganisationShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultOrganisationShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the organisationList where uuid equals to UPDATED_UUID
        defaultOrganisationShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuid is not null
        defaultOrganisationShouldBeFound("uuid.specified=true");

        // Get all the organisationList where uuid is null
        defaultOrganisationShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidEntityIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuidEntity equals to DEFAULT_UUID_ENTITY
        defaultOrganisationShouldBeFound("uuidEntity.equals=" + DEFAULT_UUID_ENTITY);

        // Get all the organisationList where uuidEntity equals to UPDATED_UUID_ENTITY
        defaultOrganisationShouldNotBeFound("uuidEntity.equals=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidEntityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuidEntity not equals to DEFAULT_UUID_ENTITY
        defaultOrganisationShouldNotBeFound("uuidEntity.notEquals=" + DEFAULT_UUID_ENTITY);

        // Get all the organisationList where uuidEntity not equals to UPDATED_UUID_ENTITY
        defaultOrganisationShouldBeFound("uuidEntity.notEquals=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidEntityIsInShouldWork() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuidEntity in DEFAULT_UUID_ENTITY or UPDATED_UUID_ENTITY
        defaultOrganisationShouldBeFound("uuidEntity.in=" + DEFAULT_UUID_ENTITY + "," + UPDATED_UUID_ENTITY);

        // Get all the organisationList where uuidEntity equals to UPDATED_UUID_ENTITY
        defaultOrganisationShouldNotBeFound("uuidEntity.in=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByUuidEntityIsNullOrNotNull() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where uuidEntity is not null
        defaultOrganisationShouldBeFound("uuidEntity.specified=true");

        // Get all the organisationList where uuidEntity is null
        defaultOrganisationShouldNotBeFound("uuidEntity.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrganisationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where name equals to DEFAULT_NAME
        defaultOrganisationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the organisationList where name equals to UPDATED_NAME
        defaultOrganisationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where name not equals to DEFAULT_NAME
        defaultOrganisationShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the organisationList where name not equals to UPDATED_NAME
        defaultOrganisationShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultOrganisationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the organisationList where name equals to UPDATED_NAME
        defaultOrganisationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where name is not null
        defaultOrganisationShouldBeFound("name.specified=true");

        // Get all the organisationList where name is null
        defaultOrganisationShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrganisationsByNameContainsSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where name contains DEFAULT_NAME
        defaultOrganisationShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the organisationList where name contains UPDATED_NAME
        defaultOrganisationShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where name does not contain DEFAULT_NAME
        defaultOrganisationShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the organisationList where name does not contain UPDATED_NAME
        defaultOrganisationShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllOrganisationsByLegalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalId equals to DEFAULT_LEGAL_ID
        defaultOrganisationShouldBeFound("legalId.equals=" + DEFAULT_LEGAL_ID);

        // Get all the organisationList where legalId equals to UPDATED_LEGAL_ID
        defaultOrganisationShouldNotBeFound("legalId.equals=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalId not equals to DEFAULT_LEGAL_ID
        defaultOrganisationShouldNotBeFound("legalId.notEquals=" + DEFAULT_LEGAL_ID);

        // Get all the organisationList where legalId not equals to UPDATED_LEGAL_ID
        defaultOrganisationShouldBeFound("legalId.notEquals=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalIdIsInShouldWork() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalId in DEFAULT_LEGAL_ID or UPDATED_LEGAL_ID
        defaultOrganisationShouldBeFound("legalId.in=" + DEFAULT_LEGAL_ID + "," + UPDATED_LEGAL_ID);

        // Get all the organisationList where legalId equals to UPDATED_LEGAL_ID
        defaultOrganisationShouldNotBeFound("legalId.in=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalId is not null
        defaultOrganisationShouldBeFound("legalId.specified=true");

        // Get all the organisationList where legalId is null
        defaultOrganisationShouldNotBeFound("legalId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrganisationsByLegalIdContainsSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalId contains DEFAULT_LEGAL_ID
        defaultOrganisationShouldBeFound("legalId.contains=" + DEFAULT_LEGAL_ID);

        // Get all the organisationList where legalId contains UPDATED_LEGAL_ID
        defaultOrganisationShouldNotBeFound("legalId.contains=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalIdNotContainsSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalId does not contain DEFAULT_LEGAL_ID
        defaultOrganisationShouldNotBeFound("legalId.doesNotContain=" + DEFAULT_LEGAL_ID);

        // Get all the organisationList where legalId does not contain UPDATED_LEGAL_ID
        defaultOrganisationShouldBeFound("legalId.doesNotContain=" + UPDATED_LEGAL_ID);
    }


    @Test
    @Transactional
    public void getAllOrganisationsByAccreditationIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where accreditation equals to DEFAULT_ACCREDITATION
        defaultOrganisationShouldBeFound("accreditation.equals=" + DEFAULT_ACCREDITATION);

        // Get all the organisationList where accreditation equals to UPDATED_ACCREDITATION
        defaultOrganisationShouldNotBeFound("accreditation.equals=" + UPDATED_ACCREDITATION);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByAccreditationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where accreditation not equals to DEFAULT_ACCREDITATION
        defaultOrganisationShouldNotBeFound("accreditation.notEquals=" + DEFAULT_ACCREDITATION);

        // Get all the organisationList where accreditation not equals to UPDATED_ACCREDITATION
        defaultOrganisationShouldBeFound("accreditation.notEquals=" + UPDATED_ACCREDITATION);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByAccreditationIsInShouldWork() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where accreditation in DEFAULT_ACCREDITATION or UPDATED_ACCREDITATION
        defaultOrganisationShouldBeFound("accreditation.in=" + DEFAULT_ACCREDITATION + "," + UPDATED_ACCREDITATION);

        // Get all the organisationList where accreditation equals to UPDATED_ACCREDITATION
        defaultOrganisationShouldNotBeFound("accreditation.in=" + UPDATED_ACCREDITATION);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByAccreditationIsNullOrNotNull() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where accreditation is not null
        defaultOrganisationShouldBeFound("accreditation.specified=true");

        // Get all the organisationList where accreditation is null
        defaultOrganisationShouldNotBeFound("accreditation.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrganisationsByAccreditationContainsSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where accreditation contains DEFAULT_ACCREDITATION
        defaultOrganisationShouldBeFound("accreditation.contains=" + DEFAULT_ACCREDITATION);

        // Get all the organisationList where accreditation contains UPDATED_ACCREDITATION
        defaultOrganisationShouldNotBeFound("accreditation.contains=" + UPDATED_ACCREDITATION);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByAccreditationNotContainsSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where accreditation does not contain DEFAULT_ACCREDITATION
        defaultOrganisationShouldNotBeFound("accreditation.doesNotContain=" + DEFAULT_ACCREDITATION);

        // Get all the organisationList where accreditation does not contain UPDATED_ACCREDITATION
        defaultOrganisationShouldBeFound("accreditation.doesNotContain=" + UPDATED_ACCREDITATION);
    }


    @Test
    @Transactional
    public void getAllOrganisationsByLegalStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalStatus equals to DEFAULT_LEGAL_STATUS
        defaultOrganisationShouldBeFound("legalStatus.equals=" + DEFAULT_LEGAL_STATUS);

        // Get all the organisationList where legalStatus equals to UPDATED_LEGAL_STATUS
        defaultOrganisationShouldNotBeFound("legalStatus.equals=" + UPDATED_LEGAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalStatus not equals to DEFAULT_LEGAL_STATUS
        defaultOrganisationShouldNotBeFound("legalStatus.notEquals=" + DEFAULT_LEGAL_STATUS);

        // Get all the organisationList where legalStatus not equals to UPDATED_LEGAL_STATUS
        defaultOrganisationShouldBeFound("legalStatus.notEquals=" + UPDATED_LEGAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalStatusIsInShouldWork() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalStatus in DEFAULT_LEGAL_STATUS or UPDATED_LEGAL_STATUS
        defaultOrganisationShouldBeFound("legalStatus.in=" + DEFAULT_LEGAL_STATUS + "," + UPDATED_LEGAL_STATUS);

        // Get all the organisationList where legalStatus equals to UPDATED_LEGAL_STATUS
        defaultOrganisationShouldNotBeFound("legalStatus.in=" + UPDATED_LEGAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrganisationsByLegalStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList where legalStatus is not null
        defaultOrganisationShouldBeFound("legalStatus.specified=true");

        // Get all the organisationList where legalStatus is null
        defaultOrganisationShouldNotBeFound("legalStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrganisationsByActionServiceIsEqualToSomething() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);
        ActionService actionService = ActionServiceResourceIT.createEntity(em);
        em.persist(actionService);
        em.flush();
        organisation.addActionService(actionService);
        organisationRepository.saveAndFlush(organisation);
        Long actionServiceId = actionService.getId();

        // Get all the organisationList where actionService equals to actionServiceId
        defaultOrganisationShouldBeFound("actionServiceId.equals=" + actionServiceId);

        // Get all the organisationList where actionService equals to actionServiceId + 1
        defaultOrganisationShouldNotBeFound("actionServiceId.equals=" + (actionServiceId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOrganisationShouldBeFound(String filter) throws Exception {
        restOrganisationMockMvc.perform(get("/api/organisations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].uuidEntity").value(hasItem(DEFAULT_UUID_ENTITY.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].legalId").value(hasItem(DEFAULT_LEGAL_ID)))
            .andExpect(jsonPath("$.[*].accreditation").value(hasItem(DEFAULT_ACCREDITATION)))
            .andExpect(jsonPath("$.[*].additionalInformation").value(hasItem(DEFAULT_ADDITIONAL_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].legalStatus").value(hasItem(DEFAULT_LEGAL_STATUS.toString())));

        // Check, that the count call also returns 1
        restOrganisationMockMvc.perform(get("/api/organisations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOrganisationShouldNotBeFound(String filter) throws Exception {
        restOrganisationMockMvc.perform(get("/api/organisations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrganisationMockMvc.perform(get("/api/organisations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrganisation() throws Exception {
        // Get the organisation
        restOrganisationMockMvc.perform(get("/api/organisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganisation() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        int databaseSizeBeforeUpdate = organisationRepository.findAll().size();

        // Update the organisation
        Organisation updatedOrganisation = organisationRepository.findById(organisation.getId()).get();
        // Disconnect from session so that the updates on updatedOrganisation are not directly saved in db
        em.detach(updatedOrganisation);
        updatedOrganisation
            .uuid(UPDATED_UUID)
            .uuidEntity(UPDATED_UUID_ENTITY)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .legalId(UPDATED_LEGAL_ID)
            .accreditation(UPDATED_ACCREDITATION)
            .additionalInformation(UPDATED_ADDITIONAL_INFORMATION)
            .legalStatus(UPDATED_LEGAL_STATUS);
        OrganisationDTO organisationDTO = organisationMapper.toDto(updatedOrganisation);

        restOrganisationMockMvc.perform(put("/api/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organisationDTO)))
            .andExpect(status().isOk());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeUpdate);
        Organisation testOrganisation = organisationList.get(organisationList.size() - 1);
        assertThat(testOrganisation.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testOrganisation.getUuidEntity()).isEqualTo(UPDATED_UUID_ENTITY);
        assertThat(testOrganisation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrganisation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrganisation.getLegalId()).isEqualTo(UPDATED_LEGAL_ID);
        assertThat(testOrganisation.getAccreditation()).isEqualTo(UPDATED_ACCREDITATION);
        assertThat(testOrganisation.getAdditionalInformation()).isEqualTo(UPDATED_ADDITIONAL_INFORMATION);
        assertThat(testOrganisation.getLegalStatus()).isEqualTo(UPDATED_LEGAL_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganisation() throws Exception {
        int databaseSizeBeforeUpdate = organisationRepository.findAll().size();

        // Create the Organisation
        OrganisationDTO organisationDTO = organisationMapper.toDto(organisation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganisationMockMvc.perform(put("/api/organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organisationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganisation() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        int databaseSizeBeforeDelete = organisationRepository.findAll().size();

        // Delete the organisation
        restOrganisationMockMvc.perform(delete("/api/organisations/{id}", organisation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
