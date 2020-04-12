package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AppFileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppFile.class);
        AppFile appFile1 = new AppFile();
        appFile1.setId(1L);
        AppFile appFile2 = new AppFile();
        appFile2.setId(appFile1.getId());
        assertThat(appFile1).isEqualTo(appFile2);
        appFile2.setId(2L);
        assertThat(appFile1).isNotEqualTo(appFile2);
        appFile1.setId(null);
        assertThat(appFile1).isNotEqualTo(appFile2);
    }
}
