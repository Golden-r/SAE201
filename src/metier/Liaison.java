package metier;

import java.util.ArrayList;

/* SAE 2.01 | Développement d'une application 
* Liaison
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Liaison
{
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/
	
	private Symbole            depart;
	private Symbole            arrivee;
	private ECouleur           reseau;
	private ArrayList<Symbole> casesTraversees;
	
	
	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
	
	public Liaison(Symbole depart, Symbole arrivee, ECouleur reseau, ArrayList<Symbole> casesTraversees)
	{
		this.depart  = depart;
		this.arrivee = arrivee;
		this.reseau  = reseau;
		this.casesTraversees = new ArrayList<Symbole>();
	}
	
	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	
	public Symbole            getDepart()       { return this.depart;          }
	public Symbole            getArrivee()      { return this.arrivee;         }
	public ECouleur           getReseau()       { return this.reseau;          }
	public ArrayList<Symbole> getCaseTraversees { return this.casesTraversees; }
	
	
	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
	
	public boolean contientCase(int x, int y)
	{
		for(int cpt = 0; cpt < this.casesTraversees.size(); cpt++)
		{
			Symbole tmpSymbole = this.casesTraversees.get(cpt);
			if(tmpSymbole != null && tmpSymbole.getX() == x && tmpSymbole.getY() == y)
				return true;
			
			return false;
		}
	}
}