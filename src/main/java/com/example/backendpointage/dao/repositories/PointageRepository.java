package com.example.backendpointage.dao.repositories;

import com.example.backendpointage.dao.model.Employe;
import com.example.backendpointage.dao.model.Pointage;
import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;
@CrossOrigin("*")

public interface PointageRepository extends JpaRepository<Pointage, Long> {
    @Query("SELECT p FROM Pointage p ORDER BY p.datePointage ASC")
    List<Pointage> findAllByOrderByDatePointageAsc();

    @Query("SELECT p FROM Pointage p WHERE p.datePointage = :date")
    List<Pointage> findPointageByDate(@Temporal(TemporalType.DATE) Date date);

    @Query("SELECT p FROM Pointage p WHERE p.employe.nom = :employeN ORDER BY p.datePointage ASC")

    List<Pointage> findByEmploye_NomContainsOrderByDatePointage(String employeN);

    List<Pointage> findByEmployeId(Long employeId);

    List<Pointage> findByEmploye(Employe employe);
}
