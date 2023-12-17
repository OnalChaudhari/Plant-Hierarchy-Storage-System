package com.backend.plant1.dto;
import com.backend.plant1.Entities.RootNode;
import com.backend.plant1.Entities.plantBasic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
@Data
public class PlantDTO {

    private Long id;
    @NotBlank(message = "Plant Name cannot be blank")
    private String plantName;
    @NotBlank(message = "Description cannot be blank")
    private String desc;
    @NotBlank(message = "Genus cannot be blank")
    private String genus;
    @NotBlank(message = "Species cannot be blank")
    private String species;
    @NotNull(message = "Parent ID cannot be null")
    private Long parentId;

    private List<PlantDTO> childNodes;

    public plantBasic toEntity() {
        plantBasic plantBasic = new plantBasic();
        plantBasic.setPlantName(this.plantName);
        plantBasic.setDesc(this.desc);
        plantBasic.setGenus(this.genus);
        plantBasic.setSpecies(this.species);

        // Set the parent if parentId is not null
        if (this.parentId != null) {
            RootNode rootNode = new RootNode();
            rootNode.setId(this.parentId);
            plantBasic.setParentId(rootNode);
        }

        return plantBasic;
    }

    public PlantDTO(Long id, String plantName,String desc ,String genus,String species, plantBasic parentDetails, List<PlantDTO> childNode) {
        this.id = id;
        this.plantName = plantName;
        this.desc=desc;
        this.genus = genus;
        this.species = species;
        this.parentId = parentDetails != null ? parentDetails.getId() : null;
        this.childNodes = childNode;
    }
}
