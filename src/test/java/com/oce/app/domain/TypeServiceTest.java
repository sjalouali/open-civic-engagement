package com.oce.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oce.app.web.rest.TestUtil;

public class TypeServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeService.class);
        TypeService typeService1 = new TypeService();
        typeService1.setId(1L);
        TypeService typeService2 = new TypeService();
        typeService2.setId(typeService1.getId());
        assertThat(typeService1).isEqualTo(typeService2);
        typeService2.setId(2L);
        assertThat(typeService1).isNotEqualTo(typeService2);
        typeService1.setId(null);
        assertThat(typeService1).isNotEqualTo(typeService2);
    }
}
