package com.example.backendpointage.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Table(name = "Commentaires")
@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private Date date;
    @ManyToOne
    private Employe employe;

    private String commentaire;
}
