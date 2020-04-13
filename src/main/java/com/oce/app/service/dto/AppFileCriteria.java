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
                (commentOceId != null ? "commentOceId=" + commentOceId + ", " : "") +
                (actionServiceId != null ? "actionServiceId=" + actionServiceId + ", " : "") +
            "}";
    }

}
