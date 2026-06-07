package metier ;

import metier.ESymbole;
import metier.ECouleur;

import java.util.ArrayList ;
import java.util.Objects;

/* 
SAE 2.01 | Développement d'une application 
* Symbole
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Symbole
{
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    private ESymbole            symbole     ;
    private ECouleur            couleurBase ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Symbole ( ESymbole symbole ) 
    {

        this.symbole     = symbole ;
        this.couleurBase = null    ;

    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public ESymbole getSymbole      () { return this.symbole     ; }
    public ECouleur getCouleurBase  () { return this.couleurBase ; }
  
    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void setBase    ( ECouleur couleurBase ) { this.couleurBase = couleurBase ; }
    public void setSymbole ( ESymbole symbole     ) { this.symbole     = symbole;      }

    /*----------------------------*/
	/*  Test                      */
	/*----------------------------*/

    public boolean estBase () { return this.couleurBase != null ; }
    
    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public String toString ()
    {
        return "Symbole : (" + this.symbole + ")" ;
    }


}