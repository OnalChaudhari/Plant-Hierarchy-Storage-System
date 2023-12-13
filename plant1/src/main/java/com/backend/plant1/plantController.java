package com.backend.plant1;
import com.backend.plant1.dto.PlantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
public class plantController {

    @Autowired
    private plantService plantService;

    @PostMapping("/addRootNode")
    public String addRootNode(@RequestBody PlantDTO plantDTO) {
        return this.plantService.addRootNode(plantDTO);
    }

    @PostMapping("/addChildNode")
    public PlantDTO addChildNode(@RequestBody PlantDTO plantDTO) {
        this.plantService.addChildNode(plantDTO);
        return plantDTO;
    }

    @PostMapping("/addDetails")
    public void addDetails(@RequestParam Long nodeId, @RequestParam String desc) {
        plantService.addDetails(nodeId, desc);
    }

    @PutMapping("/editDescription")
    public void editDescription(@RequestParam Long nodeId, @RequestParam String newDescription) {
        plantService.editDescription(nodeId, newDescription);
    }

    // API to delete given node from hierarchy
    @DeleteMapping("/deleteNode")
    public Optional<plantBasic> deleteNode(@RequestParam Long nodeId) {
        System.out.println("---------------------------------------------------"+nodeId);
      return this.plantService.deleteNode(nodeId);
    }
    @GetMapping("/getLastLevelofNode")
    public PlantDTO getLastLevelofNode(@RequestParam Long nodeId) {
        return this.plantService.getNodeDetailsWithChildren(nodeId);
    }

//    @GetMapping("/getHierarchy")
//    public PlantDTO getHierarchy(@RequestParam Long nodeId) {
//        return plantService.getHierarchy(nodeId);
//    }
}
