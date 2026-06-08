package metier;

import metier.*;

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
	private int tailleCellule;

	private Cellule[][] plateau;
	
	private ArrayList<Integer> lstCouleur ;
	private ArrayList<Integer> lstSymbole ;
	private ArrayList<Liaison> ensLiaison ; 
	private ArrayList<Zone>    ensZones ; 


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Plateau(int longueur, int largeur, int tailleCellule , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole) 
	{
		this.largeur    = largeur;
		this.longueur   = longueur;
		this.tailleCellule = tailleCellule;

		System.out.println( largeur + "|" + longueur );
		
		this.plateau    = new Cellule[longueur][largeur];

		for (int x = 0; x < longueur; x++)
			for (int y = 0; y < largeur; y++) 
			{
				this.plateau[x][y] = new Cellule(x, y);
				System.out.println(this.plateau[x][y]);
			}

	
		this.lstCouleur = lstCouleur;
		this.lstSymbole = lstSymbole;
		this.ensLiaison = new ArrayList<Liaison>();
		this.ensZones   = new ArrayList<Zone>   ();
	}


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public int     getTailleLargeur  ()     { return this.largeur;    }
	public int     getTailleLongueur ()     { return this.longueur;   }
	public int     getTailleCellule  ()     { return this.tailleCellule; }
	public Cellule getCellule        (int x, int y) 
	{
		if (!estDansPlateau(x, y)) return null;
		return plateau[x][y];
	}
	// Dans Plateau.java — section Accesseur
	public Cellule getSymboleDansCellule(Symbole symbole)
	{
		for (int x = 0; x < this.longueur; x++)
			for (int y = 0; y < this.largeur; y++)
				if (this.plateau[x][y] != null && this.plateau[x][y].getSymbole() == symbole)
					return this.plateau[x][y];
		return null;
	}

	public ArrayList<Integer>  getLstCouleur     ()     { return this.lstCouleur; }
	public ArrayList<Integer>  getLstSymbole     ()     { return this.lstSymbole; }
	public ArrayList<Liaison>  getEnsLiaison     ()     { return this.ensLiaison; }
	public ArrayList<Zone   >  getEnsZones       ()     { return this.ensZones  ; }

	public ArrayList<Cellule>  getTrajet         ( Cellule depart, Cellule arrivee )
	{
		ArrayList<Cellule> trajet = new ArrayList<Cellule>();

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

			Cellule tmpCellule = this.getCellule(curseurX, curseurY);

			//blocage si obstacle sur le chemin
			if(tmpCellule != null && !tmpCellule.estVide()) return null;

			//coordonnée pour la liaison
		    trajet.add(tmpCellule);

			curseurX += directionX;
			curseurY += directionY;
		}

		return trajet;
	}

	public Color getCouleurProchaineZone( EZone typeZone )
	{
		/*----------------*/
		/* Données       */
		/*----------------*/

		ArrayList<Zone> ensZoneMemeType ;
		Zone            nvZone          ;
		int             occurrence      ;

		/*----------------*/
		/* Instructions  */
		/*----------------*/

		ensZoneMemeType = new ArrayList<>() ;

		for ( int cptX = 0 ; cptX < this.longueur ; cptX++ )
		{
			for ( int cptY = 0 ; cptY < this.largeur ; cptY++ )
			{
				Cellule tmpCell = this.plateau[cptX][cptY] ;

				if ( tmpCell != null && tmpCell.getZone() != null && tmpCell.getZone().getTypeZone() == typeZone )
				{
					if ( !ensZoneMemeType.contains( tmpCell.getZone() ) )
					{
						ensZoneMemeType.add( tmpCell.getZone() ) ;
					}
				}
			}
		}

		occurrence = ensZoneMemeType.size() ;


		nvZone = new Zone( typeZone ) ;
		nvZone.setCouleur( typeZone, 0, occurrence ) ;

		return nvZone.getCouleur() ;
	}
	
	
	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    
	public void setSymboleDansCellule(Cellule cellule, Symbole symbole) 
	{
		if ( this.estDansPlateau(cellule.getX(), cellule.getY()) )//&& zone != null) 
		{
			plateau[cellule.getX()][cellule.getY()].setSymbole(symbole);
			cellule.setEstVide(false);
		}

	}

	public void setZoneDansCellule(Cellule cellule , Zone zone) 
	{
		if ( this.estDansPlateau(cellule.getX(), cellule.getY())) 
		{
			plateau[cellule.getX()][cellule.getY()].setZone(zone);
		}

	}

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
	public void relierToutLesSymbole ( ECouleur couleur )
	{
		ArrayList<Cellule> batiments = new ArrayList<>();

		for ( int cpt = 0 ; cpt < this.plateau.length ; cpt ++ ) {
			for ( int cpt2 = 0 ; cpt2 < this.plateau[cpt].length ; cpt2++ ) {
				Cellule c = this.plateau[cpt][cpt2];
				if ( c != null && !c.estVide() && c.getSymbole() != null ) {
					batiments.add ( c );
				}
			}
		}

		for ( int i = 0 ; i < batiments.size() ; i++ ) {
			for ( int j = i + 1 ; j < batiments.size() ; j++ ) {
				
				this.ajouterLiaison(batiments.get(i), batiments.get(j), couleur) ;
			}
		}
	}

    public boolean estDansPlateau( int x, int y ) 
    {
        return x >= 0 && x < longueur && y >= 0 && y < largeur;
    }


	public boolean estCroiser(ArrayList<Cellule> trajet)
	{
		for(int cptS = 0; cptS < trajet.size(); cptS++)
			for(int cptL = 0; cptL < this.ensLiaison.size(); cptL++)
				if(this.ensLiaison.get(cptL).contientCellule(trajet.get(cptS).getX(), trajet.get(cptS).getY()))
					return true;

		return false;
	}

	public boolean existeChemin(Cellule depart, Cellule arrivee, ECouleur couleur)
	{
		ArrayList<Cellule> visite  = new ArrayList<Cellule>();
		ArrayList<Cellule> aVisite = new ArrayList<Cellule>();

		aVisite.add(depart);

		while(!aVisite.isEmpty())
		{
			Cellule tmpCellule = aVisite.remove(0);


			if(tmpCellule == arrivee) return true;
			visite.add(tmpCellule);

			for(Liaison l : this.ensLiaison)
			{
				if(l.getReseau() == couleur)
				{
					//depart du câble vers une destination non visité -> mise en attente

					//Vérification (sens : depart -> arrivee)
					if(l.getDepart() == tmpCellule && !visite.contains(l.getArrivee()) && !aVisite.contains(l.getArrivee()))
					{
						aVisite.add(l.getArrivee());
					}
					//Vérification (sens : arrivee -> depart)
					if(l.getArrivee() == tmpCellule && !visite.contains(l.getDepart()) && !aVisite.contains(l.getDepart()))
					{
						aVisite.add(l.getDepart());
					}
				}
			}
		}
		return false;
	}

	public boolean ajouterLiaison(Cellule depart, Cellule arrivee, ECouleur couleur)
	{

		//if(this.existeChemin(depart, arrivee, couleur)) return false;

		ArrayList<Cellule> trajet = this.getTrajet(depart, arrivee);

		if(trajet == null)                 return false;
		//if(this.estCroiser(trajet)) return false;

		this.ensLiaison.add(new Liaison(depart, arrivee, couleur, trajet));

		return true;

	}

	public void retirerSymbole(int x, int y)
	{
		Cellule tmpCellule = this.getCellule(x, y);

		if (tmpCellule == null || tmpCellule.estVide())
			return;

		ArrayList<Cellule> extremites = new ArrayList<Cellule>();
		ECouleur reseau = null;

		for (int cpt = this.ensLiaison.size()-1; cpt >= 0; cpt--)
		{
			Liaison tmpLiaison = this.ensLiaison.get(cpt);

			//liaison réseau part du symbole supprimé
			if (tmpLiaison.getDepart() == tmpCellule)
			{
				if (!extremites.contains(tmpLiaison.getArrivee()))
					extremites.add(tmpLiaison.getArrivee());
				reseau = tmpLiaison.getReseau();
				this.ensLiaison.remove(cpt);
			}

			//liaison réseau arrive du symbole supprimé
			else if (tmpLiaison.getArrivee() == tmpCellule)
			{
				if (!extremites.contains(tmpLiaison.getDepart()))
					extremites.add(tmpLiaison.getDepart());
				reseau = tmpLiaison.getReseau();
				this.ensLiaison.remove(cpt);
			}
		}

		tmpCellule.setSymbole(null);
		tmpCellule.setEstVide(true);

		if (reseau != null && extremites.size() >= 2)
			for (int cpt1 = 0; cpt1 < extremites.size(); cpt1++)
				for (int cpt2 = cpt1 + 1; cpt2 < extremites.size(); cpt2++)
					this.ajouterLiaison(extremites.get(cpt1), extremites.get(cpt2), reseau);
	
	}



	public void supprimerZone(int lig, int col)
	{
		Cellule cellCible = this.getCellule(lig,col);
		
		if(cellCible == null || cellCible.getZone() == null)
			return;
			
		Zone zoneCible = cellCible.getZone();
			
		int totalCellulesZone = 0;
		int ligDepart         = -1;
		int colDepart         = -1;
		
		for (int cptX = 0; cptX < this.longueur; cptX++)
			for (int cptY = 0; cptY < this.largeur; cptY++)
				if (this.plateau[cptX][cptY].getZone() == zoneCible && this.plateau[cptX][cptY] != null)
				{
					totalCellulesZone++;
					if (cptX != lig || cptY != col)
					{
						ligDepart = cptX;
						colDepart = cptY;
					}
				}
				
		if (totalCellulesZone <= 1)
		{
			cellCible.setZone(null);
			return;
		}
		
		cellCible.setZone(null);
		
		boolean[][] visite             = new boolean[this.longueur][this.largeur];
		int         cellulesConnectees = this.compterCellulesConnectees(ligDepart, colDepart, zoneCible, visite);
		
		if (cellulesConnectees < totalCellulesZone - 1)
			cellCible.setZone(zoneCible);
		}

	private int compterCellulesConnectees(int longueur, int largeur, Zone zoneCible, boolean[][] visite)
	{
		if (!this.estDansPlateau(longueur,largeur))
			return 0;
			
		if (visite[longueur][largeur] || this.plateau[longueur][largeur] == null || this.plateau[longueur][largeur].getZone() != zoneCible )
			return 0;
			
		visite[longueur][largeur] = true;
		int nb = 1;
		
		nb += this.compterCellulesConnectees(longueur - 1, largeur, zoneCible, visite);
		nb += this.compterCellulesConnectees(longueur + 1, largeur, zoneCible, visite);
		nb += this.compterCellulesConnectees(longueur, largeur - 1, zoneCible, visite);
		nb += this.compterCellulesConnectees(longueur, largeur + 1, zoneCible, visite);
		
		return nb;
	}


	



	
	
}