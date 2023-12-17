package com.backend.plant1.Controller;
import com.backend.plant1.Entities.plantBasic;
import com.backend.plant1.dto.PlantDTO;
import com.backend.plant1.dto.RootDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            this.plantService.addRootNode(rootDTO);
            return ResponseEntity.ok("Plant saved successfully!");
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
}
