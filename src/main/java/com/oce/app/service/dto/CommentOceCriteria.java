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

    private LongFilter userId;

    public CommentOceCriteria() {
    }

    public CommentOceCriteria(CommentOceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.commentDate = other.commentDate == null ? null : other.commentDate.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
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

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
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
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        commentDate,
        userId
        );
    }

    @Override
    public String toString() {
        return "CommentOceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (commentDate != null ? "commentDate=" + commentDate + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
            "}";
    }

}
