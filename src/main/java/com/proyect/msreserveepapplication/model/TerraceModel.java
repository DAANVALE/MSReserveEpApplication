package com.proyect.msreserveepapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TerraceModel")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TerraceModel {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "asociateTerrace", nullable = false)
    @JsonProperty("asociateTerrace")
    private AsociateTerraceModel asociateTerrace;

    @JsonProperty("name")
    @Column(name = "name", length = 50)
    private String name;

    @JsonProperty("baseSize")
    @Column(name = "baseSize")
    private Integer baseSize;

    @JsonProperty("maxSize")
    @Column(name = "maxSize")
    private Integer maxSize;

    @JsonProperty("basePrice")
    @Column(name = "basePrice")
    private Integer basePrice;

    @JsonProperty("priceAdd10")
    @Column(name = "priceAdd10")
    private Integer priceAdd10;

    @JsonProperty("direction")
    @Column(name = "direction", length = 100)
    private String direction;

    // isDeleted
    @JsonProperty("killed")
    @Column(name = "killed")
    private Byte killed = 0;

    public void setKilled(Byte killed) {
        this.killed = killed;
    }

    public Integer getId() {
        return id;
    }
}
