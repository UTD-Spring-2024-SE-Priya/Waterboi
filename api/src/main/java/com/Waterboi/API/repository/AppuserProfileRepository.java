package com.Waterboi.API.repository;

import com.Waterboi.API.entity.AppuserProfile;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface AppuserProfileRepository extends JpaRepository<AppuserProfile, Long> {
    public Optional<AppuserProfile> findByAppuserId(Long appuserProfileId);
}
