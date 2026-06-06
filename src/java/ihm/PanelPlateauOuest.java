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
* PanelPlateauOuest
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelPlateauOuest extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JComboBox<String> jcbZone;

	private ButtonGroup  btgRadio ;
	private JRadioButton cbSelecion ;
	private JRadioButton cbGomme ;
	

	public PanelPlateauOuest( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

		this.setLayout( new GridLayout( 2 , 1 ));
		this.setBackground( Color.LIGHT_GRAY );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		JPanel panelChoix        = new JPanel( new GridLayout( 2 , 1) ) ;
		JPanel panelEdition      = new JPanel( new GridLayout( 6 , 1) ) ;

		this.jcbZone = new JComboBox<String>( this.ctrl.getZones() );


		this.btgRadio   = new ButtonGroup();
		this.cbSelecion = new JRadioButton("Selecion", true);
		this.cbGomme    = new JRadioButton("cbGomme"                 );


		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( panelChoix   );

		panelChoix.add( this.jcbZone ) ;
		panelChoix.add( new JLabel("Image previsualiation") ) ;



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
			this.ctrl.setIndiceZoneSelectionnee(this.jcbZone.getSelectedIndex());
	}

}



















