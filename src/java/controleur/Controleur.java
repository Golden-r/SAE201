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

// TODO : changer les noms des IHM's

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

	

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	public void setIndiceZoneSelectionnee ( int  indice  )                  { this.indiceZone = indice                             ;}
	public void setEtapeConception        ( int  etape   )                  { this.metierPlateau.setEtapeConception(etape)         ;}
	public void setZoneCourante           ( Zone zone    )                  { this.metierPlateau.setZoneCourante   (zone )         ;}
	public void setCelluleBase            ( Cellule cellule, ECouleur base) { this.metierPlateau.setBaseDansCellule(cellule, base) ;}

	

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
		PlateauData proprietes = this.metierGestionFichier.lireFichier( fichier ) ;

		System.out.println("Données lues depuis le fichier : \n" + proprietes);

		int tailleLargeur  = proprietes.tailleLargeur;
		int tailleLongueur = proprietes.tailleLongueur;
		int tailleCellule  = proprietes.tailleCellule;

		ArrayList<Integer> lstCouleur  = proprietes.lstCouleur;
		ArrayList<Integer> lstSymbole  = proprietes.lstSymbole;

		this.creerPlateau( tailleLongueur , tailleLargeur, tailleCellule, lstCouleur, lstSymbole);
		this.lancerModification();
	}


	public Zone clicSurCase(int x, int y, Zone zone) 
	{
		Cellule cell = this.getCellule(x, y);

		if (cell.getZone() != null) { return cell.getZone() ;}

		EZone typeSelectionne = EZone.values()[this.indiceZone];

		if (zone == null || zone.getTypeZone() != typeSelectionne)
		{
			ArrayList<Zone> ensZoneMemeType = new ArrayList<>();
			Zone nvZone                     = new Zone(typeSelectionne);
			
			for (int cptX = 0; cptX < this.getTailleLongueur(); cptX++)
				for (int cptY = 0; cptY < this.getTailleLargeur(); cptY++)
				{
					Cellule tmpCell = this.getCellule(cptX, cptY);
					
					if (tmpCell != null && tmpCell.getZone() != null && tmpCell.getZone().getTypeZone() == typeSelectionne)
						if( !ensZoneMemeType.contains( tmpCell.getZone() ) ) { ensZoneMemeType.add( tmpCell.getZone() ) ;}
				}

			int occurrence = ensZoneMemeType.size();

			nvZone.setCouleur(typeSelectionne, 0, occurrence);
			zone = nvZone;

			this.metierPlateau.setZoneDansCellule(cell, zone);
		}
		else
		{
			boolean estAdjacent = false;

			for(int cpt1 = -1; cpt1 <= 1; cpt1++)
				for(int cpt2 = -1; cpt2 <= 1; cpt2++)
					if( Math.abs(cpt1) + Math.abs(cpt2) == 1 && this.getCellule(x + cpt1, y + cpt2) != null && this.getCellule(x + cpt1, y + cpt2).getZone() == zone) { estAdjacent = true ;}
			
			if (estAdjacent) { this.metierPlateau.setZoneDansCellule(cell,zone) ;}
		}
		
		return zone;
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
	
	/*----------------------------*/
	/*   Main                     */
	/*----------------------------*/

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		
		ctrl.lancerCreation() ;
	}

}