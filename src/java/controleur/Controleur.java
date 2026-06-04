package controleur;

import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;


import ihm.FrameCreation;
import ihm.FrameModification;
import metier.GestionFichier;
import metier.Plateau;

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

	public int                getTailleLargeur()  { return this.metierPlateau.getTailleLargeur()  ;}
	public int                getTailleLongueur() { return this.metierPlateau.getTailleLongueur() ;}
	public int                getTailleCellule()  { return this.metierPlateau.getTailleCellule()  ;}
	public ArrayList<Integer> getLstCouleur   ()  { return this.metierPlateau.getLstCouleur()     ;}
	public ArrayList<Integer> getLstSymbole   ()  { return this.metierPlateau.getLstSymbole()     ;}

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

 

	public void creePlateau( int tailleLargeur , int tailleLongueur , int tailleCellule , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole )
	{
		metierPlateau = new Plateau( tailleLargeur, tailleLongueur, tailleCellule, lstCouleur, lstSymbole ) ;
	}

	public void chargerPlateau ( File fichier )
	{
		ArrayList<Object> proprietes = this.metierGestionFichier.lireFichier( fichier ) ;

		// TODO : faire une classe ou on stocke les propriétés du plateau pour éviter les erreurs de cast et les warnings
		// EX   : PlateauConfiguration config = this.metierGestionFichier.lireFichier( fichier ) ;
		int tailleLargeur  = (Integer) proprietes.get(0);
		int tailleLongueur = (Integer) proprietes.get(1);
		int tailleCellule  = (Integer) proprietes.get(2);

		//on a des erreurs de cast, on est obligé pour cacher le warning
		@SuppressWarnings("unchecked")
		ArrayList<Integer> lstCouleur  = (ArrayList<Integer>) proprietes.get(3);
		@SuppressWarnings("unchecked")
		ArrayList<Integer> lstSymbole  = (ArrayList<Integer>) proprietes.get(4);

		this.creePlateau(tailleLargeur, tailleLongueur, tailleCellule, lstCouleur, lstSymbole);
		this.lancerModification();
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