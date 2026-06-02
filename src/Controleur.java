package controleur;

import ihm.FrameConception;
import metier.Plateau;

/* SAE 2.01 | Développement d'une application 
* Controleur
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/


public class Controleur
{
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

	private FrameConception frameConcept;
	private Plateau         metierPlateau;


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Controleur()
	{
	}


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

		public void lancerConception()
		{
			System.out.println("Lancement de l'application conception");
			this.frameConcept = new FrameConception( this ); 
		}

		public void lancerJeu()
		{
			System.out.println("Lancement du jeu");
		}


	/*----------------------------*/
	/*   Main                     */
	/*----------------------------*/

	public static void main(String[] args)
	{
		if (args.length == 0){ System.out.println("ERREUR : Précisez le mode ! (\"concept\" / \"jeu\")"); return ;}


		Controleur ctrl = new Controleur();

		if      ( args[0].equals("concept") ){ ctrl.lancerConception() ;}
		else if ( args[0].equals("jeu"    ) ){ ctrl.lancerJeu()        ;}
		else                                          { System.out.println("ERREUR : mode incorrect ! (\"concept\" / \"jeu\")") ;}
	}

}