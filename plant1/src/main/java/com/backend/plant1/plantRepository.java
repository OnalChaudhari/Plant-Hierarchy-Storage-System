package com.backend.plant1;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface plantRepository extends JpaRepository<plantBasic, Long> {
    // You can add custom query methods here if needed


    @Query("SELECT p FROM plantBasic p WHERE p.parentId.id = :parentId")
    List<plantBasic> findByParentId(Long parentId);

}