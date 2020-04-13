package com.oce.app.repository;

import com.oce.app.domain.CommentOce;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the CommentOce entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentOceRepository extends JpaRepository<CommentOce, Long>, JpaSpecificationExecutor<CommentOce> {

    @Query("select commentOce from CommentOce commentOce where commentOce.user.login = ?#{principal.username}")
    List<CommentOce> findByUserIsCurrentUser();
}
