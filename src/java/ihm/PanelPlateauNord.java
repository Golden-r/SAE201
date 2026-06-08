package ihm;

import controleur.Controleur;


import javax.swing.* ;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;




public class PanelPlateauNord extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private PanelModification panelModification;
	private PanelPlateauSud   panelSud ;

	private JButton btnRetour  ;
	private JButton btnSuivant ;
	
	public PanelPlateauNord( Controleur ctrl , PanelModification panelModif , PanelPlateauSud panelSud ) 
	{
		this.ctrl = ctrl ;

		this.panelModification = panelModif ;
		this.panelSud          = panelSud ;

		this.setLayout( new GridLayout( 1 , 4 , 20 , 20 ));
		this.setBorder( BorderFactory.createEmptyBorder( 20 , 35,  20, 35));
		this.setBackground( Color.LIGHT_GRAY );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.btnRetour  = new JButton("Retour") ;
		this.btnSuivant = new JButton("Suivant") ;


		

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( this.btnRetour ) ;
		this.add( new JLabel("") );
		this.add( this.btnSuivant ) ;
		this.add( new JLabel("") );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		this.btnRetour.addActionListener(this);
		this.btnSuivant.addActionListener(this);

	}

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
