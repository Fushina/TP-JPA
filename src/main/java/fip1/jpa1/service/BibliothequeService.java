package fip1.jpa1.service;

import java.util.Collection;
import java.util.List;

import fip1.jpa1.dto.AuteurDTO;
import fip1.jpa1.dto.EmprunteurDTO;
import fip1.jpa1.dto.LivreDTO;
import fip1.jpa1.model.Auteur;

/**
 * BibliothequeService.
 * 
 * Note : la structure de cette application n'est pas complètement réaliste.
 * <p>
 * Pour éviter de trop vous guider, nous avons choisi de vous fournir des DTO
 * (Data Transfert Objects) simplistes, qui ne sont donc pas des images exactes
 * des classes entités sous-jacentes.
 * <p>
 * Il en résulte que certaines manipulations qui se feront immédiatement avec
 * les entités demandent d'utiliser BibliothequeService quand on travaille avec
 * les DTO.
 */
public interface BibliothequeService {

    /**
     * Crée un auteur dans la base.
     * 
     * @param id
     * @param nom
     * @param prenom
     * @return un auteur DTO avec les données de l'auteur créé.
     */
    AuteurDTO creerAuteur(Long id, String nom, String prenom);

    /**
     * Tous les auteurs existants.
     * 
     * @return une liste d'AuteurDTO représentant tous les auteurs.
     */
    List<AuteurDTO> auteurs();

    /**
     * Trouver tous les auteurs qui ont un nom et un prénom donné. (potentiellement,
     * il y en a plusieurs)
     * 
     * @param nom
     * @param prenom
     * @return
     */
    List<AuteurDTO> trouverAuteurs(String nom, String prenom);

    /**
     * Crée un nouveau livre.
     */
    LivreDTO creerLivre(Long id, String titre, Collection<AuteurDTO> auteurs, int anneeParution);

    /**
     * Récupère un livre
     * 
     * @param idLivre
     * @return
     */

    LivreDTO trouverLivre(Long idLivre);

    /**
     * Retourne tous les livre parus une année donnée.
     */

    List<LivreDTO> livresPubliesEn(int annee);

    /**
     * Retourne tous les livre parus entre deux dates données.
     */

    List<LivreDTO> livresParDate(int anneeDebut, int anneeFin);

    /**
     * Crée un nouvel emprunteur
     */
    void creerEmprunteur(Long id, String nom, String prenom);

    /**
     * Trouve un emprunteur à partir de son nom.
     * @param emprunteurId
     */
    EmprunteurDTO trouverEmprunteur(Long emprunteurId);
    
    /**
     * Marque un livre comme emprunté par un emprunteur donné.
     */
    void emprunter(LivreDTO livre, EmprunteurDTO emprunteur);

    /**
     * Marque le livre comme n'étant plus emprunté.
     */
    void rendre(LivreDTO livre);

    /**
     * Trouver l'emprunteur du livre.
     * 
     * @param id
     * @return l'emprunteur du livre, ou null si le livre n'est pas emprunté.
     */
    EmprunteurDTO trouverEmprunteurLivre(LivreDTO livre);

    /**
     * Retourne tous les livre d'un auteur.
     */

    List<LivreDTO> livresEcritsPar(AuteurDTO auteur);

    /**
     * Retourne les auteurs d'un livre.
     * 
     * @param livre
     * @return
     */
    List<AuteurDTO> auteursDuLivre(LivreDTO livre);

}