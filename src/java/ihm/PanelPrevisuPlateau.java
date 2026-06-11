package ihm;

import metier.Plateau;
import metier.Symbole;
import metier.Cellule;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

public class PanelPrevisuPlateau extends JPanel 
{
    private Plateau plateau;
	

    public PanelPrevisuPlateau(Plateau plateau) 
    {
        this.plateau = plateau;
        this.setBackground(new Color(245, 245, 245));
    }

    protected void paintComponent(Graphics g) 
    {

		/*----------------*/
		/*  Données       */
		/*----------------*/

		int nbLig , nbCol ; 
		int largeurCase , hauteurCase ; 
		Symbole s;
		Image img;

		/*----------------*/
		/*  Instructions  */
		/*----------------*/


        super.paintComponent(g);

        if (this.plateau == null) return;

        nbLig = this.plateau.getTailleLargeur();
        nbCol = this.plateau.getTailleLongueur();

        if (nbLig <= 0 || nbCol <= 0) return;

    	largeurCase = this.getWidth() / nbLig;
    	hauteurCase = this.getHeight() / nbCol;


        //dessin du plateau
        g.setColor(Color.LIGHT_GRAY);

        for (int x = 0; x <= nbLig; x++) 
        {
            g.drawLine(x * largeurCase, 0, x * largeurCase, this.getHeight());
        }
        for (int y = 0; y <= nbCol; y++) 
        {
            g.drawLine(0, y * hauteurCase, this.getWidth(), y * hauteurCase);
        }

        // dessin des symboles
        for (int x = 0; x < nbLig; x++) 
        {
            for (int y = 0; y < nbCol; y++) 
            {
                Cellule c = this.plateau.getCellule(x, y);

                if (c != null && c.getSymbole() != null) 
                {
                    String nomSymbole = c.getSymbole().toString(); 

                    String cheminImage = "./src/ressource/images/Symboles/" + nomSymbole + ".png";
                    
                    img = new ImageIcon(cheminImage).getImage();
                    g.drawImage(img, x * largeurCase + 5, y * hauteurCase + 5, largeurCase - 10, hauteurCase - 10, this);
                }
            }
        }
    }
}
