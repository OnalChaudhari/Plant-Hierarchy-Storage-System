package com.backend.plant1.dto;

import com.backend.plant1.plantBasic;
import lombok.Data;

import java.util.List;

@Data
public class PlantDTO {

    private Long id;
    private String plantName;
    private String description;
    private String division;
    private String plantClass;
    private String order;
    private String family;
    private String genus;
    private Long parentId;
    private List<PlantDTO> childNodes;


   // private List<PlantDTO> children;


    public plantBasic toEntity() {
        plantBasic plantBasic = new plantBasic();
        plantBasic.setPlantName(this.plantName);
        plantBasic.setDescription(this.description);
        plantBasic.setDivision(this.division);
        plantBasic.setPlantClass(this.plantClass);
        plantBasic.setOrder(this.order);
        plantBasic.setFamily(this.family);
        plantBasic.setGenus(this.genus);

        // Set the parent if parentId is not null
        if (this.parentId != null) {
            plantBasic parent = new plantBasic();
            parent.setId(this.parentId);
            plantBasic.setParentId(parent);
        }

        return plantBasic;
    }

    public PlantDTO(Long id, String plantName, String description, String division, String plantClass, String order, String family, String genus, plantBasic parentDetails, List<PlantDTO> childNode) {
        this.id = id;
        this.plantName = plantName;
        this.description = description;
        this.division = division;
        this.plantClass = plantClass;
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.parentId = parentDetails != null ? parentDetails.getId() : null;
        this.childNodes = childNode;

    }

//            PlantBasicDTO dto = // ... obtain the DTO from somewhere
//            PlantBasic plantBasic = dto.toEntity();


    public PlantDTO() {
    }
    public PlantDTO(String plantName, String description ) {
        this.plantName = plantName;
        this.description = description;
    }


//    public String getPlantName() {
//        return plantName;
//    }
//
//    public void setPlantName(String plantName) {
//        this.plantName = plantName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

}
