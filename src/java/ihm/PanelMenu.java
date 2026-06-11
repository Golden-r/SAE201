package ihm;
import controleur.Controleur;
import metier.EModes;
import metier.ManageurMusique;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.SpringLayout.Constraints;


import static java.awt.Image.SCALE_SMOOTH;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;

/* SAE 2.01 | Développement d'une application 
* PanelJeu
*
* Date     : 09/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelMenu extends JPanel implements ActionListener, ChangeListener, ListSelectionListener
{	

	private static final String[] TAB_ACTIONS  = {"Regles", "Stats", "Jouer", "Personnage", "Boutique"};
	private static final Color    COULEUR_BLEU = new Color (24, 32, 118);
	private static       String[] modeJeu ;
	

    private Controleur ctrl;

	private Image imgFond ;

	private JPanel    panelJoueur ;
	private JPanel    panelChoix  ;
	private JPanel    panelMode   ;
	private JPanel    panelDroite ;
	private JPanel    panelDroiteJoueur;

	private JPanel[]    tabWrapBtnChoix ;
	private ImageIcon[] tabIconesPetites ;
	private ImageIcon[] tabIconesGrandes ;

	private JPanel     panelMusic  ;
	private JSlider    sbMusic;
	private JButton    btnMute ;
	private JButton    btnDeMute ;

	private JButton[] tabBtnModes ;
	private JButton[] tabBtnChoix ;
	
	private Font      fontCustom  ;
	

	private JLabel    lblPseudo ;
	private JLabel    lblImgPerso ;
	private JButton   btnLancer ;

	private JList<String> lstSauvegardes;
	


	public PanelMenu( Controleur ctrl ) 
	{

		this.ctrl = ctrl ; 
		this.ctrl.lancerMusique();
				

		int largeurMenu = this.ctrl.getSizeMenu().width;
		int hauteurMenu = this.ctrl.getSizeMenu().height;

		PanelMenu.modeJeu = ctrl.getNomJeu();

		JPanel    panelModeBtn ;
		JPanel    panelJoueurHaut;
		JPanel    panelJoueurCentre ;
		JPanel    panelJoueurBas ;

		ImageIcon iconOrigine;
		Image     imgGrande, imgPetite ;
		ImageIcon iconMute , iconDeMute ;

		ImageIcon iconPerso ;
		Image     imgPerso ;

		JScrollPane scrollList;
		JPanel panelListe ;


		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		this.setLayout( new BorderLayout() );
		this.imgFond = new ImageIcon("./src/ressource/images/Fond/fond_menu_100.png").getImage();
	
		
		this.panelJoueur = new JPanel( new BorderLayout() );
		this.panelJoueur.setPreferredSize(new Dimension(largeurMenu, (int)(hauteurMenu * 0.60)));
		this.panelJoueur.setOpaque(false);

		this.lblPseudo = new JLabel("Joueur 1");
		this.lblPseudo.setFont(new Font("Arial", Font.BOLD, 22));
		this.lblPseudo.setForeground( new Color( 252, 227, 147 ));

		panelJoueurHaut = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		panelJoueurHaut.setOpaque(false);
		panelJoueurHaut.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0)); 
		
		iconPerso = new ImageIcon("./src/ressource/images/Personnage/p_noir.png");
		imgPerso = iconPerso.getImage().getScaledInstance(200, 200, SCALE_SMOOTH); 
		this.lblImgPerso = new JLabel(new ImageIcon(imgPerso));
		
		panelJoueurCentre = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		panelJoueurCentre.setOpaque(false);
		
		this.btnLancer = new JButton("Lancer");
		this.btnLancer.setBackground(new Color(252, 227, 147) );
		this.btnLancer.setForeground( Color.white);
		this.btnLancer.setPreferredSize(new Dimension(160, 45));
		this.btnLancer.setFont(new Font("Arial", Font.BOLD, 18));
		this.btnLancer.setFocusPainted(false);
		
		panelJoueurBas = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		panelJoueurBas.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		panelJoueurBas.setOpaque(false);
		


	
		

		this.panelMode = new JPanel( new BorderLayout() );
		this.panelMode.setPreferredSize(new Dimension((int)(largeurMenu * 0.20), hauteurMenu));
		this.panelMode.setBorder(BorderFactory.createEmptyBorder( 20 , 10, 20 , 10));
		this.panelMode.setOpaque(false);

		panelModeBtn = new JPanel( new GridLayout( 3 , 1 , 0 , 10)) ;
		panelModeBtn.setBorder(BorderFactory.createEmptyBorder( 100 , 10, 100 , 10));
		panelModeBtn.setOpaque(false );

		this.panelMusic = new JPanel(new BorderLayout());
		this.panelMusic.setOpaque(false );

		this.sbMusic = new JSlider(JSlider.HORIZONTAL, 0, 100, this.ctrl.getVolumeMusique() );
		this.sbMusic.setOpaque(false );
		iconMute   = new ImageIcon("./src/ressource/images/Bouton/mute.png");
		iconDeMute = new ImageIcon("./src/ressource/images/Bouton/demute.png");
		this.btnMute   = new JButton(iconMute);
		this.btnDeMute = new JButton(iconDeMute);
		this.btnMute  .setPreferredSize(new Dimension(30, 30));
		this.btnDeMute.setPreferredSize(new Dimension(30, 30));
		this.btnMute  .setBackground( Color.RED );
		this.btnDeMute.setBackground( PanelMenu.COULEUR_BLEU );
		this.btnMute.setBorderPainted(false);
		this.btnDeMute.setBorderPainted(false);


		
		

		this.panelChoix = new JPanel( new GridLayout( 1 , 5 , 10 , 10));
		this.panelChoix.setPreferredSize(new Dimension(largeurMenu, (int)(hauteurMenu * 0.15)));
		this.panelChoix.setOpaque(false);

		


		this.panelDroite = new JPanel(new GridLayout(2, 1)); 
		this.panelDroite.setPreferredSize(new Dimension((int)(largeurMenu * 0.20), hauteurMenu));
		this.panelDroite.setOpaque(false);

		this.panelDroiteJoueur = new JPanel();
		this.panelDroiteJoueur.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); 
		this.panelDroiteJoueur.setVisible(false); 

		panelListe = new JPanel(new BorderLayout());
		panelListe.setOpaque(false);
		panelListe.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); 

		String[] tabFichiers = this.ctrl.getListeSauvegardes();

		this.lstSauvegardes = new JList<>(tabFichiers);
		this.lstSauvegardes.setFont(new Font("Arial", Font.PLAIN, 14));
		this.lstSauvegardes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollList = new JScrollPane(this.lstSauvegardes);
		



		this.tabBtnModes = new JButton[ PanelMenu.modeJeu.length ];
		for ( int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++ )
		{
			this.tabBtnModes[cpt] = new JButton();
			this.tabBtnModes[cpt].setFont(this.fontCustom);
			this.tabBtnModes[cpt].setText(PanelMenu.modeJeu[cpt]);
			this.tabBtnModes[cpt].setPreferredSize(new Dimension(140, 100));
			this.tabBtnModes[cpt].setBackground( new Color(255, 237, 213) );
			this.tabBtnModes[cpt].setForeground( new Color(237, 109, 39)  );
			this.tabBtnModes[cpt].setBorderPainted(false);
			this.tabBtnModes[cpt].setFocusPainted(false);
		}

		this.tabBtnModes[0].setFont(new Font("Arial", Font.BOLD , 16));
		this.tabBtnModes[1].setFont(new Font("Arial", Font.PLAIN, 13));
		this.tabBtnModes[2].setFont(new Font("Arial", Font.PLAIN, 13));

		this.tabBtnModes[0].setBackground( new Color(249, 115, 22) );
		this.tabBtnModes[0].setForeground( Color.WHITE );
		
		


		this.tabBtnChoix      = new JButton  [ TAB_ACTIONS.length ];
		this.tabWrapBtnChoix  = new JPanel   [ TAB_ACTIONS.length ];
		this.tabIconesPetites = new ImageIcon[ TAB_ACTIONS.length ];
		this.tabIconesGrandes = new ImageIcon[ TAB_ACTIONS.length ];

		for ( int cpt = 0 ; cpt < this.tabBtnChoix.length ; cpt ++ )
		{
			iconOrigine = new ImageIcon("./src/ressource/images/Bouton/" + TAB_ACTIONS[cpt] + ".png");
			
			imgGrande = iconOrigine.getImage().getScaledInstance(70, 70, SCALE_SMOOTH);
			imgPetite = iconOrigine.getImage().getScaledInstance(50, 50, SCALE_SMOOTH);

			this.tabIconesGrandes[cpt] = new ImageIcon(imgGrande);
			this.tabIconesPetites[cpt] = new ImageIcon(imgPetite);

			this.tabBtnChoix[cpt] = new JButton();
			this.tabBtnChoix[cpt].setBackground   ( PanelMenu.COULEUR_BLEU ) ;
			this.tabBtnChoix[cpt].setPreferredSize( new Dimension(100 , 100) ) ;
			this.tabBtnChoix[cpt].setActionCommand( TAB_ACTIONS[cpt] );
			this.tabBtnChoix[cpt].setBorderPainted(false);//contour
			this.tabBtnChoix[cpt].setFocusPainted(false);//contour click
			
			this.tabWrapBtnChoix[cpt] = new JPanel(new BorderLayout());
			this.tabWrapBtnChoix[cpt].setOpaque(false);
			this.tabWrapBtnChoix[cpt].add(this.tabBtnChoix[cpt], BorderLayout.CENTER);

			if ( cpt == 2 ) //defaut btn jouer
			{
				this.tabBtnChoix[cpt].setIcon( this.tabIconesGrandes[cpt] );
				this.tabWrapBtnChoix[cpt].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			} 
			else 
			{
				this.tabBtnChoix[cpt].setIcon( this.tabIconesPetites[cpt] );
				this.tabWrapBtnChoix[cpt].setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
			}
		}
		
		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		this.panelMusic.add ( this.btnMute   , BorderLayout.WEST   );
		this.panelMusic.add ( this.sbMusic   , BorderLayout.CENTER );
		this.panelMusic.add ( this.btnDeMute , BorderLayout.EAST   );


		this.panelMode.add( this.panelMusic , BorderLayout.NORTH  );
		for ( int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
			panelModeBtn.add( this.tabBtnModes[cpt] );
		this.panelMode.add( panelModeBtn    , BorderLayout.CENTER );
		this.panelMode.add( new JLabel(""), BorderLayout.SOUTH );



		for ( int cpt = 0; cpt < this.tabWrapBtnChoix.length ; cpt ++)
		{
			this.panelChoix.add( this.tabWrapBtnChoix[cpt] );
		}


		this.panelDroiteJoueur.add(new JLabel("liste joueur")); //temporraire
		panelListe      .add(scrollList, BorderLayout.CENTER);
		this.panelDroite.add(this.panelDroiteJoueur);
		this.panelDroite.add(panelListe);

		panelJoueurHaut  .add(this.lblPseudo  );
		panelJoueurCentre.add(this.lblImgPerso);
		panelJoueurBas   .add(this.btnLancer  );

		this.panelJoueur.add(panelJoueurHaut  , BorderLayout.NORTH  );
		this.panelJoueur.add(panelJoueurCentre, BorderLayout.CENTER );
		this.panelJoueur.add(panelJoueurBas   , BorderLayout.SOUTH  );


		this.add( this.panelChoix  , BorderLayout.SOUTH  );
		this.add( this.panelJoueur , BorderLayout.CENTER );
		this.add( this.panelMode   , BorderLayout.WEST   );
		this.add( this.panelDroite , BorderLayout.EAST   );


		/* ----------------------------- */
		/* Activation des composants     */
		/* ----------------------------- */

		this.btnMute  .addActionListener(this);
		this.btnDeMute.addActionListener(this);
		this.sbMusic  .addChangeListener(this);

		this.btnLancer.addActionListener(this);

		this.lstSauvegardes.addListSelectionListener(this);
		
		GereSouris gereSouris = new GereSouris();

		for ( int cpt = 0; cpt < PanelMenu.modeJeu.length; cpt++)
			this.tabBtnModes[cpt].addActionListener(this);


		for ( int cpt = 0; cpt < this.tabBtnChoix.length; cpt++)
			this.tabBtnChoix[cpt].addActionListener(this);
		
	}

	private void chargerFont()
	{
		//this.fontCustom = ctrl.retourneFont("font", 12f); // TODO : en dur pour le moment, pas d'environement player.
        //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //ge.registerFont(this.fontCustom);
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
	
	public void actionPerformed( ActionEvent e)
    {
        String action = e.getActionCommand();
        
		boolean estUnBoutonChoix = false;
		for (String nomAction : TAB_ACTIONS) 
		{
			if (nomAction.equals(action))  { estUnBoutonChoix = true;	break;}
		}


		if (estUnBoutonChoix) 
		{

			for (int cpt = 0; cpt < TAB_ACTIONS.length; cpt++) 
			{
				if (TAB_ACTIONS[cpt].equals(action)) 
				{
					this.tabWrapBtnChoix[cpt].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					this.tabBtnChoix[cpt].setIcon(this.tabIconesGrandes[cpt]);
				} 
				else 
				{
					this.tabWrapBtnChoix[cpt].setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
					this.tabBtnChoix[cpt].setIcon(this.tabIconesPetites[cpt]);
				}
			}
			
			this.panelChoix.revalidate();
			this.panelChoix.repaint();
		}


		if ( e.getSource() == this.btnMute && this.sbMusic.getValue() > 0 )
		{
			this.ctrl.couperMusique(true);
			this.sbMusic.removeChangeListener(this);
			this.sbMusic.setValue(0);
			this.sbMusic.addChangeListener(this);
		}

		if ( e.getSource() == this.btnDeMute && this.sbMusic.getValue() == 0 )
		{
			this.sbMusic.removeChangeListener(this);
			this.sbMusic.setValue(this.ctrl.getVolumeMusique());
			this.ctrl.couperMusique(false);
			this.sbMusic.addChangeListener(this);
		}



		if ( e.getSource() == this.tabBtnModes[0] )
		{ 
			this.tabBtnModes[0].setBackground( new Color(249, 115, 22 ) ); this.tabBtnModes[0].setForeground( Color.WHITE );
			this.tabBtnModes[1].setBackground( new Color(255, 237, 213) ); this.tabBtnModes[1].setForeground( new Color(237, 109, 39)  );
			this.tabBtnModes[2].setBackground( new Color(255, 237, 213) ); this.tabBtnModes[2].setForeground( new Color(237, 109, 39)  );
			
			this.tabBtnModes[0].setFont(new Font("Arial", Font.BOLD , 16));
			this.tabBtnModes[1].setFont(new Font("Arial", Font.PLAIN, 13));
			this.tabBtnModes[2].setFont(new Font("Arial", Font.PLAIN, 13));
		}

		if ( e.getSource() == this.tabBtnModes[1] )
		{ 
			this.tabBtnModes[0].setBackground( new Color(255, 237, 213) ); this.tabBtnModes[0].setForeground( new Color(237, 109, 39)  );
			this.tabBtnModes[1].setBackground( new Color(249, 115, 22)  ); this.tabBtnModes[1].setForeground( Color.WHITE );
			this.tabBtnModes[2].setBackground( new Color(255, 237, 213) ); this.tabBtnModes[2].setForeground( new Color(237, 109, 39)  );
		
			this.tabBtnModes[0].setFont(new Font("Arial", Font.PLAIN, 13));
			this.tabBtnModes[1].setFont(new Font("Arial", Font.BOLD , 16));
			this.tabBtnModes[2].setFont(new Font("Arial", Font.PLAIN, 13));
		}

		if ( e.getSource() == this.tabBtnModes[2] )
		{
			this.tabBtnModes[0].setBackground( new Color(255, 237, 213) ); this.tabBtnModes[0].setForeground( new Color(237, 109, 39)  );
			this.tabBtnModes[1].setBackground( new Color(255, 237, 213) ); this.tabBtnModes[1].setForeground( new Color(237, 109, 39)  );
			this.tabBtnModes[2].setBackground( new Color(249, 115, 22)  ); this.tabBtnModes[2].setForeground( Color.WHITE );
		
			this.tabBtnModes[0].setFont(new Font("Arial", Font.PLAIN, 13));
			this.tabBtnModes[1].setFont(new Font("Arial", Font.PLAIN, 13));
			this.tabBtnModes[2].setFont(new Font("Arial", Font.BOLD , 16));
		}

		if ( e.getSource() == this.btnLancer )
		{
			String fichierSelectionne = this.lstSauvegardes.getSelectedValue();

			if (fichierSelectionne == null || fichierSelectionne.equals("Aucune sauvegarde")) 
			{

				return; 
			}

			this.btnLancer.setBackground(new Color(252, 200, 122));
			this.btnLancer.setForeground( Color.WHITE );

	
			SwingUtilities.getWindowAncestor(this).setVisible(false);
			File fichierMap = new File("./src/ressource/data/" + fichierSelectionne); 
	
			metier.EModes modeChoisi = metier.EModes.values()[0];
				
			this.ctrl.lancerPartie(fichierMap, 1, modeChoisi, false);

		}


		if ( e.getSource() == this.tabBtnModes[0] ) 
		{ 			
			this.panelDroiteJoueur.setVisible(false);
		}

		if ( e.getSource() == this.tabBtnModes[1] ) 
		{ 
			this.panelDroiteJoueur.setVisible(true);
		}

		if ( e.getSource() == this.tabBtnModes[2] )
		{
			this.panelDroiteJoueur.setVisible(true);
		}


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


	public void stateChanged(ChangeEvent e) 
	{
		if (e.getSource() == this.sbMusic) 
		{
			this.ctrl.setVolumeMusique(this.sbMusic.getValue());
		}
	}


	public void valueChanged(ListSelectionEvent e) 
	{
		if (!e.getValueIsAdjusting()) 
		{
			String fichierSelectionne = this.lstSauvegardes.getSelectedValue();
			
			if (fichierSelectionne != null && !fichierSelectionne.equals("Aucune sauvegarde")) 
			{
				this.ctrl.LancerFramePrevisu(fichierSelectionne);
				System.out.println("lancement framePREVISU");
				
			}
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



	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		if (this.imgFond != null) { g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getHeight(), this) ;}
	}

}


// --  Couleur --
/*
ORANGE click 
new Color(249, 115, 22) //fond

ORANGE pas click
new Color(255, 237, 213) // fond

VERT click
(34, 197, 94) //fond
blanc //txt


VERT pas click
(220, 252, 231)//fond
(22, 163, 74) //txt


*/
