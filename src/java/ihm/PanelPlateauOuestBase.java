
package ihm;

import controleur.Controleur;
import metier.ECouleur;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;

/* SAE 2.01 | Développement d'une application 
* PanelPlateauOuestBase
*
* Date     : 06/06/2026
* Groupe   : 4
*/

public class PanelPlateauOuestBase extends JPanel implements ActionListener
{
	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

	Controleur ctrl ;

	private JComboBox<String> jcbBase;
	private JLabel            lblImagePrevisu;

	private ButtonGroup  btgRadio;
	private JRadioButton cbPlacer;
	private JRadioButton cbGomme;

	/*----------------------------*/
	/* Constructeur de la classe */
	/*----------------------------*/

	public PanelPlateauOuestBase( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

		this.setLayout( new GridLayout( 2 , 1 ));
		this.setBackground( Color.LIGHT_GRAY );
		this.setBorder( BorderFactory.createEmptyBorder( 10 , 10,  10, 10));


		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		JPanel panelChoix   = new JPanel( new GridLayout( 2 , 1) ) ;
		JPanel panelEdition = new JPanel( new GridLayout( 6 , 1) ) ;

		panelChoix  .setBorder( BorderFactory.createEmptyBorder( 10 , 25,  10, 10));
		panelEdition.setBorder( BorderFactory.createEmptyBorder( 10 , 25,  10, 10));

		ArrayList<Integer> lstActifs    = this.ctrl.getLstCouleur();
		ArrayList<String> symbolesDispo = new ArrayList<>();

		for (int cpt = 0; cpt < ECouleur.values().length-1; cpt++) 
		{
			if (lstActifs.get(cpt) == 1  ) 
			{
				symbolesDispo.add( ECouleur.values()[cpt].getLibelle() );
			}
		}

		this.jcbBase = new JComboBox<String>( symbolesDispo.toArray(new String[0]) );
		this.lblImagePrevisu = new JLabel();

		this.btgRadio   = new ButtonGroup();
		this.cbPlacer   = new JRadioButton("Placer", true);
		this.cbGomme    = new JRadioButton("Gommer");

		this.mettreAJourImage();

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( panelChoix );
		panelChoix.add( this.jcbBase ) ;
		panelChoix.add( this.lblImagePrevisu ) ;

		this.btgRadio.add( this.cbPlacer );
		this.btgRadio.add( this.cbGomme  );

		this.add( panelEdition );
		panelEdition.add( this.cbPlacer );
		panelEdition.add( this.cbGomme  );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		this.jcbBase.addActionListener(this);

	}
	/*----------------------------*/ 
	/*  Methodes			      */
	/*----------------------------*/

	public void actionPerformed( ActionEvent e ) 
	{
		if (e.getSource() == this.jcbBase) { this.mettreAJourImage() ;}
	}


	public ECouleur getBaseSelectionne() 
	{ 
		String libelleChoisi = (String) this.jcbBase.getSelectedItem();

		for (ECouleur s : ECouleur.values()) 
			if ( s.getLibelle().equals( libelleChoisi ) ) { return s ;}

		return null;
	}

	public boolean getModePlacement() { return this.cbPlacer.isSelected() ; }

	private void mettreAJourImage()
	{
		ECouleur sym = this.getBaseSelectionne();
		
		if (sym != null)
		{
			String nomFichier = sym.getLibelle() + "_base.png";
			String chemin     = "./src/ressource/images/Symboles/" + nomFichier ;
			
			ImageIcon iconeOriginale = new ImageIcon(chemin);
			
			Image imgRedimensionnee = iconeOriginale.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			
			this.lblImagePrevisu.setIcon(new ImageIcon(imgRedimensionnee));
		}
	}

	


}
