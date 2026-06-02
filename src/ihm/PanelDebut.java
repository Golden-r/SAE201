package ihm;

import metier.ESymbole;
import metier.ECouleur;
import metier.Plateau;


import metier.ECouleur;
import metier.Zone;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JButton;


/* SAE 2.01 | Développement d'une application 
* PanelDebut
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelDebut extends JPanel 
{
    private JTextField tfL;
    private JTextField tfH;
    private JTextField tfT;

    private Plateau plateau;
	public PanelDebut() 
	{
        this.setLayout(new GridLayout(3, 2));
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
        this.tfL = new JTextField("7");
        this.tfH = new JTextField("7");
        this.tfT = new JTextField("40");


		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

        this.add(new JLabel("Largeur"));
        this.add(tfL);
        this.add(new JLabel("Hauteur"));
        this.add(tfH);
        this.add(new JLabel("Taille case (px)"));
        this.add(tfT);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

        int res = JOptionPane.showConfirmDialog(this.getTopLevelAncestor(), this, "Taille du plateau", JOptionPane.OK_CANCEL_OPTION);
        if (res != JOptionPane.OK_OPTION)
        {
            this.plateau = null; //si c nul on annule la création du plateau et on exit
            return;
        }
        int l = Integer.parseInt(this.tfL.getText());
        int h = Integer.parseInt(this.tfH.getText());
        int t = Integer.parseInt(this.tfT.getText());
        this.plateau = new Plateau(l, h, t);
	}

    public Plateau getPlateau()
    {
        return this.plateau;
    }
}

