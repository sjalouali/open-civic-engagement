package com.oce.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.oce.app.domain.TypeService} entity.
 */
public class TypeServiceDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3)
    private String nameShort;

    private String nameLong;

    @Lob
    private String description;

    private String typeServiceColor;

    private UUID uuidOce;

    private UUID uuidOrganisation;

    private UUID uuidEntity;

    private Boolean archived;

    private Boolean deleted;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeServiceColor() {
        return typeServiceColor;
    }

    public void setTypeServiceColor(String typeServiceColor) {
        this.typeServiceColor = typeServiceColor;
    }

    public UUID getUuidOce() {
        return uuidOce;
    }

    public void setUuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
    }

    public UUID getUuidOrganisation() {
        return uuidOrganisation;
    }

    public void setUuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
    }

    public UUID getUuidEntity() {
        return uuidEntity;
    }

    public void setUuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
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

        TypeServiceDTO typeServiceDTO = (TypeServiceDTO) o;
        if (typeServiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeServiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeServiceDTO{" +
            "id=" + getId() +
            ", nameShort='" + getNameShort() + "'" +
            ", nameLong='" + getNameLong() + "'" +
            ", description='" + getDescription() + "'" +
            ", typeServiceColor='" + getTypeServiceColor() + "'" +
            ", uuidOce='" + getUuidOce() + "'" +
            ", uuidOrganisation='" + getUuidOrganisation() + "'" +
            ", uuidEntity='" + getUuidEntity() + "'" +
            ", archived='" + isArchived() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
