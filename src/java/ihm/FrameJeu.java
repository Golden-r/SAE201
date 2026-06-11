package ihm;

import controleur.Controleur;


import javax.swing.*;
import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* FrameJeu
*
* Date     : 09/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class FrameJeu extends JFrame
{
	public FrameJeu( Controleur ctrl )
    {
        
        this.setTitle("Application jeu - Groupe 4");

		this.setSize(1200, 120);

		this.setLocationRelativeTo(null);

		
		this.add(new PanelJeu (ctrl ));


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }
}
