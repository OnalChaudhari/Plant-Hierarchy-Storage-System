package com.backend.plant1.Service;
import com.backend.plant1.Entities.RootNode;
import com.backend.plant1.Entities.plantBasic;
import com.backend.plant1.Repository.RootRepository;
import com.backend.plant1.dto.PlantDTO;
import com.backend.plant1.dto.PlantDetailsDTO;
import com.backend.plant1.dto.RootDTO;
import com.backend.plant1.Repository.plantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class plantService {

    private final com.backend.plant1.Repository.plantRepository plantRepository;
    private final RootRepository rootRepository;

    @Autowired
    public plantService(plantRepository plantRepository, RootRepository rootRepository) {
        this.plantRepository = plantRepository;
        this.rootRepository = rootRepository;
    }

    public void addChildNode(@Valid PlantDTO plantDTO) {
        try {
            plantBasic child = plantDTO.toEntity();
            this.plantRepository.save(child);
            System.out.println("Child Node added successfully!" + child);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ResponseEntity<String> addRootNode(@Valid RootDTO rootDTO) {
        try {
            RootNode rootNode = rootDTO.toEntity();
            this.rootRepository.save(rootNode);
            System.out.println("Root Node added successfully!" + rootNode);
            return ResponseEntity.ok("Plant saved successfully!");
        } catch (Exception e) {
            // Handle any exceptions, e.g., database errors
            String errorMessage = "Error adding root node: " + e.getMessage();
            System.err.println(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    public Optional<plantBasic> deleteNode(Long nodeId) {

        Optional<plantBasic> parentOptional = this.plantRepository.findById(nodeId);


//        if(parentOptional.get().getParentId() != null) {
//            List<plantBasic> childNodes = this.plantRepository.findByParentId(nodeId);
//
//            for (plantBasic childNode : childNodes) {
//                childNode.setParentId(parentOptional.get().getParentId());
//                this.plantRepository.save(childNode);
//            }
//        }
        this.plantRepository.deleteById(nodeId);
        return parentOptional;
    }

    public void editDescription(Long nodeId, String newDescription) {
        // Check if the node with the given ID exists
        Optional<plantBasic> optionalPlant = plantRepository.findById(nodeId);

        if (optionalPlant.isPresent()) {
            plantBasic plant = optionalPlant.get();

            // Update the description
            plant.setDesc(newDescription);

            // Save the updated entity
            plantRepository.save(plant);
        } else {
            // Handle the case where the node with the given ID is not found
            throw new NoSuchElementException("Node with ID " + nodeId + " not found");
        }
    }

    public void addDetails(Long nodeId, String desc) {
        Optional<plantBasic> optionalPlant = plantRepository.findById(nodeId);

        if (optionalPlant.isPresent()) {
            plantBasic plant = optionalPlant.get();
            // Append the new details to the existing description
            String existingDescription = plant.getDesc();
            String updatedDescription = existingDescription + "\n" + desc;

            // Update the description with the new details
            plant.setDesc(updatedDescription);

            // Save the updated entity
            plantRepository.save(plant);
        } else {
            // Handle the case where the node with the given ID is not found
            throw new NoSuchElementException("Node with ID " + nodeId + " not found");
        }
    }


    public Optional<plantBasic> getHierarchy(Long nodeId) {
        Optional<plantBasic> parentOptional = this.plantRepository.findById(nodeId);
        return parentOptional;
    }


    //----------------------------------------------------------------------------------------------------------
//    public PlantDTO getNodeDetailsWithChildren(Long nodeId) {
//        Optional<plantBasic> nodeDetailsOptional = this.plantRepository.findById(nodeId);
//
//        if (nodeDetailsOptional.isPresent()) {
//            plantBasic nodeDetails = nodeDetailsOptional.get();
//            List<PlantDTO> childNodes = getChildNodesWithDetails(nodeDetails.getId());
//
//            return new PlantDTO(
//                    nodeDetails.getId(),
//                    nodeDetails.getPlantName(),
//                    nodeDetails.getDescription(),
//                    nodeDetails.getDivision(),
//                    nodeDetails.getPlantClass(),
//                    nodeDetails.getOrder(),
//                    nodeDetails.getFamily(),
//                    nodeDetails.getOrder(),
//                    nodeDetails.getParentId(),
//                    childNodes
//            );
//        } else {
//            // Handle the case when the specified node is not found
//            return null;
//        }
//    }
//
//    // Helper method to get child nodes with details
//    private List<PlantDTO> getChildNodesWithDetails(Long parentId) {
//        List<plantBasic> childNodes = this.plantRepository.findByParentId(parentId);
//        List<PlantDTO> childNodeDetails = new ArrayList<>();
//
//        for (plantBasic childNode : childNodes) {
//            PlantDTO childNodeDTO = new PlantDTO(
//                    childNode.getId(),
//                    childNode.getPlantName(),
//                    childNode.getDescription(),
//                    childNode.getDivision(),
//                    childNode.getPlantClass(),
//                    childNode.getOrder(),
//                    childNode.getFamily(),
//                    childNode.getOrder(),
//                    childNode.getParentId(),
//                    getChildNodesWithDetails(childNode.getId())
//            );
//            childNodeDetails.add(childNodeDTO);
//        }
//        return childNodeDetails;
    //   }
//....................................................
    public List<RootNode> getAllPlantsWithChildren() {
        return plantRepository.findAllParents();
    }

    public List<String> getAllDistinctDivisions() {
        return rootRepository.findDistinctDivisions();
    }

    public List<String> getPlantClassByDivision(String division) {
        return rootRepository.findPlantClassByDivision(division);
    }

    public List<String> getOrdersByPlantClassAndDivision(String division, String plantClass) {
        return rootRepository.findOrdersByPlantClassAndDivision(division, plantClass);
    }

    public List<String> getFamilies(String division, String plantClass, String order) {
        return rootRepository.findFamilies(division, plantClass, order);
    }

    public List<Integer> getIds(String division, String plantClass, String order, String family) {
        return rootRepository.findIds(division, plantClass, order, family);
    }

    public plantBasic getPlantByName(String plantName) {
        return plantRepository.findByPlantName(plantName);
    }

    public Optional<RootNode> getKingdom(Long id) {
        return rootRepository.findById(id);
    }


    public PlantDetailsDTO getPlantDetailsByName(String plantName) {
        plantBasic plant = plantRepository.findByPlantName(plantName);

        if (plant == null) {
            // Handle the case where the plant with the given name is not found
            return null;
        }
        RootNode rootNode = plant.getParentId();

        PlantDetailsDTO plantDetailsDTO = new PlantDetailsDTO();
        plantDetailsDTO.setId(plant.getId());
        plantDetailsDTO.setPlantName(plant.getPlantName());
        plantDetailsDTO.setDesc(plant.getDesc());
        plantDetailsDTO.setGenus(plant.getGenus());
        plantDetailsDTO.setSpecies(plant.getSpecies());
        plantDetailsDTO.setDivision(rootNode.getDivision());
        plantDetailsDTO.setPlantClass(rootNode.getPlantClass());
        plantDetailsDTO.setRootId(rootNode.getId());
        plantDetailsDTO.setRootPlantname(rootNode.getPlantName());
        plantDetailsDTO.setOrder(rootNode.getOrder());
        plantDetailsDTO.setFamily(rootNode.getFamily());
        plantDetailsDTO.setImage_url(plant.getImage_url());


        return plantDetailsDTO;
    }

    public List<String> getAllPlantNames() {
        List<plantBasic> allPlants = plantRepository.findAll();

        // Extract plant names using Java streams
        return allPlants.stream()
                .map(plantBasic::getPlantName)
                .collect(Collectors.toList());
    }

    public List<String> getPlantNameSuggestions(String plantName) {
        List<plantBasic> plants = plantRepository.findByPlantNameContainingIgnoreCase(plantName);
        return plants.stream().map(plantBasic::getPlantName).collect(Collectors.toList());
    }
}
