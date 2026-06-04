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
    public static ArrayList<Object> lireFichier ( File fichier )
    {

		/*----------------*/
		/*  Données       */
		/*----------------*/

		ArrayList<Object> lignes = new ArrayList<Object>();


		int tailleLargeur  ;
		int tailleLongueur ;
		int tailleCellule  ;

		String ligneCouleurs ;
		String ligneSymboles ;

		ArrayList<Integer> lstCouleur ;
		ArrayList<Integer> lstSymbole ;


		/*----------------*/
		/*  Instructions  */
		/*----------------*/

        try 
		{
            Scanner sc = new Scanner(fichier);

            tailleLargeur  = Integer.parseInt(sc.nextLine().trim());
            tailleLongueur = Integer.parseInt(sc.nextLine().trim());
            tailleCellule  = Integer.parseInt(sc.nextLine().trim());

            ligneCouleurs = sc.nextLine();
            lstCouleur    = convertirDataEnList(ligneCouleurs);

            ligneSymboles = sc.nextLine();
            lstSymbole    = convertirDataEnList(ligneSymboles);

            lignes.add(tailleLargeur);
            lignes.add(tailleLongueur);
            lignes.add(tailleCellule);
            lignes.add(lstCouleur);
            lignes.add(lstSymbole);

            sc.close();

        }
		catch (FileNotFoundException e) { System.out.println("Erreur: Le fichier est introuvable.")             ;} 
		catch (Exception e)             { System.out.println("Erreur de lecture ou de format : " + e.getMessage()) ;}

        return lignes;
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

	
	/*
    public static boolean ecrireFichier(File fichier, ArrayList<String> donnees )
    {
        try
        {
            File dossier = new File(REPERTOIRE);
			if(!dossier.exists())
				dossier.mkdir();
            
            PrintWriter pw = new PrintWriter(fichier);

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
    }

    public static boolean supprimerFichier(String nomFichier)
    {
        if (!nomFichier.endsWith(".data"))
		    nomFichier += ".data";

        try
        {
            File fichier = new File(REPERTOIRE + nomFichier);

            if(!fichier.exists())
                return false;

            return fichier.delete();
        }
        catch(Exception e)
        {
            System.out.println("Erreur de suppression : " + e.getMessage());
            return false;
        }
    }
	*/




}