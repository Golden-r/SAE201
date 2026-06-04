package controleur;

import java.awt.Frame;

import java.util.ArrayList;


import ihm.FrameCreation;
import ihm.FrameModification;

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
	public int                getTailleCellule   ()  { return this.metierPlateau.getTailleCellule()     ;}
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
		this.frameModification = new FrameModification( this ) ;
	}

 

	public void creePlateau( int tailleLargeur , int tailleLongueur , int tailleCellule , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole )
	{
		metierPlateau = new Plateau(tailleLargeur, tailleLongueur, tailleCellule, lstCouleur, lstSymbole) ;
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