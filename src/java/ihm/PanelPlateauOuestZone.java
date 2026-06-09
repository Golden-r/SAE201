package ihm;

import controleur.Controleur;

import javax.swing.*;

import java.awt.event.* ;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* PanelPlateauOuestZone
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelPlateauOuestZone extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JComboBox<String> jcbZone;

	private ButtonGroup  btgRadio ;
	private JRadioButton cbSelecion ;
	private JRadioButton cbGomme ;

	private JPanel panelCouleurPrevisu ;
	private Color  couleurCourante ;
	

	public PanelPlateauOuestZone( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

		this.setLayout( new GridLayout( 2 , 1 ));
		this.setBackground( Color.LIGHT_GRAY );
		this.setBorder( BorderFactory.createEmptyBorder( 10 , 10,  10, 10));

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		JPanel panelChoix        = new JPanel( new GridLayout( 2 , 1) ) ;
		JPanel panelEdition      = new JPanel( new GridLayout( 6 , 1) ) ;

		panelChoix  .setBorder( BorderFactory.createEmptyBorder( 10 , 25,  10, 10));
		panelEdition.setBorder( BorderFactory.createEmptyBorder( 10 , 25,  10, 10));

		this.jcbZone = new JComboBox<String>( this.ctrl.getZones() );


		this.btgRadio   = new ButtonGroup();
		this.cbSelecion = new JRadioButton("Selecion", true);
		this.cbGomme    = new JRadioButton("Gomme"                 );



		this.couleurCourante = this.ctrl.getCouleurProchaineZone( 0 ) ;
		this.panelCouleurPrevisu = new PanelCouleur() ;
		this.panelCouleurPrevisu.setPreferredSize( new Dimension(60, 60) );

		


		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( panelChoix   );

		panelChoix.add( this.jcbZone ) ;
		panelChoix.add( this.panelCouleurPrevisu ) ;



		this.btgRadio.add( this.cbSelecion );
		this.btgRadio.add( this.cbGomme    );

		this.add( panelEdition );

		panelEdition.add( this.cbSelecion );
		panelEdition.add( this.cbGomme    );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		this.jcbZone.addActionListener(this);


	}


	public boolean getModeSelection()   { return this.cbSelecion.isSelected()    ;}
	public int     getZoneSelectioner() { return this.jcbZone.getSelectedIndex() ;}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.jcbZone) 
		{ 
			this.ctrl.setIndiceZoneSelectionnee(this.jcbZone.getSelectedIndex()) ;
			this.mettreAJourPrevisu();
		}
	}

	public void mettreAJourPrevisu()
	{
		int indice = this.jcbZone.getSelectedIndex() ;
		
		this.couleurCourante = this.ctrl.getCouleurProchaineZone( indice ) ;
		this.panelCouleurPrevisu.repaint() ;
	}



	private class PanelCouleur extends JPanel
	{
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			
			g.setColor( PanelPlateauOuestZone.this.couleurCourante );
			g.fillRect( 10, 5, 40, 40 ); 
			
			g.setColor( Color.BLACK );
			g.drawRect( 10, 5, 40, 40 ); 
		}
	}

}



















