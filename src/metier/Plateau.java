
package metier;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* Plateau
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Plateau 
{
    private int largeur;
    private int hauteur;
    private int tailleCase;
    private List<Zone> zones;
    private Symbole[][] grille;

    public Plateau(int largeur, int hauteur, int tailleCase) 
    {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.tailleCase = tailleCase;
        this.zones = new ArrayList<>();
        this.grille = new Symbole[largeur][hauteur];
    }

    public int              getLargeur       ()              { return this.largeur;    }
    public int              getHauteur       ()              { return this.hauteur;    }
    public int              getTailleCase    ()              { return this.tailleCase; }
    public List<Zone>       getZones         ()              { return this.zones; }
    public Zone             getZoneDeCellule (int x, int y)
    { 
		for (Zone zone : this.zones)
			if (zone.contient(x, y))
				return zone;

		return null; 
	}
    
    public Symbole          getSymbole       (int x, int y) 
    {
        if (!dansGrille(x, y)) return null;
        return grille[x][y];
    }
    
    public void assignerCelluleAZone(int x, int y, Zone zone , Symbole symbole) 
    {
        if ( this.dansGrille(x, y) && zone != null) 
        {
            zone.ajouterSymbole(symbole) ;
        }

    }

    public boolean dansGrille(int x, int y) 
    {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }

    public void poserSymbole(int x, int y, ESymbole symbole) 
    {
        if (dansGrille(x, y)) 
        {
            grille[x][y] = new Symbole(x, y, symbole);
        }
    }
    public void toggleBase(int x, int y, ECouleur couleur) 
    {
        if (dansGrille(x, y) && grille[x][y] != null) 
        {
            grille[x][y].setBase(couleur);
        }
    }
    public void creerZoneSiInexistante(String nom, Color couleur) 
    {
        // Implémentation basique pour éviter les crashs de l'IHM
        this.zones.add(new Zone()); 
    }
}