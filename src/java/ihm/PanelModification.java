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

	private PanelPlateau     panelPlateau ;
	private PanelPlateauNord panelNord ;
	private PanelPlateauOuest panelOuest ; 
	private JPanel panelSud ;
	private JPanel panelEst ;
	

	public PanelModification( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

        this.setLayout( new BorderLayout() );
		
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelPlateau = new PanelPlateau( this.ctrl, this );

		this.panelNord  = new PanelPlateauNord( this.ctrl ) ;
		this.panelSud   = new JPanel() ;
		this.panelEst   = new JPanel() ;
		this.panelOuest = new PanelPlateauOuest( this.ctrl ) ;
        
		this.panelNord .setBackground( Color.LIGHT_GRAY );
		this.panelSud  .setBackground( Color.LIGHT_GRAY  );
		this.panelEst  .setBackground( Color.LIGHT_GRAY  );

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

	public boolean getModeSelection()
	{
		return this.panelOuest.getModeSelection();
	}

	public int getZoneSelectioner()
	{
		return this.panelOuest.getZoneSelectioner();
	}

}

