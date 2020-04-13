package com.oce.app.repository;

import com.oce.app.domain.ActionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ActionService entity.
 */
@Repository
public interface ActionServiceRepository extends JpaRepository<ActionService, Long>, JpaSpecificationExecutor<ActionService> {

    @Query(value = "select distinct actionService from ActionService actionService left join fetch actionService.users",
        countQuery = "select count(distinct actionService) from ActionService actionService")
    Page<ActionService> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct actionService from ActionService actionService left join fetch actionService.users")
    List<ActionService> findAllWithEagerRelationships();

    @Query("select actionService from ActionService actionService left join fetch actionService.users where actionService.id =:id")
    Optional<ActionService> findOneWithEagerRelationships(@Param("id") Long id);
}
