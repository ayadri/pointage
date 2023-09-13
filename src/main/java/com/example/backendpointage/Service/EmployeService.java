package com.example.backendpointage.Service;

import com.example.backendpointage.dao.model.Employe;

import java.util.List;


public interface EmployeService {


    List<Employe> listEmploye();
    Employe save (Employe employe);
    Employe getById(Long id);
    Employe findByNomContains(String keyword);
    void delete(Long id);
}
