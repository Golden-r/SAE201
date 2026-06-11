package ihm;

import controleur.Controleur;
import metier.Plateau;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FramePrevisu extends JFrame 
{
    private Controleur ctrl;
    private Plateau    plateauPrevisu;

    public FramePrevisu(Controleur ctrl, Plateau plateauPrevisu) 
    {
        this.ctrl           = ctrl;
        this.plateauPrevisu = plateauPrevisu;


        this.setTitle("Prévisualisation de la carte");
        this.setSize(1100, 1100); 

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int posX = tailleEcran.width - this.getWidth() - 50; 
        int posY = (tailleEcran.height - this.getHeight()) / 2; 
        
		this.setLocation(posX, posY);
        
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}