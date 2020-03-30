package fip1.jpa1.dto;

/**
 * EmprunteurDTO Auteur Data Transfert Object.
 * <p> NE PAS MODIFIER CETTE CLASSE.
 */
public class EmprunteurDTO {
    private Long id;
    private String nom, prenom;


    public EmprunteurDTO() {
    }

    public EmprunteurDTO(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            "}";
    }

}