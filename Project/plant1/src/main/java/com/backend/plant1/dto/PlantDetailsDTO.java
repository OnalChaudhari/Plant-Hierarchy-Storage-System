package com.backend.plant1.dto;

import com.backend.plant1.Entities.RootNode;
import com.backend.plant1.Entities.plantBasic;
import lombok.Data;

import java.util.Optional;

@Data
public class PlantDetailsDTO {
    private Long id;
    private String plantName;
    private String desc;
    private String genus;
    private String species;
    private String division;
    private String plantClass;
    private Long rootId;
    private String rootPlantname;
    private String order;
    private String family;
    private String image_url;



    // Default no-argument constructor (automatically provided by the compiler)
    public PlantDetailsDTO() {
    }
    public PlantDetailsDTO(Long id, String plantName, String desc, String genus, String species,
                           String division, String plantClass, String order, String family,String image_url, String rootPlantname, Long rootId) {
        this.id = id;
        this.plantName = plantName;
        this.desc = desc;
        this.genus = genus;
        this.species = species;
        this.division = division;
        this.plantClass = plantClass;
        this.order = order;
        this.family = family;
        this.rootPlantname = rootPlantname;
        this.rootId = rootId;
        this.image_url=image_url;
    }

}
