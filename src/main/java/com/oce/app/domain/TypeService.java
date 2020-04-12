package com.oce.app.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TypeService.
 */
@Entity
@Table(name = "type_service")
public class TypeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name_short", nullable = false)
    private String nameShort;

    @Column(name = "name_long")
    private String nameLong;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "type_service_color")
    private String typeServiceColor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameShort() {
        return nameShort;
    }

    public TypeService nameShort(String nameShort) {
        this.nameShort = nameShort;
        return this;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getNameLong() {
        return nameLong;
    }

    public TypeService nameLong(String nameLong) {
        this.nameLong = nameLong;
        return this;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getDescription() {
        return description;
    }

    public TypeService description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeServiceColor() {
        return typeServiceColor;
    }

    public TypeService typeServiceColor(String typeServiceColor) {
        this.typeServiceColor = typeServiceColor;
        return this;
    }

    public void setTypeServiceColor(String typeServiceColor) {
        this.typeServiceColor = typeServiceColor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeService)) {
            return false;
        }
        return id != null && id.equals(((TypeService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TypeService{" +
            "id=" + getId() +
            ", nameShort='" + getNameShort() + "'" +
            ", nameLong='" + getNameLong() + "'" +
            ", description='" + getDescription() + "'" +
            ", typeServiceColor='" + getTypeServiceColor() + "'" +
            "}";
    }
}
