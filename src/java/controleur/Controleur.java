package controleur;

import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;

import ihm.FrameCreation;
import ihm.FrameModification;

import metier.*;

/* SAE 2.01 | Développement d'une application 
* Controleur
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Controleur
{

	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

	private FrameCreation     frameCreation;
	private FrameModification frameModification;
	
	private Plateau           metierPlateau;
	private GestionFichier    metierGestionFichier;

	private int indiceZone ;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Controleur()
	{

	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public int                getTailleLargeur       ()             { return this.metierPlateau.getTailleLargeur ()                               ;}
	public int                getTailleLongueur      ()             { return this.metierPlateau.getTailleLongueur() 	                          ;}
	public int                getTailleCellule       ()             { return this.metierPlateau.getTailleCellule () 	                          ;}
	public ArrayList<Integer> getLstCouleur          ()             { return this.metierPlateau.getLstCouleur    () 	                          ;}
	public ArrayList<Integer> getLstSymbole          ()             { return this.metierPlateau.getLstSymbole    () 	                          ;}
	public ArrayList<Liaison> getEnsLiaisons         ()             { return this.metierPlateau.getEnsLiaison    ()                               ;}
	public ArrayList<Zone   > getLibelleZone         ()             { return this.metierPlateau.getEnsZones      () 	                          ;}
	public String[]           getZones               ()             { return EZone.getZonesLibelles              () 	                          ;}
	public Cellule            getCellule             (int x, int y) { return this.metierPlateau.getCellule       (x,y)                            ;}
	public Color              getCouleurProchaineZone( int indice ) { return this.metierPlateau.getCouleurProchaineZone( EZone.values()[indice] ) ;}
	public int                getEtapeConception     ()             { return this.metierPlateau.getEtapeConception()                              ;}
	public Zone               getZoneCourante        ()             { return this.metierPlateau.getZoneCourante   ()                              ;}
	public int                getNbZonesDistinctes   ()             { return this.metierPlateau.getNbZonesDistinctes()                            ;}
    public int                getNbLiaisons          ()             { return this.metierPlateau.getEnsLiaison().size()                            ;}
	public int[][]            getCheminLiaison       ( int indice ) { return this.metierPlateau.getCheminLiaison( indice )                        ;}
	public Color              getCouleurLiaison      ( int indice )
	{
		metier.Liaison l = this.metierPlateau.getEnsLiaison().get( indice ) ;

		if ( l.getReseau() != null )
			return l.getReseau().getCouleur();

		return Color.BLACK ;
	}
	public boolean            getPrevisu()                          { return this.metierPlateau.getPrevisu() ;}
	

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	public void setIndiceZoneSelectionnee ( int  indice  )                  { this.indiceZone = indice                             ;}
	public void setEtapeConception        ( int  etape   )                  { this.metierPlateau.setEtapeConception(etape)         ;}
	public void setZoneCourante           ( Zone zone    )                  { this.metierPlateau.setZoneCourante   (zone )         ;}
	public void setCelluleBase            ( Cellule cellule, ECouleur base) { this.metierPlateau.setBaseDansCellule(cellule, base) ;}
	public void setPrevisu()                                                { this.metierPlateau.setPrevisu() ; }
	

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	public void lancerCreation(){ this.frameCreation = new FrameCreation( this ) ;}

	public void retourCreation()
	{
		if ( this.frameModification != null ){ this.frameModification.dispose() ;}

		this.lancerCreation() ;
	}

	public void lancerModification(  )
	{
		this.frameCreation.dispose();
		this.frameModification = new FrameModification( this ) ;
	}

	public void creerPlateau( int tailleLongueur , int tailleLargeur , int tailleCellule , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole )
	{
		this.metierPlateau = new Plateau( tailleLongueur, tailleLargeur , tailleCellule, lstCouleur, lstSymbole ) ;
	}

	
	public void chargerPlateau ( File fichier )
	{
		PlateauData        proprietes     ;
		int                tailleLargeur  ;
		int                tailleLongueur ;
		int                tailleCellule  ;
		ArrayList<Integer> lstCouleur     ;
		ArrayList<Integer> lstSymbole     ;


		proprietes = this.metierGestionFichier.lireFichier( fichier ) ;

		if ( proprietes == null ) { return ; }
		
		System.out.println( "Données lues depuis le fichier : \n" + proprietes ) ;

		tailleLargeur  = proprietes.tailleLargeur  ;
		tailleLongueur = proprietes.tailleLongueur ;
		tailleCellule  = proprietes.tailleCellule  ;

		lstCouleur     = proprietes.lstCouleur     ;
		lstSymbole     = proprietes.lstSymbole     ;

		this.creerPlateau( tailleLongueur, tailleLargeur, tailleCellule, lstCouleur, lstSymbole ) ;
		
		if ( proprietes.lstCellules != null ){ this.metierPlateau.chargerDonnees( proprietes.lstCellules ) ;}

		this.lancerModification() ;
	}

	public void enregistrerFichier( File fichier ) { this.metierPlateau.enregistrerFichier( fichier ) ;}


	public boolean toutesLesBasesPlacees()
	{
		int nbBasesRequises = 0 ;

		for ( Integer i : this.getLstCouleur() )
			if ( i == 1 ) { nbBasesRequises++ ; }

		return this.metierPlateau.getEnsBases().size() == nbBasesRequises ;
	}

	public boolean assezDeBatimentsPourBases()
	{
		int nbBasesRequises = 0 ;

		for ( Integer i : this.getLstCouleur() )
			if ( i == 1 ) 
				nbBasesRequises++ ; 

		return this.metierPlateau.getNbBatiments() >= nbBasesRequises ;
	}


	public Zone clicSurCase(int x, int y, Zone zoneCourante)
	{
		Cellule cell            = this.getCellule(x, y)           ;
		Zone    zoneCell        = cell.getZone()                  ;
		EZone   typeSelectionne = EZone.values()[this.indiceZone] ;

		if (zoneCell != null)
			return zoneCell;

		if (zoneCourante == null || zoneCourante.getTypeZone() != typeSelectionne)
		{
			ArrayList<Zone> ensZoneMemeType = new ArrayList<>();

			for (int cptX = 0; cptX < this.getTailleLongueur(); cptX++)
			{
				for (int cptY = 0; cptY < this.getTailleLargeur(); cptY++)
				{
					Cellule tmp = this.getCellule(cptX, cptY);

					if (tmp != null && tmp.getZone() != null           &&
						tmp.getZone().getTypeZone() == typeSelectionne &&
						!ensZoneMemeType.contains(tmp.getZone())          )
					{
						ensZoneMemeType.add(tmp.getZone());
					}
				}
			}

			int occurrence = ensZoneMemeType.size();

			Zone nvZone = new Zone(typeSelectionne);
			nvZone.setCouleur(typeSelectionne, occurrence);

			this.metierPlateau.setZoneDansCellule(cell, nvZone);

			return nvZone;
		}

		boolean estAdjacent = false;

		for (int dx = -1; dx <= 1; dx++)
		{
			for (int dy = -1; dy <= 1; dy++)
			{
				if (Math.abs(dx) + Math.abs(dy) == 1)
				{
					Cellule voisin = this.getCellule(x + dx, y + dy);

					if (voisin != null && voisin.getZone() == zoneCourante)
						estAdjacent = true;
				}
			}
		}

		if (estAdjacent)
		{
			this.metierPlateau.setZoneDansCellule(cell, zoneCourante);
		}

		return zoneCourante;
	}

	public void afficherLiasons()
	{
		this.metierPlateau.relierToutLesSymbole(ECouleur.LIAISON);
	}

	public void clicSurCaseSymbole(int x, int y, ESymbole esymbole) 
	{
		Cellule cell = this.getCellule(x, y);
		
		if (cell != null) 
		{
			metier.Symbole nouveauSymbole = new metier.Symbole(esymbole);
			this.metierPlateau.setSymboleDansCellule(cell, nouveauSymbole);
		}
	}
	
	public void clicSurCaseBase(int x, int y, ECouleur ecouleur) 
	{
		Cellule cell = this.getCellule(x, y);
		
		if (cell != null) 
		{
			this.metierPlateau.setBaseDansCellule(cell, ecouleur);
		}
	}

	public void reinitialiserCellule(int x, int y) { this.metierPlateau.supprimerZone (x , y)                     ;}
	public void retirerSymbole      (int x, int y) { this.metierPlateau.retirerSymbole(x , y)                     ;}
	public void renitialiserLiaison ()             { this.metierPlateau.setEnsLiaison( new ArrayList<Liaison>() ) ;}
	
	public void retirerBase         (int x, int y)
	{
		Cellule cell = this.getCellule( x, y ) ;
		
		if ( cell != null ) { this.metierPlateau.retirerBaseDansCellule( cell ) ;}
	}

	/*----------------------------*/
	/*   Main                     */
	/*----------------------------*/

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		
		ctrl.lancerCreation() ;
	}

}