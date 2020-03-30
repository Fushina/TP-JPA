package fip1.jpa1.repository;

import java.util.List;

import fip1.jpa1.dto.AuteurDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import fip1.jpa1.model.Auteur;

/**
 * AuteurRepository
 */
public interface AuteurRepository extends JpaRepository<Auteur,Long> {

    
}