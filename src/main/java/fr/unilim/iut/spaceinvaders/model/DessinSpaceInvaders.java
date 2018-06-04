package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurJeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {

	   private SpaceInvaders jeu;

	   public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		   this.jeu = spaceInvaders;
	   }

	   @Override
	   public void dessiner(BufferedImage im) {
		   if (this.jeu.aUnVaisseau()) {
			   Vaisseau vaisseau = this.jeu.recupererVaisseau();
			   this.dessinerUnVaisseau(vaisseau, im);
		   }
		   if (this.jeu.aUnMissile()) {
			   Missile missile = this.jeu.recupererMissile();
			   this.dessinerUnMissile(missile, im);
		   }
		   if (this.jeu.aUnEnvahisseur()){
				Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
				this.dessinerUnEnvahisseur(envahisseur, im);
			}
		   if(this.jeu.etreFini()) {
			   this.dessinerFinPartie(im);
		   }
	   }

	   private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();

		   crayon.setColor(Color.gray);
		   crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());

	   }
	   
	   private void dessinerUnMissile(Missile missile, BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();

		   crayon.setColor(Color.blue);
		   crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());

	   }
	   
	   private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage image){
			Graphics2D crayon = (Graphics2D) image.getGraphics();
			
			crayon.setColor(Color.green);
			crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(), envahisseur.hauteur());
		}
	   
	   private void dessinerFinPartie(BufferedImage image){
			Graphics2D crayon = (Graphics2D) image.getGraphics();
			
			crayon.setColor(Color.black);
			crayon.fillRect(0,0,Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
			crayon.setColor(Color.lightGray);
			Font f = new Font("Courier", Font.BOLD, 25);
			crayon.setFont(f);
			crayon.drawString("Fin de partie", Constante.ESPACEJEU_LONGUEUR/3, Constante.ESPACEJEU_HAUTEUR/2);
		}

 }
