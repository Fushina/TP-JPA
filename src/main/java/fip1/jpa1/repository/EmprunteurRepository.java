package fip1.jpa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fip1.jpa1.model.Emprunteur;

/**
 * EmprunteurRepository
 */
public interface EmprunteurRepository extends JpaRepository<Emprunteur, Long>{

    
}