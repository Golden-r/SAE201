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


	public Zone clicSurCase(int x, int y, Zone zone) 
	{
		Cellule cell = this.getCellule(x, y);

		if (cell.getZone() != null) 
			return cell.getZone();

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
						if(!ensZoneMemeType.contains(tmpCell.getZone()))
							ensZoneMemeType.add(tmpCell.getZone());
				}

			int occurrence = ensZoneMemeType.size();

			nvZone.setCouleur(typeSelectionne, 0, occurrence);
			zone = nvZone;
		}
		this.metierPlateau.setZoneDansCellule(cell, zone);

		return zone;
	}

	// TODO
	// code IA (la zone s'étend a 1 cellule de distance sinon créé une nouvelle zone)
/* 
	public Zone clicSurCase(int x, int y, Zone zone) 
	{
		Cellule cell = this.getCellule(x, y);

		if (cell.getZone() != null) 
			return cell.getZone();

		EZone typeSelectionne = EZone.values()[this.indiceZone];
		boolean creerNouvelleZone = false;

		// Si le pinceau est vide ou qu'on a changé de type dans la liste
		if (zone == null || zone.getTypeZone() != typeSelectionne)
		{
			creerNouvelleZone = true;
		}
		else
		{
			// Vérification de l'adjacence dans les 4 directions
			boolean estAdjacent = false;
			
			if (x > 0 && this.getCellule(x - 1, y) != null && this.getCellule(x - 1, y).getZone() == zone)
				estAdjacent = true;
				
			if (x < this.getTailleLongueur() - 1 && this.getCellule(x + 1, y) != null && this.getCellule(x + 1, y).getZone() == zone)
				estAdjacent = true;
				
			if (y > 0 && this.getCellule(x, y - 1) != null && this.getCellule(x, y - 1).getZone() == zone)
				estAdjacent = true;
				
			if (y < this.getTailleLargeur() - 1 && this.getCellule(x, y + 1) != null && this.getCellule(x, y + 1).getZone() == zone)
				estAdjacent = true;

			// Si on clique loin de la zone actuelle, on force un nouveau tracé
			if (!estAdjacent)
				creerNouvelleZone = true;
		}

		if (creerNouvelleZone)
		{
			Zone nvZone = new Zone(typeSelectionne);
			
			Zone[] zonesDuMemeType = new Zone[this.getTailleLongueur() * this.getTailleLargeur()];
			int nbZonesUniques = 0;

			for (int cptX = 0; cptX < this.getTailleLongueur(); cptX++)
			{
				for (int cptY = 0; cptY < this.getTailleLargeur(); cptY++)
				{
					Cellule c = this.getCellule(cptX, cptY);
					
					if (c != null && c.getZone() != null && c.getZone().getTypeZone() == typeSelectionne)
					{
						boolean existeDeja = false;
						for (int i = 0; i < nbZonesUniques; i++)
							if (zonesDuMemeType[i] == c.getZone())
								existeDeja = true;
								
						if (!existeDeja)
						{
							zonesDuMemeType[nbZonesUniques] = c.getZone();
							nbZonesUniques++;
						}
					}
				}
			}

			nvZone.setCouleur(typeSelectionne, 0, nbZonesUniques);
			zone = nvZone;
		}
		
		this.metierPlateau.setZoneDansCellule(cell, zone);

		return zone;
	}
*/

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