package fr.unilim.iut.spaceinvaders;

public class Vaisseau extends Sprite {

    private Missile missile;
    private Vaisseau vaisseau;

	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
	    super(dimension, positionOrigine, vitesse);
    }
    
    public Missile tirerUnMissile(Dimension dimension, int vitesse) {
  		this.missile = this.vaisseau.tirerUnMissile(dimension,vitesse);
  		
  		return this.missile;
  	}
}