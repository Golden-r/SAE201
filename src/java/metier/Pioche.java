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
	private boolean          modeDebug;
	

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Pioche(ArrayList<ESymbole> symbolesEnJeu, boolean modeDebug)
	{
		this.paquet        = new ArrayList<Carte>();
		this.cartesTirees  = new ArrayList<Carte>();
		this.nbCarteSombre = 0;
		this.modeDebug     = modeDebug;
		
		this.initialiserPaquet(symbolesEnJeu);
	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public ArrayList<Carte> getPaquet       () { return this.paquet       ; }
	public ArrayList<Carte> getCartesTirees () { return this.cartesTirees ; }
	public int              getNbCarteSombre() { return this.nbCarteSombre; }
	public boolean          getModeDebug    () { return this.modeDebug    ; }

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
		
		if(!modeDebug)
			Collections.shuffle(this.paquet);
	}
	
	public Carte piocher()
	{
		if(this.paquet.isEmpty()) return null;
		
		Carte carteTiree = this.paquet.remove(0);
		this.cartesTirees.add(carteTiree);
		
		if(carteTiree.estSombre()) this.nbCarteSombre--;
		
		return carteTiree;
	}

	public Carte piocherSpecifique(Carte carte)
	{
		if(this.paquet.isEmpty() || !this.paquet.contains(carte)) 
			return null;
		
		this.paquet.remove(carte);
		this.cartesTirees.add(carte);

		if(carte.estSombre())
			this.nbCarteSombre--;
		
		return carte;
	}
}