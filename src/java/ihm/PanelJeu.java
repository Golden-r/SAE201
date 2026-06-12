package ihm;

import controleur.Controleur;
import metier.ECouleur;

import java.util.ArrayList;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import java.awt.Dimension;

public class PanelJeu extends JPanel implements ActionListener
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
	private JPanel panelCarteActive;
	private JPanel panelHistorique;

	private JButton btnPiocher ;
	private JButton btnMancheSuivant ;
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		
		JScrollPane scrollHistorique;
		JPanel panelConteneurHistorique;

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		
		this.plateauJeuMilieu = new PanelPlateau(this.ctrl);

		this.panelNord  = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15)); 
		this.panelEst   = new JPanel();
		this.panelEst  .setBackground( PanelJeu.COULEUR_BLEU );
		this.panelNord .setBackground( PanelJeu.COULEUR_BLEU );



		this.panelOuest = new JPanel(new BorderLayout());
		this.panelOuest.setBackground( PanelJeu.COULEUR_BLEU );

		this.panelCarteActive = new JPanel();
		this.panelCarteActive.setBackground(PanelJeu.COULEUR_BLEU);
		this.lblCarteActive = new JLabel();

		panelConteneurHistorique = new JPanel(new BorderLayout());
		panelConteneurHistorique.setBackground(PanelJeu.COULEUR_BLEU);

		this.panelHistorique = new JPanel(new GridLayout(0, 1, 0, 10));
		this.panelHistorique.setBackground(PanelJeu.COULEUR_BLEU);

		panelConteneurHistorique.add(this.panelHistorique, BorderLayout.NORTH);

		scrollHistorique = new JScrollPane(this.panelHistorique);
		scrollHistorique.setPreferredSize(new Dimension(120, 200));



		//panelSud 
		this.panelSud   = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
		this.panelSud  .setBackground( PanelJeu.COULEUR_BLEU );

		this.btnPiocher       = new JButton("Piocher une carte");
		this.btnMancheSuivant = new JButton("Manche suivant"   );
		this.btnMancheSuivant.setEnabled(false);



		//panelNord
		int mancheCourante = this.ctrl.getNumeroMancheCourante();
		int mancheMax      = this.ctrl.getMancheMax();
		String nomReseau   = this.ctrl.getNomReseauJoueurCourant();


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

		this.panelCarteActive.add(this.lblCarteActive);

		this.panelOuest.add( this.panelCarteActive, BorderLayout.NORTH);
		this.panelOuest.add(scrollHistorique, BorderLayout.CENTER);

		this.panelSud.add( this.btnPiocher);
		this.panelSud.add( this.btnMancheSuivant);
		
		this.add( this.plateauJeuMilieu, BorderLayout.CENTER );	
		this.add( this.panelNord,        BorderLayout.NORTH  );
		this.add( this.panelSud,         BorderLayout.SOUTH  );
		this.add( this.panelEst,         BorderLayout.EAST   );
		this.add( this.panelOuest,       BorderLayout.WEST   );

		/* ----------------------------- */
		/* Activation des composants     */
		/* ----------------------------- */
		this.btnPiocher.addActionListener(this);
		this.btnMancheSuivant.addActionListener(this);



		this.ajouterCarteTiree();
	}

	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == this.btnPiocher )
		{
			this.ctrl.piocherCarteCourante();
			
			this.ajouterCarteTiree();
			this.majPanelNord();

			if (this.ctrl.estMancheTerminee() ){ this.finDeManche(); }
		}
		
		if ( e.getSource() == this.btnMancheSuivant )
		{
			this.ctrl.passerALaMancheSuivante();

			this.btnPiocher      .setEnabled(true  );
			this.btnMancheSuivant.setEnabled(false );

			this.majPanelNord();
			this.reinitPanelCarte();
			
			
		}

	}

	public void finDeManche ()
	{
		if ( this.ctrl.getNumeroMancheCourante() == this.ctrl.getMancheMax())
		{
			this.btnMancheSuivant.setEnabled(false );
		}

		this.btnPiocher      .setEnabled(false );
		this.btnMancheSuivant.setEnabled(true );

	}

	public void majPanelNord()
	{
		/*----------------*/
		/* Données        */
		/*----------------*/

		int       mancheCourante    ;
		int       mancheMax         ;
		String    nomReseau         ;
		String    cheminImage       ;
		ImageIcon iconOrigine       ;
		Image     imgRedimensionnee ;

		/*----------------*/
		/* Instructions   */
		/*----------------*/

		mancheCourante = this.ctrl.getNumeroMancheCourante();
		mancheMax      = this.ctrl.getMancheMax();
		nomReseau      = this.ctrl.getNomReseauJoueurCourant();

		this.lblManche.setText("Manche " + mancheCourante + " / " + mancheMax);

		cheminImage = "./src/ressource/images/Reseaux/" + nomReseau + ".png";
		
		iconOrigine       = new ImageIcon(cheminImage);
		imgRedimensionnee = iconOrigine.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		
		this.lblImageReseau.setIcon(new ImageIcon(imgRedimensionnee));
	}


	public void reinitPanelCarte()
	{
		this.panelHistorique.removeAll();
		this.panelCarteActive.removeAll();
		this.panelHistorique.revalidate();
		this.panelHistorique.repaint();
	}

	public void ajouterCarteTiree() 
	{
		/*----------------*/
		/* Données        */
		/*----------------*/

		String            cheminActive ;
		ArrayList<String> historique   ;
		JLabel            lblMiniCarte ;

		/*----------------*/
		/* Instructions   */
		/*----------------*/

		cheminActive = this.ctrl.getCheminImageCarteActive();
		if (cheminActive != null) { this.lblCarteActive.setIcon(redimensionnerImage(cheminActive, 100, 150)) ;}
		else                      { this.lblCarteActive.setIcon(null) ;}

		this.panelHistorique.removeAll(); 

		historique = this.ctrl.getCheminsHistoriqueCartes();

		for (int cpt = 0; cpt < historique.size(); cpt++)
		{
			lblMiniCarte = new JLabel(redimensionnerImage(historique.get(cpt), 60, 90));
			this.panelHistorique.add(lblMiniCarte); 
		}
		
		this.panelHistorique.revalidate();
		this.panelHistorique.repaint();
	}

	private ImageIcon redimensionnerImage(String chemin, int w, int h) 
	{
		ImageIcon icon = new ImageIcon(chemin);
		return new ImageIcon(icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}
}