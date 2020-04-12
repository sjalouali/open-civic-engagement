package com.oce.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.oce.app.domain.AppFile} entity.
 */
public class AppFileDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    private String path;

    private String fileSize;

    private String extention;


    private Long commentId;

    private Long actionServiceId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getActionServiceId() {
        return actionServiceId;
    }

    public void setActionServiceId(Long actionServiceId) {
        this.actionServiceId = actionServiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppFileDTO appFileDTO = (AppFileDTO) o;
        if (appFileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appFileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppFileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", fileSize='" + getFileSize() + "'" +
            ", extention='" + getExtention() + "'" +
            ", commentId=" + getCommentId() +
            ", actionServiceId=" + getActionServiceId() +
            "}";
    }
}
