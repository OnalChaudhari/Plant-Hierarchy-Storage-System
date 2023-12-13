package com.backend.plant1;
import lombok.Data;
import jakarta.persistence.*;
@Entity
@Data
@Table(name = "plant_hierarchy")
public class plantBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plantName;
    private String description;
    private String division;
    private String plantClass;
    @Column(name = "\"order\"")
    private String order;
    private String family;
    private String genus;

    // Many-to-One relationship for parent-child
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private plantBasic parentId;
}

