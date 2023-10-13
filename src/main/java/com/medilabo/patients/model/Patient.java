package com.medilabo.patients.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    private String nom;

    private String prenom;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String adresse;

    private String telephone;


}
