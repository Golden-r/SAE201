package metier;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Zone
{
    private String nom;
    private Color couleur;
    
    // Cellules (x,y) appartenant à la zone (forme quelconque)
    private final Set<Long> cellules = new HashSet<>();

    public Zone(String nom, Color couleur)
    {
        this.nom = nom;
        this.couleur = couleur;
    }


    public String getNom(          ) { return this.nom; }
    public void   setNom(String nom) { this.nom = nom ; }

    public Color  getCouleur(             ) { return this.couleur   ; }
    public void   setCouleur(Color couleur) { this.couleur = couleur; }



    private static long key(int x, int y)
    {
        return (((long) x) << 32) ^ (y & 0xffffffffL);
    }

    public void ajouterCellule(int x, int y)
    {
        cellules.add(key(x, y));
    }

    public void retirerCellule(int x, int y)
    {
        cellules.remove(key(x, y));
    }

    public boolean contientCellule(int x, int y)
    {
        return cellules.contains(key(x, y));
    }

    public Set<Long> getCellules() {
        return cellules;
    }
}

