package ihm;

import controleur.Controleur;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

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

	public PanelPlateau( Controleur ctrl ) 
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

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int taille = this.ctrl.getTailleCellule(); 
		
		g.setColor(Color.BLACK);

		for (int lig = 0; lig < this.ctrl.getTailleLargeur(); lig++) 
		{
			for (int col = 0; col < this.ctrl.getTailleLongueur(); col++) 
			{
				int x = col * taille;
				int y = lig * taille;
				
				g.drawRect(x, y, taille, taille); //contour
			}
		}
	}
	

}











