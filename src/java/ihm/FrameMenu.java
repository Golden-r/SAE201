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

        this.setTitle("Application jeu - Groupe 4");
        this.setSize(700, 500);
        

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int positionY = (tailleEcran.height - this.getHeight()) / 2;

        this.setLocation( 0 , positionY);


        this.add(new PanelMenu (ctrl ));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
