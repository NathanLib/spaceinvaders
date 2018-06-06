package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test
	public void test_PasDeTir_NeDoitPasDetecterCollision(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,1), 1);
		
		// Assert
		assertEquals("" + 
				".......EEE.....\n" + 
				".......EEE.....\n" +
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
	public void test_TirMissile_PlusBasQueEnvahisseurNeDoitPasDetecterCollision(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		// Assert
		assertEquals("" + 
				"........EEE....\n" + 
				"........EEE....\n" +
				"...............\n" + 
				"...............\n" + 
				"........M......\n" + 
				"........M......\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_ToucheEnvahisseurDoitDetecterCollision(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);	
		for(int i = 0; i<5; i++){
			spaceinvaders.deplacerMissile();
		}
		
		// Assert
		assertEquals("" + 
				".......EEE.....\n" + 
				".......EME.....\n" +
				"........M......\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		assertEquals(true,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_MemeNiveauQueEnvahisseurNeDoitPasDetecterCollision(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(0,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
		// Assert
		assertEquals("" + 
				"EEE.....M......\n" + 
				"EEE.....M......\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		
		assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_CollisionNonDetecteCasLimiteDroite(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(5,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
		// Assert
		assertEquals("" + 
				".....EEEM......\n" + 
				".....EEEM......\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	
		assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_CollisionDetecteCasLimiteGauche(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(9,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
		// Assert
		assertEquals("" + 
				"........MEEE...\n" + 
				"........MEEE...\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	
		assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_MissileSortiDeLEcranCollisionNonDetecte(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(4,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<7; i++){
			spaceinvaders.deplacerMissile();
		}
		
		// Assert
		assertEquals("" + 
				"....EEE........\n" + 
				"....EEE........\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......VVV.....\n" + 
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	
		assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_CollisionDetecte_SupprimeEnvahisseurEtMissile(){
		
		// Arrange
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);	
		for(int i = 0; i<5; i++){
			spaceinvaders.deplacerMissile();
		}
		
		spaceinvaders.supprimerElementsApresCollision();
				
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
}
