package fip1.jpa1.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity

public class Livre {
    @Id
    private Long id;
    private String titre;
    private int datePublication;
    @ManyToMany
    Collection<Auteur>mesAuteurs;
    @ManyToOne
    Emprunteur emprunteur;
    public Livre(Long id, String titre, int datePublication) {
        super();
        this.id = id;
        this.titre = titre;
        this.datePublication = datePublication;
    }
    public Livre(Long id, String titre,Collection<Auteur> ca, int datePublication) {
        super();
        this.id = id;
        this.titre = titre;
        this.datePublication = datePublication;
        this.mesAuteurs=ca;
    }
    public Emprunteur getEmprunteur() {
        return emprunteur;
    }
    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }
    public Collection<Auteur> getMesAuteurs() {
        return mesAuteurs;
    }
    public void setMesAuteurs(Collection<Auteur> mesAuteurs) {
        this.mesAuteurs = mesAuteurs;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public int getDatePublication() {
        return datePublication;
    }
    public void setDatePublication(int datePublication) {
        this.datePublication = datePublication;
    }
    public Livre() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Livre other = (Livre) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }





}