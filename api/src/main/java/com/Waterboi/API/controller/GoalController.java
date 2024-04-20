package com.Waterboi.API.controller;

import com.Waterboi.API.entity.AppuserDetails;
import com.Waterboi.API.entity.AppuserProfile;
import com.Waterboi.API.repository.AppuserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goal")
public class GoalController {
    @Autowired
    private AppuserProfileRepository appuserProfileRepository;
    @GetMapping("/day")
    public double goalDay(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        AppuserProfile appuserProfile = appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())
                .orElseThrow();
        return appuserProfile.getDailyGoal();
    }
    @GetMapping("/week")
    public double goalWeek(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        AppuserProfile appuserProfile = appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())
                .orElseThrow();
        return appuserProfile.getDailyGoal() * 7;
    }

}
