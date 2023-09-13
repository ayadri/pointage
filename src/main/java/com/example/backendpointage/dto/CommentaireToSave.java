package com.example.backendpointage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentaireToSave {
    private String commentaire;
    private String nomEmployer;
}
