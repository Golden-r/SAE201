package metier;

import java.util.ArrayList;

public class TestSimulation {

    public static void main(String[] args) {
        
        System.out.println("======================================================");
        System.out.println("   NOUVEAU TEST : LA LIGNE DE MIRE (EFFET D'ÉCLIPSE)  ");
        System.out.println("======================================================\n");

        // Plateau de 21x21 (idéal pour avoir un vrai centre parfait en 10,10)
        Plateau plateau = new Plateau(21, 21, 50, new ArrayList<>(), new ArrayList<>());
        
        System.out.println("[PHASE 1] PLACEMENT DU CENTRE ET DES BOUCLIERS...");

        // 1. Le Noyau Central
        placerSymbole(plateau, 10, 10, new Symbole(ESymbole.USINES)); // [U] au centre absolu

        // 2. La Première Couronne (Rayon proche) - Ce sont les "Boucliers"
        placerSymbole(plateau, 10, 6, new Symbole(ESymbole.MAISONS));  // Nord
        placerSymbole(plateau, 10, 14, new Symbole(ESymbole.MAISONS)); // Sud
        placerSymbole(plateau, 6, 10, new Symbole(ESymbole.MAISONS));  // Ouest
        placerSymbole(plateau, 14, 10, new Symbole(ESymbole.MAISONS)); // Est

        placerSymbole(plateau, 6, 6, new Symbole(ESymbole.MAISONS));   // Diagonale Haut-Gauche
        placerSymbole(plateau, 14, 6, new Symbole(ESymbole.MAISONS));  // Diagonale Haut-Droite
        placerSymbole(plateau, 6, 14, new Symbole(ESymbole.MAISONS));  // Diagonale Bas-Gauche
        placerSymbole(plateau, 14, 14, new Symbole(ESymbole.MAISONS)); // Diagonale Bas-Droite

        // 3. La Deuxième Couronne (Rayon lointain) - Placée exactement dans l'alignement
        placerSymbole(plateau, 10, 1, new Symbole(ESymbole.ECOLES));  // Nord Lointain
        placerSymbole(plateau, 10, 19, new Symbole(ESymbole.ECOLES)); // Sud Lointain
        placerSymbole(plateau, 1, 10, new Symbole(ESymbole.ECOLES));  // Ouest Lointain
        placerSymbole(plateau, 19, 10, new Symbole(ESymbole.ECOLES)); // Est Lointain

        placerSymbole(plateau, 1, 1, new Symbole(ESymbole.ECOLES));   // Diagonale Extrême HG
        placerSymbole(plateau, 19, 1, new Symbole(ESymbole.ECOLES));  // Diagonale Extrême HD
        placerSymbole(plateau, 1, 19, new Symbole(ESymbole.ECOLES));  // Diagonale Extrême BG
        placerSymbole(plateau, 19, 19, new Symbole(ESymbole.ECOLES)); // Diagonale Extrême BD

        System.out.println("\n--- Plateau Initial ---");
        afficherPlateau(plateau);

        System.out.println("\n[PHASE 2] LANCEMENT DE L'INTERCONNEXION GLOBALE (VERT)");
        plateau.relierToutLesSymbole(ECouleur.VERT);

        System.out.println("\n======================================================");
        System.out.println("                 ÉTAT FINAL (21x21)                   ");
        System.out.println("======================================================");
        afficherPlateau(plateau);
    }

    /**
     * Méthode utilitaire pour simplifier le placement des bâtiments
     */
    private static void placerSymbole(Plateau p, int x, int y, Symbole s) {
        Cellule c = p.getCellule(x, y);
        if (c != null) {
            p.setSymboleDansCellule(c, s);
            //c.setEstVide(false); 
        }
    }

    /**
     * Méthode utilitaire pour afficher le plateau dans le terminal
     */
    public static void afficherPlateau(Plateau p) {
        for (int y = 0; y < p.getTailleLongueur(); y++) {
            for (int x = 0; x < p.getTailleLargeur(); x++) {
                
                Cellule c = p.getCellule(x, y);
                
                if (c != null && !c.estVide() && c.getSymbole() != null) {
                    System.out.print("[" + c.getSymbole().getSymbole().getNom() + "]");
                } else {
                    boolean aLiaison = false;
                    for (Liaison l : p.getEnsLiaison()) {
                        if (l.contientCellule(x, y)) {
                            aLiaison = true;
                            break;
                        }
                    }
                    if (aLiaison) {
                        System.out.print("[*]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
            }
            System.out.println(); 
        }
    }
}