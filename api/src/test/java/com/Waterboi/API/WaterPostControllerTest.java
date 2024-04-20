package com.Waterboi.API;

import com.Waterboi.API.controller.WaterPostController;
import com.Waterboi.API.controller.WaterPostDto;
import com.Waterboi.API.entity.Appuser;
import com.Waterboi.API.entity.AppuserDetails;
import com.Waterboi.API.entity.Unit;
import com.Waterboi.API.entity.WaterPost;
import com.Waterboi.API.repository.AppuserRepository;
import com.Waterboi.API.repository.UnitRepository;
import com.Waterboi.API.repository.WaterPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WaterPostControllerTest {

    @Mock
    private WaterPostRepository waterPostRepository;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private Unit unit;

    @InjectMocks
    private WaterPostController waterPostController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(unit.getLiterMultiple()).thenReturn(1.0);
    }

    @Test
    public void testValidHydrationEvent() {
        AppuserDetails appuserDetails = new AppuserDetails(new Appuser("user@example.com", "password"));
        WaterPostDto waterPostDto = new WaterPostDto(80.0, 1L);  // Valid amount
        when(unitRepository.findById(waterPostDto.unitId())).thenReturn(Optional.of(unit));
        WaterPost expectedPost = new WaterPost(appuserDetails.getUserId(), 80.0, waterPostDto.unitId(), LocalDateTime.now());
        when(waterPostRepository.save(any(WaterPost.class))).thenReturn(expectedPost);

        WaterPost result = waterPostController.newWaterPost(appuserDetails, waterPostDto);

        assertNotNull(result);
        assertEquals(80.0, result.getQuantity());
        verify(unitRepository).findById(waterPostDto.unitId());
        verify(waterPostRepository).save(any(WaterPost.class));
    }

    @Test
    public void testInvalidHydrationEventNegativeAmount() {
        AppuserDetails appuserDetails = new AppuserDetails(new Appuser("user@example.com", "password"));
        WaterPostDto waterPostDto = new WaterPostDto(-5.0, 1L);  // Invalid negative amount

        assertThrows(IllegalArgumentException.class, () -> {
            waterPostController.newWaterPost(appuserDetails, waterPostDto);
        });

        // Check that no post is saved when the quantity is negative
        verify(waterPostRepository, never()).save(any(WaterPost.class));
    }
}
