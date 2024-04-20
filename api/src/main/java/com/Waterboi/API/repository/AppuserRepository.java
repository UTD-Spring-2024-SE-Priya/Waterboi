package com.Waterboi.API.repository;

import com.Waterboi.API.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppuserRepository extends JpaRepository<Appuser, Long> {
    Optional<Appuser> findByUsernameIgnoreCase(String username);
}
