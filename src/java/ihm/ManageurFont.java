package ihm;

import javax.swing.*;
import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* SAE 2.01 | Développement d'une application 
* ManageurFont
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class ManageurFont
{
    // Un preload the Fonts
    private static Map<String, Font> mapFonts = new HashMap<>();

    public static Font getFont(String nom, float size)
    {
        try
        {
            if (!mapFonts.containsKey(nom))
            {
                // TODO : Compatibilité avec le putain de windows.
                // Youssif tu peux tester si ça marche toujours sur debian?

                /*
                CLASS ROOT TEST:
                file:/C:/Users/golden/Documents/IUT/SAE201/class/
                file:/C:/Users/golden/Documents/IUT/SAE201/class/
                null
                java.lang.RuntimeException: Font introuvable : font
                        at ihm.ManageurFont.getFont(ManageurFont.java:34)
                        at controleur.Controleur.retourneFont(Controleur.java:53)
                        at ihm.PanelMenu.chargerFont(PanelMenu.java:163)
                        at ihm.PanelMenu.<init>(PanelMenu.java:56)
                        at ihm.FrameMenu.<init>(FrameMenu.java:35)
                        at controleur.Controleur.lancerMenu(Controleur.java:65)
                        at controleur.Controleur.main(Controleur.java:78)
                */

                System.out.println("CLASS ROOT TEST:");
                System.out.println(ManageurFont.class.getResource("/"));
                System.out.println(ManageurFont.class.getClassLoader().getResource(""));
                System.out.println(ManageurFont.class.getResource("/ressource/font/font.ttf"));

                
                var is = ManageurFont.class.getClassLoader().getResourceAsStream("ressource/font/" + nom + ".ttf");                
                if (is == null)
                    throw new RuntimeException("Font introuvable : " + nom);

                Font base = Font.createFont(Font.TRUETYPE_FONT, is);
                mapFonts.put(nom, base);
            }

            return mapFonts.get(nom).deriveFont(size);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int)size);
        }
    }
}