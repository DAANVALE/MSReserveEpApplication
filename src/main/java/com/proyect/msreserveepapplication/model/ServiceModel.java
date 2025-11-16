package com.proyect.msreserveepapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "ServiceModel")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ServiceModel {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // PK M-N
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "asociateService", nullable = false)
    @JsonProperty("asociateService")
    private AsociateServiceModel asociateService;

    // PK 1-N - pointer
    @OneToMany(mappedBy = "serviceModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Evita loops infinitos en JSON
    private Set<ReserveModel> reserveModels;

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
