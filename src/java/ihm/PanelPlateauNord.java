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

	private JButton btnPrevisu ;

	private JLabel lblNbZone ;

	/*----------------------------*/
	/* Constructeur de la classe */
	/*----------------------------*/
	
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
		
		JPanel panelCentre = new JPanel( new GridLayout( 1 , 2 )) ;
		panelCentre.setOpaque(false) ;


		this.btnRetour  = new JButton("Retour") ;
		this.btnSuivant = new JButton("Suivant") ;

		this.lblNbZone = new JLabel( "Nombre de zone total : ") ;

		this.btnPrevisu = new JButton("Prévisualisation") ;
		this.btnPrevisu.setBackground( Color.RED ) ;

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		panelCentre.add( this.lblNbZone );
		panelCentre.add( this.btnPrevisu );

		this.add( this.btnRetour  , BorderLayout.WEST   );
		this.add( panelCentre     , BorderLayout.CENTER );
		this.add( this.btnSuivant , BorderLayout.EAST   );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		this.btnRetour.addActionListener(this);
		this.btnSuivant.addActionListener(this);

		this.btnPrevisu.addActionListener(this);

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
					this.enregistrerSous();
					break; 

				default:
					break;
			}

		}


		else if ( e.getSource() == this.btnPrevisu )
		{
			this.ctrl.setPrevisu() ;

			if ( this.ctrl.getPrevisu() )
			{
				this.btnPrevisu.setBackground( Color.GREEN ) ;
				this.btnPrevisu.setOpaque( true ) ; 
			}
			else { this.btnPrevisu.setBackground( Color.RED ) ;}
			
			this.panelModification.repaint() ;
		}
		
	}

	private void enregistrerSous()
	{
		/*----------------*/
		/* Données       */
		/*----------------*/

		JFileChooser            fileChooser   ;
		javax.swing.filechooser.FileNameExtensionFilter filtre ;
		java.io.File            fichierChoisi ;
		int                     reponse       ;

		/*----------------*/
		/* Instructions  */
		/*----------------*/

		fileChooser = new JFileChooser( new java.io.File( "./src/ressource/data" ) ) ;
		filtre      = new javax.swing.filechooser.FileNameExtensionFilter( "Fichiers de sauvegarde (.data)", "data" ) ;
		
		fileChooser.setFileFilter( filtre ) ;

		reponse = fileChooser.showSaveDialog( this ) ;


		if ( reponse == JFileChooser.APPROVE_OPTION )
		{
			fichierChoisi = fileChooser.getSelectedFile() ;

			if ( !fichierChoisi.getName().endsWith( ".data" ) )
			{
				fichierChoisi = new java.io.File( fichierChoisi.getAbsolutePath() + ".data" ) ;
			}

			this.ctrl.enregistrerFichier( fichierChoisi ) ;
		}
	}

	public void majBtnEnregistrer()
	{
		if ( this.ctrl.getEtapeConception() == 3 ){ this.btnSuivant.setEnabled( this.ctrl.toutesLesBasesPlacees() ) ;}
	}

	public void setTextBtnEnregistrer()
	{
		this.btnSuivant.setText("Enregistrer sous");
		this.majBtnSuivant() ;
	}

	public void setTextBtnSuivant()
	{
		this.btnSuivant.setText("Suivant");
		this.majBtnSuivant() ;
	}

	public void majBtnSuivant()
	{
		if ( this.ctrl.getEtapeConception() == 1 )
		{
			this.btnSuivant.setEnabled( true ) ;
		}

		if ( this.ctrl.getEtapeConception() == 2 )
		{
			this.btnSuivant.setEnabled( this.ctrl.assezDeBatimentsPourBases() ) ;
		}
		
		if ( this.ctrl.getEtapeConception() == 3 )
		{
			this.btnSuivant.setEnabled( this.ctrl.toutesLesBasesPlacees() ) ;
		}
	}
}
