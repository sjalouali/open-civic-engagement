package com.oce.app.web.rest;

import com.oce.app.OpenCivicEngagementApp;
import com.oce.app.domain.TypeService;
import com.oce.app.repository.TypeServiceRepository;
import com.oce.app.service.TypeServiceService;
import com.oce.app.service.dto.TypeServiceDTO;
import com.oce.app.service.mapper.TypeServiceMapper;
import com.oce.app.service.dto.TypeServiceCriteria;
import com.oce.app.service.TypeServiceQueryService;

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

/**
 * Integration tests for the {@link TypeServiceResource} REST controller.
 */
@SpringBootTest(classes = OpenCivicEngagementApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TypeServiceResourceIT {

    private static final String DEFAULT_NAME_SHORT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SHORT = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_LONG = "AAAAAAAAAA";
    private static final String UPDATED_NAME_LONG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_SERVICE_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_SERVICE_COLOR = "BBBBBBBBBB";

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
    private TypeServiceRepository typeServiceRepository;

    @Autowired
    private TypeServiceMapper typeServiceMapper;

    @Autowired
    private TypeServiceService typeServiceService;

    @Autowired
    private TypeServiceQueryService typeServiceQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeServiceMockMvc;

    private TypeService typeService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeService createEntity(EntityManager em) {
        TypeService typeService = new TypeService()
            .nameShort(DEFAULT_NAME_SHORT)
            .nameLong(DEFAULT_NAME_LONG)
            .description(DEFAULT_DESCRIPTION)
            .typeServiceColor(DEFAULT_TYPE_SERVICE_COLOR)
            .uuidOce(DEFAULT_UUID_OCE)
            .uuidOrganisation(DEFAULT_UUID_ORGANISATION)
            .uuidEntity(DEFAULT_UUID_ENTITY)
            .archived(DEFAULT_ARCHIVED)
            .deleted(DEFAULT_DELETED);
        return typeService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeService createUpdatedEntity(EntityManager em) {
        TypeService typeService = new TypeService()
            .nameShort(UPDATED_NAME_SHORT)
            .nameLong(UPDATED_NAME_LONG)
            .description(UPDATED_DESCRIPTION)
            .typeServiceColor(UPDATED_TYPE_SERVICE_COLOR)
            .uuidOce(UPDATED_UUID_OCE)
            .uuidOrganisation(UPDATED_UUID_ORGANISATION)
            .uuidEntity(UPDATED_UUID_ENTITY)
            .archived(UPDATED_ARCHIVED)
            .deleted(UPDATED_DELETED);
        return typeService;
    }

    @BeforeEach
    public void initTest() {
        typeService = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeService() throws Exception {
        int databaseSizeBeforeCreate = typeServiceRepository.findAll().size();

        // Create the TypeService
        TypeServiceDTO typeServiceDTO = typeServiceMapper.toDto(typeService);
        restTypeServiceMockMvc.perform(post("/api/type-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeCreate + 1);
        TypeService testTypeService = typeServiceList.get(typeServiceList.size() - 1);
        assertThat(testTypeService.getNameShort()).isEqualTo(DEFAULT_NAME_SHORT);
        assertThat(testTypeService.getNameLong()).isEqualTo(DEFAULT_NAME_LONG);
        assertThat(testTypeService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTypeService.getTypeServiceColor()).isEqualTo(DEFAULT_TYPE_SERVICE_COLOR);
        assertThat(testTypeService.getUuidOce()).isEqualTo(DEFAULT_UUID_OCE);
        assertThat(testTypeService.getUuidOrganisation()).isEqualTo(DEFAULT_UUID_ORGANISATION);
        assertThat(testTypeService.getUuidEntity()).isEqualTo(DEFAULT_UUID_ENTITY);
        assertThat(testTypeService.isArchived()).isEqualTo(DEFAULT_ARCHIVED);
        assertThat(testTypeService.isDeleted()).isEqualTo(DEFAULT_DELETED);
    }

    @Test
    @Transactional
    public void createTypeServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeServiceRepository.findAll().size();

        // Create the TypeService with an existing ID
        typeService.setId(1L);
        TypeServiceDTO typeServiceDTO = typeServiceMapper.toDto(typeService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeServiceMockMvc.perform(post("/api/type-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameShortIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeServiceRepository.findAll().size();
        // set the field null
        typeService.setNameShort(null);

        // Create the TypeService, which fails.
        TypeServiceDTO typeServiceDTO = typeServiceMapper.toDto(typeService);

        restTypeServiceMockMvc.perform(post("/api/type-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeServiceDTO)))
            .andExpect(status().isBadRequest());

        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeServices() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList
        restTypeServiceMockMvc.perform(get("/api/type-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameShort").value(hasItem(DEFAULT_NAME_SHORT)))
            .andExpect(jsonPath("$.[*].nameLong").value(hasItem(DEFAULT_NAME_LONG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].typeServiceColor").value(hasItem(DEFAULT_TYPE_SERVICE_COLOR)))
            .andExpect(jsonPath("$.[*].uuidOce").value(hasItem(DEFAULT_UUID_OCE.toString())))
            .andExpect(jsonPath("$.[*].uuidOrganisation").value(hasItem(DEFAULT_UUID_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].uuidEntity").value(hasItem(DEFAULT_UUID_ENTITY.toString())))
            .andExpect(jsonPath("$.[*].archived").value(hasItem(DEFAULT_ARCHIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTypeService() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get the typeService
        restTypeServiceMockMvc.perform(get("/api/type-services/{id}", typeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeService.getId().intValue()))
            .andExpect(jsonPath("$.nameShort").value(DEFAULT_NAME_SHORT))
            .andExpect(jsonPath("$.nameLong").value(DEFAULT_NAME_LONG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.typeServiceColor").value(DEFAULT_TYPE_SERVICE_COLOR))
            .andExpect(jsonPath("$.uuidOce").value(DEFAULT_UUID_OCE.toString()))
            .andExpect(jsonPath("$.uuidOrganisation").value(DEFAULT_UUID_ORGANISATION.toString()))
            .andExpect(jsonPath("$.uuidEntity").value(DEFAULT_UUID_ENTITY.toString()))
            .andExpect(jsonPath("$.archived").value(DEFAULT_ARCHIVED.booleanValue()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
    }


    @Test
    @Transactional
    public void getTypeServicesByIdFiltering() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        Long id = typeService.getId();

        defaultTypeServiceShouldBeFound("id.equals=" + id);
        defaultTypeServiceShouldNotBeFound("id.notEquals=" + id);

        defaultTypeServiceShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTypeServiceShouldNotBeFound("id.greaterThan=" + id);

        defaultTypeServiceShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTypeServiceShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTypeServicesByNameShortIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameShort equals to DEFAULT_NAME_SHORT
        defaultTypeServiceShouldBeFound("nameShort.equals=" + DEFAULT_NAME_SHORT);

        // Get all the typeServiceList where nameShort equals to UPDATED_NAME_SHORT
        defaultTypeServiceShouldNotBeFound("nameShort.equals=" + UPDATED_NAME_SHORT);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameShortIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameShort not equals to DEFAULT_NAME_SHORT
        defaultTypeServiceShouldNotBeFound("nameShort.notEquals=" + DEFAULT_NAME_SHORT);

        // Get all the typeServiceList where nameShort not equals to UPDATED_NAME_SHORT
        defaultTypeServiceShouldBeFound("nameShort.notEquals=" + UPDATED_NAME_SHORT);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameShortIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameShort in DEFAULT_NAME_SHORT or UPDATED_NAME_SHORT
        defaultTypeServiceShouldBeFound("nameShort.in=" + DEFAULT_NAME_SHORT + "," + UPDATED_NAME_SHORT);

        // Get all the typeServiceList where nameShort equals to UPDATED_NAME_SHORT
        defaultTypeServiceShouldNotBeFound("nameShort.in=" + UPDATED_NAME_SHORT);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameShortIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameShort is not null
        defaultTypeServiceShouldBeFound("nameShort.specified=true");

        // Get all the typeServiceList where nameShort is null
        defaultTypeServiceShouldNotBeFound("nameShort.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeServicesByNameShortContainsSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameShort contains DEFAULT_NAME_SHORT
        defaultTypeServiceShouldBeFound("nameShort.contains=" + DEFAULT_NAME_SHORT);

        // Get all the typeServiceList where nameShort contains UPDATED_NAME_SHORT
        defaultTypeServiceShouldNotBeFound("nameShort.contains=" + UPDATED_NAME_SHORT);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameShortNotContainsSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameShort does not contain DEFAULT_NAME_SHORT
        defaultTypeServiceShouldNotBeFound("nameShort.doesNotContain=" + DEFAULT_NAME_SHORT);

        // Get all the typeServiceList where nameShort does not contain UPDATED_NAME_SHORT
        defaultTypeServiceShouldBeFound("nameShort.doesNotContain=" + UPDATED_NAME_SHORT);
    }


    @Test
    @Transactional
    public void getAllTypeServicesByNameLongIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameLong equals to DEFAULT_NAME_LONG
        defaultTypeServiceShouldBeFound("nameLong.equals=" + DEFAULT_NAME_LONG);

        // Get all the typeServiceList where nameLong equals to UPDATED_NAME_LONG
        defaultTypeServiceShouldNotBeFound("nameLong.equals=" + UPDATED_NAME_LONG);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameLongIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameLong not equals to DEFAULT_NAME_LONG
        defaultTypeServiceShouldNotBeFound("nameLong.notEquals=" + DEFAULT_NAME_LONG);

        // Get all the typeServiceList where nameLong not equals to UPDATED_NAME_LONG
        defaultTypeServiceShouldBeFound("nameLong.notEquals=" + UPDATED_NAME_LONG);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameLongIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameLong in DEFAULT_NAME_LONG or UPDATED_NAME_LONG
        defaultTypeServiceShouldBeFound("nameLong.in=" + DEFAULT_NAME_LONG + "," + UPDATED_NAME_LONG);

        // Get all the typeServiceList where nameLong equals to UPDATED_NAME_LONG
        defaultTypeServiceShouldNotBeFound("nameLong.in=" + UPDATED_NAME_LONG);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameLongIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameLong is not null
        defaultTypeServiceShouldBeFound("nameLong.specified=true");

        // Get all the typeServiceList where nameLong is null
        defaultTypeServiceShouldNotBeFound("nameLong.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeServicesByNameLongContainsSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameLong contains DEFAULT_NAME_LONG
        defaultTypeServiceShouldBeFound("nameLong.contains=" + DEFAULT_NAME_LONG);

        // Get all the typeServiceList where nameLong contains UPDATED_NAME_LONG
        defaultTypeServiceShouldNotBeFound("nameLong.contains=" + UPDATED_NAME_LONG);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByNameLongNotContainsSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where nameLong does not contain DEFAULT_NAME_LONG
        defaultTypeServiceShouldNotBeFound("nameLong.doesNotContain=" + DEFAULT_NAME_LONG);

        // Get all the typeServiceList where nameLong does not contain UPDATED_NAME_LONG
        defaultTypeServiceShouldBeFound("nameLong.doesNotContain=" + UPDATED_NAME_LONG);
    }


    @Test
    @Transactional
    public void getAllTypeServicesByTypeServiceColorIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where typeServiceColor equals to DEFAULT_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldBeFound("typeServiceColor.equals=" + DEFAULT_TYPE_SERVICE_COLOR);

        // Get all the typeServiceList where typeServiceColor equals to UPDATED_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldNotBeFound("typeServiceColor.equals=" + UPDATED_TYPE_SERVICE_COLOR);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByTypeServiceColorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where typeServiceColor not equals to DEFAULT_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldNotBeFound("typeServiceColor.notEquals=" + DEFAULT_TYPE_SERVICE_COLOR);

        // Get all the typeServiceList where typeServiceColor not equals to UPDATED_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldBeFound("typeServiceColor.notEquals=" + UPDATED_TYPE_SERVICE_COLOR);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByTypeServiceColorIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where typeServiceColor in DEFAULT_TYPE_SERVICE_COLOR or UPDATED_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldBeFound("typeServiceColor.in=" + DEFAULT_TYPE_SERVICE_COLOR + "," + UPDATED_TYPE_SERVICE_COLOR);

        // Get all the typeServiceList where typeServiceColor equals to UPDATED_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldNotBeFound("typeServiceColor.in=" + UPDATED_TYPE_SERVICE_COLOR);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByTypeServiceColorIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where typeServiceColor is not null
        defaultTypeServiceShouldBeFound("typeServiceColor.specified=true");

        // Get all the typeServiceList where typeServiceColor is null
        defaultTypeServiceShouldNotBeFound("typeServiceColor.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeServicesByTypeServiceColorContainsSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where typeServiceColor contains DEFAULT_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldBeFound("typeServiceColor.contains=" + DEFAULT_TYPE_SERVICE_COLOR);

        // Get all the typeServiceList where typeServiceColor contains UPDATED_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldNotBeFound("typeServiceColor.contains=" + UPDATED_TYPE_SERVICE_COLOR);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByTypeServiceColorNotContainsSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where typeServiceColor does not contain DEFAULT_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldNotBeFound("typeServiceColor.doesNotContain=" + DEFAULT_TYPE_SERVICE_COLOR);

        // Get all the typeServiceList where typeServiceColor does not contain UPDATED_TYPE_SERVICE_COLOR
        defaultTypeServiceShouldBeFound("typeServiceColor.doesNotContain=" + UPDATED_TYPE_SERVICE_COLOR);
    }


    @Test
    @Transactional
    public void getAllTypeServicesByUuidOceIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOce equals to DEFAULT_UUID_OCE
        defaultTypeServiceShouldBeFound("uuidOce.equals=" + DEFAULT_UUID_OCE);

        // Get all the typeServiceList where uuidOce equals to UPDATED_UUID_OCE
        defaultTypeServiceShouldNotBeFound("uuidOce.equals=" + UPDATED_UUID_OCE);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOce not equals to DEFAULT_UUID_OCE
        defaultTypeServiceShouldNotBeFound("uuidOce.notEquals=" + DEFAULT_UUID_OCE);

        // Get all the typeServiceList where uuidOce not equals to UPDATED_UUID_OCE
        defaultTypeServiceShouldBeFound("uuidOce.notEquals=" + UPDATED_UUID_OCE);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOceIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOce in DEFAULT_UUID_OCE or UPDATED_UUID_OCE
        defaultTypeServiceShouldBeFound("uuidOce.in=" + DEFAULT_UUID_OCE + "," + UPDATED_UUID_OCE);

        // Get all the typeServiceList where uuidOce equals to UPDATED_UUID_OCE
        defaultTypeServiceShouldNotBeFound("uuidOce.in=" + UPDATED_UUID_OCE);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOceIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOce is not null
        defaultTypeServiceShouldBeFound("uuidOce.specified=true");

        // Get all the typeServiceList where uuidOce is null
        defaultTypeServiceShouldNotBeFound("uuidOce.specified=false");
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOrganisationIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOrganisation equals to DEFAULT_UUID_ORGANISATION
        defaultTypeServiceShouldBeFound("uuidOrganisation.equals=" + DEFAULT_UUID_ORGANISATION);

        // Get all the typeServiceList where uuidOrganisation equals to UPDATED_UUID_ORGANISATION
        defaultTypeServiceShouldNotBeFound("uuidOrganisation.equals=" + UPDATED_UUID_ORGANISATION);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOrganisationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOrganisation not equals to DEFAULT_UUID_ORGANISATION
        defaultTypeServiceShouldNotBeFound("uuidOrganisation.notEquals=" + DEFAULT_UUID_ORGANISATION);

        // Get all the typeServiceList where uuidOrganisation not equals to UPDATED_UUID_ORGANISATION
        defaultTypeServiceShouldBeFound("uuidOrganisation.notEquals=" + UPDATED_UUID_ORGANISATION);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOrganisationIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOrganisation in DEFAULT_UUID_ORGANISATION or UPDATED_UUID_ORGANISATION
        defaultTypeServiceShouldBeFound("uuidOrganisation.in=" + DEFAULT_UUID_ORGANISATION + "," + UPDATED_UUID_ORGANISATION);

        // Get all the typeServiceList where uuidOrganisation equals to UPDATED_UUID_ORGANISATION
        defaultTypeServiceShouldNotBeFound("uuidOrganisation.in=" + UPDATED_UUID_ORGANISATION);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidOrganisationIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidOrganisation is not null
        defaultTypeServiceShouldBeFound("uuidOrganisation.specified=true");

        // Get all the typeServiceList where uuidOrganisation is null
        defaultTypeServiceShouldNotBeFound("uuidOrganisation.specified=false");
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidEntityIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidEntity equals to DEFAULT_UUID_ENTITY
        defaultTypeServiceShouldBeFound("uuidEntity.equals=" + DEFAULT_UUID_ENTITY);

        // Get all the typeServiceList where uuidEntity equals to UPDATED_UUID_ENTITY
        defaultTypeServiceShouldNotBeFound("uuidEntity.equals=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidEntityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidEntity not equals to DEFAULT_UUID_ENTITY
        defaultTypeServiceShouldNotBeFound("uuidEntity.notEquals=" + DEFAULT_UUID_ENTITY);

        // Get all the typeServiceList where uuidEntity not equals to UPDATED_UUID_ENTITY
        defaultTypeServiceShouldBeFound("uuidEntity.notEquals=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidEntityIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidEntity in DEFAULT_UUID_ENTITY or UPDATED_UUID_ENTITY
        defaultTypeServiceShouldBeFound("uuidEntity.in=" + DEFAULT_UUID_ENTITY + "," + UPDATED_UUID_ENTITY);

        // Get all the typeServiceList where uuidEntity equals to UPDATED_UUID_ENTITY
        defaultTypeServiceShouldNotBeFound("uuidEntity.in=" + UPDATED_UUID_ENTITY);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByUuidEntityIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where uuidEntity is not null
        defaultTypeServiceShouldBeFound("uuidEntity.specified=true");

        // Get all the typeServiceList where uuidEntity is null
        defaultTypeServiceShouldNotBeFound("uuidEntity.specified=false");
    }

    @Test
    @Transactional
    public void getAllTypeServicesByArchivedIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where archived equals to DEFAULT_ARCHIVED
        defaultTypeServiceShouldBeFound("archived.equals=" + DEFAULT_ARCHIVED);

        // Get all the typeServiceList where archived equals to UPDATED_ARCHIVED
        defaultTypeServiceShouldNotBeFound("archived.equals=" + UPDATED_ARCHIVED);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByArchivedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where archived not equals to DEFAULT_ARCHIVED
        defaultTypeServiceShouldNotBeFound("archived.notEquals=" + DEFAULT_ARCHIVED);

        // Get all the typeServiceList where archived not equals to UPDATED_ARCHIVED
        defaultTypeServiceShouldBeFound("archived.notEquals=" + UPDATED_ARCHIVED);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByArchivedIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where archived in DEFAULT_ARCHIVED or UPDATED_ARCHIVED
        defaultTypeServiceShouldBeFound("archived.in=" + DEFAULT_ARCHIVED + "," + UPDATED_ARCHIVED);

        // Get all the typeServiceList where archived equals to UPDATED_ARCHIVED
        defaultTypeServiceShouldNotBeFound("archived.in=" + UPDATED_ARCHIVED);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByArchivedIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where archived is not null
        defaultTypeServiceShouldBeFound("archived.specified=true");

        // Get all the typeServiceList where archived is null
        defaultTypeServiceShouldNotBeFound("archived.specified=false");
    }

    @Test
    @Transactional
    public void getAllTypeServicesByDeletedIsEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where deleted equals to DEFAULT_DELETED
        defaultTypeServiceShouldBeFound("deleted.equals=" + DEFAULT_DELETED);

        // Get all the typeServiceList where deleted equals to UPDATED_DELETED
        defaultTypeServiceShouldNotBeFound("deleted.equals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByDeletedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where deleted not equals to DEFAULT_DELETED
        defaultTypeServiceShouldNotBeFound("deleted.notEquals=" + DEFAULT_DELETED);

        // Get all the typeServiceList where deleted not equals to UPDATED_DELETED
        defaultTypeServiceShouldBeFound("deleted.notEquals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByDeletedIsInShouldWork() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where deleted in DEFAULT_DELETED or UPDATED_DELETED
        defaultTypeServiceShouldBeFound("deleted.in=" + DEFAULT_DELETED + "," + UPDATED_DELETED);

        // Get all the typeServiceList where deleted equals to UPDATED_DELETED
        defaultTypeServiceShouldNotBeFound("deleted.in=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllTypeServicesByDeletedIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        // Get all the typeServiceList where deleted is not null
        defaultTypeServiceShouldBeFound("deleted.specified=true");

        // Get all the typeServiceList where deleted is null
        defaultTypeServiceShouldNotBeFound("deleted.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTypeServiceShouldBeFound(String filter) throws Exception {
        restTypeServiceMockMvc.perform(get("/api/type-services?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameShort").value(hasItem(DEFAULT_NAME_SHORT)))
            .andExpect(jsonPath("$.[*].nameLong").value(hasItem(DEFAULT_NAME_LONG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].typeServiceColor").value(hasItem(DEFAULT_TYPE_SERVICE_COLOR)))
            .andExpect(jsonPath("$.[*].uuidOce").value(hasItem(DEFAULT_UUID_OCE.toString())))
            .andExpect(jsonPath("$.[*].uuidOrganisation").value(hasItem(DEFAULT_UUID_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].uuidEntity").value(hasItem(DEFAULT_UUID_ENTITY.toString())))
            .andExpect(jsonPath("$.[*].archived").value(hasItem(DEFAULT_ARCHIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())));

        // Check, that the count call also returns 1
        restTypeServiceMockMvc.perform(get("/api/type-services/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTypeServiceShouldNotBeFound(String filter) throws Exception {
        restTypeServiceMockMvc.perform(get("/api/type-services?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTypeServiceMockMvc.perform(get("/api/type-services/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTypeService() throws Exception {
        // Get the typeService
        restTypeServiceMockMvc.perform(get("/api/type-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeService() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        int databaseSizeBeforeUpdate = typeServiceRepository.findAll().size();

        // Update the typeService
        TypeService updatedTypeService = typeServiceRepository.findById(typeService.getId()).get();
        // Disconnect from session so that the updates on updatedTypeService are not directly saved in db
        em.detach(updatedTypeService);
        updatedTypeService
            .nameShort(UPDATED_NAME_SHORT)
            .nameLong(UPDATED_NAME_LONG)
            .description(UPDATED_DESCRIPTION)
            .typeServiceColor(UPDATED_TYPE_SERVICE_COLOR)
            .uuidOce(UPDATED_UUID_OCE)
            .uuidOrganisation(UPDATED_UUID_ORGANISATION)
            .uuidEntity(UPDATED_UUID_ENTITY)
            .archived(UPDATED_ARCHIVED)
            .deleted(UPDATED_DELETED);
        TypeServiceDTO typeServiceDTO = typeServiceMapper.toDto(updatedTypeService);

        restTypeServiceMockMvc.perform(put("/api/type-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeServiceDTO)))
            .andExpect(status().isOk());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeUpdate);
        TypeService testTypeService = typeServiceList.get(typeServiceList.size() - 1);
        assertThat(testTypeService.getNameShort()).isEqualTo(UPDATED_NAME_SHORT);
        assertThat(testTypeService.getNameLong()).isEqualTo(UPDATED_NAME_LONG);
        assertThat(testTypeService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTypeService.getTypeServiceColor()).isEqualTo(UPDATED_TYPE_SERVICE_COLOR);
        assertThat(testTypeService.getUuidOce()).isEqualTo(UPDATED_UUID_OCE);
        assertThat(testTypeService.getUuidOrganisation()).isEqualTo(UPDATED_UUID_ORGANISATION);
        assertThat(testTypeService.getUuidEntity()).isEqualTo(UPDATED_UUID_ENTITY);
        assertThat(testTypeService.isArchived()).isEqualTo(UPDATED_ARCHIVED);
        assertThat(testTypeService.isDeleted()).isEqualTo(UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeService() throws Exception {
        int databaseSizeBeforeUpdate = typeServiceRepository.findAll().size();

        // Create the TypeService
        TypeServiceDTO typeServiceDTO = typeServiceMapper.toDto(typeService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeServiceMockMvc.perform(put("/api/type-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeService in the database
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeService() throws Exception {
        // Initialize the database
        typeServiceRepository.saveAndFlush(typeService);

        int databaseSizeBeforeDelete = typeServiceRepository.findAll().size();

        // Delete the typeService
        restTypeServiceMockMvc.perform(delete("/api/type-services/{id}", typeService.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        assertThat(typeServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
