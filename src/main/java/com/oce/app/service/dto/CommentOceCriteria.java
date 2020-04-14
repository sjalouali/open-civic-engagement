package com.oce.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.oce.app.domain.CommentOce} entity. This class is used
 * in {@link com.oce.app.web.rest.CommentOceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /comment-oces?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CommentOceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter commentDate;

    private UUIDFilter uuidOce;

    private UUIDFilter uuidOrganisation;

    private UUIDFilter uuidEntity;

    private BooleanFilter archived;

    private BooleanFilter deleted;

    private LongFilter userId;

    private LongFilter actionServiceId;

    public CommentOceCriteria() {
    }

    public CommentOceCriteria(CommentOceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.commentDate = other.commentDate == null ? null : other.commentDate.copy();
        this.uuidOce = other.uuidOce == null ? null : other.uuidOce.copy();
        this.uuidOrganisation = other.uuidOrganisation == null ? null : other.uuidOrganisation.copy();
        this.uuidEntity = other.uuidEntity == null ? null : other.uuidEntity.copy();
        this.archived = other.archived == null ? null : other.archived.copy();
        this.deleted = other.deleted == null ? null : other.deleted.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.actionServiceId = other.actionServiceId == null ? null : other.actionServiceId.copy();
    }

    @Override
    public CommentOceCriteria copy() {
        return new CommentOceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(InstantFilter commentDate) {
        this.commentDate = commentDate;
    }

    public UUIDFilter getUuidOce() {
        return uuidOce;
    }

    public void setUuidOce(UUIDFilter uuidOce) {
        this.uuidOce = uuidOce;
    }

    public UUIDFilter getUuidOrganisation() {
        return uuidOrganisation;
    }

    public void setUuidOrganisation(UUIDFilter uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
    }

    public UUIDFilter getUuidEntity() {
        return uuidEntity;
    }

    public void setUuidEntity(UUIDFilter uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public BooleanFilter getArchived() {
        return archived;
    }

    public void setArchived(BooleanFilter archived) {
        this.archived = archived;
    }

    public BooleanFilter getDeleted() {
        return deleted;
    }

    public void setDeleted(BooleanFilter deleted) {
        this.deleted = deleted;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getActionServiceId() {
        return actionServiceId;
    }

    public void setActionServiceId(LongFilter actionServiceId) {
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
        final CommentOceCriteria that = (CommentOceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(commentDate, that.commentDate) &&
            Objects.equals(uuidOce, that.uuidOce) &&
            Objects.equals(uuidOrganisation, that.uuidOrganisation) &&
            Objects.equals(uuidEntity, that.uuidEntity) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(actionServiceId, that.actionServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        commentDate,
        uuidOce,
        uuidOrganisation,
        uuidEntity,
        archived,
        deleted,
        userId,
        actionServiceId
        );
    }

    @Override
    public String toString() {
        return "CommentOceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (commentDate != null ? "commentDate=" + commentDate + ", " : "") +
                (uuidOce != null ? "uuidOce=" + uuidOce + ", " : "") +
                (uuidOrganisation != null ? "uuidOrganisation=" + uuidOrganisation + ", " : "") +
                (uuidEntity != null ? "uuidEntity=" + uuidEntity + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (deleted != null ? "deleted=" + deleted + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (actionServiceId != null ? "actionServiceId=" + actionServiceId + ", " : "") +
            "}";
    }

}
