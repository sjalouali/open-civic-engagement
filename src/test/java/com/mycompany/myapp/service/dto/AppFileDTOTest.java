package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AppFileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppFileDTO.class);
        AppFileDTO appFileDTO1 = new AppFileDTO();
        appFileDTO1.setId(1L);
        AppFileDTO appFileDTO2 = new AppFileDTO();
        assertThat(appFileDTO1).isNotEqualTo(appFileDTO2);
        appFileDTO2.setId(appFileDTO1.getId());
        assertThat(appFileDTO1).isEqualTo(appFileDTO2);
        appFileDTO2.setId(2L);
        assertThat(appFileDTO1).isNotEqualTo(appFileDTO2);
        appFileDTO1.setId(null);
        assertThat(appFileDTO1).isNotEqualTo(appFileDTO2);
    }
}
