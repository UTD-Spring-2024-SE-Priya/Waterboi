package com.Waterboi.API;

import com.Waterboi.API.controller.GoalController;
import com.Waterboi.API.entity.Appuser;
import com.Waterboi.API.entity.AppuserDetails;
import com.Waterboi.API.entity.AppuserProfile;
import com.Waterboi.API.repository.AppuserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GoalControllerTest {

    @Mock
    private AppuserProfileRepository appuserProfileRepository;

    @InjectMocks
    private GoalController goalController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGoalDay() {
        //You need user details
        AppuserDetails appuserDetails = new AppuserDetails(new Appuser("testUser@gmail.com", "password123"));
        AppuserProfile userProfile = new AppuserProfile();
        userProfile.setDailyGoal(10.0);
        when(appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())).thenReturn(Optional.of(userProfile));
        double actualDailyGoal = goalController.goalDay(appuserDetails);
        assertEquals(10.0, actualDailyGoal);
    }

    @Test
    public void testGoalWeek() {
        AppuserDetails appuserDetails = new AppuserDetails(new Appuser("testUser@gmail.com", "password123"));
        AppuserProfile userProfile = new AppuserProfile();
        userProfile.setDailyGoal(10.0);
        when(appuserProfileRepository.findByAppuserId(appuserDetails.getUserId())).thenReturn(Optional.of(userProfile));
        double actualWeeklyGoal = goalController.goalWeek(appuserDetails);
        assertEquals(70.0, actualWeeklyGoal);
    }

}