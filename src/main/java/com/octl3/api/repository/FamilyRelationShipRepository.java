package com.octl3.api.repository;

import com.octl3.api.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRelationShipRepository extends JpaRepository<FamilyRelationship, Long> {
}
