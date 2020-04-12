package com.oce.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.oce.app.domain.enumeration.NatureService;

/**
 * A DTO for the {@link com.oce.app.domain.ActionService} entity.
 */
public class ActionServiceDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @Lob
    private String description;

    @NotNull
    private NatureService nature;

    @Lob
    private String missionObjective;

    private Long amount;

    private Long beneficiaryNumber;

    private Long volunteerNumber;

    @Lob
    private String additionalInformation;

    @NotNull
    @Size(min = 3)
    private String firstName;

    @NotNull
    @Size(min = 3)
    private String lastName;

    private String email;

    @NotNull
    @Size(min = 10)
    private String phoneNumber;

    private Instant startDate;

    private Instant endDate;

    private String labelAdresse;

    private String streetNumberAdresse;

    private String routeAdresse;

    private String locality;

    private String county;

    private String country;

    private String postalCode;

    private String latitude;

    private String longitude;


    private Long typeServiceId;
    private Set<UserDTO> users = new HashSet<>();

    private Long organisationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public NatureService getNature() {
        return nature;
    }

    public void setNature(NatureService nature) {
        this.nature = nature;
    }

    public String getMissionObjective() {
        return missionObjective;
    }

    public void setMissionObjective(String missionObjective) {
        this.missionObjective = missionObjective;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBeneficiaryNumber() {
        return beneficiaryNumber;
    }

    public void setBeneficiaryNumber(Long beneficiaryNumber) {
        this.beneficiaryNumber = beneficiaryNumber;
    }

    public Long getVolunteerNumber() {
        return volunteerNumber;
    }

    public void setVolunteerNumber(Long volunteerNumber) {
        this.volunteerNumber = volunteerNumber;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getLabelAdresse() {
        return labelAdresse;
    }

    public void setLabelAdresse(String labelAdresse) {
        this.labelAdresse = labelAdresse;
    }

    public String getStreetNumberAdresse() {
        return streetNumberAdresse;
    }

    public void setStreetNumberAdresse(String streetNumberAdresse) {
        this.streetNumberAdresse = streetNumberAdresse;
    }

    public String getRouteAdresse() {
        return routeAdresse;
    }

    public void setRouteAdresse(String routeAdresse) {
        this.routeAdresse = routeAdresse;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getTypeServiceId() {
        return typeServiceId;
    }

    public void setTypeServiceId(Long typeServiceId) {
        this.typeServiceId = typeServiceId;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionServiceDTO actionServiceDTO = (ActionServiceDTO) o;
        if (actionServiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actionServiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActionServiceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", nature='" + getNature() + "'" +
            ", missionObjective='" + getMissionObjective() + "'" +
            ", amount=" + getAmount() +
            ", beneficiaryNumber=" + getBeneficiaryNumber() +
            ", volunteerNumber=" + getVolunteerNumber() +
            ", additionalInformation='" + getAdditionalInformation() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", labelAdresse='" + getLabelAdresse() + "'" +
            ", streetNumberAdresse='" + getStreetNumberAdresse() + "'" +
            ", routeAdresse='" + getRouteAdresse() + "'" +
            ", locality='" + getLocality() + "'" +
            ", county='" + getCounty() + "'" +
            ", country='" + getCountry() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", typeServiceId=" + getTypeServiceId() +
            ", users='" + getUsers() + "'" +
            ", organisationId=" + getOrganisationId() +
            "}";
    }
}
