
package ihm;

import controleur.Controleur;
import metier.ESymbole;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;

/* SAE 2.01 | Développement d'une application 
* PanelPlateauOuestBase
*
* Date     : 06/06/2026
* Groupe   : 4
*/

public class PanelPlateauOuestBase extends JPanel  
{
	Controleur ctrl ;


	public PanelPlateauOuestBase( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

		this.setLayout( new GridLayout( 2 , 1 ));
		this.setBackground( Color.LIGHT_GRAY );
		this.setBorder( BorderFactory.createEmptyBorder( 10 , 10,  10, 10));


		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

	}

	


}
