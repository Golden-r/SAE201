package controleur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;

import ihm.FrameJeu;
import ihm.FrameMenu;
import ihm.ManageurFont;

import metier.*;

/* SAE 2.01 | Développement d'une application 
* Controleur
*
* Date     : 10/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Controleur
{
	final static int WIDTH_MENU  = 1000;
	final static int HEIGHT_MENU =  500;
	
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

	private FrameJeu     frameJeu ;
	private FrameMenu    frameMenu;

	private Partie         partie   ;
	private GestionFichier metierGestionFichier;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Controleur()
	{

	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	public String[]   getNomJeu          () { return EModes.getNomModes        ()           ; }
	public String[]   getDescriptionModes() { return EModes.getDescriptionModes()           ; }

	public Dimension  getSizeMenu        () { return new Dimension(WIDTH_MENU, HEIGHT_MENU) ; }

	public Partie     getPartie          () { return this.partie                            ; }

	public Font       retourneFont       (String nom, float size) { return ManageurFont.getFont(nom, size); }

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	
	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
	public void lancerMenu()
	{
		this.frameMenu = new FrameMenu(this);
	}

	public void lancerPartie(File fichier, int nbJoueur, EModes mode, boolean modeDebug)
	{
		PlateauData proprietes ;
		Plateau     plateau    ;

		if(fichier == null || !fichier.exists())
			return;
		
		proprietes = this.metierGestionFichier.lireFichier(fichier) ;

		if(proprietes == null)
			return;
		
		plateau = new Plateau();
	}

	

	/*----------------------------*/
	/*   Main                     */
	/*----------------------------*/

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		
		ctrl.lancerMenu() ;
	}

}