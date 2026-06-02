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
import java.awt.event.*;

/* SAE 2.01 | Développement d'une application 
* PanelConception
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelConception extends JPanel implements ItemListener
{

	private JPanel panelGestion ;
	private JPanel panelCreation ;

	private JPanel[] tabPanelCreation ;

	private JButton btnNouveau ; 
	private JButton btnAncien ; 
	private JButton btnCopie ;

	private JTextField  txtTailleLongueur ; 
	private JTextField  txtTailleLargeur ; 
	private JLabel      lblNbCouleur ; 
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

		this.btnNouveau.setPreferredSize( new Dimension(350 , 75 ) );
		this.btnAncien .setPreferredSize( new Dimension(350 , 75 ) );
		this.btnCopie  .setPreferredSize( new Dimension(350 , 75 ) );

		this.btnNouveau.setBackground( Color.BLACK );
		this.btnAncien .setBackground( Color.BLACK );
		this.btnCopie  .setBackground( Color.BLACK );
 
		this.btnNouveau.setForeground( Color.WHITE );
		this.btnAncien .setForeground( Color.WHITE );
		this.btnCopie  .setForeground( Color.WHITE );



		this.panelGestion = new JPanel( new FlowLayout(FlowLayout.CENTER, 30, 5) );
		this.txtTailleLongueur = new JTextField(4) ;
		this.txtTailleLongueur.setText("50");
		this.txtTailleLargeur  = new JTextField(4) ;
		this.txtTailleLongueur.setText("50");





		this.lblNbCouleur = new JLabel("0" );
		this.tabCbCouleur = new JCheckBox[ECouleur.values().length] ;
		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			this.tabCbCouleur[cpt] = new JCheckBox( ECouleur.values()[cpt].getLibelle() ) ;

		this.lblNbSymbole = new JLabel("0");
		this.tabCbSymbole = new JCheckBox[ESymbole.values().length] ;
		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ ) 
			this.tabCbSymbole[cpt] = new JCheckBox( ESymbole.values()[cpt].getLibelle() ) ;
		

		this.txtTailleCases = new JTextField("50");
	
		
		this.tabPanelCreation = new JPanel[4]  ;
		for ( int cpt = 0 ; cpt < this.tabPanelCreation.length ; cpt++ ) 
			this.tabPanelCreation[cpt] = new JPanel( new FlowLayout(FlowLayout.LEFT) );

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

		this.tabPanelCreation[1].add( new JLabel("Réseau    sélectionné [ ") );
		this.tabPanelCreation[1].add ( this.lblNbCouleur );
		this.tabPanelCreation[1].add( new JLabel(" ] :") );
		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			this.tabPanelCreation[1].add(this.tabCbCouleur[cpt] );

		this.tabPanelCreation[2].add( new JLabel("Bâtiment sélectionné [ ") );
		this.tabPanelCreation[2].add ( this.lblNbSymbole );
		this.tabPanelCreation[2].add( new JLabel(" ] :") );
		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ )
			this.tabPanelCreation[2].add(this.tabCbSymbole[cpt] );

		this.tabPanelCreation[3].add( new JLabel( "Taille des cases : " ) );
		this.tabPanelCreation[3].add( this.txtTailleCases );




		this.panelCreation.add( this.tabPanelCreation[0] );
		this.panelCreation.add( this.tabPanelCreation[1] );
		this.panelCreation.add( this.tabPanelCreation[2] );
		this.panelCreation.add( this.tabPanelCreation[3] );


		this.add( this.panelGestion  , BorderLayout.NORTH  ) ;
		this.add( this.panelCreation , BorderLayout.CENTER ) ;

		
	

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			this.tabCbCouleur[cpt].addItemListener(this);

		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ )
			this.tabCbSymbole[cpt].addItemListener(this);
	}


	public void itemStateChanged(ItemEvent e) 
	{
		int nbCouleur = 0 ; 
		int nbSymbole = 0 ;

		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			if ( this.tabCbCouleur[cpt].isSelected() )
				this.lblNbCouleur.setText( "" + ( ++ nbCouleur ) );
			

		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ ) 
			if ( this.tabCbSymbole[cpt].isSelected() ) 
				this.lblNbSymbole.setText( "" + ( ++ nbSymbole ) );
			
	
	}
}

