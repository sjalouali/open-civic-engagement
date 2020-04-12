package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ActionServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActionService.class);
        ActionService actionService1 = new ActionService();
        actionService1.setId(1L);
        ActionService actionService2 = new ActionService();
        actionService2.setId(actionService1.getId());
        assertThat(actionService1).isEqualTo(actionService2);
        actionService2.setId(2L);
        assertThat(actionService1).isNotEqualTo(actionService2);
        actionService1.setId(null);
        assertThat(actionService1).isNotEqualTo(actionService2);
    }
}
