package ihm;

import controleur.Controleur;
import metier.ECouleur;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

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
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		
		this.plateauJeuMilieu = new PanelPlateau(this.ctrl);

		this.panelNord  = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15)); 
		this.panelSud   = new JPanel();
		this.panelEst   = new JPanel();
		this.panelOuest = new JPanel();

		this.panelNord .setBackground( Color.DARK_GRAY );
		this.panelSud  .setBackground( Color.DARK_GRAY );
		this.panelEst  .setBackground( Color.DARK_GRAY );
		this.panelOuest.setBackground( Color.DARK_GRAY );



		//panelNord
		int mancheCourante = 1;
		int mancheMax      = 1;
		String nomReseau   = "ReseauDefaut"; 

		if (this.ctrl.getPartie() != null) 
		{
			mancheCourante = this.ctrl.getPartie().getMancheCourante();
			mancheMax      = this.ctrl.getPartie().getNbMancheMax();
			
			if (this.ctrl.getPartie().getManche() != null && this.ctrl.getPartie().getManche().getJoueurCourant() != null)
			{
				ECouleur reseauActuel = this.ctrl.getPartie().getManche().getJoueurCourant().getreseau();
				if (reseauActuel != null) 
				{
					nomReseau = reseauActuel.name(); 
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
		
		this.add( this.plateauJeuMilieu, BorderLayout.CENTER );	
		this.add( this.panelNord,        BorderLayout.NORTH  );
		this.add( this.panelSud,         BorderLayout.SOUTH  );
		this.add( this.panelEst,         BorderLayout.EAST   );
		this.add( this.panelOuest,       BorderLayout.WEST   );
	}
}