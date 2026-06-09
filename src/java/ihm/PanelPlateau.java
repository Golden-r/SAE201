package ihm;

import controleur.Controleur;

//
import metier.Zone;
import metier.ECouleur;
import metier.ESymbole;
//

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
			int taille = ctrl.getTailleCellule();
			int col = e.getX() / taille;
			int lig = e.getY() / taille;
			
			
			if (!PanelPlateau.this.estDansPlateau(col, lig)) return; // si c'est pas dans le plateau

			if ( PanelPlateau.this.ctrl.getEtapeConception() == 1 ) //etape selection zone 
			{
				
				if (e.getButton() == MouseEvent.BUTTON1)
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

				if (e.getButton() == MouseEvent.BUTTON3) { ctrl.setZoneCourante(null) ;}
				
			}				
				
			if ( PanelPlateau.this.ctrl.getEtapeConception() == 2 )
			{
				if (PanelPlateau.this.panelModification.getModePlacementSymbole()) 
				{
					ESymbole sym = PanelPlateau.this.panelModification.getSymboleSelectionne();
					if (sym != null) 
					{
						ctrl.clicSurCaseSymbole(col, lig, sym);
					}
				} 
				else
				{
					ctrl.retirerSymbole(col, lig);
				}
				
				PanelPlateau.this.panelModification.majBtnSuivant() ;
			}

			if ( PanelPlateau.this.ctrl.getEtapeConception() == 3 ) //etape selection base
			{
				if ( PanelPlateau.this.panelModification.getModePlacementBase() ) 
				{
					ECouleur base = PanelPlateau.this.panelModification.getBaseSelectione();

					if (base != null) 
					{
						ctrl.clicSurCaseBase(col, lig, base);
					}
				}
				else
				{
					ctrl.retirerBase(col, lig);
				}
				
				PanelPlateau.this.panelModification.majBtnSuivant() ;
			}
			
				
			
			repaint();
		}
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

					String nomFichier = this.ctrl.getCellule(col, lig).getSymbole().getTypeSymbole().getLibelle() + ".png";
					String chemin = "./src/ressource/images/Symboles/" + nomFichier;

					int size = this.ctrl.getTailleCellule();
					ImageIcon icon = new ImageIcon(chemin);
					Image rawImg = icon.getImage();

					// on converti en bufferedimage
					BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

					Graphics2D tg = img.createGraphics();
					tg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					tg.drawImage(rawImg, 0, 0, size, size, null);
					tg.dispose();

					Color baseColor = null;
					if (this.ctrl.getCellule(col, lig).getSymbole().getCouleurBase() != null) {
						baseColor = this.ctrl.getCellule(col, lig).getSymbole().getCouleurBase().getCouleur();
					}

					if (baseColor != null) {
						BufferedImage tintedImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
						Graphics2D gTint = tintedImg.createGraphics();
						
						// 1) dessiner l'image originale
						gTint.drawImage(img, 0, 0, null);
						
						// 2) Changer le composite Change pour colorier que les pixels non transparents
						gTint.setComposite(AlphaComposite.SrcAtop);
						
						// on set la transparence aussi
						Color highlightColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255);
						gTint.setColor(highlightColor);
						gTint.fillRect(0, 0, size, size);
						gTint.dispose();

						// 3) ON dessine l'image tintée finale
						g.drawImage(tintedImg, x, y, null);
					} 
					else
					{
						// pas de couleur de base, juste l'image
						g.drawImage(img, x, y, null);
					}
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