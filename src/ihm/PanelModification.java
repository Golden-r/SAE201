package ihm;

import metier.ESymbole;
import metier.ECouleur;
import metier.Plateau;
import controleur.Controleur;


import metier.ECouleur;
import metier.Zone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JButton;


/* SAE 2.01 | Développement d'une application 
* PanelModification
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelModification extends JPanel 
{
	private Controleur ctrl ;
	private JPanel     panelPlateau ;

	public PanelModification( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

        this.setLayout( new BorderLayout() );
		
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelPlateau = new JPanel( new GridLayout ( 5 , 5 )) ;
        
		this.setBackground( Color.BLACK );

		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

			
        

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

        
	}



}

