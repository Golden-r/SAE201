package ihm;
import controleur.Controleur;


import javax.swing.*;
import java.awt.*;

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
    
	public PanelJeu( Controleur ctrl ) 
	{
		this.ctrl = ctrl ; 
		
        this.setLayout(new GridLayout(3, 2));
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */
        
		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

        
	}

}
