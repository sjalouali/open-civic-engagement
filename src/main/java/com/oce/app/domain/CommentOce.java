package com.oce.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.UUID;

/**
 * A CommentOce.
 */
@Entity
@Table(name = "comment_oce")
public class CommentOce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "comment_date")
    private Instant commentDate;

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
    @JsonIgnoreProperties("commentOces")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("commentOces")
    private ActionService actionService;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public CommentOce content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCommentDate() {
        return commentDate;
    }

    public CommentOce commentDate(Instant commentDate) {
        this.commentDate = commentDate;
        return this;
    }

    public void setCommentDate(Instant commentDate) {
        this.commentDate = commentDate;
    }

    public UUID getUuidOce() {
        return uuidOce;
    }

    public CommentOce uuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
        return this;
    }

    public void setUuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
    }

    public UUID getUuidOrganisation() {
        return uuidOrganisation;
    }

    public CommentOce uuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
        return this;
    }

    public void setUuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
    }

    public UUID getUuidEntity() {
        return uuidEntity;
    }

    public CommentOce uuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
        return this;
    }

    public void setUuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public Boolean isArchived() {
        return archived;
    }

    public CommentOce archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public CommentOce deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public CommentOce user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActionService getActionService() {
        return actionService;
    }

    public CommentOce actionService(ActionService actionService) {
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
        if (!(o instanceof CommentOce)) {
            return false;
        }
        return id != null && id.equals(((CommentOce) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CommentOce{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", commentDate='" + getCommentDate() + "'" +
            ", uuidOce='" + getUuidOce() + "'" +
            ", uuidOrganisation='" + getUuidOrganisation() + "'" +
            ", uuidEntity='" + getUuidEntity() + "'" +
            ", archived='" + isArchived() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
