package ihm;

import controleur.Controleur;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;




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

	private PanelPlateau panelPlateau ;
	private JPanel panelNord ;
	private JPanel panelSud ;
	private PanelPlateauOuest panelEst ;
	private JPanel panelOuest ; 



	public PanelModification( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

        this.setLayout( new BorderLayout() );
		
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelPlateau = new PanelPlateau( this.ctrl );

		this.panelNord  = new JPanel() ;
		this.panelSud   = new JPanel() ;
		this.panelEst   = new PanelPlateauOuest( this.ctrl ) ;
		this.panelOuest = new JPanel() ;
        
		this.panelNord .setBackground( Color.DARK_GRAY );
		this.panelSud  .setBackground( Color.DARK_GRAY );
		this.panelOuest.setBackground( Color.DARK_GRAY );

		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( this.panelPlateau , BorderLayout.CENTER );	

		this.add( this.panelNord  , BorderLayout.NORTH );
		this.add( this.panelSud   , BorderLayout.SOUTH );
		this.add( this.panelEst   , BorderLayout.EAST  );
		this.add( this.panelOuest , BorderLayout.WEST  );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

        
	}



}

