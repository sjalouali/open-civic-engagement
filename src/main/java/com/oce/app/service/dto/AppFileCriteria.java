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
import io.github.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.oce.app.domain.AppFile} entity. This class is used
 * in {@link com.oce.app.web.rest.AppFileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /app-files?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AppFileCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter path;

    private StringFilter fileSize;

    private StringFilter extention;

    private UUIDFilter uuidOce;

    private UUIDFilter uuidOrganisation;

    private UUIDFilter uuidEntity;

    private BooleanFilter archived;

    private BooleanFilter deleted;

    private LongFilter commentOceId;

    private LongFilter actionServiceId;

    public AppFileCriteria() {
    }

    public AppFileCriteria(AppFileCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.path = other.path == null ? null : other.path.copy();
        this.fileSize = other.fileSize == null ? null : other.fileSize.copy();
        this.extention = other.extention == null ? null : other.extention.copy();
        this.uuidOce = other.uuidOce == null ? null : other.uuidOce.copy();
        this.uuidOrganisation = other.uuidOrganisation == null ? null : other.uuidOrganisation.copy();
        this.uuidEntity = other.uuidEntity == null ? null : other.uuidEntity.copy();
        this.archived = other.archived == null ? null : other.archived.copy();
        this.deleted = other.deleted == null ? null : other.deleted.copy();
        this.commentOceId = other.commentOceId == null ? null : other.commentOceId.copy();
        this.actionServiceId = other.actionServiceId == null ? null : other.actionServiceId.copy();
    }

    @Override
    public AppFileCriteria copy() {
        return new AppFileCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getPath() {
        return path;
    }

    public void setPath(StringFilter path) {
        this.path = path;
    }

    public StringFilter getFileSize() {
        return fileSize;
    }

    public void setFileSize(StringFilter fileSize) {
        this.fileSize = fileSize;
    }

    public StringFilter getExtention() {
        return extention;
    }

    public void setExtention(StringFilter extention) {
        this.extention = extention;
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

    public LongFilter getCommentOceId() {
        return commentOceId;
    }

    public void setCommentOceId(LongFilter commentOceId) {
        this.commentOceId = commentOceId;
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
        final AppFileCriteria that = (AppFileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(path, that.path) &&
            Objects.equals(fileSize, that.fileSize) &&
            Objects.equals(extention, that.extention) &&
            Objects.equals(uuidOce, that.uuidOce) &&
            Objects.equals(uuidOrganisation, that.uuidOrganisation) &&
            Objects.equals(uuidEntity, that.uuidEntity) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(commentOceId, that.commentOceId) &&
            Objects.equals(actionServiceId, that.actionServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        path,
        fileSize,
        extention,
        uuidOce,
        uuidOrganisation,
        uuidEntity,
        archived,
        deleted,
        commentOceId,
        actionServiceId
        );
    }

    @Override
    public String toString() {
        return "AppFileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (path != null ? "path=" + path + ", " : "") +
                (fileSize != null ? "fileSize=" + fileSize + ", " : "") +
                (extention != null ? "extention=" + extention + ", " : "") +
                (uuidOce != null ? "uuidOce=" + uuidOce + ", " : "") +
                (uuidOrganisation != null ? "uuidOrganisation=" + uuidOrganisation + ", " : "") +
                (uuidEntity != null ? "uuidEntity=" + uuidEntity + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (deleted != null ? "deleted=" + deleted + ", " : "") +
                (commentOceId != null ? "commentOceId=" + commentOceId + ", " : "") +
                (actionServiceId != null ? "actionServiceId=" + actionServiceId + ", " : "") +
            "}";
    }

}
