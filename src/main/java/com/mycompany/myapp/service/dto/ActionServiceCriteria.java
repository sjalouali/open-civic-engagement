package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.mycompany.myapp.domain.enumeration.NatureService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ActionService} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ActionServiceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /action-services?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ActionServiceCriteria implements Serializable, Criteria {
    /**
     * Class for filtering NatureService
     */
    public static class NatureServiceFilter extends Filter<NatureService> {

        public NatureServiceFilter() {
        }

        public NatureServiceFilter(NatureServiceFilter filter) {
            super(filter);
        }

        @Override
        public NatureServiceFilter copy() {
            return new NatureServiceFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private NatureServiceFilter nature;

    private LongFilter amount;

    private LongFilter beneficiaryNumber;

    private LongFilter volunteerNumber;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter email;

    private StringFilter phoneNumber;

    private InstantFilter startDate;

    private InstantFilter endDate;

    private StringFilter labelAdresse;

    private StringFilter streetNumberAdresse;

    private StringFilter routeAdresse;

    private StringFilter locality;

    private StringFilter county;

    private StringFilter country;

    private StringFilter postalCode;

    private StringFilter latitude;

    private StringFilter longitude;

    private LongFilter typeServiceId;

    private LongFilter userId;

    private LongFilter organisationId;

    public ActionServiceCriteria() {
    }

    public ActionServiceCriteria(ActionServiceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.nature = other.nature == null ? null : other.nature.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.beneficiaryNumber = other.beneficiaryNumber == null ? null : other.beneficiaryNumber.copy();
        this.volunteerNumber = other.volunteerNumber == null ? null : other.volunteerNumber.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.phoneNumber = other.phoneNumber == null ? null : other.phoneNumber.copy();
        this.startDate = other.startDate == null ? null : other.startDate.copy();
        this.endDate = other.endDate == null ? null : other.endDate.copy();
        this.labelAdresse = other.labelAdresse == null ? null : other.labelAdresse.copy();
        this.streetNumberAdresse = other.streetNumberAdresse == null ? null : other.streetNumberAdresse.copy();
        this.routeAdresse = other.routeAdresse == null ? null : other.routeAdresse.copy();
        this.locality = other.locality == null ? null : other.locality.copy();
        this.county = other.county == null ? null : other.county.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.postalCode = other.postalCode == null ? null : other.postalCode.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.typeServiceId = other.typeServiceId == null ? null : other.typeServiceId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.organisationId = other.organisationId == null ? null : other.organisationId.copy();
    }

    @Override
    public ActionServiceCriteria copy() {
        return new ActionServiceCriteria(this);
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

    public NatureServiceFilter getNature() {
        return nature;
    }

    public void setNature(NatureServiceFilter nature) {
        this.nature = nature;
    }

    public LongFilter getAmount() {
        return amount;
    }

    public void setAmount(LongFilter amount) {
        this.amount = amount;
    }

    public LongFilter getBeneficiaryNumber() {
        return beneficiaryNumber;
    }

    public void setBeneficiaryNumber(LongFilter beneficiaryNumber) {
        this.beneficiaryNumber = beneficiaryNumber;
    }

    public LongFilter getVolunteerNumber() {
        return volunteerNumber;
    }

    public void setVolunteerNumber(LongFilter volunteerNumber) {
        this.volunteerNumber = volunteerNumber;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public InstantFilter getStartDate() {
        return startDate;
    }

    public void setStartDate(InstantFilter startDate) {
        this.startDate = startDate;
    }

    public InstantFilter getEndDate() {
        return endDate;
    }

    public void setEndDate(InstantFilter endDate) {
        this.endDate = endDate;
    }

    public StringFilter getLabelAdresse() {
        return labelAdresse;
    }

    public void setLabelAdresse(StringFilter labelAdresse) {
        this.labelAdresse = labelAdresse;
    }

    public StringFilter getStreetNumberAdresse() {
        return streetNumberAdresse;
    }

    public void setStreetNumberAdresse(StringFilter streetNumberAdresse) {
        this.streetNumberAdresse = streetNumberAdresse;
    }

    public StringFilter getRouteAdresse() {
        return routeAdresse;
    }

    public void setRouteAdresse(StringFilter routeAdresse) {
        this.routeAdresse = routeAdresse;
    }

    public StringFilter getLocality() {
        return locality;
    }

    public void setLocality(StringFilter locality) {
        this.locality = locality;
    }

    public StringFilter getCounty() {
        return county;
    }

    public void setCounty(StringFilter county) {
        this.county = county;
    }

    public StringFilter getCountry() {
        return country;
    }

    public void setCountry(StringFilter country) {
        this.country = country;
    }

    public StringFilter getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(StringFilter postalCode) {
        this.postalCode = postalCode;
    }

    public StringFilter getLatitude() {
        return latitude;
    }

    public void setLatitude(StringFilter latitude) {
        this.latitude = latitude;
    }

    public StringFilter getLongitude() {
        return longitude;
    }

    public void setLongitude(StringFilter longitude) {
        this.longitude = longitude;
    }

    public LongFilter getTypeServiceId() {
        return typeServiceId;
    }

    public void setTypeServiceId(LongFilter typeServiceId) {
        this.typeServiceId = typeServiceId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(LongFilter organisationId) {
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
        final ActionServiceCriteria that = (ActionServiceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(nature, that.nature) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(beneficiaryNumber, that.beneficiaryNumber) &&
            Objects.equals(volunteerNumber, that.volunteerNumber) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(labelAdresse, that.labelAdresse) &&
            Objects.equals(streetNumberAdresse, that.streetNumberAdresse) &&
            Objects.equals(routeAdresse, that.routeAdresse) &&
            Objects.equals(locality, that.locality) &&
            Objects.equals(county, that.county) &&
            Objects.equals(country, that.country) &&
            Objects.equals(postalCode, that.postalCode) &&
            Objects.equals(latitude, that.latitude) &&
            Objects.equals(longitude, that.longitude) &&
            Objects.equals(typeServiceId, that.typeServiceId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(organisationId, that.organisationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        nature,
        amount,
        beneficiaryNumber,
        volunteerNumber,
        firstName,
        lastName,
        email,
        phoneNumber,
        startDate,
        endDate,
        labelAdresse,
        streetNumberAdresse,
        routeAdresse,
        locality,
        county,
        country,
        postalCode,
        latitude,
        longitude,
        typeServiceId,
        userId,
        organisationId
        );
    }

    @Override
    public String toString() {
        return "ActionServiceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (nature != null ? "nature=" + nature + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (beneficiaryNumber != null ? "beneficiaryNumber=" + beneficiaryNumber + ", " : "") +
                (volunteerNumber != null ? "volunteerNumber=" + volunteerNumber + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
                (startDate != null ? "startDate=" + startDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (labelAdresse != null ? "labelAdresse=" + labelAdresse + ", " : "") +
                (streetNumberAdresse != null ? "streetNumberAdresse=" + streetNumberAdresse + ", " : "") +
                (routeAdresse != null ? "routeAdresse=" + routeAdresse + ", " : "") +
                (locality != null ? "locality=" + locality + ", " : "") +
                (county != null ? "county=" + county + ", " : "") +
                (country != null ? "country=" + country + ", " : "") +
                (postalCode != null ? "postalCode=" + postalCode + ", " : "") +
                (latitude != null ? "latitude=" + latitude + ", " : "") +
                (longitude != null ? "longitude=" + longitude + ", " : "") +
                (typeServiceId != null ? "typeServiceId=" + typeServiceId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (organisationId != null ? "organisationId=" + organisationId + ", " : "") +
            "}";
    }

}
