package com.oce.app.web.rest;

import com.oce.app.OpenCivicEngagementApp;
import com.oce.app.domain.ActionService;
import com.oce.app.domain.TypeService;
import com.oce.app.domain.User;
import com.oce.app.domain.Organisation;
import com.oce.app.repository.ActionServiceRepository;
import com.oce.app.service.ActionServiceService;
import com.oce.app.service.dto.ActionServiceDTO;
import com.oce.app.service.mapper.ActionServiceMapper;
import com.oce.app.service.dto.ActionServiceCriteria;
import com.oce.app.service.ActionServiceQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.oce.app.domain.enumeration.NatureService;
/**
 * Integration tests for the {@link ActionServiceResource} REST controller.
 */
@SpringBootTest(classes = OpenCivicEngagementApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ActionServiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final NatureService DEFAULT_NATURE = NatureService.DEMANDE;
    private static final NatureService UPDATED_NATURE = NatureService.PROPOSITION;

    private static final String DEFAULT_MISSION_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_MISSION_OBJECTIVE = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;
    private static final Long SMALLER_AMOUNT = 1L - 1L;

    private static final Long DEFAULT_BENEFICIARY_NUMBER = 1L;
    private static final Long UPDATED_BENEFICIARY_NUMBER = 2L;
    private static final Long SMALLER_BENEFICIARY_NUMBER = 1L - 1L;

    private static final Long DEFAULT_VOLUNTEER_NUMBER = 1L;
    private static final Long UPDATED_VOLUNTEER_NUMBER = 2L;
    private static final Long SMALLER_VOLUNTEER_NUMBER = 1L - 1L;

    private static final String DEFAULT_ADDITIONAL_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_INFORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LABEL_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_LABEL_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_NUMBER_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_STREET_NUMBER_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALITY = "AAAAAAAAAA";
    private static final String UPDATED_LOCALITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    @Autowired
    private ActionServiceRepository actionServiceRepository;

    @Mock
    private ActionServiceRepository actionServiceRepositoryMock;

    @Autowired
    private ActionServiceMapper actionServiceMapper;

    @Mock
    private ActionServiceService actionServiceServiceMock;

    @Autowired
    private ActionServiceService actionServiceService;

    @Autowired
    private ActionServiceQueryService actionServiceQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActionServiceMockMvc;

    private ActionService actionService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActionService createEntity(EntityManager em) {
        ActionService actionService = new ActionService()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .nature(DEFAULT_NATURE)
            .missionObjective(DEFAULT_MISSION_OBJECTIVE)
            .amount(DEFAULT_AMOUNT)
            .beneficiaryNumber(DEFAULT_BENEFICIARY_NUMBER)
            .volunteerNumber(DEFAULT_VOLUNTEER_NUMBER)
            .additionalInformation(DEFAULT_ADDITIONAL_INFORMATION)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .labelAdresse(DEFAULT_LABEL_ADRESSE)
            .streetNumberAdresse(DEFAULT_STREET_NUMBER_ADRESSE)
            .routeAdresse(DEFAULT_ROUTE_ADRESSE)
            .locality(DEFAULT_LOCALITY)
            .county(DEFAULT_COUNTY)
            .country(DEFAULT_COUNTRY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return actionService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActionService createUpdatedEntity(EntityManager em) {
        ActionService actionService = new ActionService()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .nature(UPDATED_NATURE)
            .missionObjective(UPDATED_MISSION_OBJECTIVE)
            .amount(UPDATED_AMOUNT)
            .beneficiaryNumber(UPDATED_BENEFICIARY_NUMBER)
            .volunteerNumber(UPDATED_VOLUNTEER_NUMBER)
            .additionalInformation(UPDATED_ADDITIONAL_INFORMATION)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .labelAdresse(UPDATED_LABEL_ADRESSE)
            .streetNumberAdresse(UPDATED_STREET_NUMBER_ADRESSE)
            .routeAdresse(UPDATED_ROUTE_ADRESSE)
            .locality(UPDATED_LOCALITY)
            .county(UPDATED_COUNTY)
            .country(UPDATED_COUNTRY)
            .postalCode(UPDATED_POSTAL_CODE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        return actionService;
    }

    @BeforeEach
    public void initTest() {
        actionService = createEntity(em);
    }

    @Test
    @Transactional
    public void createActionService() throws Exception {
        int databaseSizeBeforeCreate = actionServiceRepository.findAll().size();

        // Create the ActionService
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);
        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the ActionService in the database
        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ActionService testActionService = actionServiceList.get(actionServiceList.size() - 1);
        assertThat(testActionService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testActionService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testActionService.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testActionService.getMissionObjective()).isEqualTo(DEFAULT_MISSION_OBJECTIVE);
        assertThat(testActionService.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testActionService.getBeneficiaryNumber()).isEqualTo(DEFAULT_BENEFICIARY_NUMBER);
        assertThat(testActionService.getVolunteerNumber()).isEqualTo(DEFAULT_VOLUNTEER_NUMBER);
        assertThat(testActionService.getAdditionalInformation()).isEqualTo(DEFAULT_ADDITIONAL_INFORMATION);
        assertThat(testActionService.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testActionService.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testActionService.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testActionService.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testActionService.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testActionService.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testActionService.getLabelAdresse()).isEqualTo(DEFAULT_LABEL_ADRESSE);
        assertThat(testActionService.getStreetNumberAdresse()).isEqualTo(DEFAULT_STREET_NUMBER_ADRESSE);
        assertThat(testActionService.getRouteAdresse()).isEqualTo(DEFAULT_ROUTE_ADRESSE);
        assertThat(testActionService.getLocality()).isEqualTo(DEFAULT_LOCALITY);
        assertThat(testActionService.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testActionService.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testActionService.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testActionService.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testActionService.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createActionServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actionServiceRepository.findAll().size();

        // Create the ActionService with an existing ID
        actionService.setId(1L);
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActionService in the database
        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = actionServiceRepository.findAll().size();
        // set the field null
        actionService.setName(null);

        // Create the ActionService, which fails.
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = actionServiceRepository.findAll().size();
        // set the field null
        actionService.setNature(null);

        // Create the ActionService, which fails.
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = actionServiceRepository.findAll().size();
        // set the field null
        actionService.setFirstName(null);

        // Create the ActionService, which fails.
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = actionServiceRepository.findAll().size();
        // set the field null
        actionService.setLastName(null);

        // Create the ActionService, which fails.
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = actionServiceRepository.findAll().size();
        // set the field null
        actionService.setPhoneNumber(null);

        // Create the ActionService, which fails.
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        restActionServiceMockMvc.perform(post("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActionServices() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList
        restActionServiceMockMvc.perform(get("/api/action-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actionService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].missionObjective").value(hasItem(DEFAULT_MISSION_OBJECTIVE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].beneficiaryNumber").value(hasItem(DEFAULT_BENEFICIARY_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].volunteerNumber").value(hasItem(DEFAULT_VOLUNTEER_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].additionalInformation").value(hasItem(DEFAULT_ADDITIONAL_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].labelAdresse").value(hasItem(DEFAULT_LABEL_ADRESSE)))
            .andExpect(jsonPath("$.[*].streetNumberAdresse").value(hasItem(DEFAULT_STREET_NUMBER_ADRESSE)))
            .andExpect(jsonPath("$.[*].routeAdresse").value(hasItem(DEFAULT_ROUTE_ADRESSE)))
            .andExpect(jsonPath("$.[*].locality").value(hasItem(DEFAULT_LOCALITY)))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllActionServicesWithEagerRelationshipsIsEnabled() throws Exception {
        when(actionServiceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restActionServiceMockMvc.perform(get("/api/action-services?eagerload=true"))
            .andExpect(status().isOk());

        verify(actionServiceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllActionServicesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(actionServiceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restActionServiceMockMvc.perform(get("/api/action-services?eagerload=true"))
            .andExpect(status().isOk());

        verify(actionServiceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getActionService() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get the actionService
        restActionServiceMockMvc.perform(get("/api/action-services/{id}", actionService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(actionService.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE.toString()))
            .andExpect(jsonPath("$.missionObjective").value(DEFAULT_MISSION_OBJECTIVE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.beneficiaryNumber").value(DEFAULT_BENEFICIARY_NUMBER.intValue()))
            .andExpect(jsonPath("$.volunteerNumber").value(DEFAULT_VOLUNTEER_NUMBER.intValue()))
            .andExpect(jsonPath("$.additionalInformation").value(DEFAULT_ADDITIONAL_INFORMATION.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.labelAdresse").value(DEFAULT_LABEL_ADRESSE))
            .andExpect(jsonPath("$.streetNumberAdresse").value(DEFAULT_STREET_NUMBER_ADRESSE))
            .andExpect(jsonPath("$.routeAdresse").value(DEFAULT_ROUTE_ADRESSE))
            .andExpect(jsonPath("$.locality").value(DEFAULT_LOCALITY))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE));
    }


    @Test
    @Transactional
    public void getActionServicesByIdFiltering() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        Long id = actionService.getId();

        defaultActionServiceShouldBeFound("id.equals=" + id);
        defaultActionServiceShouldNotBeFound("id.notEquals=" + id);

        defaultActionServiceShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultActionServiceShouldNotBeFound("id.greaterThan=" + id);

        defaultActionServiceShouldBeFound("id.lessThanOrEqual=" + id);
        defaultActionServiceShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllActionServicesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where name equals to DEFAULT_NAME
        defaultActionServiceShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the actionServiceList where name equals to UPDATED_NAME
        defaultActionServiceShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where name not equals to DEFAULT_NAME
        defaultActionServiceShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the actionServiceList where name not equals to UPDATED_NAME
        defaultActionServiceShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where name in DEFAULT_NAME or UPDATED_NAME
        defaultActionServiceShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the actionServiceList where name equals to UPDATED_NAME
        defaultActionServiceShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where name is not null
        defaultActionServiceShouldBeFound("name.specified=true");

        // Get all the actionServiceList where name is null
        defaultActionServiceShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByNameContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where name contains DEFAULT_NAME
        defaultActionServiceShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the actionServiceList where name contains UPDATED_NAME
        defaultActionServiceShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where name does not contain DEFAULT_NAME
        defaultActionServiceShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the actionServiceList where name does not contain UPDATED_NAME
        defaultActionServiceShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllActionServicesByNatureIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where nature equals to DEFAULT_NATURE
        defaultActionServiceShouldBeFound("nature.equals=" + DEFAULT_NATURE);

        // Get all the actionServiceList where nature equals to UPDATED_NATURE
        defaultActionServiceShouldNotBeFound("nature.equals=" + UPDATED_NATURE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNatureIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where nature not equals to DEFAULT_NATURE
        defaultActionServiceShouldNotBeFound("nature.notEquals=" + DEFAULT_NATURE);

        // Get all the actionServiceList where nature not equals to UPDATED_NATURE
        defaultActionServiceShouldBeFound("nature.notEquals=" + UPDATED_NATURE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNatureIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where nature in DEFAULT_NATURE or UPDATED_NATURE
        defaultActionServiceShouldBeFound("nature.in=" + DEFAULT_NATURE + "," + UPDATED_NATURE);

        // Get all the actionServiceList where nature equals to UPDATED_NATURE
        defaultActionServiceShouldNotBeFound("nature.in=" + UPDATED_NATURE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByNatureIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where nature is not null
        defaultActionServiceShouldBeFound("nature.specified=true");

        // Get all the actionServiceList where nature is null
        defaultActionServiceShouldNotBeFound("nature.specified=false");
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount equals to DEFAULT_AMOUNT
        defaultActionServiceShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the actionServiceList where amount equals to UPDATED_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount not equals to DEFAULT_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.notEquals=" + DEFAULT_AMOUNT);

        // Get all the actionServiceList where amount not equals to UPDATED_AMOUNT
        defaultActionServiceShouldBeFound("amount.notEquals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultActionServiceShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the actionServiceList where amount equals to UPDATED_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount is not null
        defaultActionServiceShouldBeFound("amount.specified=true");

        // Get all the actionServiceList where amount is null
        defaultActionServiceShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount is greater than or equal to DEFAULT_AMOUNT
        defaultActionServiceShouldBeFound("amount.greaterThanOrEqual=" + DEFAULT_AMOUNT);

        // Get all the actionServiceList where amount is greater than or equal to UPDATED_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.greaterThanOrEqual=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount is less than or equal to DEFAULT_AMOUNT
        defaultActionServiceShouldBeFound("amount.lessThanOrEqual=" + DEFAULT_AMOUNT);

        // Get all the actionServiceList where amount is less than or equal to SMALLER_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.lessThanOrEqual=" + SMALLER_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount is less than DEFAULT_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.lessThan=" + DEFAULT_AMOUNT);

        // Get all the actionServiceList where amount is less than UPDATED_AMOUNT
        defaultActionServiceShouldBeFound("amount.lessThan=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllActionServicesByAmountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where amount is greater than DEFAULT_AMOUNT
        defaultActionServiceShouldNotBeFound("amount.greaterThan=" + DEFAULT_AMOUNT);

        // Get all the actionServiceList where amount is greater than SMALLER_AMOUNT
        defaultActionServiceShouldBeFound("amount.greaterThan=" + SMALLER_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber equals to DEFAULT_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.equals=" + DEFAULT_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber equals to UPDATED_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.equals=" + UPDATED_BENEFICIARY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber not equals to DEFAULT_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.notEquals=" + DEFAULT_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber not equals to UPDATED_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.notEquals=" + UPDATED_BENEFICIARY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber in DEFAULT_BENEFICIARY_NUMBER or UPDATED_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.in=" + DEFAULT_BENEFICIARY_NUMBER + "," + UPDATED_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber equals to UPDATED_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.in=" + UPDATED_BENEFICIARY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber is not null
        defaultActionServiceShouldBeFound("beneficiaryNumber.specified=true");

        // Get all the actionServiceList where beneficiaryNumber is null
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber is greater than or equal to DEFAULT_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.greaterThanOrEqual=" + DEFAULT_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber is greater than or equal to UPDATED_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.greaterThanOrEqual=" + UPDATED_BENEFICIARY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber is less than or equal to DEFAULT_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.lessThanOrEqual=" + DEFAULT_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber is less than or equal to SMALLER_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.lessThanOrEqual=" + SMALLER_BENEFICIARY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber is less than DEFAULT_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.lessThan=" + DEFAULT_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber is less than UPDATED_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.lessThan=" + UPDATED_BENEFICIARY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByBeneficiaryNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where beneficiaryNumber is greater than DEFAULT_BENEFICIARY_NUMBER
        defaultActionServiceShouldNotBeFound("beneficiaryNumber.greaterThan=" + DEFAULT_BENEFICIARY_NUMBER);

        // Get all the actionServiceList where beneficiaryNumber is greater than SMALLER_BENEFICIARY_NUMBER
        defaultActionServiceShouldBeFound("beneficiaryNumber.greaterThan=" + SMALLER_BENEFICIARY_NUMBER);
    }


    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber equals to DEFAULT_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.equals=" + DEFAULT_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber equals to UPDATED_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.equals=" + UPDATED_VOLUNTEER_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber not equals to DEFAULT_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.notEquals=" + DEFAULT_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber not equals to UPDATED_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.notEquals=" + UPDATED_VOLUNTEER_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber in DEFAULT_VOLUNTEER_NUMBER or UPDATED_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.in=" + DEFAULT_VOLUNTEER_NUMBER + "," + UPDATED_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber equals to UPDATED_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.in=" + UPDATED_VOLUNTEER_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber is not null
        defaultActionServiceShouldBeFound("volunteerNumber.specified=true");

        // Get all the actionServiceList where volunteerNumber is null
        defaultActionServiceShouldNotBeFound("volunteerNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber is greater than or equal to DEFAULT_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.greaterThanOrEqual=" + DEFAULT_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber is greater than or equal to UPDATED_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.greaterThanOrEqual=" + UPDATED_VOLUNTEER_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber is less than or equal to DEFAULT_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.lessThanOrEqual=" + DEFAULT_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber is less than or equal to SMALLER_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.lessThanOrEqual=" + SMALLER_VOLUNTEER_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber is less than DEFAULT_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.lessThan=" + DEFAULT_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber is less than UPDATED_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.lessThan=" + UPDATED_VOLUNTEER_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByVolunteerNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where volunteerNumber is greater than DEFAULT_VOLUNTEER_NUMBER
        defaultActionServiceShouldNotBeFound("volunteerNumber.greaterThan=" + DEFAULT_VOLUNTEER_NUMBER);

        // Get all the actionServiceList where volunteerNumber is greater than SMALLER_VOLUNTEER_NUMBER
        defaultActionServiceShouldBeFound("volunteerNumber.greaterThan=" + SMALLER_VOLUNTEER_NUMBER);
    }


    @Test
    @Transactional
    public void getAllActionServicesByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where firstName equals to DEFAULT_FIRST_NAME
        defaultActionServiceShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the actionServiceList where firstName equals to UPDATED_FIRST_NAME
        defaultActionServiceShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where firstName not equals to DEFAULT_FIRST_NAME
        defaultActionServiceShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the actionServiceList where firstName not equals to UPDATED_FIRST_NAME
        defaultActionServiceShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultActionServiceShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the actionServiceList where firstName equals to UPDATED_FIRST_NAME
        defaultActionServiceShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where firstName is not null
        defaultActionServiceShouldBeFound("firstName.specified=true");

        // Get all the actionServiceList where firstName is null
        defaultActionServiceShouldNotBeFound("firstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where firstName contains DEFAULT_FIRST_NAME
        defaultActionServiceShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the actionServiceList where firstName contains UPDATED_FIRST_NAME
        defaultActionServiceShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where firstName does not contain DEFAULT_FIRST_NAME
        defaultActionServiceShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the actionServiceList where firstName does not contain UPDATED_FIRST_NAME
        defaultActionServiceShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllActionServicesByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where lastName equals to DEFAULT_LAST_NAME
        defaultActionServiceShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the actionServiceList where lastName equals to UPDATED_LAST_NAME
        defaultActionServiceShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where lastName not equals to DEFAULT_LAST_NAME
        defaultActionServiceShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the actionServiceList where lastName not equals to UPDATED_LAST_NAME
        defaultActionServiceShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultActionServiceShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the actionServiceList where lastName equals to UPDATED_LAST_NAME
        defaultActionServiceShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where lastName is not null
        defaultActionServiceShouldBeFound("lastName.specified=true");

        // Get all the actionServiceList where lastName is null
        defaultActionServiceShouldNotBeFound("lastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByLastNameContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where lastName contains DEFAULT_LAST_NAME
        defaultActionServiceShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the actionServiceList where lastName contains UPDATED_LAST_NAME
        defaultActionServiceShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where lastName does not contain DEFAULT_LAST_NAME
        defaultActionServiceShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the actionServiceList where lastName does not contain UPDATED_LAST_NAME
        defaultActionServiceShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllActionServicesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where email equals to DEFAULT_EMAIL
        defaultActionServiceShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the actionServiceList where email equals to UPDATED_EMAIL
        defaultActionServiceShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where email not equals to DEFAULT_EMAIL
        defaultActionServiceShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the actionServiceList where email not equals to UPDATED_EMAIL
        defaultActionServiceShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultActionServiceShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the actionServiceList where email equals to UPDATED_EMAIL
        defaultActionServiceShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where email is not null
        defaultActionServiceShouldBeFound("email.specified=true");

        // Get all the actionServiceList where email is null
        defaultActionServiceShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByEmailContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where email contains DEFAULT_EMAIL
        defaultActionServiceShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the actionServiceList where email contains UPDATED_EMAIL
        defaultActionServiceShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where email does not contain DEFAULT_EMAIL
        defaultActionServiceShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the actionServiceList where email does not contain UPDATED_EMAIL
        defaultActionServiceShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllActionServicesByPhoneNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where phoneNumber equals to DEFAULT_PHONE_NUMBER
        defaultActionServiceShouldBeFound("phoneNumber.equals=" + DEFAULT_PHONE_NUMBER);

        // Get all the actionServiceList where phoneNumber equals to UPDATED_PHONE_NUMBER
        defaultActionServiceShouldNotBeFound("phoneNumber.equals=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPhoneNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where phoneNumber not equals to DEFAULT_PHONE_NUMBER
        defaultActionServiceShouldNotBeFound("phoneNumber.notEquals=" + DEFAULT_PHONE_NUMBER);

        // Get all the actionServiceList where phoneNumber not equals to UPDATED_PHONE_NUMBER
        defaultActionServiceShouldBeFound("phoneNumber.notEquals=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPhoneNumberIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where phoneNumber in DEFAULT_PHONE_NUMBER or UPDATED_PHONE_NUMBER
        defaultActionServiceShouldBeFound("phoneNumber.in=" + DEFAULT_PHONE_NUMBER + "," + UPDATED_PHONE_NUMBER);

        // Get all the actionServiceList where phoneNumber equals to UPDATED_PHONE_NUMBER
        defaultActionServiceShouldNotBeFound("phoneNumber.in=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPhoneNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where phoneNumber is not null
        defaultActionServiceShouldBeFound("phoneNumber.specified=true");

        // Get all the actionServiceList where phoneNumber is null
        defaultActionServiceShouldNotBeFound("phoneNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByPhoneNumberContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where phoneNumber contains DEFAULT_PHONE_NUMBER
        defaultActionServiceShouldBeFound("phoneNumber.contains=" + DEFAULT_PHONE_NUMBER);

        // Get all the actionServiceList where phoneNumber contains UPDATED_PHONE_NUMBER
        defaultActionServiceShouldNotBeFound("phoneNumber.contains=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPhoneNumberNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where phoneNumber does not contain DEFAULT_PHONE_NUMBER
        defaultActionServiceShouldNotBeFound("phoneNumber.doesNotContain=" + DEFAULT_PHONE_NUMBER);

        // Get all the actionServiceList where phoneNumber does not contain UPDATED_PHONE_NUMBER
        defaultActionServiceShouldBeFound("phoneNumber.doesNotContain=" + UPDATED_PHONE_NUMBER);
    }


    @Test
    @Transactional
    public void getAllActionServicesByStartDateIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where startDate equals to DEFAULT_START_DATE
        defaultActionServiceShouldBeFound("startDate.equals=" + DEFAULT_START_DATE);

        // Get all the actionServiceList where startDate equals to UPDATED_START_DATE
        defaultActionServiceShouldNotBeFound("startDate.equals=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStartDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where startDate not equals to DEFAULT_START_DATE
        defaultActionServiceShouldNotBeFound("startDate.notEquals=" + DEFAULT_START_DATE);

        // Get all the actionServiceList where startDate not equals to UPDATED_START_DATE
        defaultActionServiceShouldBeFound("startDate.notEquals=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStartDateIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where startDate in DEFAULT_START_DATE or UPDATED_START_DATE
        defaultActionServiceShouldBeFound("startDate.in=" + DEFAULT_START_DATE + "," + UPDATED_START_DATE);

        // Get all the actionServiceList where startDate equals to UPDATED_START_DATE
        defaultActionServiceShouldNotBeFound("startDate.in=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStartDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where startDate is not null
        defaultActionServiceShouldBeFound("startDate.specified=true");

        // Get all the actionServiceList where startDate is null
        defaultActionServiceShouldNotBeFound("startDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllActionServicesByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where endDate equals to DEFAULT_END_DATE
        defaultActionServiceShouldBeFound("endDate.equals=" + DEFAULT_END_DATE);

        // Get all the actionServiceList where endDate equals to UPDATED_END_DATE
        defaultActionServiceShouldNotBeFound("endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEndDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where endDate not equals to DEFAULT_END_DATE
        defaultActionServiceShouldNotBeFound("endDate.notEquals=" + DEFAULT_END_DATE);

        // Get all the actionServiceList where endDate not equals to UPDATED_END_DATE
        defaultActionServiceShouldBeFound("endDate.notEquals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where endDate in DEFAULT_END_DATE or UPDATED_END_DATE
        defaultActionServiceShouldBeFound("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE);

        // Get all the actionServiceList where endDate equals to UPDATED_END_DATE
        defaultActionServiceShouldNotBeFound("endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where endDate is not null
        defaultActionServiceShouldBeFound("endDate.specified=true");

        // Get all the actionServiceList where endDate is null
        defaultActionServiceShouldNotBeFound("endDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllActionServicesByLabelAdresseIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where labelAdresse equals to DEFAULT_LABEL_ADRESSE
        defaultActionServiceShouldBeFound("labelAdresse.equals=" + DEFAULT_LABEL_ADRESSE);

        // Get all the actionServiceList where labelAdresse equals to UPDATED_LABEL_ADRESSE
        defaultActionServiceShouldNotBeFound("labelAdresse.equals=" + UPDATED_LABEL_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLabelAdresseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where labelAdresse not equals to DEFAULT_LABEL_ADRESSE
        defaultActionServiceShouldNotBeFound("labelAdresse.notEquals=" + DEFAULT_LABEL_ADRESSE);

        // Get all the actionServiceList where labelAdresse not equals to UPDATED_LABEL_ADRESSE
        defaultActionServiceShouldBeFound("labelAdresse.notEquals=" + UPDATED_LABEL_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLabelAdresseIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where labelAdresse in DEFAULT_LABEL_ADRESSE or UPDATED_LABEL_ADRESSE
        defaultActionServiceShouldBeFound("labelAdresse.in=" + DEFAULT_LABEL_ADRESSE + "," + UPDATED_LABEL_ADRESSE);

        // Get all the actionServiceList where labelAdresse equals to UPDATED_LABEL_ADRESSE
        defaultActionServiceShouldNotBeFound("labelAdresse.in=" + UPDATED_LABEL_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLabelAdresseIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where labelAdresse is not null
        defaultActionServiceShouldBeFound("labelAdresse.specified=true");

        // Get all the actionServiceList where labelAdresse is null
        defaultActionServiceShouldNotBeFound("labelAdresse.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByLabelAdresseContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where labelAdresse contains DEFAULT_LABEL_ADRESSE
        defaultActionServiceShouldBeFound("labelAdresse.contains=" + DEFAULT_LABEL_ADRESSE);

        // Get all the actionServiceList where labelAdresse contains UPDATED_LABEL_ADRESSE
        defaultActionServiceShouldNotBeFound("labelAdresse.contains=" + UPDATED_LABEL_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLabelAdresseNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where labelAdresse does not contain DEFAULT_LABEL_ADRESSE
        defaultActionServiceShouldNotBeFound("labelAdresse.doesNotContain=" + DEFAULT_LABEL_ADRESSE);

        // Get all the actionServiceList where labelAdresse does not contain UPDATED_LABEL_ADRESSE
        defaultActionServiceShouldBeFound("labelAdresse.doesNotContain=" + UPDATED_LABEL_ADRESSE);
    }


    @Test
    @Transactional
    public void getAllActionServicesByStreetNumberAdresseIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where streetNumberAdresse equals to DEFAULT_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldBeFound("streetNumberAdresse.equals=" + DEFAULT_STREET_NUMBER_ADRESSE);

        // Get all the actionServiceList where streetNumberAdresse equals to UPDATED_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldNotBeFound("streetNumberAdresse.equals=" + UPDATED_STREET_NUMBER_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStreetNumberAdresseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where streetNumberAdresse not equals to DEFAULT_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldNotBeFound("streetNumberAdresse.notEquals=" + DEFAULT_STREET_NUMBER_ADRESSE);

        // Get all the actionServiceList where streetNumberAdresse not equals to UPDATED_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldBeFound("streetNumberAdresse.notEquals=" + UPDATED_STREET_NUMBER_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStreetNumberAdresseIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where streetNumberAdresse in DEFAULT_STREET_NUMBER_ADRESSE or UPDATED_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldBeFound("streetNumberAdresse.in=" + DEFAULT_STREET_NUMBER_ADRESSE + "," + UPDATED_STREET_NUMBER_ADRESSE);

        // Get all the actionServiceList where streetNumberAdresse equals to UPDATED_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldNotBeFound("streetNumberAdresse.in=" + UPDATED_STREET_NUMBER_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStreetNumberAdresseIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where streetNumberAdresse is not null
        defaultActionServiceShouldBeFound("streetNumberAdresse.specified=true");

        // Get all the actionServiceList where streetNumberAdresse is null
        defaultActionServiceShouldNotBeFound("streetNumberAdresse.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByStreetNumberAdresseContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where streetNumberAdresse contains DEFAULT_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldBeFound("streetNumberAdresse.contains=" + DEFAULT_STREET_NUMBER_ADRESSE);

        // Get all the actionServiceList where streetNumberAdresse contains UPDATED_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldNotBeFound("streetNumberAdresse.contains=" + UPDATED_STREET_NUMBER_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByStreetNumberAdresseNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where streetNumberAdresse does not contain DEFAULT_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldNotBeFound("streetNumberAdresse.doesNotContain=" + DEFAULT_STREET_NUMBER_ADRESSE);

        // Get all the actionServiceList where streetNumberAdresse does not contain UPDATED_STREET_NUMBER_ADRESSE
        defaultActionServiceShouldBeFound("streetNumberAdresse.doesNotContain=" + UPDATED_STREET_NUMBER_ADRESSE);
    }


    @Test
    @Transactional
    public void getAllActionServicesByRouteAdresseIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where routeAdresse equals to DEFAULT_ROUTE_ADRESSE
        defaultActionServiceShouldBeFound("routeAdresse.equals=" + DEFAULT_ROUTE_ADRESSE);

        // Get all the actionServiceList where routeAdresse equals to UPDATED_ROUTE_ADRESSE
        defaultActionServiceShouldNotBeFound("routeAdresse.equals=" + UPDATED_ROUTE_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByRouteAdresseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where routeAdresse not equals to DEFAULT_ROUTE_ADRESSE
        defaultActionServiceShouldNotBeFound("routeAdresse.notEquals=" + DEFAULT_ROUTE_ADRESSE);

        // Get all the actionServiceList where routeAdresse not equals to UPDATED_ROUTE_ADRESSE
        defaultActionServiceShouldBeFound("routeAdresse.notEquals=" + UPDATED_ROUTE_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByRouteAdresseIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where routeAdresse in DEFAULT_ROUTE_ADRESSE or UPDATED_ROUTE_ADRESSE
        defaultActionServiceShouldBeFound("routeAdresse.in=" + DEFAULT_ROUTE_ADRESSE + "," + UPDATED_ROUTE_ADRESSE);

        // Get all the actionServiceList where routeAdresse equals to UPDATED_ROUTE_ADRESSE
        defaultActionServiceShouldNotBeFound("routeAdresse.in=" + UPDATED_ROUTE_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByRouteAdresseIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where routeAdresse is not null
        defaultActionServiceShouldBeFound("routeAdresse.specified=true");

        // Get all the actionServiceList where routeAdresse is null
        defaultActionServiceShouldNotBeFound("routeAdresse.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByRouteAdresseContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where routeAdresse contains DEFAULT_ROUTE_ADRESSE
        defaultActionServiceShouldBeFound("routeAdresse.contains=" + DEFAULT_ROUTE_ADRESSE);

        // Get all the actionServiceList where routeAdresse contains UPDATED_ROUTE_ADRESSE
        defaultActionServiceShouldNotBeFound("routeAdresse.contains=" + UPDATED_ROUTE_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByRouteAdresseNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where routeAdresse does not contain DEFAULT_ROUTE_ADRESSE
        defaultActionServiceShouldNotBeFound("routeAdresse.doesNotContain=" + DEFAULT_ROUTE_ADRESSE);

        // Get all the actionServiceList where routeAdresse does not contain UPDATED_ROUTE_ADRESSE
        defaultActionServiceShouldBeFound("routeAdresse.doesNotContain=" + UPDATED_ROUTE_ADRESSE);
    }


    @Test
    @Transactional
    public void getAllActionServicesByLocalityIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where locality equals to DEFAULT_LOCALITY
        defaultActionServiceShouldBeFound("locality.equals=" + DEFAULT_LOCALITY);

        // Get all the actionServiceList where locality equals to UPDATED_LOCALITY
        defaultActionServiceShouldNotBeFound("locality.equals=" + UPDATED_LOCALITY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLocalityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where locality not equals to DEFAULT_LOCALITY
        defaultActionServiceShouldNotBeFound("locality.notEquals=" + DEFAULT_LOCALITY);

        // Get all the actionServiceList where locality not equals to UPDATED_LOCALITY
        defaultActionServiceShouldBeFound("locality.notEquals=" + UPDATED_LOCALITY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLocalityIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where locality in DEFAULT_LOCALITY or UPDATED_LOCALITY
        defaultActionServiceShouldBeFound("locality.in=" + DEFAULT_LOCALITY + "," + UPDATED_LOCALITY);

        // Get all the actionServiceList where locality equals to UPDATED_LOCALITY
        defaultActionServiceShouldNotBeFound("locality.in=" + UPDATED_LOCALITY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLocalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where locality is not null
        defaultActionServiceShouldBeFound("locality.specified=true");

        // Get all the actionServiceList where locality is null
        defaultActionServiceShouldNotBeFound("locality.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByLocalityContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where locality contains DEFAULT_LOCALITY
        defaultActionServiceShouldBeFound("locality.contains=" + DEFAULT_LOCALITY);

        // Get all the actionServiceList where locality contains UPDATED_LOCALITY
        defaultActionServiceShouldNotBeFound("locality.contains=" + UPDATED_LOCALITY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLocalityNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where locality does not contain DEFAULT_LOCALITY
        defaultActionServiceShouldNotBeFound("locality.doesNotContain=" + DEFAULT_LOCALITY);

        // Get all the actionServiceList where locality does not contain UPDATED_LOCALITY
        defaultActionServiceShouldBeFound("locality.doesNotContain=" + UPDATED_LOCALITY);
    }


    @Test
    @Transactional
    public void getAllActionServicesByCountyIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where county equals to DEFAULT_COUNTY
        defaultActionServiceShouldBeFound("county.equals=" + DEFAULT_COUNTY);

        // Get all the actionServiceList where county equals to UPDATED_COUNTY
        defaultActionServiceShouldNotBeFound("county.equals=" + UPDATED_COUNTY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where county not equals to DEFAULT_COUNTY
        defaultActionServiceShouldNotBeFound("county.notEquals=" + DEFAULT_COUNTY);

        // Get all the actionServiceList where county not equals to UPDATED_COUNTY
        defaultActionServiceShouldBeFound("county.notEquals=" + UPDATED_COUNTY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountyIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where county in DEFAULT_COUNTY or UPDATED_COUNTY
        defaultActionServiceShouldBeFound("county.in=" + DEFAULT_COUNTY + "," + UPDATED_COUNTY);

        // Get all the actionServiceList where county equals to UPDATED_COUNTY
        defaultActionServiceShouldNotBeFound("county.in=" + UPDATED_COUNTY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountyIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where county is not null
        defaultActionServiceShouldBeFound("county.specified=true");

        // Get all the actionServiceList where county is null
        defaultActionServiceShouldNotBeFound("county.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByCountyContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where county contains DEFAULT_COUNTY
        defaultActionServiceShouldBeFound("county.contains=" + DEFAULT_COUNTY);

        // Get all the actionServiceList where county contains UPDATED_COUNTY
        defaultActionServiceShouldNotBeFound("county.contains=" + UPDATED_COUNTY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountyNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where county does not contain DEFAULT_COUNTY
        defaultActionServiceShouldNotBeFound("county.doesNotContain=" + DEFAULT_COUNTY);

        // Get all the actionServiceList where county does not contain UPDATED_COUNTY
        defaultActionServiceShouldBeFound("county.doesNotContain=" + UPDATED_COUNTY);
    }


    @Test
    @Transactional
    public void getAllActionServicesByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where country equals to DEFAULT_COUNTRY
        defaultActionServiceShouldBeFound("country.equals=" + DEFAULT_COUNTRY);

        // Get all the actionServiceList where country equals to UPDATED_COUNTRY
        defaultActionServiceShouldNotBeFound("country.equals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where country not equals to DEFAULT_COUNTRY
        defaultActionServiceShouldNotBeFound("country.notEquals=" + DEFAULT_COUNTRY);

        // Get all the actionServiceList where country not equals to UPDATED_COUNTRY
        defaultActionServiceShouldBeFound("country.notEquals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountryIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where country in DEFAULT_COUNTRY or UPDATED_COUNTRY
        defaultActionServiceShouldBeFound("country.in=" + DEFAULT_COUNTRY + "," + UPDATED_COUNTRY);

        // Get all the actionServiceList where country equals to UPDATED_COUNTRY
        defaultActionServiceShouldNotBeFound("country.in=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where country is not null
        defaultActionServiceShouldBeFound("country.specified=true");

        // Get all the actionServiceList where country is null
        defaultActionServiceShouldNotBeFound("country.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByCountryContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where country contains DEFAULT_COUNTRY
        defaultActionServiceShouldBeFound("country.contains=" + DEFAULT_COUNTRY);

        // Get all the actionServiceList where country contains UPDATED_COUNTRY
        defaultActionServiceShouldNotBeFound("country.contains=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllActionServicesByCountryNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where country does not contain DEFAULT_COUNTRY
        defaultActionServiceShouldNotBeFound("country.doesNotContain=" + DEFAULT_COUNTRY);

        // Get all the actionServiceList where country does not contain UPDATED_COUNTRY
        defaultActionServiceShouldBeFound("country.doesNotContain=" + UPDATED_COUNTRY);
    }


    @Test
    @Transactional
    public void getAllActionServicesByPostalCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where postalCode equals to DEFAULT_POSTAL_CODE
        defaultActionServiceShouldBeFound("postalCode.equals=" + DEFAULT_POSTAL_CODE);

        // Get all the actionServiceList where postalCode equals to UPDATED_POSTAL_CODE
        defaultActionServiceShouldNotBeFound("postalCode.equals=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPostalCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where postalCode not equals to DEFAULT_POSTAL_CODE
        defaultActionServiceShouldNotBeFound("postalCode.notEquals=" + DEFAULT_POSTAL_CODE);

        // Get all the actionServiceList where postalCode not equals to UPDATED_POSTAL_CODE
        defaultActionServiceShouldBeFound("postalCode.notEquals=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPostalCodeIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where postalCode in DEFAULT_POSTAL_CODE or UPDATED_POSTAL_CODE
        defaultActionServiceShouldBeFound("postalCode.in=" + DEFAULT_POSTAL_CODE + "," + UPDATED_POSTAL_CODE);

        // Get all the actionServiceList where postalCode equals to UPDATED_POSTAL_CODE
        defaultActionServiceShouldNotBeFound("postalCode.in=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPostalCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where postalCode is not null
        defaultActionServiceShouldBeFound("postalCode.specified=true");

        // Get all the actionServiceList where postalCode is null
        defaultActionServiceShouldNotBeFound("postalCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByPostalCodeContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where postalCode contains DEFAULT_POSTAL_CODE
        defaultActionServiceShouldBeFound("postalCode.contains=" + DEFAULT_POSTAL_CODE);

        // Get all the actionServiceList where postalCode contains UPDATED_POSTAL_CODE
        defaultActionServiceShouldNotBeFound("postalCode.contains=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByPostalCodeNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where postalCode does not contain DEFAULT_POSTAL_CODE
        defaultActionServiceShouldNotBeFound("postalCode.doesNotContain=" + DEFAULT_POSTAL_CODE);

        // Get all the actionServiceList where postalCode does not contain UPDATED_POSTAL_CODE
        defaultActionServiceShouldBeFound("postalCode.doesNotContain=" + UPDATED_POSTAL_CODE);
    }


    @Test
    @Transactional
    public void getAllActionServicesByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where latitude equals to DEFAULT_LATITUDE
        defaultActionServiceShouldBeFound("latitude.equals=" + DEFAULT_LATITUDE);

        // Get all the actionServiceList where latitude equals to UPDATED_LATITUDE
        defaultActionServiceShouldNotBeFound("latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLatitudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where latitude not equals to DEFAULT_LATITUDE
        defaultActionServiceShouldNotBeFound("latitude.notEquals=" + DEFAULT_LATITUDE);

        // Get all the actionServiceList where latitude not equals to UPDATED_LATITUDE
        defaultActionServiceShouldBeFound("latitude.notEquals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where latitude in DEFAULT_LATITUDE or UPDATED_LATITUDE
        defaultActionServiceShouldBeFound("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE);

        // Get all the actionServiceList where latitude equals to UPDATED_LATITUDE
        defaultActionServiceShouldNotBeFound("latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where latitude is not null
        defaultActionServiceShouldBeFound("latitude.specified=true");

        // Get all the actionServiceList where latitude is null
        defaultActionServiceShouldNotBeFound("latitude.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByLatitudeContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where latitude contains DEFAULT_LATITUDE
        defaultActionServiceShouldBeFound("latitude.contains=" + DEFAULT_LATITUDE);

        // Get all the actionServiceList where latitude contains UPDATED_LATITUDE
        defaultActionServiceShouldNotBeFound("latitude.contains=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLatitudeNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where latitude does not contain DEFAULT_LATITUDE
        defaultActionServiceShouldNotBeFound("latitude.doesNotContain=" + DEFAULT_LATITUDE);

        // Get all the actionServiceList where latitude does not contain UPDATED_LATITUDE
        defaultActionServiceShouldBeFound("latitude.doesNotContain=" + UPDATED_LATITUDE);
    }


    @Test
    @Transactional
    public void getAllActionServicesByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where longitude equals to DEFAULT_LONGITUDE
        defaultActionServiceShouldBeFound("longitude.equals=" + DEFAULT_LONGITUDE);

        // Get all the actionServiceList where longitude equals to UPDATED_LONGITUDE
        defaultActionServiceShouldNotBeFound("longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLongitudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where longitude not equals to DEFAULT_LONGITUDE
        defaultActionServiceShouldNotBeFound("longitude.notEquals=" + DEFAULT_LONGITUDE);

        // Get all the actionServiceList where longitude not equals to UPDATED_LONGITUDE
        defaultActionServiceShouldBeFound("longitude.notEquals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where longitude in DEFAULT_LONGITUDE or UPDATED_LONGITUDE
        defaultActionServiceShouldBeFound("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE);

        // Get all the actionServiceList where longitude equals to UPDATED_LONGITUDE
        defaultActionServiceShouldNotBeFound("longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where longitude is not null
        defaultActionServiceShouldBeFound("longitude.specified=true");

        // Get all the actionServiceList where longitude is null
        defaultActionServiceShouldNotBeFound("longitude.specified=false");
    }
                @Test
    @Transactional
    public void getAllActionServicesByLongitudeContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where longitude contains DEFAULT_LONGITUDE
        defaultActionServiceShouldBeFound("longitude.contains=" + DEFAULT_LONGITUDE);

        // Get all the actionServiceList where longitude contains UPDATED_LONGITUDE
        defaultActionServiceShouldNotBeFound("longitude.contains=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllActionServicesByLongitudeNotContainsSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        // Get all the actionServiceList where longitude does not contain DEFAULT_LONGITUDE
        defaultActionServiceShouldNotBeFound("longitude.doesNotContain=" + DEFAULT_LONGITUDE);

        // Get all the actionServiceList where longitude does not contain UPDATED_LONGITUDE
        defaultActionServiceShouldBeFound("longitude.doesNotContain=" + UPDATED_LONGITUDE);
    }


    @Test
    @Transactional
    public void getAllActionServicesByTypeServiceIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);
        TypeService typeService = TypeServiceResourceIT.createEntity(em);
        em.persist(typeService);
        em.flush();
        actionService.setTypeService(typeService);
        actionServiceRepository.saveAndFlush(actionService);
        Long typeServiceId = typeService.getId();

        // Get all the actionServiceList where typeService equals to typeServiceId
        defaultActionServiceShouldBeFound("typeServiceId.equals=" + typeServiceId);

        // Get all the actionServiceList where typeService equals to typeServiceId + 1
        defaultActionServiceShouldNotBeFound("typeServiceId.equals=" + (typeServiceId + 1));
    }


    @Test
    @Transactional
    public void getAllActionServicesByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        actionService.addUser(user);
        actionServiceRepository.saveAndFlush(actionService);
        Long userId = user.getId();

        // Get all the actionServiceList where user equals to userId
        defaultActionServiceShouldBeFound("userId.equals=" + userId);

        // Get all the actionServiceList where user equals to userId + 1
        defaultActionServiceShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllActionServicesByOrganisationIsEqualToSomething() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);
        Organisation organisation = OrganisationResourceIT.createEntity(em);
        em.persist(organisation);
        em.flush();
        actionService.setOrganisation(organisation);
        actionServiceRepository.saveAndFlush(actionService);
        Long organisationId = organisation.getId();

        // Get all the actionServiceList where organisation equals to organisationId
        defaultActionServiceShouldBeFound("organisationId.equals=" + organisationId);

        // Get all the actionServiceList where organisation equals to organisationId + 1
        defaultActionServiceShouldNotBeFound("organisationId.equals=" + (organisationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultActionServiceShouldBeFound(String filter) throws Exception {
        restActionServiceMockMvc.perform(get("/api/action-services?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actionService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].missionObjective").value(hasItem(DEFAULT_MISSION_OBJECTIVE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].beneficiaryNumber").value(hasItem(DEFAULT_BENEFICIARY_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].volunteerNumber").value(hasItem(DEFAULT_VOLUNTEER_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].additionalInformation").value(hasItem(DEFAULT_ADDITIONAL_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].labelAdresse").value(hasItem(DEFAULT_LABEL_ADRESSE)))
            .andExpect(jsonPath("$.[*].streetNumberAdresse").value(hasItem(DEFAULT_STREET_NUMBER_ADRESSE)))
            .andExpect(jsonPath("$.[*].routeAdresse").value(hasItem(DEFAULT_ROUTE_ADRESSE)))
            .andExpect(jsonPath("$.[*].locality").value(hasItem(DEFAULT_LOCALITY)))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)));

        // Check, that the count call also returns 1
        restActionServiceMockMvc.perform(get("/api/action-services/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultActionServiceShouldNotBeFound(String filter) throws Exception {
        restActionServiceMockMvc.perform(get("/api/action-services?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restActionServiceMockMvc.perform(get("/api/action-services/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingActionService() throws Exception {
        // Get the actionService
        restActionServiceMockMvc.perform(get("/api/action-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActionService() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        int databaseSizeBeforeUpdate = actionServiceRepository.findAll().size();

        // Update the actionService
        ActionService updatedActionService = actionServiceRepository.findById(actionService.getId()).get();
        // Disconnect from session so that the updates on updatedActionService are not directly saved in db
        em.detach(updatedActionService);
        updatedActionService
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .nature(UPDATED_NATURE)
            .missionObjective(UPDATED_MISSION_OBJECTIVE)
            .amount(UPDATED_AMOUNT)
            .beneficiaryNumber(UPDATED_BENEFICIARY_NUMBER)
            .volunteerNumber(UPDATED_VOLUNTEER_NUMBER)
            .additionalInformation(UPDATED_ADDITIONAL_INFORMATION)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .labelAdresse(UPDATED_LABEL_ADRESSE)
            .streetNumberAdresse(UPDATED_STREET_NUMBER_ADRESSE)
            .routeAdresse(UPDATED_ROUTE_ADRESSE)
            .locality(UPDATED_LOCALITY)
            .county(UPDATED_COUNTY)
            .country(UPDATED_COUNTRY)
            .postalCode(UPDATED_POSTAL_CODE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(updatedActionService);

        restActionServiceMockMvc.perform(put("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isOk());

        // Validate the ActionService in the database
        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeUpdate);
        ActionService testActionService = actionServiceList.get(actionServiceList.size() - 1);
        assertThat(testActionService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testActionService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testActionService.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testActionService.getMissionObjective()).isEqualTo(UPDATED_MISSION_OBJECTIVE);
        assertThat(testActionService.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testActionService.getBeneficiaryNumber()).isEqualTo(UPDATED_BENEFICIARY_NUMBER);
        assertThat(testActionService.getVolunteerNumber()).isEqualTo(UPDATED_VOLUNTEER_NUMBER);
        assertThat(testActionService.getAdditionalInformation()).isEqualTo(UPDATED_ADDITIONAL_INFORMATION);
        assertThat(testActionService.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testActionService.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testActionService.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testActionService.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testActionService.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testActionService.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testActionService.getLabelAdresse()).isEqualTo(UPDATED_LABEL_ADRESSE);
        assertThat(testActionService.getStreetNumberAdresse()).isEqualTo(UPDATED_STREET_NUMBER_ADRESSE);
        assertThat(testActionService.getRouteAdresse()).isEqualTo(UPDATED_ROUTE_ADRESSE);
        assertThat(testActionService.getLocality()).isEqualTo(UPDATED_LOCALITY);
        assertThat(testActionService.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testActionService.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testActionService.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testActionService.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testActionService.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingActionService() throws Exception {
        int databaseSizeBeforeUpdate = actionServiceRepository.findAll().size();

        // Create the ActionService
        ActionServiceDTO actionServiceDTO = actionServiceMapper.toDto(actionService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActionServiceMockMvc.perform(put("/api/action-services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(actionServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActionService in the database
        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActionService() throws Exception {
        // Initialize the database
        actionServiceRepository.saveAndFlush(actionService);

        int databaseSizeBeforeDelete = actionServiceRepository.findAll().size();

        // Delete the actionService
        restActionServiceMockMvc.perform(delete("/api/action-services/{id}", actionService.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActionService> actionServiceList = actionServiceRepository.findAll();
        assertThat(actionServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
