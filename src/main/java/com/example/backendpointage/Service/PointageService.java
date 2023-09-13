package com.example.backendpointage.Service;

import com.example.backendpointage.dao.model.Employe;
import com.example.backendpointage.dao.model.Pointage;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface PointageService {

    List<Pointage> pointage();

    Pointage pointerArrivage(String employeN) throws Exception;
    Pointage pointerSortie(String employeN) throws Exception;
    Pointage pointerDebutPause(String employeN) throws Exception;
    Pointage pointerFinPause(String employeN) throws Exception;

    Pointage getById(Long id);

    void delete(Long id);

    List<Pointage> findPointageByDate(String keyword) throws ParseException;

    Pointage ajouterPointage(Pointage pointage);

    Map<String, Double> calculerHeuresTravailleesParMois(Employe employe);
}
