package com.example.backendpointage.dao.repositories;

import com.example.backendpointage.dao.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    Commentaire findByDate(Date keyword);


}
