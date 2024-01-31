package com.backend.plant1.Repository;
import com.backend.plant1.Entities.RootNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RootRepository extends JpaRepository<RootNode, Long> {
    // You can add custom query methods here if needed


//    @Query("SELECT p FROM plantBasic p WHERE p.parentId.id = :parentId")
//    List<plantBasic> findByParentId(Long parentId);


//@Query("SELECT r.id FROM RootNode r WHERE r.parentId = :parentId")
//List<Long> findIdsByParentId(Long parentId);

    @Query("SELECT DISTINCT r.division FROM RootNode r")
    List<String> findDistinctDivisions();

    @Query("SELECT DISTINCT r.plantClass FROM RootNode r WHERE r.division = :division")
    List<String> findPlantClassByDivision(String division);


    @Query("SELECT DISTINCT r.order FROM RootNode r WHERE r.plantClass = :plantClass AND r.division = :division")
    List<String> findOrdersByPlantClassAndDivision( String division , String plantClass);

    @Query("SELECT DISTINCT r.family FROM RootNode r WHERE r.plantClass = :plantClass AND r.division = :division AND r.order = :order")
    List<String> findFamilies( String division , String plantClass , String order);

    @Query("SELECT DISTINCT r.id FROM RootNode r WHERE r.plantClass = :plantClass AND r.division = :division AND r.order = :order AND r.family = :family")
    List<Integer> findIds( String division , String plantClass , String order , String family);


    Optional<RootNode> findById(Long id);

}