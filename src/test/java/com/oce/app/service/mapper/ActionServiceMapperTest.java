package com.oce.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActionServiceMapperTest {

    private ActionServiceMapper actionServiceMapper;

    @BeforeEach
    public void setUp() {
        actionServiceMapper = new ActionServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(actionServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(actionServiceMapper.fromId(null)).isNull();
    }
}
