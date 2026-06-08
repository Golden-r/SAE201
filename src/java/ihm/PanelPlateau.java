package ihm;

import controleur.Controleur;

//
import metier.Zone;
import metier.ESymbole;
//

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
	private PanelModification panelModification;

	private int hoveredLig = -1;
	private int hoveredCol = -1;

	public PanelPlateau( Controleur ctrl, PanelModification panelModification ) 
	{
		this.ctrl  = ctrl ;
		this.panelModification = panelModification;
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


	private boolean estDansPlateau(int col, int lig) 
	{
		return col >= 0 && col < this.ctrl.getTailleLongueur() && lig >= 0 && lig < this.ctrl.getTailleLargeur();
	}

	/*-----------------------------------------*/
	/* Définition de la classe interne Adapter */
	/*-----------------------------------------*/
	private class GereSouris extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				int taille = ctrl.getTailleCellule();
				int col = e.getX() / taille;
				int lig = e.getY() / taille;
				//
				// TODO : recoder la logique pour verifier si on est dans l'etape placement des zones.
				if (PanelPlateau.this.panelModification.estEtapeZone()) 
				//
				{

					if (PanelPlateau.this.estDansPlateau(col, lig)) 
					{
						if (PanelPlateau.this.panelModification.getModeSelection())
							PanelPlateau.this.ctrl.setZoneCourante( ctrl.clicSurCase(col, lig, (PanelPlateau.this.ctrl.getZoneCourante() == null ? null : PanelPlateau.this.ctrl.getZoneCourante()))) ;
						else
							ctrl.reinitialiserCellule(col, lig);

						
					}
				}
				else 
				{
					//
					if (PanelPlateau.this.panelModification.getModePlacementSymbole()) 
					{
						ESymbole sym = PanelPlateau.this.panelModification.getSymboleSelectionner();
						if (sym != null) 
						{
							ctrl.clicSurCaseSymbole(col, lig, sym);
						}
					} 
					else
					{
						ctrl.retirerSymbole(col, lig);
					}
					//
				}
				repaint();
			}
			
			if (e.getButton() == MouseEvent.BUTTON3)
				ctrl.setZoneCourante(null);

		}

		public void mouseMoved(MouseEvent e) 
		{
			int taille = ctrl.getTailleCellule();
			int col = e.getX() / taille;
			int lig = e.getY() / taille;


			if (PanelPlateau.this.estDansPlateau(col, lig)) 
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
				
                if (this.ctrl.getCellule(col, lig) != null && this.ctrl.getCellule(col, lig).getZone() != null) 
                {
                    g.setColor(this.ctrl.getCellule(col, lig).getZone().getCouleur());
                    g.fillRect(x, y, taille, taille);
                }

				// 
				if (this.ctrl.getCellule(col, lig) != null && this.ctrl.getCellule(col, lig).getSymbole() != null) 
				{
					g.setColor(Color.BLACK); 
					g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, taille / 2));
					String lettre = String.valueOf(this.ctrl.getCellule(col, lig).getSymbole().getSymbole().getNom());
					g.drawString(lettre, x + (taille / 3), y + (taille / 2) + (taille / 6));
				}
				//


				if (lig == this.hoveredLig && col == this.hoveredCol) 
				{
					g.setColor(new Color(100, 150, 255, 120)); 
					g.fillRect(x, y, taille, taille);
				}



				g.setColor(Color.BLACK);
				g.drawRect(x, y, taille, taille); 
			}


		// Dessin des liaisons (trajets)

		// TODO : Apres le symbole on initialize les bases
		
		for (metier.Liaison l : this.ctrl.getEnsLiaisons())
		{
			g.setColor(new Color(0, 0, 0, 120));
			g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, Math.max(10, taille / 4)));

			java.util.ArrayList<metier.Cellule> traversees = l.getCelluleTraversees();
			if (traversees == null || traversees.isEmpty()) continue;

			for (int i = 0; i < traversees.size(); i++)
			{
				metier.Cellule c = traversees.get(i);
				if (c == null) continue;
				int cx = c.getX() * taille;
				int cy = c.getY() * taille;
				g.setColor(new Color(0, 0, 0, 120));
				
				// Petit rectangle “câble” centré dans la cellule
				int pad = Math.max(3, taille / 8);
				int w = taille - 2 * pad;
				g.fillRect(cx + pad, cy + pad, w, w);
			}
		}
			//
		
	}

}