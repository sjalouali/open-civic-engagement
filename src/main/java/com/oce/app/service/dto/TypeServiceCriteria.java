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
 * Criteria class for the {@link com.oce.app.domain.TypeService} entity. This class is used
 * in {@link com.oce.app.web.rest.TypeServiceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /type-services?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypeServiceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameShort;

    private StringFilter nameLong;

    private StringFilter typeServiceColor;

    private UUIDFilter uuidOce;

    private UUIDFilter uuidOrganisation;

    private UUIDFilter uuidEntity;

    private BooleanFilter archived;

    private BooleanFilter deleted;

    public TypeServiceCriteria() {
    }

    public TypeServiceCriteria(TypeServiceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameShort = other.nameShort == null ? null : other.nameShort.copy();
        this.nameLong = other.nameLong == null ? null : other.nameLong.copy();
        this.typeServiceColor = other.typeServiceColor == null ? null : other.typeServiceColor.copy();
        this.uuidOce = other.uuidOce == null ? null : other.uuidOce.copy();
        this.uuidOrganisation = other.uuidOrganisation == null ? null : other.uuidOrganisation.copy();
        this.uuidEntity = other.uuidEntity == null ? null : other.uuidEntity.copy();
        this.archived = other.archived == null ? null : other.archived.copy();
        this.deleted = other.deleted == null ? null : other.deleted.copy();
    }

    @Override
    public TypeServiceCriteria copy() {
        return new TypeServiceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNameShort() {
        return nameShort;
    }

    public void setNameShort(StringFilter nameShort) {
        this.nameShort = nameShort;
    }

    public StringFilter getNameLong() {
        return nameLong;
    }

    public void setNameLong(StringFilter nameLong) {
        this.nameLong = nameLong;
    }

    public StringFilter getTypeServiceColor() {
        return typeServiceColor;
    }

    public void setTypeServiceColor(StringFilter typeServiceColor) {
        this.typeServiceColor = typeServiceColor;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TypeServiceCriteria that = (TypeServiceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nameShort, that.nameShort) &&
            Objects.equals(nameLong, that.nameLong) &&
            Objects.equals(typeServiceColor, that.typeServiceColor) &&
            Objects.equals(uuidOce, that.uuidOce) &&
            Objects.equals(uuidOrganisation, that.uuidOrganisation) &&
            Objects.equals(uuidEntity, that.uuidEntity) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nameShort,
        nameLong,
        typeServiceColor,
        uuidOce,
        uuidOrganisation,
        uuidEntity,
        archived,
        deleted
        );
    }

    @Override
    public String toString() {
        return "TypeServiceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nameShort != null ? "nameShort=" + nameShort + ", " : "") +
                (nameLong != null ? "nameLong=" + nameLong + ", " : "") +
                (typeServiceColor != null ? "typeServiceColor=" + typeServiceColor + ", " : "") +
                (uuidOce != null ? "uuidOce=" + uuidOce + ", " : "") +
                (uuidOrganisation != null ? "uuidOrganisation=" + uuidOrganisation + ", " : "") +
                (uuidEntity != null ? "uuidEntity=" + uuidEntity + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (deleted != null ? "deleted=" + deleted + ", " : "") +
            "}";
    }

}
