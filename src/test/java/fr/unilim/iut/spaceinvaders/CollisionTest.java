package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

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
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,1), 1);
		
		
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
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(8,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
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
		
		assertEquals(false,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_ToucheEnvahisseurDoitDetecterCollision(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);	
		for(int i = 0; i<5; i++){
			spaceinvaders.deplacerMissile();
		}
		
		
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
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(0,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
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
		
		assertEquals(false,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_CollisionNonDetecteCasLimiteDroite(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(5,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
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
	
		assertEquals(false,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_TirMissile_CollisionNonDetecteCasLimiteGauche(){
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(9,1), 1);
		
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for(int i = 0; i<6; i++){
			spaceinvaders.deplacerMissile();
		}
		
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
	
		assertEquals(false,Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	}
	
}
