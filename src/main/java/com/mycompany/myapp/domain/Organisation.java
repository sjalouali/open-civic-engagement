package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.LegalStatus;

/**
 * The Organisation entity.
 */
@Entity
@Table(name = "organisation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "legal_id")
    private String legalId;

    @Column(name = "accreditation")
    private String accreditation;

    @Lob
    @Column(name = "additional_information")
    private String additionalInformation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "legal_status", nullable = false)
    private LegalStatus legalStatus;

    @OneToMany(mappedBy = "organisation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ActionService> actionServices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Organisation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Organisation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLegalId() {
        return legalId;
    }

    public Organisation legalId(String legalId) {
        this.legalId = legalId;
        return this;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public Organisation accreditation(String accreditation) {
        this.accreditation = accreditation;
        return this;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public Organisation additionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    public Organisation legalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
        return this;
    }

    public void setLegalStatus(LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }

    public Set<ActionService> getActionServices() {
        return actionServices;
    }

    public Organisation actionServices(Set<ActionService> actionServices) {
        this.actionServices = actionServices;
        return this;
    }

    public Organisation addActionService(ActionService actionService) {
        this.actionServices.add(actionService);
        actionService.setOrganisation(this);
        return this;
    }

    public Organisation removeActionService(ActionService actionService) {
        this.actionServices.remove(actionService);
        actionService.setOrganisation(null);
        return this;
    }

    public void setActionServices(Set<ActionService> actionServices) {
        this.actionServices = actionServices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organisation)) {
            return false;
        }
        return id != null && id.equals(((Organisation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organisation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", legalId='" + getLegalId() + "'" +
            ", accreditation='" + getAccreditation() + "'" +
            ", additionalInformation='" + getAdditionalInformation() + "'" +
            ", legalStatus='" + getLegalStatus() + "'" +
            "}";
    }
}
