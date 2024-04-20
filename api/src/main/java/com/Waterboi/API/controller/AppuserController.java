package com.Waterboi.API.controller;

import com.Waterboi.API.entity.AppuserDetails;
import com.Waterboi.API.entity.AppuserProfile;
import com.Waterboi.API.repository.AppuserProfileRepository;
import com.Waterboi.API.repository.AppuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class AppuserController {
    @Autowired
    private AppuserRepository appuserRepository;
    @Autowired
    private AppuserProfileRepository appuserProfileRepository;
    @GetMapping("/id")
    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppuserDetails) {
            AppuserDetails appuserDetails = (AppuserDetails) authentication.getPrincipal();
            return appuserDetails.getUserId();
        } else {throw new AuthenticationCredentialsNotFoundException("User is not authenticated");}
    }
    private record ProfileDto(double goal) {}
    @GetMapping("/profile")
    public ProfileDto getAppuserProfile(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        AppuserProfile appuserProfile = appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return new ProfileDto(appuserProfile.getDailyGoal());
    }

    @PostMapping("/profile")
    public ProfileDto setAppuserProfile(@AuthenticationPrincipal AppuserDetails appuserDetails, @RequestBody ProfileDto profileDto) {
        if (profileDto.goal() <= 0 || profileDto.goal() >= 1000) {
            throw new IllegalArgumentException("Invalid daily goal value");
        }

        AppuserProfile appuserProfile = appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        appuserProfile.setDailyGoal(profileDto.goal());
        appuserProfileRepository.save(appuserProfile);
        return profileDto;
    }

    private record GoalDto(double goal) {}
    @PostMapping("/profile/goal/day")
    public double setDailyGoal(@AuthenticationPrincipal AppuserDetails appuserDetails, @RequestBody GoalDto goalDto) {
        AppuserProfile appuserProfile = appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        appuserProfile.setDailyGoal(goalDto.goal());
        appuserProfileRepository.save(appuserProfile);
        return goalDto.goal();
    }

}
