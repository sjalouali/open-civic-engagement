package com.oce.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

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

    @ManyToOne
    @JsonIgnoreProperties("appFiles")
    private Comment comment;

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

    public Comment getComment() {
        return comment;
    }

    public AppFile comment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
            "}";
    }
}
