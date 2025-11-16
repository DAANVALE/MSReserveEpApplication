package com.proyect.msreserveepapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ReserveModel")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReserveModel {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "eventModel", nullable = false)
    @JsonProperty("eventModel")
    private EventModel eventModel;

    // PK 1 - N Data
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceModel", nullable = false)
    @JsonProperty("serviceModel")
    private ServiceModel serviceModel;

    // PK 1 - N Data
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "stateReserveType", nullable = false)
    @JsonProperty("stateReserveType")
    private StateReserveType stateReserveType;

    @JsonProperty("dayTime")
    @Column(name = "dayTime")
    private String dayTime;

    @JsonProperty("sizePeople")
    @Column(name = "sizePeople")
    private Integer sizePeople;

    @JsonProperty("finalPrice")
    @Column(name = "finalPrice")
    private Integer finalPrice;
}
