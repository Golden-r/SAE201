package metier;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

/* SAE 2.01 | Développement d'une application 
* GestionFichier
*
* Date     : 03/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class GestionFichier
{
    private static final String REPERTOIRE = "./";
    
    public static ArrayList<String> lireFichier(String nomFichier)
    {
        ArrayList<String> lignes = new ArrayList<String>();

        if(!nomFichier.endsWith(".data"))
			nomFichier += ".data";
        
        try
        {
            Scanner sc = new Scanner(new File(REPERTOIRE + nomFichier));

            while(sc.hasNextLine())
                lignes.add(sc.nextLine());

            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        return lignes;
    }

    public static boolean ecrireFichier(String nomFichier, ArrayList<String> donnees )
    {
        if(!nomFichier.endsWith(".data"))
			nomFichier += ".data";
        
        try
        {
            File dossier = new File(REPERTOIRE);
			if(!dossier.exists())
				dossier.mkdirs();
            
            PrintWriter pw = new PrintWriter(new File(REPERTOIRE + nomFichier));

            for(int cpt = 0; cpt < donnees.size(); cpt++)
                pw.println(donnees.get(cpt));

            pw.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Erreur d'écriture : " + e.getMessage());
            return false;
        }
    
        return true;
        
    }
}