package com.proyect.msreserveepapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "EventModel")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EventModel {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "clientModel", nullable = false)
    @JsonProperty("clientModel")
    private ClientModel clientModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "terraceModel")
    @JsonProperty("terraceModel")
    private TerraceModel terraceModel = null;

    @JsonProperty("address")
    @Column(name = "address")
    private String address = null;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "stateEventType", nullable = false)
    @JsonProperty("stateEventType")
    private StateEventType stateEventType;

    // PK 1 - N Pointer
    @OneToMany(mappedBy = "eventModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Evita loops infinitos en JSON
    private Set<ReserveModel> reserveModels;

    // MS payments order
    @JsonProperty("payment")
    @Column(name = "payment")
    private String payment;

    // Sum price by Reserves
    @JsonProperty("sumPrice")
    @Column(name = "sumPrice")
    private BigDecimal sumPrice;

    @JsonProperty("sumPeople")
    @Column(name = "sumPeople")
    private Integer sizePeople;

    @JsonProperty("dayDate")
    @Column(name = "dayDate")
    private String dayDate;
}
