package metier ;

import java.util.ArrayList ;

/* SAE 2.01 | Développement d'une application 
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

    private ArrayList<Symbole>  ensVoisin                   ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Symbole ( int coordonneeX , int coordonneeY ) 
    {
        this.coordonneeX  = coordonneeX ;
        this.coordonneeY  = coordonneeY ;

        this.ensVoisin    = new ArrayList<Symbole>() ;
    }


    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public int  getX () { return this.coordonneeX ; }
    public int  getY () { return this.coordonneeY ; }


    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void setX ( int coordonneeX ) { this.coordonneeX = coordonneeX ; }
    public void setY ( int coordonneeY ) { this.coordonneeY = coordonneeY ; }


    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
    
	public boolean ajouterVoisin ( Symbole voisin )
    {
        this  .ensVoisin   .add( voisin ) ;
        voisin.ensVoisin   .add( this   ) ;

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
        for ( Symbole s : this.ensVoisin )
        {
            s.ensVoisin.remove( this ) ;
        }
        this.ensVoisin.clear();

        return true ;

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

        s1 = new Symbole(1, 2) ;
        System.out.println(s1);

        for (int cpt = 0 ; cpt < x; cpt++)
        {
            s1.ajouterVoisin( new Symbole(cpt, cpt) );
            System.out.println("Voisin " + cpt + " : " + s1.ensVoisin.get(cpt));
        }

        System.out.println("Voisins : " + s1.ensVoisin);
    }
}