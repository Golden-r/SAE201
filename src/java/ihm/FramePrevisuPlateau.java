package ihm;

import controleur.Controleur;
import metier.Plateau;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FramePrevisuPlateau extends JFrame 
{
    private PanelPlateau panelActuel;

    public FramePrevisuPlateau(Controleur ctrl, Plateau plateauPrevisu) 
    {

        this.setTitle("Prévisualisation de la carte");
        this.setSize(1100, 1100); 

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int posX = tailleEcran.width - this.getWidth() - 50; 
        int posY = (tailleEcran.height - this.getHeight()) / 2; 
		this.setLocation(posX, posY);

		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

        this.panelActuel = new PanelPlateau(plateauPrevisu);
		this.add(this.panelActuel, BorderLayout.CENTER);

        this.setVisible(true);
    }

	public void majPlateau(Plateau nouveauPlateau) 
	{
		if (this.panelActuel != null) 
		{
			this.remove(this.panelActuel);
		}

		this.panelActuel = new PanelPlateau(nouveauPlateau);
		this.add(this.panelActuel, BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
	}
}