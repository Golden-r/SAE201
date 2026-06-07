package ihm;

import controleur.Controleur;
import metier.ESymbole;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;

/* SAE 2.01 | Développement d'une application 
* PanelPlateauOuestSymbole
*
* Date     : 06/06/2026
* Groupe   : 4
*/

public class PanelPlateauOuestSymbole extends JPanel 
{
	private Controleur ctrl;

	private JComboBox<String> jcbSymbole;

	private ButtonGroup  btgRadio;
	private JRadioButton cbPlacer;
	private JRadioButton cbGomme;

	public PanelPlateauOuestSymbole( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

		this.setLayout( new GridLayout( 2 , 1 ));
		this.setBackground( Color.LIGHT_GRAY );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		JPanel panelChoix   = new JPanel( new GridLayout( 2 , 1) ) ;
		JPanel panelEdition = new JPanel( new GridLayout( 6 , 1) ) ;

		ArrayList<Integer> lstActifs    = this.ctrl.getLstSymbole();
		ArrayList<String> symbolesDispo = new ArrayList<>();

		for (int i = 0; i < ESymbole.values().length; i++) {
			if (lstActifs.get(i) == 1) {
				symbolesDispo.add(ESymbole.values()[i].getLibelle());
			}
		}

		this.jcbSymbole = new JComboBox<String>( symbolesDispo.toArray(new String[0]) );

		this.btgRadio   = new ButtonGroup();
		this.cbPlacer   = new JRadioButton("Placer", true);
		this.cbGomme    = new JRadioButton("Gommer");

		/* ----------------------------- */ 
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( panelChoix );
		panelChoix.add( this.jcbSymbole ) ;
		panelChoix.add( new JLabel("Image prévisualisation") ) ;

		this.btgRadio.add( this.cbPlacer );
		this.btgRadio.add( this.cbGomme  );

		this.add( panelEdition );
		panelEdition.add( this.cbPlacer );
		panelEdition.add( this.cbGomme  );
	}

	public boolean getModePlacement() { return this.cbPlacer.isSelected() ;}
	
	public ESymbole getSymboleSelectionne() 
	{ 
		String libelleChoisi = (String) this.jcbSymbole.getSelectedItem();
		for (ESymbole s : ESymbole.values()) {
			if (s.getLibelle().equals(libelleChoisi)) {
				return s;
			}
		}
		return null;
	}
}