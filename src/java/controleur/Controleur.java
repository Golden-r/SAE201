package controleur;

import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;


import ihm.FrameCreation;
import ihm.FrameModification;
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

	
	private Plateau           metierPlateau;
	private GestionFichier    metierGestionFichier;


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


	public void clicSurCase(int x, int y) 
	{
		Cellule cell = this.getCellule(x, y);


		Zone nvZone = new Zone();
		nvZone.setCouleur(EZone.values()[5].getCouleur());
		
		this.metierPlateau.setZoneDansCellule(cell, nvZone);
		
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