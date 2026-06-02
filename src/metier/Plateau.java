package metier ;
import java.util.ArrayList ;


/* SAE 2.01 | Développement d'une application 
* Plateau
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Plateau 
{
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    private int taille ;
    private int nbCouleur ;
    private int nbSymbole ;
    private Zone[] ensZone ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Plateau ( int taille , int nbCouleur , int nbSymbole ) 
    {
        this.taille      = taille ;
        this.nbCouleur   = nbCouleur ;
        this.nbSymbole   = nbSymbole ;
        this.ensZone     = new Zone[taille * taille]  ;
    }


    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
    
	public int getTaille    () { return this.taille    ; }
    public int getNbCouleur () { return this.nbCouleur ; }
    public int getNbSymbole () { return this.nbSymbole ; }


    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/
    
	public void setTaille    ( int taille    ) { this.taille    = taille    ; }
    public void setNbCouleur ( int nbCouleur ) { this.nbCouleur = nbCouleur ; }
    public void setNbSymbole ( int nbSymbole ) { this.nbSymbole = nbSymbole ; }

    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
   

}