package ihm;

import metier.Plateau;

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

    private Plateau plateau;

    public FrameDebut()
    {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }



}