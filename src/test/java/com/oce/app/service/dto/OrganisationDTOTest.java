package com.oce.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oce.app.web.rest.TestUtil;

public class OrganisationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganisationDTO.class);
        OrganisationDTO organisationDTO1 = new OrganisationDTO();
        organisationDTO1.setId(1L);
        OrganisationDTO organisationDTO2 = new OrganisationDTO();
        assertThat(organisationDTO1).isNotEqualTo(organisationDTO2);
        organisationDTO2.setId(organisationDTO1.getId());
        assertThat(organisationDTO1).isEqualTo(organisationDTO2);
        organisationDTO2.setId(2L);
        assertThat(organisationDTO1).isNotEqualTo(organisationDTO2);
        organisationDTO1.setId(null);
        assertThat(organisationDTO1).isNotEqualTo(organisationDTO2);
    }
}
