package com.oce.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oce.app.web.rest.TestUtil;

public class CommentOceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommentOceDTO.class);
        CommentOceDTO commentOceDTO1 = new CommentOceDTO();
        commentOceDTO1.setId(1L);
        CommentOceDTO commentOceDTO2 = new CommentOceDTO();
        assertThat(commentOceDTO1).isNotEqualTo(commentOceDTO2);
        commentOceDTO2.setId(commentOceDTO1.getId());
        assertThat(commentOceDTO1).isEqualTo(commentOceDTO2);
        commentOceDTO2.setId(2L);
        assertThat(commentOceDTO1).isNotEqualTo(commentOceDTO2);
        commentOceDTO1.setId(null);
        assertThat(commentOceDTO1).isNotEqualTo(commentOceDTO2);
    }
}
