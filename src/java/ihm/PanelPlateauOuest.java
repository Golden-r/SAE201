package ihm;

import controleur.Controleur;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* PanelPlateauOuest
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelPlateauOuest extends JPanel
{
	private Controleur ctrl;

	public PanelPlateauOuest( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;
		
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.setBackground( Color.red );
		this.add ( new JLabel( "test") ) ;

		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

	}

	
	

}



















