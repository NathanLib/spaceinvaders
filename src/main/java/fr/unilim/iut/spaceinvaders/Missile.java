package fr.unilim.iut.spaceinvaders;

public class Missile extends Sprite {

	private Dimension dimensionMissile;
	private Position positionOrigineMissile;
	private int vitesseMissile;
	
	public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile) {
		this.dimensionMissile = dimensionMissile;
		this.positionOrigineMissile = positionOrigineMissile;
		this.vitesseMissile = vitesseMissile;
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);

		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
	}
}
