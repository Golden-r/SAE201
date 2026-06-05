package controleur;

import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;


import ihm.FrameCreation;
import ihm.FrameModification;
import ihm.PanelPlateauOuest;
import metier.Cellule;
import metier.EZone;
import metier.GestionFichier;
import metier.Plateau;
import metier.Zone;
import metier.PlateauData;

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
	//private PanelPlateauOuest panelPlateauOuest ;
	
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

	public int                getTailleLargeur()              { return this.metierPlateau.getTailleLargeur () ;}
	public int                getTailleLongueur()             { return this.metierPlateau.getTailleLongueur() ;}
	public int                getTailleCellule()              { return this.metierPlateau.getTailleCellule () ;}
	public ArrayList<Integer> getLstCouleur   ()              { return this.metierPlateau.getLstCouleur    () ;}
	public ArrayList<Integer> getLstSymbole   ()              { return this.metierPlateau.getLstSymbole    () ;}
	public ArrayList<Zone   > getLibelleZone  ()              { return this.metierPlateau.getEnsZones      () ;}
	public String[]           getZones        ()              { return EZone.getZonesLibelles              () ;}
	public Cellule            getCellule      (int x, int y)  { return this.metierPlateau.getCellule(x,y);     }

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	public void setIndiceZoneSelectionnee ( int indice ) { this.indiceZone = indice ;}
	



	

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	public void lancerCreation()
	{
		System.out.println("Lancement du mode creation");
		this.frameCreation = new FrameCreation( this ); 
	}

	public void lancerModification(  )
	{
		System.out.println("Lancement du mode modification");
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

/* 
	public Zone clicSurCase(int x, int y, Zone zone) 
	{
		Cellule cell = this.getCellule(x, y);

		if (cell.getZone() != null) { return cell.getZone() ;}

		if (zone == null)
		{
			/*
			zone = new Zone( ); //zone.getTypeZone() 
			zone.setCouleur(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));//EZone.values()[5].getCouleur());
			//zone.setCouleur( zone.getTypeZone() , zone.getId() , )
			
			System.out.println( this.indiceZone );
			EZone typeSelectionne = EZone.values()[ this.indiceZone ];
			Zone nvZone = new Zone(typeSelectionne); //typeSelectionne
			nvZone.setCouleur(typeSelectionne, 0, 0);
			zone = nvZone;
			
 
		}
		
		this.metierPlateau.setZoneDansCellule(cell, zone);

		return zone;
	}
*/
	// TODO
	// code IA
	public Zone clicSurCase(int x, int y, Zone zone) 
	{
		Cellule cell = this.getCellule(x, y);

		if (cell.getZone() != null) 
			return cell.getZone();

		if (zone == null)
		{
			//System.out.println(this.indiceZone);
			EZone typeSelectionne = EZone.values()[this.indiceZone];
			Zone  nvZone          = new Zone(typeSelectionne);
			
			int occurrence = 0;
			for (int cptX = 0; cptX < this.getTailleLongueur(); cptX++)
				for (int cptY = 0; cptY < this.getTailleLargeur(); cptY++)
					if (this.getCellule(cptX, cptY) != null && this.getCellule(cptX, cptY).getZone() != null && this.getCellule(cptX, cptY).getZone().getTypeZone() == typeSelectionne)
						occurrence++;

			nvZone.setCouleur(typeSelectionne, 0, occurrence);
			zone = nvZone;
		}
		
		this.metierPlateau.setZoneDansCellule(cell, zone);

		return zone;
	}

	public void reinitialiserCellule(int x, int y) { this.metierPlateau.supprimerZone(x , y) ;}

	/*----------------------------*/
	/*   Main                     */
	/*----------------------------*/

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		
		ctrl.lancerCreation() ;
	}

}