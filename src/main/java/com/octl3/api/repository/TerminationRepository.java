package com.octl3.api.repository;

import com.octl3.api.entity.Termination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminationRepository extends JpaRepository<Termination, Long> {
}
