
package metier;

import metier.Liaison;

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
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/
	
	private int largeur;
	private int longueur;
	private int tailleCase;

	private Symbole[][] grille;
	private List<Zone> zones;
	
	private ArrayList<Integer> lstCouleur ;
	private ArrayList<Integer> lstSymbole ;
	private ArrayList<Liaison> liaisons;


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Plateau(int largeur, int longueur, int tailleCase) 
	{
		this.largeur    = largeur;
		this.longueur   = longueur;
		this.tailleCase = tailleCase;
		
		this.grille = new Symbole[largeur][longueur];
		this.zones  = new ArrayList<>();
		
		this.lstCouleur = new ArrayList<Integer>();
		this.lstSymbole = new ArrayList<Integer>();
		this.liaisons   = new ArrayList<Liaison>();
	}


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public int                 getTailleLargeur  ()     { return this.largeur;    }
	public int                 getTailleLongueur ()     { return this.longueur;   }
	public int                 getTailleCase     ()     { return this.tailleCase; }
	public Symbole             getSymbole        (int x, int y) 
	{
		if (!dansGrille(x, y)) return null;
		return grille[x][y];
	}
	public List<Zone>          getZones          ()     { return this.zones     ; }
	public Zone                getZoneDeCellule  (int x, int y)
	{ 
		for (Zone zone : this.zones)
			if (zone.contient(x, y))
				return zone ;
		return null; 
	}
	public ArrayList<Integer>  getLstCouleur     ()     { return this.lstCouleur; }
	public ArrayList<Integer>  getLstSymbole     ()     { return this.lstSymbole; }
	public ArrayList<Liaison>  getLiaison        ()     { return this.liaisons;   }
	
	
	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void                setTailleLargeur  ( int largeur)        { this.largeur = largeur ;    }
	public void                setTailleLongueur ( int longueur)       { this.longueur = longueur;   }
	public void                setTailleCase     ( int tailleCase)     { this.tailleCase = tailleCase; }
	public void                setSymboleDansZone(int x, int y, Zone zone , Symbole symbole) 
	{
		if ( this.dansGrille(x, y) && zone != null) 
		{
			zone.ajouterSymbole(symbole) ;
		}

	}
	public ArrayList<Integer>  setLstCouleur     ( ArrayList<Integer> lstCouleur )     { return this.lstCouleur = lstCouleur; }
    public ArrayList<Integer>  setLstSymbole     ( ArrayList<Integer> lstSymbole )     { return this.lstSymbole = lstSymbole; }
	

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	public boolean dansGrille(int x, int y) 
    {
        return x >= 0 && x < this.largeur && y >= 0 && y < this.longueur;
    }

    

	public ArrayList<Symbole> getTrajet (Symbole depart, Symbole arrivee)
	{
		ArrayList<Symbole> trajet = new ArrayList<Symbole>();

		if ( depart == null || arrivee == null ) return null;

		int distX = arrivee.getX() - depart.getX();
		int distY = arrivee.getY() - depart.getY();

		//vérifie que le mouvement est une ligne droite ou une diagonale
		if ( distX != 0 && distY != 0 && Math.abs(distX) != Math.abs(distY) ) return null;

	
		int directionX = 0;
		if(distX > 0) directionX =  1;
		if(distX < 0) directionX = -1;


		int directionY = 0;
		if(distY > 0) directionY =  1;
		if(distY < 0) directionY = -1;

		int curseurX = depart.getX() + directionX;
		int curseurY = depart.getY() + directionY;


		while(curseurX != arrivee.getX() || curseurY != arrivee.getY())
		{

			Symbole tmpSymbole = this.getSymbole(curseurX, curseurY);

			//blocage si obstacle sur le chemin
			if(tmpSymbole != null) return null;

			//coordonnée pour la liaison
			trajet.add(new Symbole(curseurX, curseurY, null));

			curseurX += directionX;
			curseurY += directionY;
		}

		return trajet;
	}

	public boolean croiseAutreReseau(ArrayList<Symbole> trajet)
	{
		for(int cptS = 0; cptS < trajet.size(); cptS++)
			for(int cptL = 0; cptL < this.liaisons.size(); cptL++)
				if(this.liaisons.get(cptL).contientCase(trajet.get(cptS).getX(), trajet.get(cptS).getY()))
					return true;

		return false;
	}


	public boolean ajouterLiaison(Symbole depart, Symbole arrivee, ECouleur couleur)
	{

		if(this.existeChemin(depart, arrivee, couleur)) return false;

		ArrayList<Symbole> trajet = this.getTrajet(depart, arrivee);

		if(trajet == null)                 return false;
		if(this.croiseAutreReseau(trajet)) return false;

		this.liaisons.add(new Liaison(depart, arrivee, couleur, trajet));

		return true;

	}

	public boolean existeChemin(Symbole depart, Symbole arrivee, ECouleur couleur)
	{
		ArrayList<Symbole> visite  = new ArrayList<Symbole>();
		ArrayList<Symbole> aVisite = new ArrayList<Symbole>();

		aVisite.add(depart);

		while(!aVisite.isEmpty())
		{
			Symbole tmpSymbole = aVisite.remove(0);


			if(tmpSymbole == arrivee) return true;
			visite.add(tmpSymbole);

			for(Liaison l : this.liaisons)
			{
				if(l.getReseau() == couleur)
				{
					//depart du câble vers une destination non visité -> mise en attente

					
					//Vérification (sens : depart -> arrivee)
					if(l.getDepart() == tmpSymbole && !visite.contains(l.getArrivee()) && !aVisite.contains(l.getArrivee()))
					{
						aVisite.add(l.getArrivee());
					}
					//Vérification (sens : arrivee -> depart)
					if(l.getArrivee() == tmpSymbole && !visite.contains(l.getDepart()) && !aVisite.contains(l.getDepart()))
					{
						aVisite.add(l.getDepart());
					}
				}
			}
		}
		return false;
	}

	
}