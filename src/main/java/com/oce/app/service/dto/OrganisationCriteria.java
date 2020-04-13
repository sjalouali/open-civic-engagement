package com.oce.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.oce.app.domain.enumeration.LegalStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.oce.app.domain.Organisation} entity. This class is used
 * in {@link com.oce.app.web.rest.OrganisationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /organisations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrganisationCriteria implements Serializable, Criteria {
    /**
     * Class for filtering LegalStatus
     */
    public static class LegalStatusFilter extends Filter<LegalStatus> {

        public LegalStatusFilter() {
        }

        public LegalStatusFilter(LegalStatusFilter filter) {
            super(filter);
        }

        @Override
        public LegalStatusFilter copy() {
            return new LegalStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private UUIDFilter uuid;

    private UUIDFilter uuidEntity;

    private StringFilter name;

    private StringFilter legalId;

    private StringFilter accreditation;

    private LegalStatusFilter legalStatus;

    private LongFilter actionServiceId;

    public OrganisationCriteria() {
    }

    public OrganisationCriteria(OrganisationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.uuidEntity = other.uuidEntity == null ? null : other.uuidEntity.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.legalId = other.legalId == null ? null : other.legalId.copy();
        this.accreditation = other.accreditation == null ? null : other.accreditation.copy();
        this.legalStatus = other.legalStatus == null ? null : other.legalStatus.copy();
        this.actionServiceId = other.actionServiceId == null ? null : other.actionServiceId.copy();
    }

    @Override
    public OrganisationCriteria copy() {
        return new OrganisationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public UUIDFilter getUuid() {
        return uuid;
    }

    public void setUuid(UUIDFilter uuid) {
        this.uuid = uuid;
    }

    public UUIDFilter getUuidEntity() {
        return uuidEntity;
    }

    public void setUuidEntity(UUIDFilter uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getLegalId() {
        return legalId;
    }

    public void setLegalId(StringFilter legalId) {
        this.legalId = legalId;
    }

    public StringFilter getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(StringFilter accreditation) {
        this.accreditation = accreditation;
    }

    public LegalStatusFilter getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(LegalStatusFilter legalStatus) {
        this.legalStatus = legalStatus;
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
        final OrganisationCriteria that = (OrganisationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(uuidEntity, that.uuidEntity) &&
            Objects.equals(name, that.name) &&
            Objects.equals(legalId, that.legalId) &&
            Objects.equals(accreditation, that.accreditation) &&
            Objects.equals(legalStatus, that.legalStatus) &&
            Objects.equals(actionServiceId, that.actionServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        uuidEntity,
        name,
        legalId,
        accreditation,
        legalStatus,
        actionServiceId
        );
    }

    @Override
    public String toString() {
        return "OrganisationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (uuidEntity != null ? "uuidEntity=" + uuidEntity + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (legalId != null ? "legalId=" + legalId + ", " : "") +
                (accreditation != null ? "accreditation=" + accreditation + ", " : "") +
                (legalStatus != null ? "legalStatus=" + legalStatus + ", " : "") +
                (actionServiceId != null ? "actionServiceId=" + actionServiceId + ", " : "") +
            "}";
    }

}
