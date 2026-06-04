package ihm;

import metier.ESymbole;
import metier.ECouleur;
import metier.Plateau;
import controleur.Controleur;


import metier.ECouleur;
import metier.Zone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.ImageIcon;
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

	private JPanel panelPlateau ;
	private JPanel panelNord ;
	private JPanel panelSud ;
	private JPanel panelEst ;
	private JPanel panelOuest ; 

	public PanelModification( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

        this.setLayout( new BorderLayout() );
		
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelPlateau = new JPanel( new GridLayout ( this.ctrl.getTailleLargeur() , this.ctrl.getTailleLongueur() )) ;


		this.panelNord  = new JPanel() ;
		this.panelSud   = new JPanel() ;
		this.panelEst   = new JPanel() ;
		this.panelOuest = new JPanel() ;
        
		this.panelNord .setBackground( Color.DARK_GRAY );
		this.panelSud  .setBackground( Color.DARK_GRAY );
		this.panelEst  .setBackground( Color.DARK_GRAY );
		this.panelOuest.setBackground( Color.DARK_GRAY );


		ImageIcon imageVide = new ImageIcon("./src/ressource/images/h.png");
		Image     imageNonRedim = imageVide.getImage() ;
		Image     imageRedim    = imageNonRedim.getScaledInstance( this.ctrl.getTailleCase(), this.ctrl.getTailleCase(), Image.SCALE_SMOOTH) ; //Image.SCALE_SMOOTH evite la pixelisation
		imageVide = new ImageIcon( imageRedim ) ;
		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		

		for ( int lig = 0 ; lig < this.ctrl.getTailleLargeur() ; lig ++ )
			for ( int col = 0 ; col < this.ctrl.getTailleLongueur() ; col ++ )
			{
				JLabel lblCase = new JLabel( imageVide );
				this.panelPlateau.add( lblCase ) ;
			}



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

