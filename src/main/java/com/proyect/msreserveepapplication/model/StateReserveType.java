package com.proyect.msreserveepapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "StateReserveType")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StateReserveType {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("kind")
    @Column(name = "kind", length = 30)
    private String kind;

    @JsonProperty("killed")
    @Column(name = "killed")
    private Byte killed = 0;

    public void setKilled(Byte killed) {
        this.killed = killed;
    }

    public Integer getId() {
        return id;
    }

    @OneToMany(mappedBy = "stateReserveType")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Evita loops infinitos en JSON
    @JsonIgnore
    private Set<ReserveModel> reserveModels;
}
