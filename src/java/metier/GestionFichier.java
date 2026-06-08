package metier ;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import metier.* ;

/* SAE 2.01 | Développement d'une application 
* GestionFichier
*
* Date     : 03/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class GestionFichier
{    
    public static PlateauData lireFichier ( File fichier )
    {

		/*----------------*/
		/*  Données       */
		/*----------------*/
        
        PlateauData proprietes = new PlateauData() ;

		String ligneCouleurs ;
		String ligneSymboles ;

		/*----------------*/
		/*  Instructions  */
		/*----------------*/

        try 
		{
            Scanner sc = new Scanner(fichier);

            proprietes.tailleLargeur  = Integer.parseInt(sc.nextLine().trim());
            proprietes.tailleLongueur = Integer.parseInt(sc.nextLine().trim());
            proprietes.tailleCellule  = Integer.parseInt(sc.nextLine().trim());

            ligneCouleurs = sc.nextLine();
            proprietes.lstCouleur    = convertirDataEnList(ligneCouleurs);

            ligneSymboles = sc.nextLine();
            proprietes.lstSymbole    = convertirDataEnList(ligneSymboles);

            sc.close();
        }
		catch (FileNotFoundException e) { System.out.println("Erreur: Le fichier est introuvable.")                ;} 
		catch (Exception e)             { System.out.println("Erreur de lecture ou de format : " + e.getMessage()) ;}

        return proprietes;
    }


	private static ArrayList<Integer> convertirDataEnList(String ligne) 
	{
		/*----------------*/
		/*  Données       */
		/*----------------*/

        ArrayList<Integer> liste   ;
        String[]           valeurs ;
        
		/*----------------*/
		/*  Instructions  */
		/*----------------*/

		liste   = new ArrayList<>();
		valeurs = ligne.split(",");

        for (String val : valeurs) 
            liste.add(Integer.parseInt(val.trim()));
    

        return liste;
    }
	
    public static boolean ecrireFichier(File fichier, ArrayList<String> donnees )
    {
        try
        {
            PrintWriter pw = new PrintWriter(fichier);

            for( int cpt = 0; cpt < donnees.size(); cpt++)
                pw.println( donnees.get(cpt));

            pw.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Erreur d'écriture : " + e.getMessage());
            return false;
        }
    }

    public static boolean supprimerFichier(File fichier)
    {
        try
        {
            if(fichier == null || !fichier.exists())
                return false;

            return fichier.delete();
        }
        catch(Exception e)
        {
            System.out.println("Erreur de suppression : " + e.getMessage());
            return false;
        }
    }
}