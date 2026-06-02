package ihm;

import metier.ESymbole;
import metier.ECouleur;
import metier.Plateau;

import javax.swing.*;
import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* FrameConception
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class FrameConception extends JFrame
{

    public FrameConception()
    {

        this.setTitle("Application conception - Groupe 4");
		this.setSize(500, 200);
		this.setLocation(400, 400);

		
		this.add(new PanelConception());


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    

}