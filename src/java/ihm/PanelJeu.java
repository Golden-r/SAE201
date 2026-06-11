package ihm;
import controleur.Controleur;

import metier.Zone;
import metier.Cellule;
import metier.ECouleur;
import metier.ESymbole;
import metier.Plateau; // Import ajouté

import javax.swing.*;
import java.awt.GridLayout ;
import java.awt.event.* ;

import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
* Date     : 11/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelJeu extends JPanel
{
    private Controleur ctrl;
    private Plateau    plateauPrevisu; 

	private int hoveredLig = -1;
	private int hoveredCol = -1;
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl           = ctrl ; 
		this.plateauPrevisu = null ;

		int largeurTotal = this.getTailleLongueur() * this.getTailleCellule();
		int hauteurTotal = this.getTailleLargeur () * this.getTailleCellule();
        
		this.setPreferredSize( new Dimension( largeurTotal, hauteurTotal ) );
		this.setBackground( Color.WHITE );


	}

	public PanelJeu( Plateau plateauPrevisu ) 
	{
		this.ctrl           = null ; 
		this.plateauPrevisu = plateauPrevisu ;

		int largeurTotal = this.getTailleLongueur() * this.getTailleCellule();
		int hauteurTotal = this.getTailleLargeur () * this.getTailleCellule();
        
		this.setPreferredSize( new Dimension( largeurTotal, hauteurTotal ) );
		this.setBackground( Color.WHITE );
	}


	private int getTailleLongueur() 
	{
		if (this.plateauPrevisu != null) { return this.plateauPrevisu.getTailleLongueur() ;} 
		else                             { return this.ctrl.getTailleLongueur() ;}
	}
	private int getTailleLargeur() 
	{
		if (this.plateauPrevisu != null) { return this.plateauPrevisu.getTailleLargeur() ;} 
		else                             { return this.ctrl.getTailleLargeur() ;}
	}
	private int getTailleCellule() 
	{
		if (this.plateauPrevisu != null) { return this.plateauPrevisu.getTailleCellule() ;} 
		else                             { return this.ctrl.getTailleCellule() ;}
	}
	private Cellule getCellule(int col, int lig) 
	{
		if (this.plateauPrevisu != null) { return this.plateauPrevisu.getCellule(col, lig) ;} 
		else                             { return this.ctrl.getCellule(col, lig) ;}
	}
	private int getNbLiaisons() 
	{
		if (this.plateauPrevisu != null) { return this.plateauPrevisu.getEnsLiaison().size() ;} 
		else                             { return this.ctrl.getNbLiaisons() ;}
	}
	private Color getCouleurLiaison(int indice) 
	{
		if (this.plateauPrevisu != null) { return Color.BLACK ;} 
		else                             { return this.ctrl.getCouleurLiaison(indice) ;}
	}
	private int[][] getCheminLiaison(int indice) 
	{
		if (this.plateauPrevisu != null) { return null ;} 
		else                             { return this.ctrl.getCheminLiaison(indice) ;}
	}
	private boolean estDansPlateau(int col, int lig) 
	{
		return col >= 0 && col < this.getTailleLongueur() && lig >= 0 && lig < this.getTailleLargeur();
	}

	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int taille = this.getTailleCellule(); 

		for (int lig = 0; lig < this.getTailleLargeur(); lig++) 
			for (int col = 0; col < this.getTailleLongueur(); col++) 
			{
				int x = col * taille;
				int y = lig * taille;

				Cellule c = this.getCellule(col, lig) ;

                if ( c != null && c.getZone() != null ) 
                {
                    String nomFichier ;

                    if ( c.getSymbole() != null ) { nomFichier = c.getZone().getTypeZone().getNomImageBase() ;}
                    else                          { nomFichier = c.getZone().getTypeZone().getNomImage()     ;}

                    String chemin  = "./src/ressource/images/Zones/" + nomFichier ;
                    Image  imgZone = new ImageIcon(chemin).getImage() ;

                    g.drawImage( imgZone, x, y, taille, taille, null ) ;
                }

				if (this.getCellule(col, lig) != null && this.getCellule(col, lig).getSymbole() != null)
				{
					g.setColor(Color.BLACK);
					g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, taille / 2));

					String nomFichier = this.getCellule(col, lig).getSymbole().getTypeSymbole().getLibelle() + ".png";
					String chemin = "./src/ressource/images/Symboles/" + nomFichier;

					int size = this.getTailleCellule();
					ImageIcon icon = new ImageIcon(chemin);
					Image rawImg = icon.getImage();

					BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

					Graphics2D tg = img.createGraphics();
					tg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					tg.drawImage(rawImg, 0, 0, size, size, null);
					tg.dispose();

					Color coulBase = null;
					if (this.getCellule(col, lig).getSymbole().getCouleurBase() != null)
					{
						coulBase = this.getCellule(col, lig).getSymbole().getCouleurBase().getCouleur();
					}

					if (coulBase != null)
					{
						BufferedImage tintedImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
						Graphics2D gTint = tintedImg.createGraphics();
						
						gTint.drawImage(img, 0, 0, null);
						gTint.setComposite(AlphaComposite.SrcAtop);
						
						Color highlightColor = new Color(coulBase.getRed(), coulBase.getGreen(), coulBase.getBlue(), 175);
						gTint.setColor(highlightColor);
						gTint.fillRect(0, 0, size, size);
						gTint.dispose();

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


		// Dessin des liaisons
		for ( int cptL = 0 ; cptL < this.getNbLiaisons() ; cptL++ )
		{
			g.setColor( this.getCouleurLiaison( cptL ) ) ;

			int[][] chemin = this.getCheminLiaison( cptL ) ;

			if ( chemin != null && chemin.length > 1 )
			{
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2.0f));

				for ( int cptP = 0 ; cptP < chemin.length - 1 ; cptP++ )
				{
					int x1 = chemin[cptP][0]     * taille + ( taille / 2 ) ;
					int y1 = chemin[cptP][1]     * taille + ( taille / 2 ) ;
					int x2 = chemin[cptP + 1][0] * taille + ( taille / 2 ) ;
					int y2 = chemin[cptP + 1][1] * taille + ( taille / 2 ) ;
					
					g2.setColor(Color.BLACK) ;
					g2.drawLine( x1, y1, x2, y2 ) ;
				}
				g2.setStroke(new BasicStroke(1.0f));
			}
		}
	}
}