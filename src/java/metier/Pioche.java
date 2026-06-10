package metier ;

import java.util.ArrayList;
import java.util.Collections;

import metier.Carte;

/* SAE 2.01 | Développement d'une application 
* Pioche
*
* Date     : 10/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Pioche
{

	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

	private ArrayList<Carte> paquet;
	private ArrayList<Carte> cartesTirees;
	private int              nbCarteSombre;
	

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Pioche(ArrayList<ESymbole> symbolesEnJeu)
	{
		this.paquet        = new ArrayList<Carte>();
		this.cartesTirees  = new ArrayList<Carte>();
		this.nbCarteSombre = 0;
		
		this.initialiserPaquet(symbolesEnJeu);
	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public ArrayList<Carte> getPaquet       () { return this.paquet       ; }
	public ArrayList<Carte> getCartesTirees () { return this.cartesTirees ; }
	public int              getNbCarteSombre() { return this.nbCarteSombre; }
	

	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	public boolean estMancheTerminee()
	{
		return this.nbCarteSombre <= 0;
	}
	
	private void initialiserPaquet(ArrayList<ESymbole> symbolesEnJeu)
	{
		for(int cpt = 0; cpt < symbolesEnJeu.size(); cpt++)
		{
			this.paquet.add(new Carte(symbolesEnJeu.get(cpt),false,false));
			this.paquet.add(new Carte(symbolesEnJeu.get(cpt),true, false));
			
			this.nbCarteSombre++;
		}
		
		this.paquet.add(new Carte(null,false,true));
		this.paquet.add(new Carte(null,true, true));
		
		this.nbCarteSombre++;
		
		Collections.shuffle(this.paquet);
	}
	
	public Carte piocher()
	{
		if(this.paquet.isEmpty()) return null;
		
		Carte carteTiree = this.paquet.remove(0);
		this.cartesTirees.add(carteTiree);
		
		if(carteTiree.isSombre()) this.nbCarteSombre--;
		
		return carteTiree;
	}
}