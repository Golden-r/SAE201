package ihm;
import controleur.Controleur;
import metier.EModes;

import javax.swing.*;
import javax.swing.SpringLayout.Constraints;



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* SAE 2.01 | Développement d'une application 
* PanelJeu
*
* Date     : 09/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelMenu extends JPanel
{	

	private static String[] modeJeu ;
	
    private Controleur ctrl;

	private JPanel     panelSelectionModes ;
	private JPanel     panelDescription    ;

	private JButton[]  tabBtnModes;
	
	private JLabel     lblDescription;


	private JPanel     panelDroite ;
	private JButton    btnRegle ;

	
	public PanelMenu( Controleur ctrl ) 
	{
		this.ctrl = ctrl ; 
		PanelMenu.modeJeu = ctrl.getNomJeu();
		
		this.setLayout( new BorderLayout() );

		int largeurMenu = this.ctrl.getSizeMenu().width;
		int hauteurMenu = this.ctrl.getSizeMenu().height;

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		
		this.panelSelectionModes = new JPanel(new GridBagLayout());
		this.panelSelectionModes.setBackground(Color.BLUE);

		this.panelSelectionModes.setPreferredSize(new Dimension(largeurMenu, (int)(hauteurMenu * 0.60)));

		this.panelDescription = new JPanel();
		this.panelDescription.setBackground(Color.RED);

		this.panelDescription.setPreferredSize(new Dimension(largeurMenu, (int)(hauteurMenu * 0.40)));
		
		this.tabBtnModes = new JButton[ PanelMenu.modeJeu.length ];

		for (int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
		{
			JButton tmp = new JButton();
			tmp.setText(PanelMenu.modeJeu[cpt]);
			tmp.setPreferredSize(new Dimension(140, 100));
			this.tabBtnModes[cpt] = tmp;
		}
		
		this.lblDescription = new JLabel(EModes.values()[0].getDescription());
		this.panelDescription.add(this.lblDescription);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(50, 40, 20, 40);
		gbc.gridy = 0;



		
		this.panelDroite = new JPanel();

		this.btnRegle = new JButton("Regle du jeu");




		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		for (int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
		{
			gbc.gridx = cpt;
			this.panelSelectionModes.add( this.tabBtnModes[cpt], gbc);
		}

		this.add( this.panelSelectionModes , BorderLayout.CENTER);
		this.add( this.panelDescription    , BorderLayout.SOUTH);

		
		/* ----------------------------- */
		/* Activation des composants     */
		/* ----------------------------- */
		GereSouris gereSouris = new GereSouris();

		for (int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
		{
			this.tabBtnModes[cpt].addMouseListener(gereSouris);
		}


	}

	private class GereSouris extends MouseAdapter
	{
		public void mouseEntered(MouseEvent e)
		{
			Object boutonSurvole;
			String nouvelleDescription;


			boutonSurvole = e.getSource();

			for ( int i = 0; i < tabBtnModes.length; i++ )
			{
				if (boutonSurvole == tabBtnModes[i])
				{
					nouvelleDescription = EModes.values()[i].getDescription();
					
					lblDescription.setText(nouvelleDescription);
					
					break; 
				}
			}
		}

		public void mouseExited(MouseEvent e)
		{
			lblDescription.setText("");
		}

		public void mouseClicked(MouseEvent e)
		{

		}
	}
}
