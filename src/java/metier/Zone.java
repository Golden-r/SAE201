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

    private Color couleur;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Zone () 
    {
        this.couleur = new Color(200, 200, 200);
    }


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public Color getCouleur () { return this.couleur  ; }

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	public void setCouleur ( Color couleur ) {  this.couleur = couleur ; }

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

}