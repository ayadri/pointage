package com.example.backendpointage.Service.impl;

import com.example.backendpointage.Service.EmployeService;
import com.example.backendpointage.dao.model.Employe;
import com.example.backendpointage.dao.repositories.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Transactional
public class EmployeServiceImpl implements EmployeService {
    private final EmployeRepository employeRepository;


    @Override
    public List<Employe> listEmploye() {
        List<Employe> all = employeRepository.findAll();
        return all;
    }

    @Override
    public Employe save(Employe employe) {
        return
                employeRepository.save(employe);
    }


    @Override
    public Employe getById(Long id) {
        Employe employe = employeRepository.findById(id).orElse(null);
        return employe;
    }

    @Override
    public Employe findByNomContains(String keyword) {
        return employeRepository.findByNomContains(keyword);
    }

    @Override
    public void delete(Long id) {
        employeRepository.deleteById(id);
    }
}
