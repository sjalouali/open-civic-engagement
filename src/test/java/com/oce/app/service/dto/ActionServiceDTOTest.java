package com.oce.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oce.app.web.rest.TestUtil;

public class ActionServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActionServiceDTO.class);
        ActionServiceDTO actionServiceDTO1 = new ActionServiceDTO();
        actionServiceDTO1.setId(1L);
        ActionServiceDTO actionServiceDTO2 = new ActionServiceDTO();
        assertThat(actionServiceDTO1).isNotEqualTo(actionServiceDTO2);
        actionServiceDTO2.setId(actionServiceDTO1.getId());
        assertThat(actionServiceDTO1).isEqualTo(actionServiceDTO2);
        actionServiceDTO2.setId(2L);
        assertThat(actionServiceDTO1).isNotEqualTo(actionServiceDTO2);
        actionServiceDTO1.setId(null);
        assertThat(actionServiceDTO1).isNotEqualTo(actionServiceDTO2);
    }
}
