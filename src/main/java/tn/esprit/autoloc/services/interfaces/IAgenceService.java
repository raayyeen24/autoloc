package tn.esprit.autoloc.services.interfaces;

import tn.esprit.autoloc.entities.Agence;

import java.util.List;

public interface IAgenceService {

    public Agence ajouterAgence (Agence a) ;
    public Agence updateAgence (Agence a);
    public void deleteAgence (int id) ;
    public Agence getAgence (int id );
    public List <Agence> getAllAgences () ;

}