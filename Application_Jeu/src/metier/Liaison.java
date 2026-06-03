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

	//private boolean estDiagonale   ;
	//priavet boolean estHorizontale ;
	//private boolean estVerticale   ;

	
	
	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
	
	public Liaison(Symbole depart, Symbole arrivee, ECouleur reseau, ArrayList<Symbole> casesTraversees) // , boolean estDiagonale, boolean estHorizontale, boolean estVerticale)
	{
		this.depart  = depart;
		this.arrivee = arrivee;
		this.reseau  = reseau;
		this.casesTraversees = casesTraversees;
		//this.estDiagonale   = estDiagonale  ;
		//this.estHorizontale = estHorizontale;
		//this.estVerticale   = estVerticale  ;
	}
	
	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	
	public Symbole            getDepart()         { return this.depart;          }
	public Symbole            getArrivee()        { return this.arrivee;         }
	public ECouleur           getReseau()         { return this.reseau;          }
	public ArrayList<Symbole> getCaseTraversees() { return this.casesTraversees; }

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	// public void setEstDiagonale  (boolean estDiagonale )  { this.estDiagonale   = estDiagonale  ; }
	// public void setEstHorizontale(boolean estHorizontale) { this.estHorizontale = estHorizontale; }
	// public void setEstVerticale  (boolean estVertical   ) { this.estVerticale   = estVerticale  ; }
	
	
	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	// public boolean estDiagonale()   { return this.estDiagonale  ; }
	// public boolean estHorizontale() { return this.estHorizontale; }
	// public boolean estVerticale()   { return this.estVerticale  ; }	
	
	public boolean contientCase(int x, int y)
	{
		for(int cpt = 0; cpt < this.casesTraversees.size(); cpt++)
		{
			Symbole tmpSymbole = this.casesTraversees.get(cpt);
			if(tmpSymbole != null && tmpSymbole.getX() == x && tmpSymbole.getY() == y)
				return true;
		}
		
		return false;
	}
}