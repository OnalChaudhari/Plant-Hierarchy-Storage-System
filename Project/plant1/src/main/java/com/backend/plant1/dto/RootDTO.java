package com.backend.plant1.dto;
import com.backend.plant1.Entities.RootNode;
import com.backend.plant1.Entities.plantBasic;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RootDTO extends plantBasic{

    private Long id;

    @NotBlank(message = "Plant name cannot be blank")
    private String plantName;
    @NotBlank(message = "Division cannot be blank")
    private String division;
    @NotBlank(message = "Plant class cannot be blank")
    private String plantClass;
    @NotBlank(message = "Order cannot be blank")
    private String order;
    @NotBlank(message = "Family cannot be blank")
    private String family;

    private List<PlantDTO> childNodes;

    public RootNode toEntity() {
        RootNode rootNode = new RootNode();
        rootNode.setPlantName(this.plantName);
        rootNode.setDivision(this.division);
        rootNode.setPlantClass(this.plantClass);
        rootNode.setOrder(this.order);
        rootNode.setFamily(this.family);
        return rootNode;
    }
    public RootDTO(Long id, String plantName,String division,String plantClass, String order , String family) {
        this.id = id;
        this.plantName = plantName;
        this.division = division;
        this.plantClass = plantClass;
        this.order=order;
        this.family=family;
    }

}
