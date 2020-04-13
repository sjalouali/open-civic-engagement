package com.oce.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CommentOce.
 */
@Entity
@Table(name = "comment_oce")
public class CommentOce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "comment_date")
    private Instant commentDate;

    @ManyToOne
    @JsonIgnoreProperties("commentOces")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public CommentOce content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCommentDate() {
        return commentDate;
    }

    public CommentOce commentDate(Instant commentDate) {
        this.commentDate = commentDate;
        return this;
    }

    public void setCommentDate(Instant commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public CommentOce user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentOce)) {
            return false;
        }
        return id != null && id.equals(((CommentOce) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CommentOce{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", commentDate='" + getCommentDate() + "'" +
            "}";
    }
}
