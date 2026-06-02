package ihm;

import metier.ESymbole;
import metier.ECouleur;
import metier.Plateau;


import metier.ECouleur;
import metier.ESymbole;
import metier.Zone;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* PanelConception
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelConception extends JPanel 
{

	private JPanel panelGestion ;
	private JPanel panelCreation ;

	private JPanel[] tabPanelCreation ;

	private JButton btnNouveau ; 
	private JButton btnAncien ; 
	private JButton btnCopie ;

	private JTextField txtTailleLongueur ; 
	private JTextField txtTailleLargeur  ; 
	private JLabel     lblNbCouleur      ; 
	private JCheckBox[] tabCbCouleur ;
	private JLabel      lblNbSymbole ;
	private JCheckBox[] tabCbSymbole ;
	private JTextField  txtTailleCases ;

	public PanelConception() 
	{

		this.setLayout( new BorderLayout() );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelCreation = new JPanel( new GridLayout( 4 , 1 ) );

		this.btnNouveau = new JButton("Nouveau") ;
		this.btnAncien  = new JButton("Ancien" ) ;
		this.btnCopie   = new JButton("Copie"  ) ;



		this.panelGestion  = new JPanel( new GridLayout( 1 , 3 ) );
		this.txtTailleLongueur = new JTextField("10") ;
		this.txtTailleLargeur  = new JTextField("10") ;





		this.lblNbCouleur = new JLabel("0");
		this.tabCbCouleur = new JCheckBox[ECouleur.values().length] ;
		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			this.tabCbCouleur[cpt] = new JCheckBox( ECouleur.values()[cpt].getLibelle() ) ;

		this.lblNbSymbole = new JLabel("0");
		this.tabCbSymbole = new JCheckBox[ESymbole.values().length] ;
		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ ) 
			this.tabCbSymbole[cpt] = new JCheckBox( ESymbole.values()[cpt].getLibelle() ) ;
		

		
		
		this.tabPanelCreation = new JPanel[4]  ;
		this.tabPanelCreation[0] = new JPanel();
		this.tabPanelCreation[1] = new JPanel();
		this.tabPanelCreation[2] = new JPanel();
		this.tabPanelCreation[3] = new JPanel();


		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		this.panelGestion.add( this.btnNouveau ) ;
		this.panelGestion.add( this.btnAncien ) ;
		this.panelGestion.add( this.btnCopie ) ;



		this.tabPanelCreation[0].add( new JLabel("Taille : " ) );
		this.tabPanelCreation[0].add( this.txtTailleLargeur );
		this.tabPanelCreation[0].add( new JLabel(" X " )       );
		this.tabPanelCreation[0].add( this.txtTailleLongueur );

		this.tabPanelCreation[1].add( this.txtTailleLargeur );





		this.panelCreation.add( this.tabPanelCreation[0] );
		this.panelCreation.add( this.tabPanelCreation[1] );
		this.panelCreation.add( this.tabPanelCreation[2] );
		this.panelCreation.add( this.tabPanelCreation[3] );


		this.add( this.panelGestion  , BorderLayout.NORTH  ) ;
		this.add( this.panelCreation , BorderLayout.CENTER ) ;

		
		

		



		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
	}
}

