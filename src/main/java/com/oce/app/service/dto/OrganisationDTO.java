package com.oce.app.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import com.oce.app.domain.enumeration.LegalStatus;

/**
 * A DTO for the {@link com.oce.app.domain.Organisation} entity.
 */
@ApiModel(description = "The Organisation entity.")
public class OrganisationDTO implements Serializable {
    
    private Long id;

    private UUID uuid;

    private UUID uuidEntity;

    @NotNull
    @Size(min = 3)
    private String name;

    @Lob
    private String description;

    private String legalId;

    private String accreditation;

    @Lob
    private String additionalInformation;

    @NotNull
    private LegalStatus legalStatus;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuidEntity() {
        return uuidEntity;
    }

    public void setUuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganisationDTO organisationDTO = (OrganisationDTO) o;
        if (organisationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organisationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganisationDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", uuidEntity='" + getUuidEntity() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", legalId='" + getLegalId() + "'" +
            ", accreditation='" + getAccreditation() + "'" +
            ", additionalInformation='" + getAdditionalInformation() + "'" +
            ", legalStatus='" + getLegalStatus() + "'" +
            "}";
    }
}
