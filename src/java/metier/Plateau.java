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

	private ArrayList<Case> ensCases;

	//private Symbole[][] plateau;
	//private List<Zone>  zones;
	
	private ArrayList<Integer> lstCouleur ;
	private ArrayList<Integer> lstSymbole ; 
	private ArrayList<Liaison> ensLiaison ; // ?


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Plateau(int largeur, int longueur, int tailleCase , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole) 
	{
		this.largeur    = largeur;
		this.longueur   = longueur;
		this.tailleCase = tailleCase;
		
		this.plateau    = new Symbole[largeur][longueur];
		this.zones      = new ArrayList<>();
		
		this.lstCouleur = lstCouleur;
		this.lstSymbole = lstSymbole;
		this.ensLiaison   = new ArrayList<Liaison>();
	}


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public int                 getTailleLargeur  ()     { return this.largeur;    }
	public int                 getTailleLongueur ()     { return this.longueur;   }
	public int                 getTailleCase     ()     { return this.tailleCase; }
	public Symbole             getSymbole        (int x, int y) 
	{
		if (!estDansPlateau(x, y)) return null;
		return plateau[x][y];
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
	public ArrayList<Liaison>  getEnsLiaison     ()     { return this.ensLiaison;   }
	public ArrayList<Symbole>  getTrajet         (Symbole depart, Symbole arrivee)
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
	
	
	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    
	public void setSymboleDansZone(int x, int y, Zone zone , Symbole symbole) 
	{
		if ( this.estDansPlateau(x, y) && zone != null) 
		{
			zone.ajouterSymbole(symbole) ;
		}

	}
	//public void  setZone             ( Zone               zone )          { this.zones = zone          ; }
	//public void  setEnsLiaisons      ( ArrayList<Liaison> ensLiaison )      { this.ensLiaison   = ensLiaison ; }
    

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/


    public boolean estDansPlateau(int x, int y) 
    {
        return x >= 0 && x < largeur && y >= 0 && y < longueur;
    }

	public boolean estCroiser(ArrayList<Symbole> trajet)
	{
		for(int cptS = 0; cptS < trajet.size(); cptS++)
			for(int cptL = 0; cptL < this.ensLiaison.size(); cptL++)
				if(this.ensLiaison.get(cptL).contientCase(trajet.get(cptS).getX(), trajet.get(cptS).getY()))
					return true;

		return false;
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

			for(Liaison l : this.ensLiaison)
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

	public boolean ajouterLiaison(Symbole depart, Symbole arrivee, ECouleur couleur)
	{

		if(this.existeChemin(depart, arrivee, couleur)) return false;

		ArrayList<Symbole> trajet = this.getTrajet(depart, arrivee);

		if(trajet == null)                 return false;
		if(this.estCroiser(trajet)) return false;

		this.ensLiaison.add(new Liaison(depart, arrivee, couleur, trajet));

		return true;

	}

	public void retirerSymbole(int x, int y)
	{
		Symbole supprSymbole = this.getSymbole(x,y);

		if(supprSymbole == null) return;
		
		ArrayList<Symbole> extremites = new ArrayList<Symbole>();
		ECouleur reseau = null;

		for(int cpt = this.ensLiaison.size()-1; cpt >= 0; cpt--)
		{
			Liaison tmpLiaison = this.ensLiaison.get(cpt);

			//liaison réseau part du symbole supprimé
			if(tmpLiaison.getDepart() == supprSymbole)
			{
				if(!extremites.contains(tmpLiaison.getArrivee()))
					extremites.add(tmpLiaison.getArrivee());
				reseau = tmpLiaison.getReseau();
				this.ensLiaison.remove(cpt);
			}

			//liaison réseau arrive du symbole supprimé
			else if (tmpLiaison.getArrivee() == supprSymbole)
			{
				if(!extremites.contains(tmpLiaison.getDepart()))
					extremites.add(tmpLiaison.getDepart());
				reseau = tmpLiaison.getReseau();
				this.ensLiaison.remove(cpt);
			}
		}

		this.plateau[x][y] = null;

		if(reseau != null && extremites.size() >= 2)
			for(int cpt1 = 0; cpt1 < extremites.size(); cpt1++)
				for(int cpt2 = cpt1 + 1; cpt2 < extremites.size(); cpt2++)
					this.ajouterLiaison(extremites.get(cpt1), extremites.get(cpt2), reseau);

	}

	public int calculerScore(ECouleur reseau)
	{
		ArrayList<Zone> zoneTraversees = new ArrayList<Zone>();

		for(int cptL = 0; cptL < this.ensLiaison.size(); cptL++)
		{
			Liaison tmpLiaison = this.ensLiaison.get(cptL);
			
			if(tmpLiaison.getReseau() == reseau)
			{
				Zone zoneDep = this.getZoneDeCellule(tmpLiaison.getDepart().getX(), tmpLiaison.getDepart().getY());
				if(zoneDep != null && !zoneTraversees.contains(zoneDep))
					zoneTraversees.add(zoneDep);

				Zone zoneArr = this.getZoneDeCellule(tmpLiaison.getArrivee().getX(), tmpLiaison.getArrivee().getY());
				if(zoneArr != null && !zoneTraversees.contains(zoneArr))
					zoneTraversees.add(zoneArr);
				
				for(int cptL2 = 0; cptL2 < tmpLiaison.getCaseTraversees().size(); cptL2++)
				{
					Symbole tmpSymbole = tmpLiaison.getCaseTraversees().get(cptL2);
					Zone zoneCase = this.getZoneDeCellule(tmpSymbole.getX(),tmpSymbole.getY());

					if(zoneCase != null && !zoneTraversees.contains(zoneCase))
						zoneTraversees.add(zoneCase);
				}
			}
		}

		int nbZones    = zoneTraversees.size();
		int maxSymbole = 0;

		for(int cptZ = 0; cptZ < zoneTraversees.size(); cptZ++)
		{
			Zone tmpZone = zoneTraversees.get(cptZ);
			ArrayList<Symbole> batimentConnectes = new ArrayList<Symbole>();

			for(int cptL = 0; cptL < this.ensLiaison.size(); cptL++)
			{
				Liaison tmpLiaison = this.ensLiaison.get(cptL);

				if(tmpLiaison.getReseau() == reseau)
				{
					if(tmpZone.contient(tmpLiaison.getDepart().getX(), tmpLiaison.getDepart().getY()) 
					   && !batimentConnectes.contains(tmpLiaison.getDepart()))
						batimentConnectes.add(tmpLiaison.getDepart());
					
					if(tmpZone.contient(tmpLiaison.getArrivee().getX(), tmpLiaison.getArrivee().getY()) 
					   && !batimentConnectes.contains(tmpLiaison.getArrivee()))
						batimentConnectes.add(tmpLiaison.getArrivee());
				}
			}

			if(batimentConnectes.size() > maxSymbole)
				maxSymbole = batimentConnectes.size();
		}
		
		return nbZones * maxSymbole;
	}
}