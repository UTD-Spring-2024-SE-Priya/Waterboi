package com.Waterboi.API.controller;

import com.Waterboi.API.entity.AppuserDetails;
import com.Waterboi.API.entity.Unit;
import com.Waterboi.API.entity.WaterPost;
import com.Waterboi.API.repository.AppuserRepository;
import com.Waterboi.API.repository.UnitRepository;
import com.Waterboi.API.repository.WaterPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/post")
public class WaterPostController {

    @Autowired
    private AppuserRepository appuserRepository;
    @Autowired
    private WaterPostRepository waterPostRepository;
    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("/units")
    public List<Unit> getUnitsOfMeasure() {
        return unitRepository.findAll();
    }

    @GetMapping("/all")
    public List<WaterPost> getAllPosts(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        Long userId = appuserDetails.getUserId();
        return waterPostRepository.findByAppuserId(userId);
    }

    @PostMapping("/new")
    public WaterPost newWaterPost(@AuthenticationPrincipal AppuserDetails appuserDetails,
                                  @RequestBody WaterPostDto waterPostDto) {
        Long userId = appuserDetails.getUserId();
        if (waterPostDto.quantity() < 0.0) {throw new IllegalArgumentException("Quantity cannot be negative.");}

        //quantity of water drank is converted to liters before saving in the repository
        double quantity = waterPostDto.quantity() * unitRepository.findById(waterPostDto.unitId())
                .orElseThrow(() -> new NoSuchElementException("Unit ID not found"))
                .getLiterMultiple();

        return waterPostRepository.save(new WaterPost(
                userId,
                quantity,
                waterPostDto.unitId(),
                LocalDateTime.now()));
    }
    @GetMapping("/sum")
    public double getSum(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        return waterPostRepository.sumQuantity(appuserDetails.getUserId()).orElse(0.0);
    }
    @GetMapping("sum/day")
    public double getSumDay(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        return waterPostRepository.sumQuantity(
                appuserDetails.getUserId(),
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now()
        ).orElse(0.0);
    }
    @GetMapping("/sum/week")
    public double getSumWeek(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        return waterPostRepository.sumQuantity(
                appuserDetails.getUserId(),
                LocalDateTime.now().minusWeeks(1L),
                LocalDateTime.now()
        ).orElse(0.0);
    }
    @GetMapping("/sum/month")
    public double getSumMonth(@AuthenticationPrincipal AppuserDetails appuserDetails) {
        return waterPostRepository.sumQuantity(
                appuserDetails.getUserId(),
                LocalDateTime.now().minusMonths(1L),
                LocalDateTime.now()
        ).orElse(0.0);
    }
}
