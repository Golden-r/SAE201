package metier;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import controleur.Controleur;

public class TestJeu
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int choix;

		System.out.println("====================================================");
		System.out.println("                VALIDATION DU METIER                ");
		System.out.println("====================================================\n");

		System.out.println("Choisissez un test a executer :");
		System.out.println("1 - Test aleatoire sur plusieurs manches");
		System.out.println("2 - Test gagnant avec departage en cas d'egalite");
		System.out.print("Votre choix : ");

		choix = sc.nextInt();
		System.out.println();

		switch (choix)
		{
			case 1:
				testAleatoire();
				break;

			case 2:
				testDepartageGagnant();
				break;

			default:
				System.out.println("Choix invalide.");
				break;
		}

		sc.close();

		System.out.println("====================================================");
		System.out.println("                   FIN DES TESTS                    ");
		System.out.println("====================================================");
	}

	public static Plateau creerPlateau()
	{
		ArrayList<Integer> lstCouleur = new ArrayList<Integer>();
		ArrayList<Integer> lstSymbole = new ArrayList<Integer>();

		lstCouleur.add(1);
		lstCouleur.add(1);
		lstCouleur.add(0);
		lstCouleur.add(0);

		lstSymbole.add(1);
		lstSymbole.add(1);
		lstSymbole.add(0);
		lstSymbole.add(0);

		return new Plateau(3, 3, 50, lstCouleur, lstSymbole);
	}

	public static ArrayList<Joueur> creerJoueurs()
	{
		ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();

		Joueur joueur1 = new Joueur("Timothe");
		Joueur joueur2 = new Joueur("Rayan");

		listeJoueurs.add(joueur1);
		listeJoueurs.add(joueur2);

		return listeJoueurs;
	}

	public static Pioche creerPioche(Plateau plateau)
	{
		ArrayList<ESymbole> symbolesPioche = new ArrayList<ESymbole>();

		if (plateau.getLstSymbole() != null)
		{
			for (int cpt = 0; cpt < plateau.getLstSymbole().size(); cpt++)
			{
				if (plateau.getLstSymbole().get(cpt) == 1)
				{
					symbolesPioche.add(ESymbole.values()[cpt]);
				}
			}
		}

		return new Pioche(symbolesPioche, true);
	}

	public static ArrayList<ECouleur> creerReseauxCarte()
	{
		ArrayList<ECouleur> reseauxCarte = new ArrayList<ECouleur>();
		reseauxCarte.add(ECouleur.BLEU);
		reseauxCarte.add(ECouleur.ROUGE);
		return reseauxCarte;
	}

	public static void testAleatoire()
	{
		Plateau plateau;
		ArrayList<Joueur> listeJoueurs;
		ArrayList<ECouleur> reseauxCarte;
		Pioche pioche;
		Manche manche;
		Partie partie;
		Carte carteTiree;
		Random random;

		random = new Random();

		System.out.println("[TEST 1] TEST ALEATOIRE");
		System.out.println("----------------------------------------------------");

		try
		{
			plateau      = creerPlateau();
			listeJoueurs = creerJoueurs();
			reseauxCarte = creerReseauxCarte();
			pioche       = creerPioche(plateau);

			manche = new Manche(1, listeJoueurs, pioche, reseauxCarte, true);
			carteTiree = manche.getPioche().piocher();

			System.out.printf("%-30s : %s\n", "Carte tiree valide", (carteTiree != null ? "OK" : "ECHEC"));

			partie = new Partie(plateau, 2, EModes.POSTE, true);
			partie.getEnsJoueur().clear();

			for (Joueur j : listeJoueurs)
				partie.ajouterJoueur(j);

			int nbManches = 3;

			for (int i = 1; i <= nbManches; i++)
			{
				partie.passerManche();

				int pointsGagnes = random.nextInt(3) + 1;
				int joueurChoisi = random.nextInt(2);

				if (joueurChoisi == 0)
				{
					for (int p = 0; p < pointsGagnes; p++)
						listeJoueurs.get(0).ajouterScore();

					System.out.printf("%-30s : %s gagne %d pt(s)\n", "Resultat manche " + i, listeJoueurs.get(0).getPseudo(), pointsGagnes);
				}
				else
				{
					for (int p = 0; p < pointsGagnes; p++)
						listeJoueurs.get(1).ajouterScore();

					System.out.printf("%-30s : %s gagne %d pt(s)\n", "Resultat manche " + i, listeJoueurs.get(1).getPseudo(), pointsGagnes);
				}

				System.out.printf("%-30s : %d pts\n", "Score J1 (" + listeJoueurs.get(0).getPseudo() + ")", listeJoueurs.get(0).getScore());
				System.out.printf("%-30s : %d pts\n", "Score J2 (" + listeJoueurs.get(1).getPseudo() + ")", listeJoueurs.get(1).getScore());
				System.out.println();
			}

			ArrayList<Joueur> gagnants = partie.getGagnant();

			if (gagnants != null && !gagnants.isEmpty())
			{
				for (Joueur j : gagnants)
				{
					System.out.printf("%-30s : %s (%d pts)\n", "Gagnant detecte", j.getPseudo(), j.getScore());
				}
			}
			else
			{
				System.out.printf("%-30s : %s\n", "Gagnant detecte", "AUCUN");
			}
		}
		catch (Exception e)
		{
			System.out.println("Erreur Test Aleatoire : " + e.getMessage());
		}

		System.out.println();
	}

	public static void testDepartageGagnant()
	{
		Plateau plateau;
		ArrayList<Joueur> listeJoueurs;
		Partie partie;

		System.out.println("[TEST 2] TEST DEPARTAGE GAGNANT");
		System.out.println("----------------------------------------------------");

		try
		{
			plateau      = creerPlateau();
			listeJoueurs = creerJoueurs();

			partie = new Partie(plateau, 2, EModes.POSTE, true);
			partie.getEnsJoueur().clear();

			for (Joueur j : listeJoueurs)
				partie.ajouterJoueur(j);

			Joueur j1 = listeJoueurs.get(0);
			Joueur j2 = listeJoueurs.get(1);

			int[] manchesJ1 = {3, 0, 2};
			int[] manchesJ2 = {1, 2, 2};

			int totalJ1 = 0;
			int totalJ2 = 0;

			for (int i = 0; i < manchesJ1.length; i++)
			{
				totalJ1 += manchesJ1[i];
				totalJ2 += manchesJ2[i];
			}

			for (int i = 0; i < totalJ1; i++) j1.ajouterScore();
			for (int i = 0; i < totalJ2; i++) j2.ajouterScore();

			System.out.printf("%-30s : %s\n", "Joueur 1", j1.getPseudo());
			System.out.printf("%-30s : %s\n", "Joueur 2", j2.getPseudo());
			System.out.printf("%-30s : %d pts\n", "Score final J1", j1.getScore());
			System.out.printf("%-30s : %d pts\n", "Score final J2", j2.getScore());

			if (j1.getScore() > j2.getScore())
			{
				System.out.printf("%-30s : %s\n", "Gagnant", j1.getPseudo());
			}
			else if (j2.getScore() > j1.getScore())
			{
				System.out.printf("%-30s : %s\n", "Gagnant", j2.getPseudo());
			}
			else
			{
				System.out.println("Egalite sur le score total.");
				System.out.println("Application du departage par somme des manches...");

				int sommeManchesJ1 = 0;
				int sommeManchesJ2 = 0;

				for (int i = 0; i < manchesJ1.length; i++)
				{
					sommeManchesJ1 += manchesJ1[i];
					sommeManchesJ2 += manchesJ2[i];
				}

				System.out.printf("%-30s : %d\n", "Somme manches J1", sommeManchesJ1);
				System.out.printf("%-30s : %d\n", "Somme manches J2", sommeManchesJ2);

				if (sommeManchesJ1 > sommeManchesJ2)
				{
					System.out.printf("%-30s : %s\n", "Gagnant au departage", j1.getPseudo());
				}
				else if (sommeManchesJ2 > sommeManchesJ1)
				{
					System.out.printf("%-30s : %s\n", "Gagnant au departage", j2.getPseudo());
				}
				else
				{
					System.out.printf("%-30s : %s\n", "Resultat final", "EGALITE PARFAITE");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Erreur Test Departage : " + e.getMessage());
		}

		System.out.println();
	}

	public static void testFichierEtControleur()
	{
		Plateau plateau;
		File fichierTest;
		PlateauData pData;
		Controleur ctrl;

		System.out.println("[TEST ANNEXE] FICHIERS ET CONTROLEUR");
		System.out.println("----------------------------------------------------");

		try
		{
			plateau = creerPlateau();

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
		System.out.printf("%-30s : %d\n", "Controleur (nb modes)", ctrl.getNomJeu().length);
		System.out.println();
	}
}