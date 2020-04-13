package com.oce.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.oce.app.domain.enumeration.NatureService;

/**
 * A ActionService.
 */
@Entity
@Table(name = "action_service")
public class ActionService implements Serializable {

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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nature", nullable = false)
    private NatureService nature;

    @Lob
    @Column(name = "mission_objective")
    private String missionObjective;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "beneficiary_number")
    private Long beneficiaryNumber;

    @Column(name = "volunteer_number")
    private Long volunteerNumber;

    @Lob
    @Column(name = "additional_information")
    private String additionalInformation;

    @NotNull
    @Size(min = 3)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 10)
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "label_adresse")
    private String labelAdresse;

    @Column(name = "street_number_adresse")
    private String streetNumberAdresse;

    @Column(name = "route_adresse")
    private String routeAdresse;

    @Column(name = "locality")
    private String locality;

    @Column(name = "county")
    private String county;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Type(type = "uuid-char")
    @Column(name = "uuid_oce", length = 36)
    private UUID uuidOce;

    @Type(type = "uuid-char")
    @Column(name = "uuid_organisation", length = 36)
    private UUID uuidOrganisation;

    @Type(type = "uuid-char")
    @Column(name = "uuid_entity", length = 36)
    private UUID uuidEntity;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("actionServices")
    private TypeService typeService;

    @ManyToMany
    @JoinTable(name = "action_service_user",
               joinColumns = @JoinColumn(name = "action_service_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("actionServices")
    private Organisation organisation;

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

    public ActionService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ActionService description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NatureService getNature() {
        return nature;
    }

    public ActionService nature(NatureService nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(NatureService nature) {
        this.nature = nature;
    }

    public String getMissionObjective() {
        return missionObjective;
    }

    public ActionService missionObjective(String missionObjective) {
        this.missionObjective = missionObjective;
        return this;
    }

    public void setMissionObjective(String missionObjective) {
        this.missionObjective = missionObjective;
    }

    public Long getAmount() {
        return amount;
    }

    public ActionService amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getBeneficiaryNumber() {
        return beneficiaryNumber;
    }

    public ActionService beneficiaryNumber(Long beneficiaryNumber) {
        this.beneficiaryNumber = beneficiaryNumber;
        return this;
    }

    public void setBeneficiaryNumber(Long beneficiaryNumber) {
        this.beneficiaryNumber = beneficiaryNumber;
    }

    public Long getVolunteerNumber() {
        return volunteerNumber;
    }

    public ActionService volunteerNumber(Long volunteerNumber) {
        this.volunteerNumber = volunteerNumber;
        return this;
    }

    public void setVolunteerNumber(Long volunteerNumber) {
        this.volunteerNumber = volunteerNumber;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public ActionService additionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getFirstName() {
        return firstName;
    }

    public ActionService firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ActionService lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public ActionService email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ActionService phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public ActionService startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public ActionService endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getLabelAdresse() {
        return labelAdresse;
    }

    public ActionService labelAdresse(String labelAdresse) {
        this.labelAdresse = labelAdresse;
        return this;
    }

    public void setLabelAdresse(String labelAdresse) {
        this.labelAdresse = labelAdresse;
    }

    public String getStreetNumberAdresse() {
        return streetNumberAdresse;
    }

    public ActionService streetNumberAdresse(String streetNumberAdresse) {
        this.streetNumberAdresse = streetNumberAdresse;
        return this;
    }

    public void setStreetNumberAdresse(String streetNumberAdresse) {
        this.streetNumberAdresse = streetNumberAdresse;
    }

    public String getRouteAdresse() {
        return routeAdresse;
    }

    public ActionService routeAdresse(String routeAdresse) {
        this.routeAdresse = routeAdresse;
        return this;
    }

    public void setRouteAdresse(String routeAdresse) {
        this.routeAdresse = routeAdresse;
    }

    public String getLocality() {
        return locality;
    }

    public ActionService locality(String locality) {
        this.locality = locality;
        return this;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCounty() {
        return county;
    }

    public ActionService county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public ActionService country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public ActionService postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public ActionService latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public ActionService longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public UUID getUuidOce() {
        return uuidOce;
    }

    public ActionService uuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
        return this;
    }

    public void setUuidOce(UUID uuidOce) {
        this.uuidOce = uuidOce;
    }

    public UUID getUuidOrganisation() {
        return uuidOrganisation;
    }

    public ActionService uuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
        return this;
    }

    public void setUuidOrganisation(UUID uuidOrganisation) {
        this.uuidOrganisation = uuidOrganisation;
    }

    public UUID getUuidEntity() {
        return uuidEntity;
    }

    public ActionService uuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
        return this;
    }

    public void setUuidEntity(UUID uuidEntity) {
        this.uuidEntity = uuidEntity;
    }

    public Boolean isArchived() {
        return archived;
    }

    public ActionService archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public ActionService deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public ActionService typeService(TypeService typeService) {
        this.typeService = typeService;
        return this;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public Set<User> getUsers() {
        return users;
    }

    public ActionService users(Set<User> users) {
        this.users = users;
        return this;
    }

    public ActionService addUser(User user) {
        this.users.add(user);
        return this;
    }

    public ActionService removeUser(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public ActionService organisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionService)) {
            return false;
        }
        return id != null && id.equals(((ActionService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ActionService{" +
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
            ", uuidOce='" + getUuidOce() + "'" +
            ", uuidOrganisation='" + getUuidOrganisation() + "'" +
            ", uuidEntity='" + getUuidEntity() + "'" +
            ", archived='" + isArchived() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
