package com.backend.plant1.Controller;
import com.backend.plant1.Entities.RootNode;
import com.backend.plant1.Entities.plantBasic;
import com.backend.plant1.dto.PlantDTO;
import com.backend.plant1.dto.PlantDetailsDTO;
import com.backend.plant1.dto.RootDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/plants")
public class plantController {

    @Autowired
    private com.backend.plant1.Service.plantService plantService;

    @PostMapping("/addRootNode")
    public ResponseEntity<String> addRootNode(@Valid @RequestBody RootDTO rootDTO) {

        try {
            return this.plantService.addRootNode(rootDTO);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save plant: " + e.getMessage());
        }

    }


    @PostMapping("/addChildNode")
    public ResponseEntity<String> addChildNode(@Valid @RequestBody PlantDTO plantDTO) {

        try {
            this.plantService.addChildNode(plantDTO);
            return ResponseEntity.ok("Plant saved successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save plant: " + e.getMessage());
        }

    }

    @PostMapping("/addDetails")
    public ResponseEntity<String> addDetails(@Valid @RequestParam Long nodeId, @RequestParam String desc) {

        try {
            plantService.addDetails(nodeId, desc);
            return ResponseEntity.ok("Details added successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add details: " + e.getMessage());
        }

    }

    @PutMapping("/editDescription")
    public ResponseEntity<String> editDescription(@Valid @RequestParam Long nodeId, @RequestParam String newDescription) {

        try {
            plantService.editDescription(nodeId, newDescription);
            return ResponseEntity.ok("Description updated successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update description: " + e.getMessage());
        }

    }

    @DeleteMapping("/deleteNode")
    public ResponseEntity<String> deleteNode(@Valid @RequestParam Long nodeId) {

        try {
            this.plantService.deleteNode(nodeId);
            return ResponseEntity.ok("Plant deleted successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete plant: " + e.getMessage());
        }

    }

//    @GetMapping("/getLastLevelofNode")
//    public PlantDTO getLastLevelofNode(@RequestParam Long nodeId) {
//        return this.plantService.getNodeDetailsWithChildren(nodeId);
//    }

    @GetMapping("/getHierarchy")
    public Optional<plantBasic> getHierarchy(@Valid @RequestParam Long nodeId) {

//        try {
//            this.plantService.getHierarchy(nodeId);
//            return ResponseEntity.ok("Details fetched successfully!");
//        } catch (ConstraintViolationException e) {
//            return ResponseEntity.badRequest().body("Validation failed: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch details: " + e.getMessage());
//        }
            return this.plantService.getHierarchy(nodeId);
    }
    @GetMapping("/allWithChildren")
    public List<RootNode> getAllPlantsWithChildren() {
        return plantService.getAllPlantsWithChildren();
    }

    @GetMapping("/getDivisions")
    public List<String> getAllDivisions() {
        return plantService.getAllDistinctDivisions();
    }

    @GetMapping("/{division}/plant-classes")
    public List<String> getPlantClassesByDivision(@PathVariable String division) {
        return plantService.getPlantClassByDivision(division);
    }

//    @GetMapping("/{division}/{plantClass}/order")
//    public List<String> findOrdersByPlantClassAndDivision(@PathVariable String division,@PathVariable String plantClass) {
//        return plantService.findOrdersByPlantClassAndDivision(plantClass);
//    }

    @GetMapping("/{division}/{plantClass}/order")
    public List<String> getOrdersByDivisionAndPlantClass( @PathVariable String division, @PathVariable String plantClass ) {
        return plantService.getOrdersByPlantClassAndDivision(division,plantClass);
    }
    @GetMapping("/{division}/{plantClass}/{order}/family")
    public List<String> getFamilies( @PathVariable String division, @PathVariable String plantClass , @PathVariable String order ) {
        return plantService.getFamilies(division,plantClass,order);
    }

    @GetMapping("/{division}/{plantClass}/{order}/{family}/id")
    public List<Integer> getIds( @PathVariable String division, @PathVariable String plantClass , @PathVariable String order , @PathVariable String family ) {
        return plantService.getIds(division,plantClass,order , family);
    }

    @GetMapping("/plant/{plantName}")
    public plantBasic getPlantByName(@PathVariable String plantName) {
        return plantService.getPlantByName(plantName);
    }

    @GetMapping("/kingdom/{kingdom}")
    public Optional<RootNode> getKingdom(@PathVariable Long id) {
        return plantService.getKingdom(id);
    }

    @GetMapping("/getPlantDetails/{plantName}")
    public ResponseEntity<PlantDetailsDTO> getPlantDetails(@PathVariable String plantName) {
        PlantDetailsDTO plantDetails = plantService.getPlantDetailsByName(plantName);
        return ResponseEntity.ok(plantDetails);
    }

    @GetMapping("/all-names")
    public ResponseEntity<List<String>> getAllPlantNames() {
        List<String> allPlantNames = plantService.getAllPlantNames();

        if (allPlantNames.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no plant names are found
        }

        return ResponseEntity.ok(allPlantNames);
    }

    @GetMapping("/suggestions/{plantName}")
    public List<String> getPlantNameSuggestions(@PathVariable String plantName) {
        return plantService.getPlantNameSuggestions(plantName);
    }
}
