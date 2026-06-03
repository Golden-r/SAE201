package controleur;

import java.awt.Frame;

import java.util.ArrayList;


import ihm.FrameCreation;
import ihm.FrameModification;
import ihm.FrameJeu;

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
	private FrameJeu          frameJeu ;

	
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
	public int                getTailleLongeur()  { return this.metierPlateau.getTailleLongueur() ;}
	public int                getTailleCase   ()  { return this.metierPlateau.getTailleCase()     ;}
	public ArrayList<Integer> getLstCouleur   ()  { return this.metierPlateau.getLstCouleur()     ;}
	public ArrayList<Integer> getLstSymbole   ()  { return this.metierPlateau.getLstCouleur()     ;}

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/
	
	public void setTailleLargeur ( int tailleLargeur  )            { this.metierPlateau.setTailleLargeur ( tailleLargeur  ) ;}
	public void setTailleLongueur( int tailleLongueur )            { this.metierPlateau.setTailleLongueur( tailleLongueur ) ;}
	public void setTailleCase    ( int tailleCases )               { this.metierPlateau.setTailleCase ( tailleCases)        ;}
	public void setLstCouleur    ( ArrayList<Integer> lstCouleur ) { this.metierPlateau.setLstCouleur( lstCouleur )         ;}
	public void setLstSymbole    ( ArrayList<Integer> lstSymbole ) { this.metierPlateau.setLstSymbole( lstSymbole )         ;}


	

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

	public void lancerJeu()
	{
		System.out.println("Lancement du jeu");
		this.frameJeu = new FrameJeu( this );
	}


	public void creePlateau( int tailleLargeur , int tailleLongueur , int tailleCases , ArrayList<Integer> lstCouleur , ArrayList<Integer> lstSymbole )
	{
		//Plateau meti
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