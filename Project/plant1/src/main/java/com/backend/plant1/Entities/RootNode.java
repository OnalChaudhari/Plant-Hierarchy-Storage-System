package com.backend.plant1.Entities;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.*;
@Entity
@Data
@Table(name = "root_node")
public class RootNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plantName;
    private String division;
    private String plantClass;
    @Column(name = "\"order\"")
    private String order;
    private String family;

}

