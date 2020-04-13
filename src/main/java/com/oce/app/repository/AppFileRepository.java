package com.oce.app.repository;

import com.oce.app.domain.AppFile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AppFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppFileRepository extends JpaRepository<AppFile, Long>, JpaSpecificationExecutor<AppFile> {
}
