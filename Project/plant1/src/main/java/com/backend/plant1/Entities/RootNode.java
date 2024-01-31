package com.backend.plant1.Entities;
import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@Table(name = "root_node")
public class RootNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//desc

    private String plantName;
    private String division;
    private String plantClass;
    @Column(name = "\"order\"")
    private String order;
    private String family;
    @OneToMany(mappedBy = "parentId")
    private List<plantBasic> children;

}

