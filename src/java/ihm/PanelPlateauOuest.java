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

	private JComboBox<String> jcbZone;

	private ButtonGroup  btgRadio ;
	private JRadioButton cbSelecion ;
	private JRadioButton cbGomme ;
	

	public PanelPlateauOuest( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;
		this.setLayout( new GridLayout( 2 , 1 ));

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.setBackground( Color.red );


		this.btgRadio = new ButtonGroup();
		this.cbSelecion = new JRadioButton("Selecion", true);
		this.cbGomme = new JRadioButton("cbGomme");


		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add ( new JLabel( "test") ) ;



		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

	}

	
	

}



















