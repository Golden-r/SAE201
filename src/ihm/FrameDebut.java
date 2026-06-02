package ihm;


import javax.swing.*;
import java.awt.*;

/* SAE 2.01 | Développement d'une application 
* FrameDebut
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class FrameDebut extends JFrame
{
    public FrameDebut()
    {
        
        this.setTitle("Application conception - Groupe 4");
		this.setSize(1200, 120);
		this.setLocation(50, 100);

		
		this.add(new PanelDebut());


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }



}