package com.example.backendpointage.dao.model;

import com.example.backendpointage.enums.StatusPointage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Pointages")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Pointage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusPointage pointage;


    @CreationTimestamp
    private Date datePointage;

    @ManyToOne
    private Employe employe;


}

