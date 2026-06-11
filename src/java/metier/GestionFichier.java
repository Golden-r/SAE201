package metier ;

import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
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
        
        // pour relier une zone à l'id
        HashMap<Integer, Zone> zonesDejaCrees = new HashMap<>();

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

            while (sc.hasNextLine())
            {
                String ligne = sc.nextLine().trim();
                if (ligne.isEmpty()) continue;

                String[] parties = ligne.split(",");

                if (parties.length < 9)
                {
                    System.out.println("Ligne invalide : " + ligne);
                    continue;
                }

                try
                {
                    int x = Integer.parseInt(parties[0].trim());
                    int y = Integer.parseInt(parties[1].trim());

                    String symboleNom   = parties[2].trim();
                    String reseaux      = parties[3].trim();

                    String zoneColor    = parties[4].trim();
                    String typeZone     = parties[5].trim();
                    String idStr        = parties[6].trim();

                    boolean estVide = Boolean.parseBoolean(parties[7].trim());
                    boolean estBase = Boolean.parseBoolean(parties[8].trim());

                    Cellule c = new Cellule(x, y);

                    // -------- SYMBOLE --------
                    if (!symboleNom.equals("null"))
                    {
                        ESymbole symbole = ESymbole.valueOf(symboleNom);

                        c.setSymbole(new Symbole(symbole));

                        if ( !reseaux.equals("null") )
                        {
                            c.getSymbole().setBase(ECouleur.valueOf(reseaux)) ;
                        }
                    }

                    // -------- ZONE --------
                    if (!typeZone.equals("null"))
                    {
                        int id = idStr.equals("null") ? -1 : Integer.parseInt(idStr);

                        Zone z = zonesDejaCrees.get(id);

                        if (z == null)
                        {
                            Color color = null;

                            if (!zoneColor.equals("null"))
                            {
                                color = new Color(Integer.parseInt(zoneColor));
                            }

                            z = new Zone(EZone.valueOf(typeZone), id);
                            
                            if ( color != null )
                            {
                                z.setCouleur( color ) ;
                            }

                            zonesDejaCrees.put(id, z);
                        }

                        c.setZone(z);
                    }


                    proprietes.lstCellules.add(c);
                }
                catch (Exception e)
                {
                    System.out.println("Erreur ligne: " + ligne);
                    e.printStackTrace();
                }
            }

            sc.close();
        }
		
		catch (FileNotFoundException e) { System.out.println("Erreur: Le fichier est introuvable.")             ;   return null;} 
		catch (Exception e)             { System.out.println("Erreur de lecture ou de format : " + e.getMessage()) ;  return null;}

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


	public static String[] getListeSauvegardes() 
    {
        File dossier = new File("./src/ressource/data");
        
        if (dossier.exists() && dossier.isDirectory()) 
        {
            File[] fichiersData = dossier.listFiles((dir, nom) -> nom.endsWith(".data"));
            
            if (fichiersData != null && fichiersData.length > 0) 
            {
                String[] tabFichiers = new String[fichiersData.length];
                for (int cpt = 0; cpt < fichiersData.length; cpt++) 
                {
                    tabFichiers[cpt] = fichiersData[cpt].getName();
                }
                return tabFichiers;
            }
        }
        
        return new String[]{"Aucune sauvegarde"};
    }

}