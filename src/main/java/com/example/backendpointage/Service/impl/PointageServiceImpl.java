package com.example.backendpointage.Service.impl;

import com.example.backendpointage.Service.PointageService;
import com.example.backendpointage.dao.model.Employe;
import com.example.backendpointage.dao.model.Pointage;
import com.example.backendpointage.dao.repositories.EmployeRepository;
import com.example.backendpointage.dao.repositories.PointageRepository;
import com.example.backendpointage.enums.StatusPointage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional

public class PointageServiceImpl implements PointageService {

    private final PointageRepository pointageRepository;
    private final EmployeRepository employeRepository;
    @Override
    public List<Pointage> pointage() {
        List<Pointage> allByOrderByDatePointageAsc = pointageRepository.findAllByOrderByDatePointageAsc();
        return allByOrderByDatePointageAsc;
    }
    public Pointage ajouterPointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }

    @Override
    public Map<String, Double> calculerHeuresTravailleesParMois(Employe employe) {
        List<Pointage> pointages = pointageRepository.findByEmploye(employe);
        Map<String, Double> heuresTravailleesParMois = new TreeMap<>();

        Date debutTravail = null;
        Calendar calendar = Calendar.getInstance();

        // Mapping des numéros de mois aux noms des mois
        String[] moisEnLettres = new String[]{
                "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        };

        for (Pointage pointage : pointages) {
            if (pointage.getPointage() == StatusPointage.ARRIVAGE) {
                debutTravail = pointage.getDatePointage();
                calendar.setTime(debutTravail);
                int moisEnCours = calendar.get(Calendar.MONTH) + 1; // Mois en cours (0 pour janvier, 1 pour février, etc.)

                String moisEnLettresStr = moisEnLettres[moisEnCours];

                // Utilisation du numéro de mois comme clé
                heuresTravailleesParMois.put(Integer.toString(moisEnCours), heuresTravailleesParMois.getOrDefault(Integer.toString(moisEnCours), 0.0));
            } else if (pointage.getPointage() == StatusPointage.FIN && debutTravail != null) {
                long dureeMinutes = calculerDureeMinutesTravaillees(debutTravail, pointage.getDatePointage());
                calendar.setTime(debutTravail);
                int moisEnCours = calendar.get(Calendar.MONTH) + 1; // Mois en cours (0 pour janvier, 1 pour février, etc.)

                String moisEnLettresStr = moisEnLettres[moisEnCours];

                double heuresTravaillees = dureeMinutes / 60.0; // Convertir en heures avec des décimales

                // Utilisation du numéro de mois comme clé
                heuresTravailleesParMois.put(Integer.toString(moisEnCours), heuresTravailleesParMois.getOrDefault(Integer.toString(moisEnCours), 0.0) + heuresTravaillees);
                debutTravail = null;
            }
        }

        return heuresTravailleesParMois;
    }
    private long calculerDureeMinutesTravaillees(Date debut, Date fin) {
        long differenceMillis = fin.getTime() - debut.getTime();
        return differenceMillis / (60 * 1000); // Convertir en minutes
    }
    @Override
    public Pointage pointerArrivage(String employeN) throws Exception {

        Employe employe =employeRepository.findByNomContains(employeN);
        if(employe==null)throw new Exception("employer pas trouver");
        Pointage pointage = new Pointage();
        pointage.setPointage(StatusPointage.ARRIVAGE);
        pointage.setEmploye(employe);
        pointageRepository.save(pointage);
        return pointage;
    }

    @Override
    public Pointage pointerSortie(String employeN) throws Exception {
        Employe employe = employeRepository.findByNomContains(employeN);
        if(employe==null)throw new Exception("employer pas trouver");
        Pointage pointage = new Pointage();
        pointage.setPointage(StatusPointage.FIN);
        pointage.setEmploye(employe);
        pointageRepository.save(pointage);

        return pointage;
    }

    @Override
    public Pointage pointerDebutPause(String employeN) throws Exception {
        Employe employe = employeRepository.findByNomContains(employeN);
        if(employe==null)throw new Exception("employer pas trouver");
        Pointage pointage = new Pointage();
        pointage.setPointage(StatusPointage.DEBUTPAUSE);
        pointage.setEmploye(employe);
        pointageRepository.save(pointage);

        return pointage;
    }

    @Override
    public Pointage pointerFinPause(String employeN) throws Exception {
        Employe employe = employeRepository.findByNomContains(employeN);
        if(employe==null)throw new Exception("employer pas trouver");
        Pointage pointage = new Pointage();
        pointage.setPointage(StatusPointage.FINPAUSE);
        pointage.setEmploye(employe);
        pointageRepository.save(pointage);

        return pointage;
    }

    @Override
    public Pointage getById(Long id) {
        return pointageRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        pointageRepository.deleteById(id);
    }

    @Override
    public List<Pointage> findPointageByDate(String keyword) throws ParseException {
        if(keyword.isEmpty()){
            return pointageRepository.findAll();
        }else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = formatter.parse(keyword);
            return pointageRepository.findPointageByDate(date);
        }

    }
}
