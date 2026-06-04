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

    private int                 coordonneeX ;
    private int                 coordonneeY ;

    private ArrayList<Symbole>  ensVoisin   ;

    private ESymbole            symbole     ;
    private ECouleur            couleurBase ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Symbole ( int coordonneeX , int coordonneeY , ESymbole symbole ) 
    {
        this.coordonneeX  = coordonneeX ;
        this.coordonneeY  = coordonneeY ;

        this.ensVoisin    = new ArrayList<Symbole>() ;

        this.symbole      = symbole          ;
        
        this.couleurBase  = null             ;
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public int                 getX            () { return this.coordonneeX         ; }
    public int                 getY            () { return this.coordonneeY         ; }
    public ArrayList<Symbole>  getEnsVoisin    () { return this.ensVoisin           ; }
    public ESymbole            getSymbole      () { return this.symbole             ; }
    public ECouleur            getCouleurBase  () { return this.couleurBase         ; }
    


    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void setX        ( int      coordonneeX )            { this.coordonneeX = coordonneeX ; }
    public void setY        ( int      coordonneeY )            { this.coordonneeY = coordonneeY ; }
    public void setEnsVoisin( ArrayList<Symbole> ensVoisin )    { this.ensVoisin   = ensVoisin   ; }
    public void setSymbole  ( ESymbole symbole     )            { this.symbole     = symbole     ; }
    public void setBase     ( ECouleur couleurBase )            { this.couleurBase = couleurBase ; }

    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public boolean estBase () { return this.couleurBase != null ; }

	public boolean ajouterVoisin ( Symbole voisin )
    {
        this.ensVoisin.add( voisin ) ;
        voisin.ensVoisin.add( this ) ;

        return true ;
    }

    public boolean supprimerVoisin( Symbole voisin )
    {
        if (voisin == null){ return false;}
        
        this.ensVoisin  .remove ( voisin ) ;
        voisin.ensVoisin.remove ( this   ) ;

        return true ;
    }

    public boolean supprimerEnsVoisin ( )
    {
        this.ensVoisin.clear();

        return true ;
    }


    public String toString ()
    {
        return "Symbole : (" + this.coordonneeX + "," + this.coordonneeY + ")" ;
    }

}