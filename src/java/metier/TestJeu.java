package metier;

import java.util.ArrayList;

/* SAE 2.01 | Développement d'une application 
* TestJeu - Classe de validation du modèle métier
*
* Date     : 11/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/
public class TestJeu {

    public static void main(String[] args) {
        System.out.println("=== DÉBUT DES JEUX DE TEST DU MÉTIER ===\n");

        // 1. Initialisation des listes de configuration pour un plateau 3x3
        ArrayList<Integer> lstCouleur = new ArrayList<>();
        lstCouleur.add(1); lstCouleur.add(1); lstCouleur.add(0); lstCouleur.add(0);

        ArrayList<Integer> lstSymbole = new ArrayList<>();
        lstSymbole.add(1); lstSymbole.add(1); lstSymbole.add(0); lstSymbole.add(0);

        // Création d'un plateau 3x3 avec des cellules de taille 50
        Plateau plateau = new Plateau(3, 3, 50, lstCouleur, lstSymbole);

        // 2. TEST DU REGLAGE DES BÂTIMENTS ET BLOCAGE GÉOMÉTRIQUE
        System.out.println("--- Test 1 : Alignement et Blocage géométrique ---");
        Cellule cellA = plateau.getCellule(0, 0); // Case A
        Cellule cellB = plateau.getCellule(1, 0); // Case B (Milieu)
        Cellule cellC = plateau.getCellule(2, 0); // Case C

        // On place des symboles pour simuler : [ Bâtiment A ] -> [ Bâtiment B ] -> [ Bâtiment C ]
        cellA.setSymbole(new Symbole(ESymbole.values()[0]));
        cellB.setSymbole(new Symbole(ESymbole.values()[1]));
        cellC.setSymbole(new Symbole(ESymbole.values()[0]));

        // Le trajet A -> B doit être valide (0 case intermédiaire vide)
        ArrayList<Cellule> trajetAB = plateau.getTrajet(cellA, cellB);
        System.out.println("Trajet A -> B valide ? " + (trajetAB != null ? "OUI" : "NON (Erreur)"));

        // Le trajet A -> C doit être bloqué par B (Renvoie null car B a un symbole)
        ArrayList<Cellule> trajetAC = plateau.getTrajet(cellA, cellC);
        System.out.println("Trajet A -> C bloqué par B ? " + (trajetAC == null ? "OUI (Comportement Normal)" : "NON (Bug)"));


        // 3. TEST DE POSE DE LIAISON (CÂBLE) ET DE CROISEMENT
        System.out.println("\n--- Test 2 : Pose de liaisons et Croisements ---");
        // On libère la case B pour le test de croisement
        cellB.setSymbole(null); 
        
        // On pose une liaison Bleue sur la ligne du haut (0,0) à (2,0)
        plateau.ajouterLiaison(cellA, cellC, ECouleur.BLEU);
        System.out.println("Nombre de liaisons sur le plateau : " + plateau.getEnsLiaison().size()); // Doit être 1

        // On essaie de faire un câble Rouge vertical qui traverse la case (1,0) depuis (1,1)
        Cellule cellHaut = plateau.getCellule(1, 0);
        Cellule cellBas  = plateau.getCellule(1, 2);
        
        ArrayList<Cellule> trajetCroise = plateau.getTrajet(cellHaut, cellBas);
        boolean estCroise = plateau.estCroiser(trajetCroise);
        System.out.println("Le câble vertical détecte-t-il le croisement ? " + (estCroise ? "OUI (Succès)" : "NON (Bug)"));


        // 4. TEST DU CALCUL DES SCORES D'UN JOUEUR
        System.out.println("\n--- Test 3 : Calcul mathématique du Score ---");
        Joueur joueur = new Joueur("Timothé");
        
        // On crée une fausse zone et on l'affecte aux cellules
        Zone zone1 = new Zone(EZone.values()[0], 1); // Zone ID 1
        cellA.setZone(zone1);
        cellC.setZone(zone1);

        // On crée une liaison manuelle pour le joueur dans cette zone
        ArrayList<Cellule> cellsTraversees = new ArrayList<>();
        cellsTraversees.add(cellB);
        Liaison l1 = new Liaison(cellA, cellC, ECouleur.BLEU, cellsTraversees);
        joueur.ajouterLiaison(l1);

        // Calcul du score : 1 zone visitée * 2 cellules touchées (A et C) = 2 points
        int scoreObtenu = joueur.calculerScore();
        System.out.println("Score calculé pour la manche : " + scoreObtenu + " pts (Attendu : 2 pts)");
        System.out.println("Score cumulé du joueur : " + joueur.getScore() + " pts");


        // 5. TEST DE LA SÉCURITÉ DE LA MANCHE (ANTI-FREEZE)
        System.out.println("\n--- Test 4 : Assignation des réseaux de la Manche ---");
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(joueur);
        listeJoueurs.add(new Joueur("Rayan"));

        ArrayList<ECouleur> reseauxCarte = new ArrayList<>();
        reseauxCarte.add(ECouleur.BLEU);
        reseauxCarte.add(ECouleur.ROUGE);

        // Conversion des entiers du plateau en objets ESymbole valides pour le paquet de cartes
        ArrayList<ESymbole> symbolesPourLaPioche = new ArrayList<>();
        ArrayList<Integer> lstSymbolePlateau = plateau.getLstSymbole();
        if (lstSymbolePlateau != null) {
            for (int cpt = 0; cpt < lstSymbolePlateau.size(); cpt++) {
                if (lstSymbolePlateau.get(cpt) == 1) {
                    symbolesPourLaPioche.add(ESymbole.values()[cpt]);
                }
            }
        }

        // On passe la bonne liste d'ESymbole convertie à la pioche
        Pioche pioche = new Pioche(symbolesPourLaPioche, true); 

        try {
            // Création de la manche
            Manche manche = new Manche(1, listeJoueurs, pioche, reseauxCarte, true);
            System.out.println("Manche créée avec succès !");
            System.out.println("Réseau Joueur 1 : " + listeJoueurs.get(0).getreseau());
            System.out.println("Réseau Joueur 2 : " + listeJoueurs.get(1).getreseau());
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la manche : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== FIN DES JEUX DE TEST ===");
    }
}