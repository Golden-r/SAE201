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
    private static Map<String, Font> mapFonts = new HashMap<>();

    public static Font getFont(String nom, float size)
    {
        try
        {
            if (!mapFonts.containsKey(nom))
            {
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