package fip1.jpa1.dto;

/**
 * LivreDTO : Livre Data Transfert Object.
 * <p> Ne pas modifier cette classe
 * <p> Dans une "vraie" application, cette classe, en particulier,
 * pourrait être plus complexe.
 * Ici, nous avons voulu créer une classe très simple pour vous laisser
 * prendre les décisions qui s'imposent dans les classes du modèle.
 */
public class LivreDTO {  
    private Long id;
    private String titre;
    private int datePublication;


    public LivreDTO() {
    }

    public LivreDTO(Long id, String titre, int datePublication) {
        this.id = id;
        this.titre = titre;
        this.datePublication = datePublication;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDatePublication() {
        return this.datePublication;
    }

    public void setDatePublication(int datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titre='" + getTitre() + "'" +
            ", datePublication='" + getDatePublication() + "'" +
            "}";
    }

    
}