package ihm;
import controleur.Controleur;

//
import metier.Zone;
import metier.Cellule;
import metier.ECouleur;
import metier.ESymbole;
//

import javax.swing.*;
import java.awt.GridLayout ;
import java.awt.event.* ;

import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.AlphaComposite;

import java.awt.image.BufferedImage;

/* SAE 2.01 | Développement d'une application 
* PanelJeu
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelJeu extends JPanel
{
    private Controleur ctrl;

	private int hoveredLig = -1;
	private int hoveredCol = -1;
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl = ctrl ; 

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

        
	}

	private boolean estDansPlateau(int col, int lig) 
	{
		return col >= 0 && col < this.ctrl.getTailleLongueur() && lig >= 0 && lig < this.ctrl.getTailleLargeur();
	}

	/*-----------------------------------------*/
	/* Définition de la classe interne Adapter */
	/*-----------------------------------------*/
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int taille = this.ctrl.getTailleCellule(); 

		for (int lig = 0; lig < this.ctrl.getTailleLargeur(); lig++) 
			for (int col = 0; col < this.ctrl.getTailleLongueur(); col++) 
			{
				int x = col * taille;
				int y = lig * taille;



				Cellule c = this.ctrl.getCellule(col, lig) ;

                if ( c != null && c.getZone() != null ) 
                {
                    if ( this.ctrl.getPrevisu() ) // mode image des zone
                    {
                        String nomFichier ;

                        // Si un bâtiment ou une base est présent, on charge l'image modifiée
                        if ( c.getSymbole() != null ) { nomFichier = c.getZone().getTypeZone().getNomImageBase() ;}
                        else                          { nomFichier = c.getZone().getTypeZone().getNomImage()     ;}

                        String chemin  = "./src/ressource/images/Zones/" + nomFichier ;
                        Image  imgZone = new ImageIcon(chemin).getImage() ;

                        g.drawImage( imgZone, x, y, taille, taille, null ) ;
                    }
                    else // mode couleur des zone
                    {
                        g.setColor( c.getZone().getCouleur() ) ;
                        g.fillRect( x, y, taille, taille ) ;
                    }
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

					Color coulBase = null;
					if (this.ctrl.getCellule(col, lig).getSymbole().getCouleurBase() != null)
					{
						coulBase = this.ctrl.getCellule(col, lig).getSymbole().getCouleurBase().getCouleur();
					}

					if (coulBase != null)
					{
						BufferedImage tintedImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
						Graphics2D gTint = tintedImg.createGraphics();
						
						// 1) dessiner l'image originale
						gTint.drawImage(img, 0, 0, null);
						
						// 2) Changer le composite Change pour colorier que les pixels non transparents
						gTint.setComposite(AlphaComposite.SrcAtop);
						
						// on set la transparence aussi
						Color highlightColor = new Color(coulBase.getRed(), coulBase.getGreen(), coulBase.getBlue(), 175);
						gTint.setColor(highlightColor);
						gTint.fillRect(0, 0, size, size);
						gTint.dispose();

						// 3) ON dessine l'image tintée finale
						g.drawImage(tintedImg, x, y, null);
					} 
					else
					{
						g.drawImage(img, x, y, null);
					}
				}

				if (lig == this.hoveredLig && col == this.hoveredCol) 
				{
					g.setColor(new Color(100, 150, 255, 120)); 
					g.fillRect(x, y, taille, taille);
				}

				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x, y, taille, taille); 
			}


		// Dessin des liaisons (trajets)

		for ( int cptL = 0 ; cptL < this.ctrl.getNbLiaisons() ; cptL++ )
		{
			g.setColor( this.ctrl.getCouleurLiaison( cptL ) ) ;

			int[][] chemin = this.ctrl.getCheminLiaison( cptL ) ;

			if ( chemin != null && chemin.length > 1 )
			{
				// 1. On crée le contexte Graphics2D à partir de g
				Graphics2D g2 = (Graphics2D) g;
				
				// 2. On applique l'épaisseur de 5 pixels AVANT de dessiner
				g2.setStroke(new BasicStroke(2.0f));

				for ( int cptP = 0 ; cptP < chemin.length - 1 ; cptP++ )
				{
					int x1 = chemin[cptP][0]     * taille + ( taille / 2 ) ;
					int y1 = chemin[cptP][1]     * taille + ( taille / 2 ) ;
					int x2 = chemin[cptP + 1][0] * taille + ( taille / 2 ) ;
					int y2 = chemin[cptP + 1][1] * taille + ( taille / 2 ) ;
					
					// 3. On utilise g2 pour appliquer la couleur et dessiner la ligne
					g2.setColor(Color.BLACK) ;
					g2.drawLine( x1, y1, x2, y2 ) ;
				}
				
				// 4. On remet l'épaisseur par défaut à la fin du chemin
				g2.setStroke(new BasicStroke(1.0f));
			}
		}
		
	}


}
