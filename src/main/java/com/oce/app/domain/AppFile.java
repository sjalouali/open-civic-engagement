package com.oce.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A AppFile.
 */
@Entity
@Table(name = "app_file")
public class AppFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "extention")
    private String extention;

    @Type(type = "uuid-char")
    @Column(name = "uuid_oce", length = 36)
    private UUID uuidOce;

    @Type(type = "uuid-char")
    @Column(name = "uuid_organisation", length = 36)
    private UUID uuidOrganisation;

    @Type(type = "uuid-char")
    @Column(name = "uuid_entity", length = 36)
    private UUID uuidEntity;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("appFiles")
    private CommentOce commentOce;

    @ManyToOne
    @JsonIgnoreProperties("appFiles")
    private ActionService actionService;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AppFile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public AppFile path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileSize() {
        return fileSize;
    }

    public AppFile fileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtention() {
        return extention;
    }

    public AppFile extention(String extention) {
        this.extention = extention;
        return this;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public UUID getUuidOce() {
        return uuidOce;
    }

    public AppFile uuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
        return this;
    }

    public void setUuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
    }

    public UUID getUuidOrganisation() {
        return uuidOrganisation;
    }

    public AppFile uuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
        return this;
    }

    public void setUuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
    }

    public UUID getUuidEntity() {
        return uuidEntity;
    }

    public AppFile uuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
        return this;
    }

    public void setUuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public Boolean isArchived() {
        return archived;
    }

    public AppFile archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public AppFile deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public CommentOce getCommentOce() {
        return commentOce;
    }

    public AppFile commentOce(CommentOce commentOce) {
        this.commentOce = commentOce;
        return this;
    }

    public void setCommentOce(CommentOce commentOce) {
        this.commentOce = commentOce;
    }

    public ActionService getActionService() {
        return actionService;
    }

    public AppFile actionService(ActionService actionService) {
        this.actionService = actionService;
        return this;
    }

    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppFile)) {
            return false;
        }
        return id != null && id.equals(((AppFile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AppFile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", fileSize='" + getFileSize() + "'" +
            ", extention='" + getExtention() + "'" +
            ", uuidOce='" + getUuidOce() + "'" +
            ", uuidOrganisation='" + getUuidOrganisation() + "'" +
            ", uuidEntity='" + getUuidEntity() + "'" +
            ", archived='" + isArchived() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
