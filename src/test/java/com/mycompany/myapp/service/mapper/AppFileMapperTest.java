package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppFileMapperTest {

    private AppFileMapper appFileMapper;

    @BeforeEach
    public void setUp() {
        appFileMapper = new AppFileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appFileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appFileMapper.fromId(null)).isNull();
    }
}
