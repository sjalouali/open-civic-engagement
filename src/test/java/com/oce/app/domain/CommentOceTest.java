package com.oce.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.oce.app.web.rest.TestUtil;

public class CommentOceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommentOce.class);
        CommentOce commentOce1 = new CommentOce();
        commentOce1.setId(1L);
        CommentOce commentOce2 = new CommentOce();
        commentOce2.setId(commentOce1.getId());
        assertThat(commentOce1).isEqualTo(commentOce2);
        commentOce2.setId(2L);
        assertThat(commentOce1).isNotEqualTo(commentOce2);
        commentOce1.setId(null);
        assertThat(commentOce1).isNotEqualTo(commentOce2);
    }
}
