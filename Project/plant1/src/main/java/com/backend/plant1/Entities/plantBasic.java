package com.backend.plant1.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
@Entity
@Data
@Table(name = "child_nodes")
public class plantBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plantName;
    @Column(name = "`desc`" )
    private String desc;

    private String genus;

    private String species;

    private String image_url;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    @JsonIgnore
    private RootNode parentId;
}
