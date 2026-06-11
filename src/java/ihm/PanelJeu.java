package ihm;

import controleur.Controleur;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class PanelJeu extends JPanel
{
	private Controleur   ctrl;
	private PanelPlateau plateauJeuMilieu; // Le plateau au milieu

	private JPanel panelNord;
	private JPanel panelSud;
	private JPanel panelEst;
	private JPanel panelOuest;
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
		
		// On crée le plateau du milieu en lui passant le controleur
		this.plateauJeuMilieu = new PanelPlateau(this.ctrl);

		this.panelNord  = new JPanel();
		this.panelSud   = new JPanel();
		this.panelEst   = new JPanel();
		this.panelOuest = new JPanel();
        
		this.panelNord .setBackground( Color.DARK_GRAY );
		this.panelSud  .setBackground( Color.DARK_GRAY );
		this.panelEst  .setBackground( Color.DARK_GRAY );
		this.panelOuest.setBackground( Color.DARK_GRAY );

		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */
		
		this.add( this.plateauJeuMilieu, BorderLayout.CENTER );	
		this.add( this.panelNord,        BorderLayout.NORTH  );
		this.add( this.panelSud,         BorderLayout.SOUTH  );
		this.add( this.panelEst,         BorderLayout.EAST   );
		this.add( this.panelOuest,        BorderLayout.WEST   );
	}
}