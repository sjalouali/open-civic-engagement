package com.oce.app.web.rest;

import com.oce.app.OpenCivicEngagementApp;
import com.oce.app.domain.AppFile;
import com.oce.app.domain.CommentOce;
import com.oce.app.domain.ActionService;
import com.oce.app.repository.AppFileRepository;
import com.oce.app.service.AppFileService;
import com.oce.app.service.dto.AppFileDTO;
import com.oce.app.service.mapper.AppFileMapper;
import com.oce.app.service.dto.AppFileCriteria;
import com.oce.app.service.AppFileQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AppFileResource} REST controller.
 */
@SpringBootTest(classes = OpenCivicEngagementApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AppFileResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENTION = "AAAAAAAAAA";
    private static final String UPDATED_EXTENTION = "BBBBBBBBBB";

    @Autowired
    private AppFileRepository appFileRepository;

    @Autowired
    private AppFileMapper appFileMapper;

    @Autowired
    private AppFileService appFileService;

    @Autowired
    private AppFileQueryService appFileQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppFileMockMvc;

    private AppFile appFile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppFile createEntity(EntityManager em) {
        AppFile appFile = new AppFile()
            .name(DEFAULT_NAME)
            .path(DEFAULT_PATH)
            .fileSize(DEFAULT_FILE_SIZE)
            .extention(DEFAULT_EXTENTION);
        return appFile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppFile createUpdatedEntity(EntityManager em) {
        AppFile appFile = new AppFile()
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .fileSize(UPDATED_FILE_SIZE)
            .extention(UPDATED_EXTENTION);
        return appFile;
    }

    @BeforeEach
    public void initTest() {
        appFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppFile() throws Exception {
        int databaseSizeBeforeCreate = appFileRepository.findAll().size();

        // Create the AppFile
        AppFileDTO appFileDTO = appFileMapper.toDto(appFile);
        restAppFileMockMvc.perform(post("/api/app-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appFileDTO)))
            .andExpect(status().isCreated());

        // Validate the AppFile in the database
        List<AppFile> appFileList = appFileRepository.findAll();
        assertThat(appFileList).hasSize(databaseSizeBeforeCreate + 1);
        AppFile testAppFile = appFileList.get(appFileList.size() - 1);
        assertThat(testAppFile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAppFile.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testAppFile.getFileSize()).isEqualTo(DEFAULT_FILE_SIZE);
        assertThat(testAppFile.getExtention()).isEqualTo(DEFAULT_EXTENTION);
    }

    @Test
    @Transactional
    public void createAppFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appFileRepository.findAll().size();

        // Create the AppFile with an existing ID
        appFile.setId(1L);
        AppFileDTO appFileDTO = appFileMapper.toDto(appFile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppFileMockMvc.perform(post("/api/app-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppFile in the database
        List<AppFile> appFileList = appFileRepository.findAll();
        assertThat(appFileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = appFileRepository.findAll().size();
        // set the field null
        appFile.setName(null);

        // Create the AppFile, which fails.
        AppFileDTO appFileDTO = appFileMapper.toDto(appFile);

        restAppFileMockMvc.perform(post("/api/app-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appFileDTO)))
            .andExpect(status().isBadRequest());

        List<AppFile> appFileList = appFileRepository.findAll();
        assertThat(appFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppFiles() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList
        restAppFileMockMvc.perform(get("/api/app-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)))
            .andExpect(jsonPath("$.[*].extention").value(hasItem(DEFAULT_EXTENTION)));
    }
    
    @Test
    @Transactional
    public void getAppFile() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get the appFile
        restAppFileMockMvc.perform(get("/api/app-files/{id}", appFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appFile.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE))
            .andExpect(jsonPath("$.extention").value(DEFAULT_EXTENTION));
    }


    @Test
    @Transactional
    public void getAppFilesByIdFiltering() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        Long id = appFile.getId();

        defaultAppFileShouldBeFound("id.equals=" + id);
        defaultAppFileShouldNotBeFound("id.notEquals=" + id);

        defaultAppFileShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAppFileShouldNotBeFound("id.greaterThan=" + id);

        defaultAppFileShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAppFileShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAppFilesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where name equals to DEFAULT_NAME
        defaultAppFileShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the appFileList where name equals to UPDATED_NAME
        defaultAppFileShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAppFilesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where name not equals to DEFAULT_NAME
        defaultAppFileShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the appFileList where name not equals to UPDATED_NAME
        defaultAppFileShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAppFilesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where name in DEFAULT_NAME or UPDATED_NAME
        defaultAppFileShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the appFileList where name equals to UPDATED_NAME
        defaultAppFileShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAppFilesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where name is not null
        defaultAppFileShouldBeFound("name.specified=true");

        // Get all the appFileList where name is null
        defaultAppFileShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllAppFilesByNameContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where name contains DEFAULT_NAME
        defaultAppFileShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the appFileList where name contains UPDATED_NAME
        defaultAppFileShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAppFilesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where name does not contain DEFAULT_NAME
        defaultAppFileShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the appFileList where name does not contain UPDATED_NAME
        defaultAppFileShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllAppFilesByPathIsEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where path equals to DEFAULT_PATH
        defaultAppFileShouldBeFound("path.equals=" + DEFAULT_PATH);

        // Get all the appFileList where path equals to UPDATED_PATH
        defaultAppFileShouldNotBeFound("path.equals=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAppFilesByPathIsNotEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where path not equals to DEFAULT_PATH
        defaultAppFileShouldNotBeFound("path.notEquals=" + DEFAULT_PATH);

        // Get all the appFileList where path not equals to UPDATED_PATH
        defaultAppFileShouldBeFound("path.notEquals=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAppFilesByPathIsInShouldWork() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where path in DEFAULT_PATH or UPDATED_PATH
        defaultAppFileShouldBeFound("path.in=" + DEFAULT_PATH + "," + UPDATED_PATH);

        // Get all the appFileList where path equals to UPDATED_PATH
        defaultAppFileShouldNotBeFound("path.in=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAppFilesByPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where path is not null
        defaultAppFileShouldBeFound("path.specified=true");

        // Get all the appFileList where path is null
        defaultAppFileShouldNotBeFound("path.specified=false");
    }
                @Test
    @Transactional
    public void getAllAppFilesByPathContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where path contains DEFAULT_PATH
        defaultAppFileShouldBeFound("path.contains=" + DEFAULT_PATH);

        // Get all the appFileList where path contains UPDATED_PATH
        defaultAppFileShouldNotBeFound("path.contains=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAppFilesByPathNotContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where path does not contain DEFAULT_PATH
        defaultAppFileShouldNotBeFound("path.doesNotContain=" + DEFAULT_PATH);

        // Get all the appFileList where path does not contain UPDATED_PATH
        defaultAppFileShouldBeFound("path.doesNotContain=" + UPDATED_PATH);
    }


    @Test
    @Transactional
    public void getAllAppFilesByFileSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where fileSize equals to DEFAULT_FILE_SIZE
        defaultAppFileShouldBeFound("fileSize.equals=" + DEFAULT_FILE_SIZE);

        // Get all the appFileList where fileSize equals to UPDATED_FILE_SIZE
        defaultAppFileShouldNotBeFound("fileSize.equals=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllAppFilesByFileSizeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where fileSize not equals to DEFAULT_FILE_SIZE
        defaultAppFileShouldNotBeFound("fileSize.notEquals=" + DEFAULT_FILE_SIZE);

        // Get all the appFileList where fileSize not equals to UPDATED_FILE_SIZE
        defaultAppFileShouldBeFound("fileSize.notEquals=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllAppFilesByFileSizeIsInShouldWork() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where fileSize in DEFAULT_FILE_SIZE or UPDATED_FILE_SIZE
        defaultAppFileShouldBeFound("fileSize.in=" + DEFAULT_FILE_SIZE + "," + UPDATED_FILE_SIZE);

        // Get all the appFileList where fileSize equals to UPDATED_FILE_SIZE
        defaultAppFileShouldNotBeFound("fileSize.in=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllAppFilesByFileSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where fileSize is not null
        defaultAppFileShouldBeFound("fileSize.specified=true");

        // Get all the appFileList where fileSize is null
        defaultAppFileShouldNotBeFound("fileSize.specified=false");
    }
                @Test
    @Transactional
    public void getAllAppFilesByFileSizeContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where fileSize contains DEFAULT_FILE_SIZE
        defaultAppFileShouldBeFound("fileSize.contains=" + DEFAULT_FILE_SIZE);

        // Get all the appFileList where fileSize contains UPDATED_FILE_SIZE
        defaultAppFileShouldNotBeFound("fileSize.contains=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllAppFilesByFileSizeNotContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where fileSize does not contain DEFAULT_FILE_SIZE
        defaultAppFileShouldNotBeFound("fileSize.doesNotContain=" + DEFAULT_FILE_SIZE);

        // Get all the appFileList where fileSize does not contain UPDATED_FILE_SIZE
        defaultAppFileShouldBeFound("fileSize.doesNotContain=" + UPDATED_FILE_SIZE);
    }


    @Test
    @Transactional
    public void getAllAppFilesByExtentionIsEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where extention equals to DEFAULT_EXTENTION
        defaultAppFileShouldBeFound("extention.equals=" + DEFAULT_EXTENTION);

        // Get all the appFileList where extention equals to UPDATED_EXTENTION
        defaultAppFileShouldNotBeFound("extention.equals=" + UPDATED_EXTENTION);
    }

    @Test
    @Transactional
    public void getAllAppFilesByExtentionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where extention not equals to DEFAULT_EXTENTION
        defaultAppFileShouldNotBeFound("extention.notEquals=" + DEFAULT_EXTENTION);

        // Get all the appFileList where extention not equals to UPDATED_EXTENTION
        defaultAppFileShouldBeFound("extention.notEquals=" + UPDATED_EXTENTION);
    }

    @Test
    @Transactional
    public void getAllAppFilesByExtentionIsInShouldWork() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where extention in DEFAULT_EXTENTION or UPDATED_EXTENTION
        defaultAppFileShouldBeFound("extention.in=" + DEFAULT_EXTENTION + "," + UPDATED_EXTENTION);

        // Get all the appFileList where extention equals to UPDATED_EXTENTION
        defaultAppFileShouldNotBeFound("extention.in=" + UPDATED_EXTENTION);
    }

    @Test
    @Transactional
    public void getAllAppFilesByExtentionIsNullOrNotNull() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where extention is not null
        defaultAppFileShouldBeFound("extention.specified=true");

        // Get all the appFileList where extention is null
        defaultAppFileShouldNotBeFound("extention.specified=false");
    }
                @Test
    @Transactional
    public void getAllAppFilesByExtentionContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where extention contains DEFAULT_EXTENTION
        defaultAppFileShouldBeFound("extention.contains=" + DEFAULT_EXTENTION);

        // Get all the appFileList where extention contains UPDATED_EXTENTION
        defaultAppFileShouldNotBeFound("extention.contains=" + UPDATED_EXTENTION);
    }

    @Test
    @Transactional
    public void getAllAppFilesByExtentionNotContainsSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        // Get all the appFileList where extention does not contain DEFAULT_EXTENTION
        defaultAppFileShouldNotBeFound("extention.doesNotContain=" + DEFAULT_EXTENTION);

        // Get all the appFileList where extention does not contain UPDATED_EXTENTION
        defaultAppFileShouldBeFound("extention.doesNotContain=" + UPDATED_EXTENTION);
    }


    @Test
    @Transactional
    public void getAllAppFilesByCommentOceIsEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);
        CommentOce commentOce = CommentOceResourceIT.createEntity(em);
        em.persist(commentOce);
        em.flush();
        appFile.setCommentOce(commentOce);
        appFileRepository.saveAndFlush(appFile);
        Long commentOceId = commentOce.getId();

        // Get all the appFileList where commentOce equals to commentOceId
        defaultAppFileShouldBeFound("commentOceId.equals=" + commentOceId);

        // Get all the appFileList where commentOce equals to commentOceId + 1
        defaultAppFileShouldNotBeFound("commentOceId.equals=" + (commentOceId + 1));
    }


    @Test
    @Transactional
    public void getAllAppFilesByActionServiceIsEqualToSomething() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);
        ActionService actionService = ActionServiceResourceIT.createEntity(em);
        em.persist(actionService);
        em.flush();
        appFile.setActionService(actionService);
        appFileRepository.saveAndFlush(appFile);
        Long actionServiceId = actionService.getId();

        // Get all the appFileList where actionService equals to actionServiceId
        defaultAppFileShouldBeFound("actionServiceId.equals=" + actionServiceId);

        // Get all the appFileList where actionService equals to actionServiceId + 1
        defaultAppFileShouldNotBeFound("actionServiceId.equals=" + (actionServiceId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAppFileShouldBeFound(String filter) throws Exception {
        restAppFileMockMvc.perform(get("/api/app-files?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)))
            .andExpect(jsonPath("$.[*].extention").value(hasItem(DEFAULT_EXTENTION)));

        // Check, that the count call also returns 1
        restAppFileMockMvc.perform(get("/api/app-files/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAppFileShouldNotBeFound(String filter) throws Exception {
        restAppFileMockMvc.perform(get("/api/app-files?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAppFileMockMvc.perform(get("/api/app-files/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAppFile() throws Exception {
        // Get the appFile
        restAppFileMockMvc.perform(get("/api/app-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppFile() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        int databaseSizeBeforeUpdate = appFileRepository.findAll().size();

        // Update the appFile
        AppFile updatedAppFile = appFileRepository.findById(appFile.getId()).get();
        // Disconnect from session so that the updates on updatedAppFile are not directly saved in db
        em.detach(updatedAppFile);
        updatedAppFile
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .fileSize(UPDATED_FILE_SIZE)
            .extention(UPDATED_EXTENTION);
        AppFileDTO appFileDTO = appFileMapper.toDto(updatedAppFile);

        restAppFileMockMvc.perform(put("/api/app-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appFileDTO)))
            .andExpect(status().isOk());

        // Validate the AppFile in the database
        List<AppFile> appFileList = appFileRepository.findAll();
        assertThat(appFileList).hasSize(databaseSizeBeforeUpdate);
        AppFile testAppFile = appFileList.get(appFileList.size() - 1);
        assertThat(testAppFile.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAppFile.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testAppFile.getFileSize()).isEqualTo(UPDATED_FILE_SIZE);
        assertThat(testAppFile.getExtention()).isEqualTo(UPDATED_EXTENTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAppFile() throws Exception {
        int databaseSizeBeforeUpdate = appFileRepository.findAll().size();

        // Create the AppFile
        AppFileDTO appFileDTO = appFileMapper.toDto(appFile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppFileMockMvc.perform(put("/api/app-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appFileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppFile in the database
        List<AppFile> appFileList = appFileRepository.findAll();
        assertThat(appFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppFile() throws Exception {
        // Initialize the database
        appFileRepository.saveAndFlush(appFile);

        int databaseSizeBeforeDelete = appFileRepository.findAll().size();

        // Delete the appFile
        restAppFileMockMvc.perform(delete("/api/app-files/{id}", appFile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppFile> appFileList = appFileRepository.findAll();
        assertThat(appFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
