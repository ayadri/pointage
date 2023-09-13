package com.example.backendpointage.dao.repositories;

import com.example.backendpointage.dao.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Employe findByNomContains(String keyword);
    List<Employe> findByEmail(String email);
    Boolean existsByEmail(String email);


}
