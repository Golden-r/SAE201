package ihm;

import controleur.Controleur;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* SAE 2.01 | Développement d'une application 
* PanelPlateau
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelPlateau extends JPanel
{
	private Controleur ctrl;

	private int hoveredLig = -1;
	private int hoveredCol = -1;


	public PanelPlateau( Controleur ctrl ) 
	{
		this.ctrl  = ctrl ;

		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		int largeurTotal = this.ctrl.getTailleLongueur() * this.ctrl.getTailleCellule();
		int hauteurTotal = this.ctrl.getTailleLargeur () * this.ctrl.getTailleCellule();

		this.setPreferredSize( new Dimension( largeurTotal, hauteurTotal ) );
		this.setBackground( Color.WHITE );
		
		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
	

		GereSouris gereSouris = new GereSouris();
		this.addMouseMotionListener(gereSouris);
		this.addMouseListener(gereSouris);
	}


	/*-----------------------------------------*/
	/* Définition de la classe interne Adapter */
	/*-----------------------------------------*/
	private class GereSouris extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			int taille = ctrl.getTailleCellule();
			int col = e.getX() / taille;
			int lig = e.getY() / taille;

			if (lig >= 0 && lig < ctrl.getTailleLargeur() && col >= 0 && col < ctrl.getTailleLongueur()) 
			{
				ctrl.clicSurCase(col, lig);
				repaint();
			}
				


		}

		public void mouseMoved(MouseEvent e) 
		{
			int taille = ctrl.getTailleCellule();
			int col = e.getX() / taille;
			int lig = e.getY() / taille;


			if (lig >= 0 && lig < ctrl.getTailleLargeur() && col >= 0 && col < ctrl.getTailleLongueur()) 
			{
				if (hoveredLig != lig || hoveredCol != col) 
				{
					hoveredLig = lig;
					hoveredCol = col;
				}
			} 
			else 
			{
				if (hoveredLig != -1 || hoveredCol != -1) 
				{
					hoveredLig = -1;
					hoveredCol = -1;
				}
			}
			repaint();
		}


		public void mouseExited(MouseEvent e) 
		{
			hoveredLig = -1;
			hoveredCol = -1;
			repaint();
		}
	}



	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int taille = this.ctrl.getTailleCellule(); 

		for (int lig = 0; lig < this.ctrl.getTailleLargeur(); lig++) 
			for (int col = 0; col < this.ctrl.getTailleLongueur(); col++) 
			{
				int x = col * taille;
				int y = lig * taille;
				


				metier.Cellule cell = this.ctrl.getCellule(col, lig);
                
                if (cell != null && cell.getZone() != null) 
                {
                    g.setColor(cell.getZone().getCouleur());
                    g.fillRect(x, y, taille, taille);
                }



				if (lig == this.hoveredLig && col == this.hoveredCol) 
				{
					g.setColor(new Color(100, 150, 255, 120)); 
					g.fillRect(x, y, taille, taille);
				}



				g.setColor(Color.BLACK);
				g.drawRect(x, y, taille, taille); 
			}

	}

}