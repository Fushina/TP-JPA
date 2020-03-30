package fip1.jpa1.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import fip1.jpa1.model.Auteur;
import fip1.jpa1.model.Emprunteur;
import fip1.jpa1.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fip1.jpa1.dto.AuteurDTO;
import fip1.jpa1.dto.EmprunteurDTO;
import fip1.jpa1.dto.LivreDTO;
import fip1.jpa1.repository.AuteurRepository;
import fip1.jpa1.repository.EmprunteurRepository;
import fip1.jpa1.repository.LivreRepository;

/**
 * Implémentation de BibliothequeService utilisant JPA.
 */
@Service
@Transactional
public class BibliothequeServiceImpl implements BibliothequeService {

    // Les Repositories à utiliser !
    // N'ajoutez pas d'autres variables d'instance, celles-ci suffisent.
    @Autowired
    AuteurRepository auteurRepository;
    
    @Autowired
    LivreRepository livreRepository;

    @Autowired
    EmprunteurRepository emprunteurRepository;

    @Override
    public AuteurDTO creerAuteur(Long id, String nom, String prenom) {
        AuteurDTO auteurDTO = new AuteurDTO(id, nom, prenom);
        Auteur auteur = new Auteur(id, nom, prenom);
        auteurRepository.save(auteur);
        return auteurDTO;
    }

    @Override
    public List<AuteurDTO> auteurs() {
        List<AuteurDTO> list = new ArrayList<AuteurDTO>();
        for(Auteur auteur: auteurRepository.findAll())
            list.add(new AuteurDTO(auteur.getId(),auteur.getNom(),auteur.getPrenom()));
        return list;
    }

    @Override
    public List<AuteurDTO> trouverAuteurs(String nom, String prenom) {
        List<AuteurDTO> list = new ArrayList<AuteurDTO>();
        for(Auteur auteur: auteurRepository.findAll())
            if(auteur.getNom() == nom && auteur.getPrenom() == prenom)
                list.add(new AuteurDTO(auteur.getId(),auteur.getNom(),auteur.getPrenom()));
        return list;
    }

    @Override
    public LivreDTO creerLivre(Long id, String titre, Collection<AuteurDTO> auteurs, int anneeParution) {
        LivreDTO dto = new LivreDTO(id,titre,anneeParution);
        Collection<Auteur> auteur = new ArrayList<Auteur>();
        for(AuteurDTO a: auteurs)
            auteur.add(new Auteur(a.getId(),a.getNom(),a.getPrenom()));
        Livre livre = new Livre(id,titre,auteur, anneeParution);
        livreRepository.save(livre);
        return dto;
    }

    @Override
    public LivreDTO trouverLivre(Long idLivre) {
        // TODO Auto-generated method stub
        List<Livre> livres = livreRepository.findAll();
        LivreDTO livreDTO = null;
        for (Livre livre : livres)
            if (livre.getId() == idLivre)
                livreDTO = new LivreDTO(livre.getId(), livre.getTitre(), livre.getDatePublication());
        return livreDTO;
    }

    @Override
    public List<LivreDTO> livresPubliesEn(int annee) {
        // TODO Auto-generated method stub
        List<Livre> livres = livreRepository.findAll();
        List<LivreDTO> LivreDTOs = new ArrayList<LivreDTO>();
        for (Livre livre : livres)
            if (livre.getDatePublication() == annee)
                LivreDTOs.add(new LivreDTO(livre.getId(), livre.getTitre(), livre.getDatePublication()));
        return LivreDTOs;
    }

    @Override
    public List<LivreDTO> livresParDate(int anneeDebut, int anneeFin) {
        // TODO Auto-generated method stub
        List<Livre> livres = livreRepository.findAll();
        List<LivreDTO> LivreDTOs = new ArrayList<LivreDTO>();
        for (Livre livre : livres)
            if (livre.getDatePublication() >= anneeDebut && livre.getDatePublication() <= anneeFin)
                LivreDTOs.add(new LivreDTO(livre.getId(), livre.getTitre(), livre.getDatePublication()));
        return LivreDTOs;
    }

    @Override
    public void creerEmprunteur(Long id, String nom, String prenom) {
        new EmprunteurDTO(id, nom, prenom);
        Emprunteur emprunteur = new Emprunteur(id, nom, prenom);
        emprunteurRepository.save(emprunteur);
    }

    @Override
    public EmprunteurDTO trouverEmprunteur(Long emprunteurId) {
        List<Emprunteur> list = emprunteurRepository.findAll();
        EmprunteurDTO e = null;
        for(Emprunteur emp: list)
            if(emp.getId() == emprunteurId)
                e = new EmprunteurDTO(emp.getId(),emp.getNom(),emp.getPrenom());
        return e;
    }

    @Override
    public void emprunter(LivreDTO livre, EmprunteurDTO emprunteur) {
        Livre l = new Livre(livre.getId(),livre.getTitre(),livre.getDatePublication());
        Emprunteur e = new Emprunteur(emprunteur.getId(),emprunteur.getNom(),emprunteur.getPrenom());
        e.addLivre(l);
        l.setEmprunteur(e);
        livreRepository.save(l);
    }
    @Override
    public void rendre(LivreDTO livre) {
        List<Livre> livres = livreRepository.findAll();
        for (Livre livrea : livres)
            if (livrea.getId() == livre.getId()) {
                Livre newlivre = new Livre(livrea.getId(), livrea.getTitre(), livrea.getDatePublication());
                livreRepository.delete(newlivre);
            }
    }

    @Override
    public EmprunteurDTO trouverEmprunteurLivre(LivreDTO livre) {
        List<Livre> livres = livreRepository.findAll();
        for (Livre livrea : livres)
            if (livrea.getId() == livre.getId() && livrea.getEmprunteur()!=null)
                return new EmprunteurDTO(livrea.getEmprunteur().getId(), livrea.getEmprunteur().getNom(), livrea.getEmprunteur().getPrenom());
        return null;
    }

    @Override
    public List<LivreDTO> livresEcritsPar(AuteurDTO auteur) {
        List<Livre> livres = livreRepository.findAll();
        List<LivreDTO> LivreDTOs = new ArrayList<LivreDTO>();
        for (Livre livre : livres)
            for (Auteur _auteur : livre.getMesAuteurs())
                if (_auteur.getId() ==  auteur.getId())
                    LivreDTOs.add(new LivreDTO(livre.getId(), livre.getTitre(), livre.getDatePublication()));
        return LivreDTOs;
    }

    @Override
    public List<AuteurDTO> auteursDuLivre(LivreDTO livre) {
        List<Livre> livres = livreRepository.findAll();
        List<AuteurDTO> listA = new ArrayList<>();
        for (Livre livrea : livres)
            if (livrea.getId() == livre.getId())
                for(Auteur a: livrea.getMesAuteurs())
                    listA.add(new AuteurDTO(a.getId(),a.getNom(),a.getPrenom()));
        return listA;
    }


}