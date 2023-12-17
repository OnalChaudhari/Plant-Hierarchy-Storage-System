package com.backend.plant1.Repository;

import com.backend.plant1.Entities.plantBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface plantRepository extends JpaRepository<plantBasic, Long> {
    // You can add custom query methods here if needed


    @Query("SELECT p FROM plantBasic p WHERE p.parentId.id = :parentId")
    List<plantBasic> findByParentId(Long parentId);

}