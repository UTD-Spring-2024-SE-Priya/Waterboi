package com.Waterboi.API.repository;

import com.Waterboi.API.entity.WaterPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface WaterPostRepository extends JpaRepository<WaterPost, Long> {
    public List<WaterPost> findByAppuserId(Long appuserId);
    @Query("SELECT SUM(post.quantity) FROM WaterPost post WHERE post.appuserId = :appuserId")
    public Optional<Double> sumQuantity(Long appuserId);

    @Query("SELECT SUM(post.quantity) FROM WaterPost post WHERE post.appuserId = :appuserId AND postTime BETWEEN :begin AND :end")
    public Optional<Double> sumQuantity(Long appuserId,
                              LocalDateTime begin,
                              LocalDateTime end);
}
