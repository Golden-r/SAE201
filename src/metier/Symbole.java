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

    private boolean             estBase     ;
    private ECouleur            couleurBase ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Symbole ( int coordonneeX , int coordonneeY , ESymbole symbole ) 
    {
        this.coordonneeX  = coordonneeX ;
        this.coordonneeY  = coordonneeY ;

        this.ensVoisin    = new ArrayList<Symbole>() ;

        this.symbole = symbole          ;
        
        this.estBase = false            ;
        this.couleurBase = null         ;
    }


    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public int          getX            () { return this.coordonneeX ; }
    public int          getY            () { return this.coordonneeY ; }
    public ESymbole     getSymbole      () { return symbole          ; }
    public ECouleur     getCouleurBase  () { return couleurBase      ; }


    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void setX        ( int coordonneeX   )   { this.coordonneeX = coordonneeX ; }
    public void setY        ( int coordonneeY   )   { this.coordonneeY = coordonneeY ; }
    public void setSymbole  ( ESymbole symbole  )   { this.symbole = symbole;          }


    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public boolean isBase() { return estBase; }
    
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

    public void setBase(boolean estBase, ECouleur couleurBase)
    {
        this.estBase = estBase;
        //TODO
        this.couleurBase = estBase ? Objects.requireNonNull(couleurBase, "couleurBase") : null;
    }
    
    public String toString ()
    {
        return "Symbole : (" + this.coordonneeX + "," + this.coordonneeY + ")" ;
    }



    /*----------------------------*/
	/*  MAIN TEST                 */
	/*----------------------------*/
    public static void main(String[] args)
    {
        int     x = 1;
		Symbole s1;

        if (args.length > 0)
        {
            try
            {
                x = Integer.parseInt(args[0]) ;
            } catch (NumberFormatException e)
            {
                System.out.println("l'argument n'est pas un entier : " + args[0]) ;
                return;
            }
        }

        s1 = new Symbole(1, 2, ESymbole.MAISONS) ;
        System.out.println(s1);

        for (int cpt = 0 ; cpt < x; cpt++)
        {
            s1.ajouterVoisin( new Symbole(cpt, cpt, ESymbole.MAISONS) );
            System.out.println("Voisin " + cpt + " : " + s1.ensVoisin.get(cpt));
        }

        System.out.println("Voisins : " + s1.ensVoisin);
    }
}