package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TypeService;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Long>, JpaSpecificationExecutor<TypeService> {
}
