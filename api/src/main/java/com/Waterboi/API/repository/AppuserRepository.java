package com.waterboi.api.repository;

import com.waterboi.api.model.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppuserRepository extends JpaRepository<Appuser, Long> {
    Appuser findByUsernameIgnoreCase(String username);
}
