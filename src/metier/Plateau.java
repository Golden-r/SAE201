package metier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plateau
{
    private final int largeur;
    private final int hauteur;
    private final int tailleCase;

    private final List<Zone> zones = new ArrayList<>();
    private final List<Enums> couleursReseau;

    // Sommets indexés par cellule
    private final Map<Long, Sommet> sommets = new HashMap<>();

    // Zone par cellule (pour que la zone soit éditable)
    private final Map<Long, Zone> zoneParCellule = new HashMap<>();

    public Plateau(int largeur, int hauteur, int tailleCase) {
        if (largeur <= 0 || hauteur <= 0 || tailleCase <= 0) {
            throw new IllegalArgumentException("dimensions invalides");
        }
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.tailleCase = tailleCase;
        this.couleursReseau = List.of(Enums.BLEU);
    }

    public int getLargeur() { return largeur; }
    public int getHauteur() { return hauteur; }
    public int getTailleCase() { return tailleCase; }

    public List<Zone> getZones() { return zones; }

    private static long key(int x, int y) {
        return (((long) x) << 32) ^ (y & 0xffffffffL);
    }

    public boolean dansGrille(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }

    public Sommet getSommet(int x, int y) {
        return sommets.get(key(x, y));
    }

    public void supprimerSommet(int x, int y) {
        sommets.remove(key(x, y));
        // liens recalculés à terme
    }

    public void poserSommet(int x, int y, TypeSymbole symbole) {
        if (!dansGrille(x, y)) return;
        sommets.put(key(x, y), new Sommet(x, y, symbole));
        // liens recalculés à terme
    }

    public void toggleBase(int x, int y, Enums couleurBase) {
        Sommet s = getSommet(x, y);
        if (s == null) return;
        if (!s.isBase()) {
            s.setBase(true, couleurBase);
        } else {
            s.setBase(false, null);
        }
    }

    public Zone getZoneDeCellule(int x, int y) {
        return zoneParCellule.get(key(x, y));
    }

    public void creerZoneSiInexistante(String nom, Color couleur) {
        if (!zones.isEmpty()) return;
        zones.add(new Zone(nom, couleur));
    }

    public void assignerCelluleAZone(int x, int y, Zone zone) {
        if (!dansGrille(x, y) || zone == null) return;

        // retirer de l’ancienne zone
        Zone ancienne = zoneParCellule.get(key(x, y));
        if (ancienne != null) {
            ancienne.retirerCellule(x, y);
        }

        zoneParCellule.put(key(x, y), zone);
        zone.ajouterCellule(x, y);
    }
}

