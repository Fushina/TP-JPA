package fip1.jpa1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import fip1.jpa1.dto.AuteurDTO;
import fip1.jpa1.dto.EmprunteurDTO;
import fip1.jpa1.dto.LivreDTO;
import fip1.jpa1.service.BibliothequeService;
import fip1.jpa1.service.BibliothequeServiceImpl;

@SpringBootTest
@Transactional
class ExoJPATest {

	@Configuration
	@EnableAutoConfiguration
	@Import(BibliothequeServiceImpl.class)
	static class ConfigDuTest {

	}

	@Autowired
	BibliothequeService bibliothequeService;

	@Test
	public void testCreerAuteur() {
		final AuteurDTO auteur = bibliothequeService.creerAuteur(1l, "a", "b");
		assertEquals(1l, auteur.getId());
		assertEquals("a", auteur.getNom());
		assertEquals("b", auteur.getPrenom());
	}

	@Test
	public void testPostCreationAuteur() {
		bibliothequeService.creerAuteur(1l, "a", "b");
		final List<AuteurDTO> liste = bibliothequeService.auteurs();
		assertEquals(1, liste.size());
		final AuteurDTO o = liste.get(0);
		assertEquals(1l, o.getId());
	}

	@Test
	public void testCreerAuteurs() {
		final int nbAuteurs = 5;
		// Crée n auteurs...
		for (long i = 1; i <= nbAuteurs; i++) {
			bibliothequeService.creerAuteur(i, "nom" + i, "prenom" + i);
		}
		// Récupère les auteurs...
		final List<AuteurDTO> auteurs = bibliothequeService.auteurs();
		final List<Long> ids = auteurs.stream().map(a -> a.getId()).sorted().collect(Collectors.toList());
		assertEquals(Arrays.asList(1l, 2l, 3l, 4l, 5l), ids);
	}

	@Test
	public void testChercherNomPrenom() {
		bibliothequeService.creerAuteur(1l, "n1", "p1");
		bibliothequeService.creerAuteur(2l, "n2", "p1");
		bibliothequeService.creerAuteur(3l, "n2", "p2");
		bibliothequeService.creerAuteur(5l, "n4", "p2");
		bibliothequeService.creerAuteur(5l, "n4", "p3");

		final List<AuteurDTO> resultat = bibliothequeService.trouverAuteurs("n2", "p2");
		assertEquals(1, resultat.size());
		assertEquals(3l, resultat.get(0).getId());
	}

	@Test
	public void testCreerEmprunteur() {
		bibliothequeService.creerEmprunteur(10l, "a", "b");
		final EmprunteurDTO e = bibliothequeService.trouverEmprunteur(10l);
		assertEquals(10l, e.getId());
		assertEquals("a", e.getNom());
		assertEquals("b", e.getPrenom());
	}

	@Test
	public void testCreerLivre() {
		final AuteurDTO a1 = bibliothequeService.creerAuteur(1l, "n1", "p1");
		final AuteurDTO a2 = bibliothequeService.creerAuteur(2l, "n2", "p2");
		bibliothequeService.creerLivre(3l, "livre1", Arrays.asList(a1, a2), 2000);
		final LivreDTO livreDTO = bibliothequeService.trouverLivre(3l);
		assertEquals(3l, livreDTO.getId());
		assertEquals("livre1", livreDTO.getTitre());
		assertEquals(2000, livreDTO.getDatePublication());
	}

	@Test
	public void testCreerLivreEtAuteurs() {
		final AuteurDTO a1 = bibliothequeService.creerAuteur(1l, "n1", "p1");
		final AuteurDTO a2 = bibliothequeService.creerAuteur(2l, "n2", "p2");
		bibliothequeService.creerLivre(3l, "livre1", Arrays.asList(a1, a2), 2000);
		final LivreDTO livreDTO = bibliothequeService.trouverLivre(3l);
		final List<AuteurDTO> auteurs = bibliothequeService.auteursDuLivre(livreDTO);
		assertEquals(2, auteurs.size());
		auteurs.sort(Comparator.comparing(AuteurDTO::getId));
		assertEquals(1l, auteurs.get(0).getId());
		assertEquals(2l, auteurs.get(1).getId());
		assertEquals("n1", auteurs.get(0).getNom());
		assertEquals("n2", auteurs.get(1).getNom());
	}

	@Test
	public void testDatesPublication() {
		bibliothequeService.creerLivre(1l, "l1", Collections.emptyList(), 1000);
		bibliothequeService.creerLivre(2l, "l1", Collections.emptyList(), 800);
		bibliothequeService.creerLivre(3l, "l1", Collections.emptyList(), 1200);
		bibliothequeService.creerLivre(4l, "l1", Collections.emptyList(), 799);
		final List<LivreDTO> livres = bibliothequeService.livresPubliesEn(1200);
		assertEquals(1, livres.size());
		assertEquals(3l, livres.get(0).getId());
	}

	@Test
	public void testDatesIntervalle() {
		bibliothequeService.creerLivre(1l, "l1", Collections.emptyList(), 1000);
		bibliothequeService.creerLivre(2l, "l1", Collections.emptyList(), 800);
		bibliothequeService.creerLivre(3l, "l1", Collections.emptyList(), 1200);
		bibliothequeService.creerLivre(4l, "l1", Collections.emptyList(), 799);
		final List<LivreDTO> livres = bibliothequeService.livresParDate(200, 900);
		assertEquals(2, livres.size());
		livres.sort(Comparator.comparing(LivreDTO::getId));
		assertEquals(2l, livres.get(0).getId());
		assertEquals(4l, livres.get(1).getId());
	}

	// Tests sur la notion d'emprunteur

	@Test
	public void testEmprunter() {
		bibliothequeService.creerEmprunteur(10l, "a", "b");
		final EmprunteurDTO e = bibliothequeService.trouverEmprunteur(10l);
		bibliothequeService.creerLivre(1l, "l1", Collections.emptyList(), 1000);
		LivreDTO livre = bibliothequeService.trouverLivre(1l);
		assertNull(bibliothequeService.trouverEmprunteurLivre(livre));
		bibliothequeService.emprunter(livre, e);
		EmprunteurDTO emprunteurLivre = bibliothequeService.trouverEmprunteurLivre(livre);
		assertNotNull(emprunteurLivre);
		assertEquals(10l, emprunteurLivre.getId());
		bibliothequeService.rendre(livre);
		assertNull(bibliothequeService.trouverEmprunteurLivre(livre));
	}

	// Test sur les liens un peu complexes entre livre et auteur.

	@Test
	public void testTrouverLivresDunAuteur() {
		final AuteurDTO a1 = bibliothequeService.creerAuteur(1l, "n1", "p1");
		final AuteurDTO a2 = bibliothequeService.creerAuteur(2l, "n2", "p2");
		bibliothequeService.creerLivre(1l, "livre1", Arrays.asList(a1, a2), 2000);
		bibliothequeService.creerLivre(2l, "livre1", Arrays.asList(a1), 2000);
		bibliothequeService.creerLivre(3l, "livre1", Arrays.asList(a2), 2000);
		final List<LivreDTO> livres = bibliothequeService.livresEcritsPar(a2);
		livres.sort(Comparator.comparing(LivreDTO::getId));
		assertEquals(2, livres.size());
		assertEquals(1l, livres.get(0).getId());
		assertEquals(3l, livres.get(1).getId());
	}

}
