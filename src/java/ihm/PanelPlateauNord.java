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

	private JButton btnRetour  ;
	private JButton btnSuivant ;
	
	public PanelPlateauNord( Controleur ctrl , PanelModification panelModif ) 
	{
		this.ctrl = ctrl ;

		this.panelModification = panelModif;

		this.setLayout( new GridLayout( 1 , 4 , 20 , 20 ));
		this.setBorder( BorderFactory.createEmptyBorder( 20 , 35,  20, 35));
		this.setBackground( Color.LIGHT_GRAY );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.btnRetour  = new JButton("Retour") ;
		this.btnSuivant = new JButton("Suivant") ;

		this.btnSuivant.setEnabled(true );

		

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( this.btnRetour ) ;
		this.add( new JLabel("ETAPE : Selection zone") );
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
		if ( e.getSource() == this.btnRetour ){ System.out.println("btn retour") ;}
		if ( e.getSource() == this.btnSuivant)
		{ 
			System.out.println("btn suivant");
			this.panelModification.passerEtapeSymbole();
		
		}
	}
}
