package metier ;

/* SAE 2.01 | Développement d'une application 
* Carte
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Carte
{
	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/
	
	private ESymbole symbole;
	private boolean  estSombre;
	private boolean  estJoker;
	
	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
	public Carte(ESymbole symbole, boolean estSombre, boolean estJoker)
	{
		this.symbole   = symbole;
		this.estSombre = estSombre;
		this.estJoker  = estJoker;
	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	
	public ESymbole getSymbole() { return this.symbole;   }
	
	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

	public boolean  isSombre()   { return this.estSombre; }
	public boolean  isJoker()    { return this.estJoker;  }
}