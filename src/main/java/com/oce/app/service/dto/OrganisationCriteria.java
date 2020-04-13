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

    private StringFilter name;

    private StringFilter legalId;

    private StringFilter accreditation;

    private LegalStatusFilter legalStatus;

    private UUIDFilter uuidOce;

    private UUIDFilter uuidOrganisation;

    private UUIDFilter uuidEntity;

    private BooleanFilter archived;

    private BooleanFilter deleted;

    private LongFilter actionServiceId;

    public OrganisationCriteria() {
    }

    public OrganisationCriteria(OrganisationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.legalId = other.legalId == null ? null : other.legalId.copy();
        this.accreditation = other.accreditation == null ? null : other.accreditation.copy();
        this.legalStatus = other.legalStatus == null ? null : other.legalStatus.copy();
        this.uuidOce = other.uuidOce == null ? null : other.uuidOce.copy();
        this.uuidOrganisation = other.uuidOrganisation == null ? null : other.uuidOrganisation.copy();
        this.uuidEntity = other.uuidEntity == null ? null : other.uuidEntity.copy();
        this.archived = other.archived == null ? null : other.archived.copy();
        this.deleted = other.deleted == null ? null : other.deleted.copy();
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
            Objects.equals(name, that.name) &&
            Objects.equals(legalId, that.legalId) &&
            Objects.equals(accreditation, that.accreditation) &&
            Objects.equals(legalStatus, that.legalStatus) &&
            Objects.equals(uuidOce, that.uuidOce) &&
            Objects.equals(uuidOrganisation, that.uuidOrganisation) &&
            Objects.equals(uuidEntity, that.uuidEntity) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(actionServiceId, that.actionServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        legalId,
        accreditation,
        legalStatus,
        uuidOce,
        uuidOrganisation,
        uuidEntity,
        archived,
        deleted,
        actionServiceId
        );
    }

    @Override
    public String toString() {
        return "OrganisationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (legalId != null ? "legalId=" + legalId + ", " : "") +
                (accreditation != null ? "accreditation=" + accreditation + ", " : "") +
                (legalStatus != null ? "legalStatus=" + legalStatus + ", " : "") +
                (uuidOce != null ? "uuidOce=" + uuidOce + ", " : "") +
                (uuidOrganisation != null ? "uuidOrganisation=" + uuidOrganisation + ", " : "") +
                (uuidEntity != null ? "uuidEntity=" + uuidEntity + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (deleted != null ? "deleted=" + deleted + ", " : "") +
                (actionServiceId != null ? "actionServiceId=" + actionServiceId + ", " : "") +
            "}";
    }

}
