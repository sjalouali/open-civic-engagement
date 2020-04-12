package com.mycompany.myapp.service.dto;

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
 * Criteria class for the {@link com.mycompany.myapp.domain.TypeService} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TypeServiceResource} to receive all the possible filtering options from
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

    public TypeServiceCriteria() {
    }

    public TypeServiceCriteria(TypeServiceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameShort = other.nameShort == null ? null : other.nameShort.copy();
        this.nameLong = other.nameLong == null ? null : other.nameLong.copy();
        this.typeServiceColor = other.typeServiceColor == null ? null : other.typeServiceColor.copy();
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
            Objects.equals(typeServiceColor, that.typeServiceColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nameShort,
        nameLong,
        typeServiceColor
        );
    }

    @Override
    public String toString() {
        return "TypeServiceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nameShort != null ? "nameShort=" + nameShort + ", " : "") +
                (nameLong != null ? "nameLong=" + nameLong + ", " : "") +
                (typeServiceColor != null ? "typeServiceColor=" + typeServiceColor + ", " : "") +
            "}";
    }

}
