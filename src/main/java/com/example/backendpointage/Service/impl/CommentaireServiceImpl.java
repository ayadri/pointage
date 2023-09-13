package com.example.backendpointage.Service.impl;

import com.example.backendpointage.Service.CommentaireService;
import com.example.backendpointage.dao.model.Commentaire;
import com.example.backendpointage.dao.model.Employe;
import com.example.backendpointage.dao.repositories.CommentaireRepository;
import com.example.backendpointage.dao.repositories.EmployeRepository;
import com.example.backendpointage.dto.CommentaireToSave;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final EmployeRepository employeRepository;
    @Override
    public List<Commentaire> listCommentaire() {
        List<Commentaire> all = commentaireRepository.findAll();
        return all;
    }

    @Override
    public Commentaire save(CommentaireToSave commentaire) {

            Employe employe = employeRepository.findByNomContains(commentaire.getNomEmployer());
            if (employe != null) {
                Commentaire cmt = new Commentaire();
                cmt.setCommentaire(commentaire.getCommentaire());
                cmt.setEmploye(employe);
                return commentaireRepository.save(cmt);
            }

        return null;
    }

    @Override
    public Commentaire getById(Long id) {
        Commentaire commentaire = commentaireRepository.findById(id).orElse(null);
        return commentaire;
    }

    @Override
    public Collection<? extends Commentaire> findByDate(Date keyword) {
        return (Collection<? extends Commentaire>) commentaireRepository.findByDate(keyword);
    }

    @Override
    public void delete(Long id) {
        commentaireRepository.deleteById(id);
    }
}
