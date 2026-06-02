import ihm.FrameConception; 

/* SAE 2.01 | Développement d'une application 
* Controleur
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Controleur
{

    public static void main(String[] args)
    {

        if (args.length == 0)
        {
            System.out.println("ERREUR : Précisez le mode ! (\"concept\" / \"jeu\")");
            return;
        }


        if ( args[0].equals("concept") )
        {
            System.out.println("Lancement de l'application conception");

			FrameConception frame = new FrameConception();

        }
        else 
		{
			
			if ( args[0].equals("jeu") )
			{
				System.out.println("Lancement du jeu");
			}
			else 
			{
				 System.out.println("ERREUR : mode incorrect ! (\"concept\" / \"jeu\")");
			}

		}
    }

}