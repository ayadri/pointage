package com.example.backendpointage.Service;

import com.example.backendpointage.dao.model.Commentaire;
import com.example.backendpointage.dto.CommentaireToSave;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface CommentaireService {

    List<Commentaire> listCommentaire();
    Commentaire save (CommentaireToSave commentaire);
    Commentaire getById(Long id);
    Collection<? extends Commentaire> findByDate(Date keyword);
    void delete(Long id);
}
