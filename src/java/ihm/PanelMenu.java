package ihm;
import controleur.Controleur;
import metier.EModes;

import javax.swing.*;
import javax.swing.SpringLayout.Constraints;



import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

/* SAE 2.01 | Développement d'une application 
* PanelJeu
*
* Date     : 09/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelMenu extends JPanel implements ActionListener
{	

	private static final String[] TAB_ACTIONS = {"Regles", "Stats", "Jouer", "Personnage", "Boutique"};

	private static String[] modeJeu ;
	

    private Controleur ctrl;

	private JPanel    panelJoueur ;
	private JPanel    panelChoix  ;
	private JPanel    panelMode   ;
	private JPanel    panelMap    ;

	private JPanel     panelMusic  ;
	private JScrollBar sbMusic ;
	private JButton    btnMute ;
	private JButton    btnDeMute ;

	private JButton[] tabBtnModes ;
	private JButton[] tabBtnChoix ;
	
	private Font      fontCustom  ;


	public PanelMenu( Controleur ctrl ) 
	{
		this.ctrl = ctrl ; 
		this.setLayout( new BorderLayout() );

		this.chargerFont();

		int largeurMenu = this.ctrl.getSizeMenu().width;
		int hauteurMenu = this.ctrl.getSizeMenu().height;

		PanelMenu.modeJeu = ctrl.getNomJeu();

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		
		this.panelJoueur = new JPanel();
		this.panelJoueur.setPreferredSize(new Dimension(largeurMenu, (int)(hauteurMenu * 0.60)));
		this.panelJoueur.setBackground(Color.YELLOW);


		this.panelChoix = new JPanel( new GridLayout( 1 , 5 ));
		this.panelChoix.setPreferredSize(new Dimension(largeurMenu, (int)(hauteurMenu * 0.15)));
		this.panelJoueur.setBackground(Color.RED);
		
		
		this.panelMode = new JPanel( new GridLayout( 6 , 1 , 10 , 10));
		this.panelMode.setPreferredSize(new Dimension((int)(largeurMenu * 0.15), hauteurMenu));
		this.panelMode.setBorder(BorderFactory.createEmptyBorder( 20 , 20, 20 , 20));
		this.panelMode.setBackground(Color.GREEN);

		

		this.panelMusic = new JPanel(new BorderLayout());
		this.sbMusic = new JScrollBar( Scrollbar.HORIZONTAL , 10 , 10 , 0 , 100);
		this.btnMute = new JButton("mute");
		this.btnDeMute = new JButton("demute");
		
		this.panelMap  = new JPanel();
		this.panelMap.setPreferredSize(new Dimension((int)(largeurMenu * 0.15), hauteurMenu));
		this.panelMap .setBackground(Color.BLUE);


		this.tabBtnModes = new JButton[ PanelMenu.modeJeu.length ];
		for ( int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++ )
		{
			this.tabBtnModes[cpt] = new JButton();
			this.tabBtnModes[cpt].setFont(this.fontCustom);
			this.tabBtnModes[cpt].setText(PanelMenu.modeJeu[cpt]);
			this.tabBtnModes[cpt].setPreferredSize(new Dimension(140, 100));
		}


		this.tabBtnChoix = new JButton[ TAB_ACTIONS.length ];
		for ( int cpt = 0 ; cpt < this.tabBtnChoix.length ; cpt ++ )
		{
            ImageIcon iconOrigine = new ImageIcon("./src/ressource/images/Bouton/" + TAB_ACTIONS[cpt] + ".png");
            Image imgRedimensionnee = iconOrigine.getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);

            ImageIcon imgIconeFinal = new ImageIcon(imgRedimensionnee);


            this.tabBtnChoix[cpt] = new JButton(imgIconeFinal);
            
			this.tabBtnChoix[cpt].setBackground   ( new Color (41,98,145) ) ;
            this.tabBtnChoix[cpt].setPreferredSize( new Dimension(100 , 100) ) ;
            this.tabBtnChoix[cpt].setActionCommand( TAB_ACTIONS[cpt] );
		}
		
		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		this.panelMusic.add ( this.btnMute   , BorderLayout.WEST );
		this.panelMusic.add ( this.sbMusic   , BorderLayout.CENTER );
		this.panelMusic.add ( this.btnDeMute , BorderLayout.EAST );


		this.panelMode.add( this.panelMusic );
		this.panelMode.add( new JLabel("")  );
		for ( int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
			this.panelMode.add( this.tabBtnModes[cpt] );

		for ( int cpt = 0; cpt < this.tabBtnChoix.length ; cpt ++)
			this.panelChoix.add( this.tabBtnChoix[cpt] );


		this.add( this.panelChoix  , BorderLayout.SOUTH);
		this.add( this.panelJoueur , BorderLayout.CENTER);
		this.add( this.panelMode   , BorderLayout.WEST );
		this.add( this.panelMap    , BorderLayout.EAST );


		/* ----------------------------- */
		/* Activation des composants     */
		/* ----------------------------- */

		GereSouris gereSouris = new GereSouris();

		for (int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
		{
			this.tabBtnModes[cpt].addMouseListener(gereSouris);
		}

		for (int cpt = 0; cpt < this.tabBtnChoix.length; cpt++)
		{
			this.tabBtnChoix[cpt].addActionListener(this);
		}
	}

	private void chargerFont()
	{
		this.fontCustom = ctrl.retourneFont("font", 12f); // TODO : en dur pour le moment, pas d'environement player.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(this.fontCustom);
	}

	// TODO : Reviser ça car on sait bien que si on setFont puis le joueur change de font, bahh faut mettre a jour
	/*public void majFont(Font newFont)
	{
		this.fontCustom = newFont;

		for (JButton b : tabBtnModes)
			b.setFont(newFont);

		for (JButton b : tabBtnChoix)
			b.setFont(newFont);

		repaint();
		revalidate();
	}*/
	
	public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String action = e.getActionCommand();
        
        switch (action) 
        {
            case "Regles" ->
			{
                System.out.println("Action déclenchée : Affichage des règles");
            }
            case "Stats" ->
			{
                System.out.println("Action déclenchée : Affichage des statistiques");
            }
            case "Jouer" ->
			{
                System.out.println("Action déclenchée : Lancement de la partie");
            }
            case "Personnage" ->
			{
                System.out.println("Action déclenchée : Personnalisation du profil");
            }
            case "Boutique" ->
			{
                System.out.println("Action déclenchée : Ouverture de la boutique");
            }
			
            default -> System.out.println("Action inconnue : " + action);
        }
    }

	private class GereSouris extends MouseAdapter
	{
		public void mouseEntered(MouseEvent e)
		{
	
		}

		public void mouseExited(MouseEvent e)
		{
		}

		public void mouseClicked(MouseEvent e)
		{

		}
	}
}
