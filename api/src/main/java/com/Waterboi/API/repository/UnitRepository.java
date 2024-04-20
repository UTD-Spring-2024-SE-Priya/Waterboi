package com.Waterboi.API.repository;

import com.Waterboi.API.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UnitRepository extends JpaRepository<Unit, Long> {
    public Optional<Unit> findByNameIgnoreCase(String name);
}
