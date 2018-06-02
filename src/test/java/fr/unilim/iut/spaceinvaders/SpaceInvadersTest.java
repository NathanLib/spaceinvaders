package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvadersTest {
	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		
		// Arrange
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		
		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9), 1);
		
		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......V.......\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}


	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}


	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 1);
		
		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}	
	}


	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		
		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"............VVV\n" + 
				"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"....VVV........\n" + 
				"....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"VVV............\n" + 
				"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		
		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"..........VVV..\n" + 
				"..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		
		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"............VVV\n" + 
				"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"VVV............\n" + 
				"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......MMM.....\n" + 
				".......MMM.....\n" + 
				".....VVVVVVV...\n" + 
				".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}


	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(7,9),1);
	}

	@Test
	public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		spaceinvaders.deplacerMissile();

		// Assert
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				".......MMM.....\n" + 
				".......MMM.....\n" + 
				"...............\n" + 
				"...............\n" + 
				".....VVVVVVV...\n" + 
				".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
		for (int i = 1; i <=6 ; i++) {
			spaceinvaders.deplacerMissile();
		}

		spaceinvaders.deplacerMissile();

		// Assert
		assertEquals("" +
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" +
				"...............\n" +
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				".....VVVVVVV...\n" + 
				".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}


	@Test
	public void test_unNouvelEnvahisseurEstCorrectementPositionneDansEspaceJeu(){

		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);

		// Assert
		assertEquals("" +
				".......EE......\n" +
				".......EE......\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());

	}

	@Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {

		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();

		// Assert
		assertEquals("" +
				"........EE.....\n" +
				"........EE.....\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
			    "...............\n" +
				"...............\n" +
				"...............\n",
				spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {

		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,1), 1);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();

		// Assert
		assertEquals("" +
				"......EE.......\n" +
				"......EE.......\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n",
				spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {
		
		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(13,1), 1);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		
		// Assert
		assertEquals("" + 
				".............EE\n" + 
				".............EE\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {

		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(0,1), 1);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();
		
		// Assert
		assertEquals("" + 
				"EE.............\n" + 
				"EE.............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaDroite() {

		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(12,1), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		
		// Assert
		assertEquals("" + 
				".............EE\n" + 
				".............EE\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaGauche() {

		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(2,1), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();
		
		// Assert
		assertEquals("" + 
				"EE.............\n" + 
				"EE.............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurChangeDeDirectionDeDroiteAGauche(){
		
		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(12,1), 1);
		for (int i = 0; i<2; i++){
			spaceinvaders.deplacerEnvahisseur();
		}
		
		// Assert
		assertEquals("" + 
				"............EE.\n" + 
				"............EE.\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurChangeDeDirectionDeGaucheADroite(){
		
		// Arrange
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(12,1), 1);
		for (int i = 0; i<15; i++){
			spaceinvaders.deplacerEnvahisseur();
		}

		// Assert
		assertEquals("" + 
				".EE............\n" + 
				".EE............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}


}
