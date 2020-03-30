package fip1.jpa1.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import fip1.jpa1.dto.AuteurDTO;


/**
 * Auteur
 */
@Entity

public class Auteur {
    @Id
    private Long id;
    private String nom, prenom;
    @ManyToMany(mappedBy="mesAuteurs")
    Set<Livre> livre;
    public Auteur(Long id, String nom, String prenom) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.livre=new HashSet<Livre>();
    }
    public void addLivre(Livre l)
    {
        this.livre.add(l);
    }
    public Set<Livre> getLivre() {
        return livre;
    }
    public void setLivre(Set<Livre> livre) {
        this.livre = livre;
    }
    public Auteur() {
        this.livre=new HashSet<Livre>();
    }
    public Auteur(AuteurDTO a) {
        this.id = a.getId();
        this.nom =a.getNom();
        this.prenom = a.getPrenom();
        this.livre=new HashSet<Livre>();

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



}