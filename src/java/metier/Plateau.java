package metier;

import metier.*;

import java.util.ArrayList;
import java.awt.Color;

import java.io.File;

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

	private ArrayList<Integer> lstCouleur ;
	private ArrayList<Integer> lstSymbole ;

	private Cellule[][] plateau;

	private ArrayList<Cellule> ensBases   ;
	private ArrayList<Liaison> ensLiaison ;
	private ArrayList<Zone>    ensZones   ;

	private int  etapeConception ; // 1 = zone , 2 = symbole , 3 = base
	private Zone zoneCourante;


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Plateau(int longueur, int largeur, int tailleCellule , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole) 
	{
		this.largeur       = largeur;
		this.longueur      = longueur;
		this.tailleCellule = tailleCellule;

		System.out.println( largeur + "|" + longueur );
		
		this.plateau    = new Cellule[longueur][largeur];

		for (int x = 0; x < longueur; x++)
			for (int y = 0; y < largeur; y++) 
				this.plateau[x][y] = new Cellule(x, y);
	
		this.lstCouleur = lstCouleur;
		this.lstSymbole = lstSymbole;
		this.ensBases   = new ArrayList<Cellule>();
		this.ensLiaison = new ArrayList<Liaison>();
		this.ensZones   = new ArrayList<Zone>   ();

		this.etapeConception = 1 ;
	}


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public int     getTailleLargeur  ()             { return this.largeur       ;}
	public int     getTailleLongueur ()             { return this.longueur      ;}
	public int     getTailleCellule  ()             { return this.tailleCellule ;}
	public Cellule getCellule        (int x, int y) 
	{
		if (!estDansPlateau(x, y)) return null;
		return plateau[x][y];
	}
	public Cellule getSymboleDansCellule(Symbole symbole)
	{
		for (int x = 0; x < this.longueur; x++)
			for (int y = 0; y < this.largeur; y++)
				if (this.plateau[x][y] != null && this.plateau[x][y].getSymbole() == symbole)
					return this.plateau[x][y];
		
		return null;
	}
	public ArrayList<Integer>  getLstCouleur     ()          { return this.lstCouleur                                 ;}
	public ArrayList<Integer>  getLstSymbole     ()          { return this.lstSymbole                                 ;}
	public ArrayList<Liaison>  getEnsLiaison     ()          { return this.ensLiaison                                 ;}
	public ArrayList<Zone   >  getEnsZones       ()          { return this.ensZones                                   ;}
	public ArrayList<Cellule>  getEnsBases       ()          { return this.ensBases                                   ;}
	public int                 getEtapeConception()          { return this.etapeConception                            ;}
	public Zone                getZoneCourante   ()          { return this.zoneCourante                               ;}
	public int[][]             getCheminLiaison( int indice ){ return this.ensLiaison.get( indice ).getCoordsChemin() ;}

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
		ArrayList<Zone> ensZoneMemeType ;
		Zone            nvZone          ;
		int             occurrence      ;

		ensZoneMemeType = new ArrayList<>() ;

		for(int cptX = 0; cptX < this.longueur; cptX++)
		{
			for(int cptY = 0; cptY < this.largeur; cptY++)
			{
				Cellule tmpCell = this.plateau[cptX][cptY] ;

				if(tmpCell != null && tmpCell.getZone() != null && tmpCell.getZone().getTypeZone() == typeZone)
					if(!ensZoneMemeType.contains(tmpCell.getZone()))
						ensZoneMemeType.add(tmpCell.getZone()) ;
			}
		}

		occurrence = ensZoneMemeType.size() ;

		nvZone = new Zone( typeZone ) ;
		nvZone.setCouleur( typeZone, 0, occurrence ) ;

		return nvZone.getCouleur() ;
	}
	
	public int getNbZonesDistinctes()
	{
		ArrayList<Zone> zonesUniques = new ArrayList<Zone>() ;

		for ( int cptX = 0 ; cptX < this.longueur ; cptX++ )
			for ( int cptY = 0 ; cptY < this.largeur ; cptY++ )
			{
				Cellule tmpCell = this.plateau[cptX][cptY] ;

				if ( tmpCell != null && tmpCell.getZone() != null )
					if ( !zonesUniques.contains( tmpCell.getZone() ) )
						zonesUniques.add( tmpCell.getZone() ) ;
			}

		return zonesUniques.size() ;
	}
	
	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    
	public void setSymboleDansCellule(Cellule cellule, Symbole symbole) 
	{
		if ( this.estDansPlateau(cellule.getX(), cellule.getY()) )
			plateau[cellule.getX()][cellule.getY()].setSymbole(symbole);
	}

	public void setZoneDansCellule(Cellule cellule , Zone zone) 
	{
		if ( this.estDansPlateau(cellule.getX(), cellule.getY())) 
			plateau[cellule.getX()][cellule.getY()].setZone(zone);

	}

	public void setBaseDansCellule(Cellule cellule, ECouleur base)
	{
		if (cellule.getSymbole() == null) return; 

		System.out.println("setting the base "+base.getLibelle() + " with cell : "+ cellule);

		Cellule ecrase = null;

		for (Cellule cel : this.ensBases)
		{

			System.out.println(cel);

			if (cel.getSymbole().getCouleurBase() != null )
			{
				// Si il y'a deja une base du même type
				if (cel.getSymbole().getCouleurBase() == base)
				{
					cel.getSymbole().setBase(null );
					ecrase = cel;
					System.out.println("gay");
				}
			}
		}

		if (ecrase != null)
			this.ensBases.remove(ecrase);

		this.ensBases.add(cellule);

		cellule.getSymbole().setBase(base);
	} 

	public void setEtapeConception( int etape  )                   { this.etapeConception = etape ;}
	public void setZoneCourante   ( Zone zone  )                   { this.zoneCourante    = zone  ;}
	public void setEnsLiaison     (ArrayList<Liaison> EnsLiaison ) { this.ensLiaison = EnsLiaison  ;}

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
	public void relierToutLesSymbole ( ECouleur couleur )
	{
		ArrayList<Cellule> batiments = new ArrayList<>();

		for ( int cpt = 0 ; cpt < this.plateau.length ; cpt ++ ) {
			for ( int col = 0 ; col < this.plateau[cpt].length ; col++ )
			{
				Cellule c = this.plateau[cpt][col];

				if ( c != null && !c.estVide() && c.getSymbole() != null )
					batiments.add ( c );	
			}
		}

		for ( int i = 0 ; i < batiments.size() ; i++ ) 
			for ( int j = i + 1 ; j < batiments.size() ; j++ ) 
				this.ajouterLiaison(batiments.get(i), batiments.get(j), couleur) ;
	}

    public boolean estDansPlateau( int x, int y )            { return x >= 0 && x < longueur && y >= 0 && y < largeur; }
	public boolean estCroiser    (ArrayList<Cellule> trajet)
	{
		for(int cptS = 0; cptS < trajet.size(); cptS++)
			for(int cptL = 0; cptL < this.ensLiaison.size(); cptL++)
				if(this.ensLiaison.get(cptL).contientCellule(trajet.get(cptS).getX(), trajet.get(cptS).getY()))
					return true;

		return false;
	}

	public boolean existeChemin  (Cellule depart, Cellule arrivee, ECouleur couleur)
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
					//depart du câble vers une destination non visité

					//Vérification (sens : depart -> arrivee)
					if(l.getDepart() == tmpCellule && !visite.contains(l.getArrivee()) && !aVisite.contains(l.getArrivee()))
						aVisite.add(l.getArrivee());
					
					//Vérification (sens : arrivee -> depart)
					if(l.getArrivee() == tmpCellule && !visite.contains(l.getDepart()) && !aVisite.contains(l.getDepart()))
						aVisite.add(l.getDepart());
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

		if (reseau != null && extremites.size() >= 2)
			for (int lig = 0; lig < extremites.size(); lig++)
				for (int col = lig + 1; col < extremites.size(); col++)
					this.ajouterLiaison(extremites.get(lig), extremites.get(col), reseau);
	
	}

	public void supprimerZone(int x, int y)
	{
		Cellule cellCible = this.getCellule(x, y);

		if (cellCible == null || cellCible.getZone() == null)
			return;

		Zone zoneCible         = cellCible.getZone();
		int  totalCellulesZone = 0;
		int  ligDepart         = -1;
		int  colDepart         = -1;

		for (int cptX = 0; cptX < this.longueur; cptX++)
			for (int cptY = 0; cptY < this.largeur; cptY++)
				if (this.plateau[cptX][cptY] != null && this.plateau[cptX][cptY].getZone() == zoneCible)
				{
					totalCellulesZone++;
					if (cptX != x || cptY != y)
					{
						ligDepart = cptX;
						colDepart = cptY;
					}
				}

		if (totalCellulesZone <= 1)
		{
			cellCible.setZone(null);
			this.ensZones.remove(zoneCible);
			return;
		}

		cellCible.setZone(null);

		boolean[][] visite             = new boolean[this.longueur][this.largeur];
		int         cellulesConnectees = this.compterCellulesConnectees(ligDepart, colDepart, zoneCible, visite);

		if (cellulesConnectees < totalCellulesZone - 1)
			cellCible.setZone(zoneCible);
	}

	private int compterCellulesConnectees(int lig, int col, Zone zoneCible, boolean[][] visite)
	{
		if (!this.estDansPlateau(lig, col))
			return 0;

		if (visite[lig][col] || this.plateau[lig][col] == null || this.plateau[lig][col].getZone() != zoneCible)
			return 0;

		visite[lig][col] = true;
		int nb = 1;

		nb += this.compterCellulesConnectees(lig - 1, col, zoneCible, visite);
		nb += this.compterCellulesConnectees(lig + 1, col, zoneCible, visite);
		nb += this.compterCellulesConnectees(lig, col - 1, zoneCible, visite);
		nb += this.compterCellulesConnectees(lig, col + 1, zoneCible, visite);

		return nb;
	}

	public void initialiserEnsBases ()
	{
		for ( int lig = 0 ; lig < plateau.length ; lig ++ )
		{
			for ( int col = 0 ; col < plateau[lig].length ; col++ )
			{
				if ( this.plateau[lig][col].estBase() )
				{
					this.ensBases.add( this.plateau[lig][col] ) ;
				}
			}
		}
	}

/* 
	public void enregistrerFichier ( File fichier )
	{
		ArrayList<String> lstEnregistrelent =  new ArrayList<String>() ;

		lstEnregistrelent.add ( "" + this.largeur       ) ;
		lstEnregistrelent.add ( "" + this.longueur      ) ;
		lstEnregistrelent.add ( "" + this.tailleCellule ) ;
				
		lstEnregistrelent.add ( this.getLstCouleur().toString().replace("[", "").replace("]", "")) ;
		lstEnregistrelent.add ( this.getLstSymbole().toString().replace("[", "").replace("]", "") ) ;

		for ( int lig = 0 ; lig < plateau.length ; lig ++ )
			for ( int col = 0 ; col < plateau[lig].length ; col++ )
				if ( this.plateau[lig][col].getZone() != null || this.plateau[lig][col].getSymbole() != null ) 
				{ 
					lstEnregistrelent.add( "" + this.plateau[lig][col] ) ;
				}

		GestionFichier.ecrireFichier( fichier, lstEnregistrelent ) ;
	}
	*/

	public void enregistrerFichier(File fichier)
	{
		/*----------------*/
		/* Données        */
		/*----------------*/

		ArrayList<String> lstEnregistrement;

		/*----------------*/
		/* Instructions   */
		/*----------------*/

		lstEnregistrement = new ArrayList<String>();

		lstEnregistrement.add("" + this.largeur);
		lstEnregistrement.add("" + this.longueur);
		lstEnregistrement.add("" + this.tailleCellule);
				
		lstEnregistrement.add(this.getLstCouleur().toString().replace("[", "").replace("]", ""));
		lstEnregistrement.add(this.getLstSymbole().toString().replace("[", "").replace("]", ""));

		for (int x = 0; x < this.plateau.length; x++)
			for (int y = 0; y < this.plateau[x].length; y++)
			{
				Cellule c = this.plateau[x][y];
				
				if (c.getZone() != null || c.getSymbole() != null) 
				{ 
					// On récupère le nom de l'enum sous forme de texte (ou "null" si vide)
					String nomZone    = (c.getZone()    != null) ? c.getZone().getTypeZone().name()   : "null";
					String nomSymbole = (c.getSymbole() != null) ? c.getSymbole().getSymbole().name() : "null";
					
					// Format de sauvegarde : X,Y,Zone,Symbole (ex: 5,10,ZONE1,MAISONS)
					lstEnregistrement.add(x + "," + y + "," + nomZone + "," + nomSymbole);
				}
			}

		GestionFichier.ecrireFichier(fichier, lstEnregistrement);
	}
}

