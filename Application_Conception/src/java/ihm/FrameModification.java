package ihm;

import controleur.Controleur;


import javax.swing.*;
import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* FrameModification
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class FrameModification extends JFrame
{
    public FrameModification( Controleur ctrl)
    {
        
        this.setTitle("Application modification - Groupe 4");
		this.setSize(1200, 120);
		this.setLocation(50, 100);

		
		this.add( new PanelModification( ctrl ));


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }



}