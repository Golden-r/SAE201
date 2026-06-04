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
	private ArrayList<Cellule>    CelluleTraversees;
	
	
	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
	
	public Liaison(Symbole depart, Symbole arrivee, ECouleur reseau, ArrayList<Cellule> CelluleTraversees)
	{
		this.depart  = depart;
		this.arrivee = arrivee;
		this.reseau  = reseau;
		this.CelluleTraversees = CelluleTraversees;
	}
	
	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	
	public Symbole         getDepart()         { return this.depart;          }
	public Symbole         getArrivee()        { return this.arrivee;         }
	public ECouleur        getReseau()         { return this.reseau;          }
	public ArrayList<Cellule> getCelluleTraversees() { return this.CelluleTraversees; }
	
	
	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
	
	public boolean contientCellule(int x, int y)
	{
		for(int cpt = 0; cpt < this.CelluleTraversees.size(); cpt++)
		{
			Cellule tmpCellule = this.CelluleTraversees.get(cpt);
			if(tmpCellule != null && tmpCellule.getX() == x && tmpCellule.getY() == y)
				return true;
		}
		
		return false;
	}
}