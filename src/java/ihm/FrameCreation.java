package ihm;


import controleur.Controleur;

import metier.ESymbole;
import metier.ECouleur;
import metier.Plateau;

import javax.swing.*;
import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* FrameCreation
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class FrameCreation extends JFrame
{

    public FrameCreation( Controleur ctrl )
    {

        this.setTitle("Application création - Groupe 4 - ");
		this.setSize(1200, 120);
		this.setLocation(50, 100);

		
		this.add( new PanelCreation( ctrl ) );


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    

}