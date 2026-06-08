
package ihm;

import controleur.Controleur;


import javax.swing.* ;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;




public class PanelPlateauSud extends JPanel 
{
	private Controleur ctrl;

	private JLabel lblMessage ;


	public PanelPlateauSud( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;


		this.setBorder( BorderFactory.createEmptyBorder( 40 , 40,  40, 40));
		this.setBackground( Color.LIGHT_GRAY );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.lblMessage = new JLabel("Veuillez sélectionner les zones et passer à l'étape suivante.") ;

		

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( this.lblMessage ) ;


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
	}

	public void afficherMsgZone()
	{
		this.lblMessage.setText( "Veuillez sélectionner les zones et passer à l'étape suivante." ); 
	}

	public void afficherMsgSymbole()
	{
		this.lblMessage.setText( "Veuillez sélectionner les symboles et passer à l'étape suivante." );
	}

	public void afficherMsgBase()
	{
		this.lblMessage.setText( "Veuillez sélectionner les bases de départ (marqueurs bases) et enregistrer." ); 
	}


}
