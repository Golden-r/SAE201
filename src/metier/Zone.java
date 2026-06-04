package metier ;

import java.util.ArrayList ;
import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* Zone
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Zone 
{
	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private ArrayList<Symbole> ensSymbole ;
	private ArrayList<Long>    ensCase   ;

    private Color couleur;


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Zone () 
    {
        this.ensSymbole = new ArrayList<Symbole>() ;
		this.ensCase    = new ArrayList<Long>();
        this.couleur    = new Color(200, 200, 200);
    }


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public ArrayList<Symbole> 	getEnsSymbole	() { return this.ensSymbole; }
	public ArrayList<Long> 		getCase		    () { return this.ensCase   ; }
    public Color           		getCouleur 		() { return this.couleur   ; }

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/


	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public boolean ajouterSymbole ( Symbole symbole )
    {
        this.ensSymbole.add( symbole ) ;

        return true ;
    }

    public boolean supprimerSymbole ( Symbole symbole )
    {
        if ( symbole == null ) { return false; }
        
        this.ensSymbole.remove( symbole ) ;

        return true ;
    }

    public boolean contient(int x, int y)
	{
		for (Symbole s : this.ensSymbole)
			if (s != null && s.getX() == x && s.getY() == y)
				return true;

		return false;
	}
}