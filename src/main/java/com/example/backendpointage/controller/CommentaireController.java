package com.example.backendpointage.controller;

import com.example.backendpointage.Service.CommentaireService;
import com.example.backendpointage.dao.model.Commentaire;
import com.example.backendpointage.dto.CommentaireToSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class CommentaireController {
    @Autowired
    private CommentaireService commentaireService;
    @GetMapping("/getAllCommentaires")
    public ResponseEntity<List<Commentaire>> getAllCommentaires(){
        try{
            List<Commentaire> commentaireList = new ArrayList<>();
            commentaireService.listCommentaire().forEach(commentaireList::add);

            if (commentaireList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(commentaireList,HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCommentairesById/{id}")

    public ResponseEntity<Commentaire> getAllCommentairesById(@PathVariable Long id){
        Commentaire byId = commentaireService.getById(id);

        if (byId!=null){
            return new ResponseEntity<>(byId,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getAllCommentaires/search")

    public List<Commentaire> searchCommentaires(@RequestParam("keyword") Date keyword) {
        List<Commentaire> commentaires = new ArrayList<>();
        commentaires.addAll(commentaireService.findByDate(keyword));
        return commentaires;
    }

    @PostMapping("/addCommentaire")

    public ResponseEntity<Commentaire> addEmploye(@RequestBody CommentaireToSave commentaire ){
        Commentaire commentaireObj =commentaireService.save(commentaire);

        return new ResponseEntity<>(commentaireObj, HttpStatus.OK);
    }
}
