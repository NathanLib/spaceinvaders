package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurJeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurJeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {
	int longueur;
	int hauteur;

	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;

	boolean deplacementDroiteEnvahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;

		//Les envahisseurs effectue leur premier déplacement par la droite
		this.deplacementDroiteEnvahisseur = true;
	}


	//Méthode pour l'espace de jeu
	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	/*---------------------------------------------------------------------------------------------------------*/

	//Méthode pour le vaisseau
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);

	}


	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	/*---------------------------------------------------------------------------------------------------------*/

	//Méthode pour le missile
	public void deplacerMissile() {
		missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);

		if (missile.ordonneeLaPlusBasse() < 0)
			missile = null;
	}


	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}

	public boolean aUnMissile() {
		return missile != null;
	}	

	public Missile recupererMissile() {
		return this.missile;
	}

	/*---------------------------------------------------------------------------------------------------------*/

	//Méthode pour l'envahisseur
	public void positionnerUnNouvelEnvahisseur(Dimension dimension,Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException(
					"La position de l'envahisseur est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException(
					"L'Envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException(
					"L'Envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		this.envahisseur = new Envahisseur(dimension, position, vitesse);

	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusHaute());
			}
		}

	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		}	
	}

	public void deplacerEnvahisseur() {

		if(this.envahisseurSeDeplaceVersLaDroite()){
			this.deplacerEnvahisseurVersLaDroite();
		}
		else{
			this.deplacerEnvahisseurVersLaGauche();
		}

	}

	private boolean envahisseurSeDeplaceVersLaDroite() {
		if(this.envahisseurToucheAGauche()){
			this.deplacementDroiteEnvahisseur = true;
		}
		else if(this.envahisseurToucheADroite()){
			this.deplacementDroiteEnvahisseur = false;
		}

		return this.deplacementDroiteEnvahisseur;
	}

	private boolean envahisseurToucheADroite() {
		return this.longueur-1 == this.envahisseur.abscisseLaPlusADroite();
	}

	private boolean envahisseurToucheAGauche() {
		return this.envahisseur.abscisseLaPlusAGauche() == 0;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && this.envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnEnvahisseur() {
		return this.envahisseur != null;
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	/*---------------------------------------------------------------------------------------------------------*/

	//Méthode de l'interface Jeu
	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir && !this.aUnMissile())
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);

		if (this.aUnMissile())
			this.deplacerMissile();

		if (this.aUnEnvahisseur()){
			this.deplacerEnvahisseur();
		}
		
		supprimerElementsApresCollision();
	}


	public void supprimerElementsApresCollision() {
		if (Collision.detecterCollision(this.missile, this.envahisseur)) {
			this.envahisseur=null;
			this.missile=null;
		}
	}



	public boolean etreFini() {
		// Le jeu se finit lorsque l'on détecte une collision entre le missile et l'envahisseur
		return !this.aUnEnvahisseur();
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		Position positionEnvahisseur = new Position(dimensionEnvahisseur.longueur()/2, dimensionEnvahisseur.hauteur()-1);
		positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	}

}