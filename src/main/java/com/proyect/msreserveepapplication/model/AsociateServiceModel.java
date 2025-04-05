package com.proyect.msreserveepapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "AsociateServiceModel")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AsociateServiceModel {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonProperty("idUser")
    @Column(name = "idUser")
    private Integer IdUser;

    @JsonProperty("name")
    @Column(name = "name", length = 30)
    private String name;

    @JsonProperty("mail")
    @Column(name = "mail")
    private String mail;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

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

    @OneToMany(mappedBy = "asociateServiceModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Evita loops infinitos en JSON
    private Set<ServiceModel> serviceModels;
}
