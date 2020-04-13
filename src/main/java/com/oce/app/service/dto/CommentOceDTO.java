package com.oce.app.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.oce.app.domain.CommentOce} entity.
 */
public class CommentOceDTO implements Serializable {
    
    private Long id;

    @Lob
    private String content;

    private Instant commentDate;

    private UUID uuidOce;

    private UUID uuidOrganisation;

    private UUID uuidEntity;

    private Boolean archived;

    private Boolean deleted;


    private Long userId;

    private Long actionServiceId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Instant commentDate) {
        this.commentDate = commentDate;
    }

    public UUID getUuidOce() {
        return uuidOce;
    }

    public void setUuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
    }

    public UUID getUuidOrganisation() {
        return uuidOrganisation;
    }

    public void setUuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
    }

    public UUID getUuidEntity() {
        return uuidEntity;
    }

    public void setUuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

        CommentOceDTO commentOceDTO = (CommentOceDTO) o;
        if (commentOceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentOceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentOceDTO{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", commentDate='" + getCommentDate() + "'" +
            ", uuidOce='" + getUuidOce() + "'" +
            ", uuidOrganisation='" + getUuidOrganisation() + "'" +
            ", uuidEntity='" + getUuidEntity() + "'" +
            ", archived='" + isArchived() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", userId=" + getUserId() +
            ", actionServiceId=" + getActionServiceId() +
            "}";
    }
}
