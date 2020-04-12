package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TypeService} entity.
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
            "}";
    }
}
