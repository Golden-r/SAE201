package ihm;

import controleur.Controleur;


import javax.swing.*;
import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* FrameJeu
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class FrameMenu extends JFrame
{
	private Controleur ctrl;

	public FrameMenu( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.setTitle("Selection de mode de jeu - Groupe 4");
		this.setSize(this.ctrl.getSizeMenu());

		this.setLocation(1000, 10);

		
		this.add( new PanelMenu (ctrl) );


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

    }
}
