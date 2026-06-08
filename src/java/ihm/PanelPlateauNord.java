package ihm;

import controleur.Controleur;


import javax.swing.* ;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;




public class PanelPlateauNord extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private PanelModification panelModification;
	private PanelPlateauSud   panelSud ;

	private JButton btnRetour  ;
	private JButton btnSuivant ;

	private JLabel lblNbZone ;
	
	public PanelPlateauNord( Controleur ctrl , PanelModification panelModif , PanelPlateauSud panelSud ) 
	{
		this.ctrl = ctrl ;

		this.panelModification = panelModif ;
		this.panelSud          = panelSud ;

		this.setLayout( new BorderLayout( 20 , 20 ));
		this.setBorder( BorderFactory.createEmptyBorder( 20 , 35,  20, 35));
		this.setBackground( Color.LIGHT_GRAY );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.btnRetour  = new JButton("Retour") ;
		this.btnSuivant = new JButton("Suivant") ;

		this.lblNbZone = new JLabel( "Nombre de zone total : ") ;


		

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( this.btnRetour  , BorderLayout.WEST   );
		this.add( this.lblNbZone  , BorderLayout.CENTER );
		this.add( this.btnSuivant , BorderLayout.EAST   );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		this.btnRetour.addActionListener(this);
		this.btnSuivant.addActionListener(this);

	}

	public void setNbZone( int nbzone ) { this.lblNbZone.setText( "Nombre de zone total : " + nbzone ) ;}

	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnRetour )
		{
			switch ( this.ctrl.getEtapeConception() ) 
			{
				case 1:
					this.ctrl.retourCreation() ;
					break;
			
				case 2:
					this.panelModification.passerEtapeZone();
					this.panelSud.afficherMsgZone() ;
					break;

				case 3:
					this.panelModification.passerEtapeSymbole();
					this.panelSud.afficherMsgSymbole();
					this.ctrl.renitialiserLiaison() ;
					break; 

				default:
					break;
			}

			
		}

		
		else if ( e.getSource() == this.btnSuivant)
		{

			switch ( this.ctrl.getEtapeConception() ) 
			{
				case 1:
					this.panelModification.passerEtapeSymbole();
					this.panelSud.afficherMsgSymbole();
					break;
			
				case 2:
					this.panelModification.passerEtapeBase();
					this.panelSud.afficherMsgBase();
					break;

				case 3:
					//Ouvrir menu enregistrer sous.
					break; 

				default:
					break;
			}


			
		
		}
	}
}
