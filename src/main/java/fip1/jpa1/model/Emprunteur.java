package fip1.jpa1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Emprunteur
 */


@Entity

public class Emprunteur {
    @Id
    private Long id;
    private String nom, prenom;
    @OneToMany
    List<Livre>meslivresempruntes;

    public Emprunteur(Long id, String nom, String prenom) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.meslivresempruntes= new ArrayList();
    }
    public void removeLivre(Livre l)
    {
        this.meslivresempruntes.remove(l);
    }
    public Emprunteur() {}
    public void addLivre(Livre l)
    {
        this.meslivresempruntes.add(l);
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
    public List<Livre> getMeslivresempruntes() {
        return meslivresempruntes;
    }
    public void setMeslivresempruntes(List<Livre> meslivresempruntes) {
        this.meslivresempruntes = meslivresempruntes;

    }

}