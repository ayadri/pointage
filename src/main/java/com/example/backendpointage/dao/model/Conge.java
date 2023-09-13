package com.example.backendpointage.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Table(name = "Conges")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @CreationTimestamp
    private Date dateDemande;
    private Date dateDebutC;
    private Date dateFinC;
    private String motif;

    @ManyToOne
    private Employe employe;

}
