package com.devinhouse.devagro.repositories;

import com.devinhouse.devagro.models.Grain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrainRepository extends JpaRepository<Grain,Long> {
}
