package ihm;

import controleur.Controleur;

//
import metier.Zone;
import metier.ESymbole;
//

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
				

				if ( PanelPlateau.this.ctrl.getEtapeConception() == 1 )  
				{

					if (PanelPlateau.this.estDansPlateau(col, lig)) 
					{

						if ( PanelPlateau.this.panelModification.getModeSelection() )
						{
							PanelPlateau.this.ctrl.setZoneCourante( ctrl.clicSurCase(col, lig, (PanelPlateau.this.ctrl.getZoneCourante() == null ? null : PanelPlateau.this.ctrl.getZoneCourante()))) ;
						}
						else
						{
							ctrl.reinitialiserCellule(col, lig);
						}
							
						PanelPlateau.this.panelModification.mettreAJourNbZones();
						
					}
				}
				else 
				{
					if ( PanelPlateau.this.ctrl.getEtapeConception() == 2 )
					{
					
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
						
					}
					else
					{

					}
				}



				repaint();
			}
			
			if (e.getButton() == MouseEvent.BUTTON3) { ctrl.setZoneCourante(null) ;}

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

				
				if (this.ctrl.getCellule(col, lig) != null && this.ctrl.getCellule(col, lig).getSymbole() != null) 
				{
					g.setColor(Color.BLACK); 
					g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, taille / 2));
					
					
					String nomFichier = this.ctrl.getCellule(col, lig).getSymbole().getTypeSymbole().getNom() + ".png" ;
					String chemin     = "./src/ressource/images/" + nomFichier;

					Image image = new ImageIcon(chemin).getImage();
					
					g.drawImage(image, x , y,this.ctrl.getTailleCellule() , this.ctrl.getTailleCellule() , null);
					
					//g.drawImage()					String lettre = String.valueOf(this.ctrl.getCellule(col, lig).getSymbole().getTypeSymbole().getNom());
					//g.drawString(lettre, x + (taille / 3), y + (taille / 2) + (taille / 6));
				}
				


				if (lig == this.hoveredLig && col == this.hoveredCol) 
				{
					g.setColor(new Color(100, 150, 255, 120)); 
					g.fillRect(x, y, taille, taille);
				}



				g.setColor(Color.BLACK);
				g.drawRect(x, y, taille, taille); 
			}


		// Dessin des liaisons (trajets)
	
		for ( int cptL = 0 ; cptL < this.ctrl.getNbLiaisons() ; cptL++ )
		{
			g.setColor( this.ctrl.getCouleurLiaison( cptL ) ) ;

			int[][] chemin = this.ctrl.getCheminLiaison( cptL ) ;

			if ( chemin != null && chemin.length > 1 )
			{
				for ( int cptP = 0 ; cptP < chemin.length - 1 ; cptP++ )
				{
					int x1 = chemin[cptP][0]     * taille + ( taille / 2 ) ;
					int y1 = chemin[cptP][1]     * taille + ( taille / 2 ) ;
					int x2 = chemin[cptP + 1][0] * taille + ( taille / 2 ) ;
					int y2 = chemin[cptP + 1][1] * taille + ( taille / 2 ) ;

					g.drawLine( x1, y1, x2, y2 ) ;
				}
			}
		}
		
	}

}