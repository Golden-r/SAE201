package ihm;

import controleur.Controleur;
import metier.ECouleur;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import java.awt.Dimension;

public class PanelJeu extends JPanel
{
	private static final Color    COULEUR_BLEU = new Color (24, 32, 118);

	private Controleur   ctrl;
	private PanelPlateau plateauJeuMilieu;

	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelEst;
	private JPanel panelOuest;

	private JLabel lblManche ;
	private JLabel lblImageReseau ;

	private JLabel lblCarteActive;
	private JPanel panelHistorique;
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		JPanel panelCarteActive;
		JScrollPane scrollHistorique;

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		
		this.plateauJeuMilieu = new PanelPlateau(this.ctrl);

		this.panelNord  = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15)); 
		this.panelSud   = new JPanel();
		this.panelEst   = new JPanel();
		this.panelSud  .setBackground( PanelJeu.COULEUR_BLEU );
		this.panelEst  .setBackground( PanelJeu.COULEUR_BLEU );
		this.panelNord .setBackground( PanelJeu.COULEUR_BLEU );



		this.panelOuest = new JPanel(new BorderLayout());
		this.panelOuest.setBackground( PanelJeu.COULEUR_BLEU );

		panelCarteActive = new JPanel();
		panelCarteActive.setBackground(Color.DARK_GRAY);
		this.lblCarteActive = new JLabel();

		this.panelHistorique = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.panelHistorique.setBackground(Color.GRAY);
		scrollHistorique = new JScrollPane(this.panelHistorique);
		scrollHistorique.setPreferredSize(new Dimension(120, 200));

		


		//panelNord
		int mancheCourante = 1;
		int mancheMax      = 1;
		String nomReseau   = "Eau_potable"; 

		if (this.ctrl.getPartie() != null) 
		{
			mancheCourante = this.ctrl.getPartie().getMancheCourante();
			mancheMax      = this.ctrl.getPartie().getNbMancheMax();
			
			if (this.ctrl.getPartie().getManche() != null && this.ctrl.getPartie().getManche().getJoueurCourant() != null)
			{
				ECouleur reseauActuel = this.ctrl.getPartie().getManche().getJoueurCourant().getreseau();
				if (reseauActuel != null) 
				{
					nomReseau = reseauActuel.getLibelle(); 

				}
			}
		}

		this.lblManche = new JLabel("Manche " + mancheCourante + " / " + mancheMax);
		this.lblManche.setForeground(Color.WHITE);
		this.lblManche.setFont(new Font("Arial", Font.BOLD, 22));

		String cheminImage = "./src/ressource/images/Reseaux/" + nomReseau + ".png";
		
		ImageIcon iconOrigine = new ImageIcon(cheminImage);
		Image imgRedimensionnee = iconOrigine.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		this.lblImageReseau = new JLabel(new ImageIcon(imgRedimensionnee));


		this.panelNord.add(this.lblManche);
		this.panelNord.add(this.lblImageReseau);



		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		panelCarteActive.add(this.lblCarteActive);

		this.panelOuest.add(panelCarteActive, BorderLayout.NORTH);
		this.panelOuest.add(scrollHistorique, BorderLayout.CENTER);

		
		this.add( this.plateauJeuMilieu, BorderLayout.CENTER );	
		this.add( this.panelNord,        BorderLayout.NORTH  );
		this.add( this.panelSud,         BorderLayout.SOUTH  );
		this.add( this.panelEst,         BorderLayout.EAST   );
		this.add( this.panelOuest,       BorderLayout.WEST   );
	}


	public void ajouterCarteTiree() 
	{
		String chemin = this.ctrl.getCheminImageCarteActive();
		JLabel lblMiniCarte;
		
		if (chemin != null) 
		{
			this.lblCarteActive.setIcon(redimensionnerImage(chemin, 100, 150));

			lblMiniCarte = new JLabel(redimensionnerImage(chemin, 30, 45));
			this.panelHistorique.add(lblMiniCarte);
			
			this.panelHistorique.revalidate();
			this.panelHistorique.repaint();
		}
	}

	private ImageIcon redimensionnerImage(String chemin, int w, int h) 
	{
		ImageIcon icon = new ImageIcon(chemin);
		return new ImageIcon(icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}
}