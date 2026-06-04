package ihm;

import controleur.Controleur;
import java.util.ArrayList;
import java.util.Collections;

import metier.ESymbole;
import metier.ECouleur;


import javax.swing.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/* SAE 2.01 | Développement d'une application 
* PanelCreation
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelCreation extends JPanel implements ActionListener , ItemListener
{
	private Controleur ctrl;

	private JPanel panelGestion ;
	private JPanel panelCreation;

	private JPanel[] tabPanelCreation ;

	private JButton btnNouveau ; 
	private JButton btnAncien ; 
	private JButton btnCopie ;

	private JButton btnValider;
	private JButton btnReinitialiser ;

	private JTextField  txtTailleLongueur ; 
	private JTextField  txtTailleLargeur ; 
	private JLabel      lblNbCouleur ; 
	private JCheckBox[] tabCbCouleur ;
	private JLabel      lblNbSymbole ;
	private JCheckBox[] tabCbSymbole ;
	private JTextField  txtTailleCases ;


	public PanelCreation( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;
		this.setLayout( new BorderLayout() );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelCreation = new JPanel( new GridLayout( 5 , 1 ) );
		this.panelCreation.setVisible(false);



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


		this.btnValider       = new JButton("Valider"      );
		this.btnReinitialiser = new JButton("Réinitialiser");
		this.btnValider      .setBackground( Color.GREEN  );
		this.btnReinitialiser.setBackground( Color.YELLOW );




		this.panelGestion = new JPanel( new FlowLayout(FlowLayout.CENTER, 30, 5) );
		this.txtTailleLongueur = new JTextField("10" , 4) ;
		this.txtTailleLargeur  = new JTextField("10" , 4) ;


		this.lblNbCouleur = new JLabel("0" );
		this.tabCbCouleur = new JCheckBox[ECouleur.values().length] ;
		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			this.tabCbCouleur[cpt] = new JCheckBox( ECouleur.values()[cpt].getLibelle() ) ;


		this.lblNbSymbole = new JLabel("0");
		this.tabCbSymbole = new JCheckBox[ESymbole.values().length] ;
		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ ) 
			this.tabCbSymbole[cpt] = new JCheckBox( ESymbole.values()[cpt].getLibelle() ) ;
		
		this.txtTailleCases = new JTextField("50" , 4);
	
		this.tabPanelCreation = new JPanel[5]  ;
		for ( int cpt = 0 ; cpt < this.tabPanelCreation.length-1 ; cpt++ ) 
			this.tabPanelCreation[cpt] = new JPanel( new FlowLayout(FlowLayout.LEFT) );

		this.tabPanelCreation[4] = new JPanel();
		this.tabPanelCreation[4].setBackground( Color.LIGHT_GRAY );


		this.tabPanelCreation[0].setBorder( BorderFactory.createEmptyBorder( 20 , 25,  0, 25));
		this.tabPanelCreation[1].setBorder( BorderFactory.createEmptyBorder( 0  , 25,  0, 25));
		this.tabPanelCreation[2].setBorder( BorderFactory.createEmptyBorder( 0  , 25,  0, 25));
		this.tabPanelCreation[3].setBorder( BorderFactory.createEmptyBorder( 0  , 25, 10, 25));


		//TODO
		//(A supprimer , laisser pour debug)
		for ( int cpt = 0 ; cpt < 5; cpt++ ) 
		{
			this.tabCbCouleur[cpt].setSelected(true );
			this.tabCbSymbole[cpt].setSelected(true );
		}

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

		this.tabPanelCreation[4].add( this.btnValider );
		this.tabPanelCreation[4].add( this.btnReinitialiser );





		this.panelCreation.add( this.tabPanelCreation[0] );
		this.panelCreation.add( this.tabPanelCreation[1] );
		this.panelCreation.add( this.tabPanelCreation[2] );
		this.panelCreation.add( this.tabPanelCreation[3] );
		this.panelCreation.add( this.tabPanelCreation[4] );


		this.add( this.panelGestion  , BorderLayout.NORTH  ) ;
		this.add( this.panelCreation , BorderLayout.CENTER ) ;

		
	

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		this.btnNouveau.addActionListener(this);
		this.btnAncien .addActionListener(this);
		this.btnCopie  .addActionListener(this);

		this.btnValider      .addActionListener(this);
		this.btnReinitialiser.addActionListener(this);


		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
			this.tabCbCouleur[cpt].addItemListener(this);

		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ )
			this.tabCbSymbole[cpt].addItemListener(this);
	}


	public void itemStateChanged(ItemEvent e) 
	{

		this.lblNbCouleur.setText( Collections.frequency(this.getEnsCouleur(), 1) + "" );
		this.lblNbSymbole.setText( Collections.frequency(this.getEnsSymbole(), 1) + "" );

	}

	public void actionPerformed(ActionEvent e) 
	{
		Window fenetrePrincipale;

		if ( e.getSource() == this.btnNouveau ) 
		{ 
			this.panelCreation.setVisible(true);

			this.btnNouveau.setBackground( Color.DARK_GRAY );
			this.btnAncien .setBackground( Color.BLACK     );
			this.btnCopie  .setBackground( Color.BLACK     );
		}

		if ( e.getSource() == this.btnAncien ) 
		{
			this.panelCreation.setVisible(false);
			this.reinitialiserCreation() ;

			this.btnNouveau.setBackground( Color.BLACK     );
			this.btnAncien .setBackground( Color.DARK_GRAY );
			this.btnCopie  .setBackground( Color.BLACK     );

			this.choisirFichier () ;
		}

		if ( e.getSource() == this.btnCopie ) 
		{
			this.panelCreation.setVisible(false);
			this.reinitialiserCreation() ;

			this.btnNouveau.setBackground( Color.BLACK     );
			this.btnAncien .setBackground( Color.BLACK     );
			this.btnCopie  .setBackground( Color.DARK_GRAY );
		}


		if ( e.getSource() == this.btnValider      ) { this.validerCreation()       ;}
		if ( e.getSource() == this.btnReinitialiser) { this.reinitialiserCreation() ;}



		fenetrePrincipale = SwingUtilities.getWindowAncestor(this);
		if (fenetrePrincipale instanceof JFrame) { ( (JFrame) fenetrePrincipale).pack() ;}
	}


	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	public void validerCreation()
	{
		String tailleLargeur  = this.txtTailleLargeur .getText() ;
		String tailleLongueur = this.txtTailleLongueur.getText() ;
		String tailleCases    = this.txtTailleCases   .getText() ;

		boolean reseauSelect   = Collections.frequency(this.getEnsCouleur(), 1) >= 1 ;
		boolean batimentSelect = Collections.frequency(this.getEnsSymbole(), 1) >= 2 ;


		if ( this.estEntier( tailleLargeur ) && this.estEntier(tailleLongueur) && this.estEntier(tailleCases) && reseauSelect && batimentSelect )
		{
			this.ctrl.creePlateau ( Integer.parseInt(tailleLargeur) , Integer.parseInt(tailleLongueur) ,
									Integer.parseInt(tailleCases)   , this.getEnsCouleur() , this.getEnsSymbole() ) ;

			this.ctrl.lancerModification() ;
	
		}

		
	}

	public void reinitialiserCreation()
	{
		this.txtTailleLargeur .setText("");
		this.txtTailleLongueur.setText("");
		this.txtTailleCases   .setText("");

		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ )
			this.tabCbCouleur[cpt].setSelected( false );

		for ( int cpt = 0 ; cpt < this.tabCbSymbole.length ; cpt++ )
			this.tabCbSymbole[cpt].setSelected( false );

		this.lblNbCouleur.setText( "0");
		this.lblNbSymbole.setText( "0");
	}

	private boolean estEntier ( String val )
	{
		try { Integer.parseInt ( val )       ;}
		catch ( Exception e ) { return false ;}

		return true;
	}

	private ArrayList<Integer> getEnsCouleur()
	{
		ArrayList<Integer> lstCouleur = new ArrayList<>() ;

		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
		{
			if ( this.tabCbCouleur[cpt].isSelected() ) { lstCouleur.add(1) ;}
			else                                       { lstCouleur.add(0) ;}
		}

		return lstCouleur ;
			
	}

	private ArrayList<Integer> getEnsSymbole()
	{
		ArrayList<Integer> lstSymbole = new ArrayList<>() ;

		for ( int cpt = 0 ; cpt < this.tabCbCouleur.length ; cpt++ ) 
		{
			if ( this.tabCbSymbole[cpt].isSelected() ) { lstSymbole.add(1) ;}
			else                                       { lstSymbole.add(0) ;}
		}

		return lstSymbole ;
			
	}

	private void choisirFichier()
	{
		/*----------------*/
		/*  Données       */
		/*----------------*/

		JFileChooser            fileChooser ;
		FileNameExtensionFilter filtre ;
		File                    fichierChoisi ;
		String                  cheminFichier ;
		int                     reponse ;

		/*----------------*/
		/*  Instructions  */
		/*----------------*/

		fileChooser = new JFileChooser( new File("./src/ressource/data") );
		
		filtre = new FileNameExtensionFilter("Fichiers de sauvegarde (.data)", "data"); //filtre que les .data
		fileChooser.setFileFilter(filtre);


		reponse = fileChooser.showOpenDialog(this);

		if ( reponse == JFileChooser.APPROVE_OPTION )
		{
			fichierChoisi = fileChooser  .getSelectedFile();
			
			this.ctrl.chargerPlateau( fichierChoisi );
		}
	}
	


}

