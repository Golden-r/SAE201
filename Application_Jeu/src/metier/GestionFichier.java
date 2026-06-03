package metier;

import java.io.File;
import java.io.PrintWriter;
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
    public static ArrayList<String> lireFichier(String cheminFichier)
    {
        ArrayList<String> lignes = new ArrayList<String>();

        try
        {
            Scanner sc = new Scanner(new File(cheminFichier));

            while(sc.hasNextLine())
                lignes.add(sc.nextLine())

            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }

    public static boolean ecrireFichier(String cheminFichier, ArrayList<String> donnees)
    {

    }
}