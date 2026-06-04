package metier;

import java.util.ArrayList;

public class TestSimulation {

    public static void main(String[] args) {
        
        System.out.println("======================================================");
        System.out.println("   ÉPREUVE TITANESQUE : LE MUR ET LE TEST DE CHARGE   ");
        System.out.println("======================================================\n");

        // Plateau gigantesque (30x30)
        Plateau plateau = new Plateau(30, 30, 50, new ArrayList<>(), new ArrayList<>());
        
        System.out.println("[PHASE 1] CONSTRUCTION DU GRAND MUR...");
        // Un mur vertical infranchissable de Haut en Bas sur la colonne X = 15
        for(int y = 0; y < 30; y++) {
            if (y != 14 && y != 15) { // On laisse une double porte au milieu de la carte
                placerSymbole(plateau, 15, y, new Symbole(ESymbole.USINES));
            }
        }

        System.out.println("[PHASE 2] INVASION DE BÂTIMENTS...");
        // On place des dizaines de bâtiments symétriquement
        int nbPlaces = 0;
        for(int x = 2; x < 28; x += 3) {
            for(int y = 2; y < 28; y += 3) {
                if (plateau.getCellule(x, y).estVide()) {
                    placerSymbole(plateau, x, y, new Symbole(ESymbole.MAISONS));
                    nbPlaces++;
                }
            }
        }
        System.out.println(" -> " + nbPlaces + " Maisons ajoutées de part et d'autre du mur.");


        System.out.println("\n[PHASE 3] TEST DE CHARGE (BENCHMARK)");
        System.out.println(" -> Lancement du Brute Force. Le processeur va calculer des milliers de routes...");

        // Chronométrage des performances
        long tempsDebut = System.currentTimeMillis();
        
        plateau.relierToutLesSymbole(ECouleur.BLEU);
        
        long tempsFin = System.currentTimeMillis();


        System.out.println("\n======================================================");
        System.out.println("          ÉTAT FINAL : LE LABYRINTHE (30x30)            ");
        System.out.println("======================================================");
        afficherPlateau(plateau);
        
        System.out.println("\n======================================================");
        System.out.println(" -> TEMPS D'EXÉCUTION DE L'ALGORITHME : " + (tempsFin - tempsDebut) + " millisecondes !");
        System.out.println("======================================================\n");
    }

    private static void placerSymbole(Plateau p, int x, int y, Symbole s) {
        Cellule c = p.getCellule(x, y);
        if (c != null) {
            p.setSymboleDansCellule(c, s);
            c.setEstVide(false); 
        }
    }

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