package com.oce.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentOceMapperTest {

    private CommentOceMapper commentOceMapper;

    @BeforeEach
    public void setUp() {
        commentOceMapper = new CommentOceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commentOceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commentOceMapper.fromId(null)).isNull();
    }
}
