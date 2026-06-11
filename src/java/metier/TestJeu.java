package metier;

import java.util.ArrayList;
import java.io.File;
import controleur.Controleur;

public class TestJeu 
{
	public static void main(String[] args) 
	{
		ArrayList<Integer>  lstCouleur;
		ArrayList<Integer>  lstSymbole;
		Plateau             plateau;
		Cellule             cellA;
		Cellule             cellB;
		Cellule             cellC;
		Cellule             cellGauche;
		Cellule             cellDroite;
		Cellule             cellHaut;
		Cellule             cellBas;
		ArrayList<Cellule>  trajetAB;
		ArrayList<Cellule>  trajetAC;
		ArrayList<Cellule>  trajetCroise;
		boolean             estCroise;
		Joueur              joueur1;
		Joueur              joueur2;
		Zone                zone1;
		ArrayList<Cellule>  cellsTraversees;
		Liaison             l1;
		Liaison             l2;
		int                 scoreObtenu1;
		int                 scoreObtenu2;
		ArrayList<Joueur>   listeJoueurs;
		ArrayList<ECouleur> reseauxCarte;
		ArrayList<ESymbole> symbolesPioche;
		Pioche              pioche;
		Manche              manche;
		Partie              partie;
		Carte               carteTiree;
		Controleur          ctrl;
		File                fichierTest;
		PlateauData         pData;

		System.out.println("====================================================");
		System.out.println("                VALIDATION DU METIER                ");
		System.out.println("====================================================\n");

		lstCouleur = new ArrayList<Integer>();
		lstCouleur.add(1); 
		lstCouleur.add(1); 
		lstCouleur.add(0); 
		lstCouleur.add(0);

		lstSymbole = new ArrayList<Integer>();
		lstSymbole.add(1); 
		lstSymbole.add(1); 
		lstSymbole.add(0); 
		lstSymbole.add(0);

		plateau = new Plateau(3, 3, 50, lstCouleur, lstSymbole);

		cellA = plateau.getCellule(0, 0);
		cellB = plateau.getCellule(1, 0);
		cellC = plateau.getCellule(2, 0);

		cellA.setSymbole(new Symbole(ESymbole.values()[0]));
		cellB.setSymbole(new Symbole(ESymbole.values()[1]));
		cellC.setSymbole(new Symbole(ESymbole.values()[0]));

		trajetAB = plateau.getTrajet(cellA, cellB);
		trajetAC = plateau.getTrajet(cellA, cellC);

		System.out.println("[1] GEOMETRIE ET PLATEAU");
		System.out.println("----------------------------------------------------");
		System.out.printf("%-30s : %s\n", "Trajet A -> B valide", (trajetAB != null ? "OK" : "ECHEC"));
		System.out.printf("%-30s : %s\n", "Trajet A -> C bloque", (trajetAC == null ? "OK" : "ECHEC"));

		cellB.setSymbole(null); 
		
		cellGauche = plateau.getCellule(0, 1);
		cellDroite = plateau.getCellule(2, 1);
		
		plateau.ajouterLiaison(cellGauche, cellDroite, ECouleur.BLEU);
		System.out.printf("%-30s : %d\n", "Nb liaisons plateau", plateau.getEnsLiaison().size());

		cellHaut = plateau.getCellule(1, 0);
		cellBas  = plateau.getCellule(1, 2);
		
		trajetCroise = plateau.getTrajet(cellHaut, cellBas);
		estCroise    = plateau.estCroiser(trajetCroise);
		System.out.printf("%-30s : %s\n\n", "Croisement detecte", (estCroise ? "OK" : "ECHEC"));

		joueur1 = new Joueur("Timothe");
		joueur2 = new Joueur("Rayan");
		
		zone1 = new Zone(EZone.values()[0], 1);
		cellA.setZone(zone1);
		cellC.setZone(zone1);

		cellsTraversees = new ArrayList<Cellule>();
		cellsTraversees.add(cellB);
		
		l1 = new Liaison(cellA, cellC, ECouleur.BLEU, cellsTraversees);
		joueur1.ajouterLiaison(l1);

		l2 = new Liaison(cellA, cellC, ECouleur.ROUGE, cellsTraversees);
		joueur2.ajouterLiaison(l2);

		scoreObtenu1 = joueur1.calculerScore();
		scoreObtenu2 = joueur2.calculerScore();

		listeJoueurs = new ArrayList<Joueur>();
		listeJoueurs.add(joueur1);
		listeJoueurs.add(joueur2);

		reseauxCarte = new ArrayList<ECouleur>();
		reseauxCarte.add(ECouleur.BLEU);
		reseauxCarte.add(ECouleur.ROUGE);

		symbolesPioche = new ArrayList<ESymbole>();
		
		if (plateau.getLstSymbole() != null) 
			for (int cpt = 0; cpt < plateau.getLstSymbole().size(); cpt++) 
				if (plateau.getLstSymbole().get(cpt) == 1) 
					symbolesPioche.add(ESymbole.values()[cpt]);

		pioche = new Pioche(symbolesPioche, true); 

		System.out.println("[2] JEU, PIOCHE ET SCORES");
		System.out.println("----------------------------------------------------");
		System.out.printf("%-30s : %d pts\n", "Score J1 (" + joueur1.getPseudo() + ")", scoreObtenu1);
		System.out.printf("%-30s : %d pts\n", "Score J2 (" + joueur2.getPseudo() + ")", scoreObtenu2);

		try 
		{
			manche = new Manche(1, listeJoueurs, pioche, reseauxCarte, true);
			System.out.printf("%-30s : %s\n", "Reseau J1", listeJoueurs.get(0).getreseau().getLibelle());
			
			manche.passerTour();
			System.out.printf("%-30s : %s\n", "Joueur courant", manche.getJoueurCourant().getPseudo());

			carteTiree = manche.getPioche().piocher();
			System.out.printf("%-30s : %s\n", "Carte tiree valide", (carteTiree != null ? "OK" : "ECHEC"));
			System.out.printf("%-30s : %s\n\n", "Manche terminee", (manche.getPioche().estMancheTerminee() ? "OUI" : "NON"));

			partie = new Partie(plateau, 2, EModes.POSTE, true);
			partie.getEnsJoueur().clear();
			partie.ajouterJoueur(joueur1);
			partie.ajouterJoueur(joueur2);
			partie.passerManche();
			
			System.out.println("[3] GESTION DES GAGNANTS");
			System.out.println("----------------------------------------------------");
			System.out.printf("%-30s : %d\n", "Manche courante", partie.getMancheCourante());
			

			System.out.println();
		} 
		catch (Exception e) 
		{
			System.out.println("Erreur Partie/Manche : " + e.getMessage());
		}

		System.out.println("[4] FICHIERS ET CONTROLEUR");
		System.out.println("----------------------------------------------------");
		
		try
		{
			fichierTest = new File("test_plateau.csv");
			plateau.enregistrerFichier(fichierTest);
			System.out.printf("%-30s : %s\n", "Sauvegarde plateau", (fichierTest.exists() ? "OK" : "ECHEC"));

			pData = GestionFichier.lireFichier(fichierTest);
			System.out.printf("%-30s : %d\n", "Lecture plateau (longueur)", pData.tailleLongueur);
			
			GestionFichier.supprimerFichier(fichierTest);
		}
		catch (Exception e)
		{
			System.out.println("Erreur Fichier : " + e.getMessage());
		}

		ctrl = new Controleur();
		System.out.printf("%-30s : %d\n\n", "Controleur (nb modes)", ctrl.getNomJeu().length);

		System.out.println("====================================================");
		System.out.println("                   FIN DES TESTS                    ");
		System.out.println("====================================================");
	}
}