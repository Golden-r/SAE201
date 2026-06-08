package metier;

import metier.* ;
import java.util.ArrayList ;

public class Cellule 
{
    /*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private int     coordonneeX ;
    private int     coordonneeY ;

    private Symbole symbole ;
    private Zone    zone ;

    private boolean estVide ;

    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
    public Cellule ( int coordonneeX , int coordonneeY ) 
    {
        this.coordonneeX  = coordonneeX ;
        this.coordonneeY  = coordonneeY ;

        this.symbole      = null         ;
        this.zone         = null         ;

        this.estVide      = true         ;
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public int     getX      () { return this.coordonneeX ; }
    public int     getY      () { return this.coordonneeY ; } 

    public Symbole getSymbole() { return this.symbole     ; }
    public Zone    getZone   () { return this.zone        ; }

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void setX        ( int     coordonneeX  ) { this.coordonneeX  = coordonneeX ; }
    public void setY        ( int     coordonneeY  ) { this.coordonneeY  = coordonneeY ; }

    public void setSymbole  ( Symbole symbole      ) { this.symbole      = symbole     ; }
    public void setZone     ( Zone    zone         ) { this.zone         = zone        ; }

    //public void setEstVide  ( boolean estVide      ) { this.estVide      = estVide     ; }

    /*----------------------------*/
	/*  Test                      */
	/*----------------------------*/

    public boolean estVide() { return this.symbole == null ; }

    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    
    public String toString()
    {
        return "Cellule (" + this.coordonneeX + "," + this.coordonneeY + ") : " ;
    }
    
	
}