package com.medilabo.patients.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String rue;

    private String dateNaissance;

    private String codePostal;

    private String ville;

    private String telephone;

    public Patient(String nom, String prenom, Genre genre, String rue, String dateNaissance, String codePostal, String ville, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.rue = rue;
        this.dateNaissance = dateNaissance;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
    }
}
