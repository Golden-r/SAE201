package metier ;

import java.util.ArrayList ;

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


	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Zone () 
    {
        this.ensSymbole = new ArrayList<Symbole>() ;
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

}