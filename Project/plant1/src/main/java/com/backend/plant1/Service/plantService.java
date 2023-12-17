package com.backend.plant1.Service;
import com.backend.plant1.Entities.RootNode;
import com.backend.plant1.Entities.plantBasic;
import com.backend.plant1.Repository.RootRepository;
import com.backend.plant1.dto.PlantDTO;
import com.backend.plant1.dto.RootDTO;
import com.backend.plant1.Repository.plantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


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

    public void addRootNode(@Valid RootDTO rootDTO) {
        try {
            RootNode rootNode = rootDTO.toEntity();
            this.rootRepository.save(rootNode);
            System.out.println("Root Node added successfully!" + rootNode);

        } catch (Exception e) {
            // Handle any exceptions, e.g., database errors
            e.getMessage();
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

    public void addDetails(Long nodeId,String desc) {
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
//
//
//    }


//
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


}
