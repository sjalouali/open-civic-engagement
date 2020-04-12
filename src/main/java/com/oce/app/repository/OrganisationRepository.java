package com.oce.app.repository;

import com.oce.app.domain.Organisation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Organisation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long>, JpaSpecificationExecutor<Organisation> {
}
