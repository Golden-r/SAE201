package ihm;

import controleur.Controleur;
import metier.Cellule;
import metier.Plateau;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelPlateau extends JPanel 
{
	private Controleur ctrl;
	private Plateau    plateauPrevisu;

	// Constructeur pour le JEU
	public PanelPlateau(Controleur ctrl) 
	{
		this.ctrl           = ctrl;
		this.plateauPrevisu = null;

		int largeurTotal = this.getTailleLongueur() * this.getTailleCellule();
		int hauteurTotal = this.getTailleLargeur()  * this.getTailleCellule();
		this.setPreferredSize(new Dimension(largeurTotal, hauteurTotal));
		this.setBackground(Color.WHITE);
	}

	// Constructeur pour la PREVISU
	public PanelPlateau(Plateau plateauPrevisu) 
	{
		this.ctrl           = null;
		this.plateauPrevisu = plateauPrevisu;

		int largeurTotal = this.getTailleLongueur() * this.getTailleCellule();
		int hauteurTotal = this.getTailleLargeur()  * this.getTailleCellule();
		this.setPreferredSize(new Dimension(largeurTotal, hauteurTotal));
		this.setBackground(Color.WHITE);
	}

	/*-------------------------------------------------*/
	/* MÉTHODES PASSE-PLAT                             */
	/*-------------------------------------------------*/
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

	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int taille = this.getTailleCellule(); 

		/* --------------------------------------------------- */
		/* COUCHE 1 : DESSIN DES ZONES DE FOND ET DES CONTOURS */
		/* --------------------------------------------------- */
		for (int lig = 0; lig < this.getTailleLargeur(); lig++) 
		{
			for (int col = 0; col < this.getTailleLongueur(); col++) 
			{
				int x = col * taille;
				int y = lig * taille;

				Cellule c = this.getCellule(col, lig);

				// Dessin des zones de fond
				if (c != null && c.getZone() != null) 
				{
					String nomFichier = c.getZone().getTypeZone().getNomImageBase();

					String chemin = "./src/ressource/images/Zones/" + nomFichier;
					Image imgZone = new ImageIcon(chemin).getImage();
					g.drawImage(imgZone, x, y, taille, taille, null);
				}

				// Dessin des contours de cases
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x, y, taille, taille); 
			}
		}

		/* --------------------------------------------------- */
		/* COUCHE 2 : DESSIN DES LIAISONS                      */
		/* --------------------------------------------------- */
		for (int cptL = 0; cptL < this.getNbLiaisons(); cptL++) 
		{
			g.setColor(this.getCouleurLiaison(cptL));
			int[][] chemin = this.getCheminLiaison(cptL);

			if (chemin != null && chemin.length > 1) 
			{
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2.0f));

				for (int cptP = 0; cptP < chemin.length - 1; cptP++) 
				{
					int x1 = chemin[cptP][0]     * taille + (taille / 2);
					int y1 = chemin[cptP][1]     * taille + (taille / 2);
					int x2 = chemin[cptP + 1][0] * taille + (taille / 2);
					int y2 = chemin[cptP + 1][1] * taille + (taille / 2);
					
					g2.setColor(Color.BLACK);
					g2.drawLine(x1, y1, x2, y2);
				}
				g2.setStroke(new BasicStroke(1.0f));
			}
		}

		/* --------------------------------------------------- */
		/* COUCHE 3 : DESSIN DES BÂTIMENTS ET SYMBOLES         */
		/* --------------------------------------------------- */
		for (int lig = 0; lig < this.getTailleLargeur(); lig++) 
		{
			for (int col = 0; col < this.getTailleLongueur(); col++) 
			{
				int x = col * taille;
				int y = lig * taille;

				Cellule c = this.getCellule(col, lig);

				if (c != null && c.getSymbole() != null) 
				{
					String nomFichier = c.getSymbole().getTypeSymbole().getLibelle() + ".png";
					String chemin = "./src/ressource/images/Symboles/" + nomFichier;

					ImageIcon icon = new ImageIcon(chemin);
					Image rawImg = icon.getImage();

					BufferedImage img = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
					Graphics2D tg = img.createGraphics();
					tg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					tg.drawImage(rawImg, 0, 0, taille, taille, null);
					tg.dispose();

					Color coulBase = null;
					if (c.getSymbole().getCouleurBase() != null) 
					{
						coulBase = c.getSymbole().getCouleurBase().getCouleur();
					}

					if (coulBase != null) 
					{
						BufferedImage tintedImg = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
						Graphics2D gTint = tintedImg.createGraphics();
						gTint.drawImage(img, 0, 0, null);
						gTint.setComposite(AlphaComposite.SrcAtop);
						Color highlightColor = new Color(coulBase.getRed(), coulBase.getGreen(), coulBase.getBlue(), 175);
						gTint.setColor(highlightColor);
						gTint.fillRect(0, 0, taille, taille);
						gTint.dispose();

						g.drawImage(tintedImg, x, y, null);
					} 
					else 
					{
						g.drawImage(img, x, y, null);
					}
				}
			}
		}
	}
}
