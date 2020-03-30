package fip1.jpa1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fip1.jpa1.model.Auteur;
import fip1.jpa1.model.Livre;

/**
 * LivreRepository
 */
public interface LivreRepository extends JpaRepository<Livre,Long>{


}