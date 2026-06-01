import java.util.ArrayList ;

public class Symbole
{

    private int                 coordonneeX ;

    private int                 coordonneeY ;

    private ArrayList<Symbole>  ensVoisin                   ;

    /* CONSTRUCTEUR */
    public Symbole ( int coordonneeX , int coordonneeY ) 
    {
        this.coordonneeX  = coordonneeX ;
        this.coordonneeY  = coordonneeY ;

        this.ensVoisin    = new ArrayList<Symbole>() ;
    }

    /* GETTERS */
    public int  getX () { return this.coordonneeX ; }
    public int  getY () { return this.coordonneeY ; }

    /* SETTERS */
    public void setX ( int coordonneeX ) { this.coordonneeX = coordonneeX ; }
    public void setY ( int coordonneeY ) { this.coordonneeY = coordonneeY ; }

    /* METHODES */
    public boolean ajouterVoisin ( Symbole voisin )
    {
        this  .ensVoisin   .add( voisin ) ;

        // on n'appele pas ajouterVoisin pour éviter les appels récursifs infinis
        voisin.ensVoisin   .add( this   ) ;

        return true ;
    }

    public String toString ()
    {
        return "Symbole : (" + this.coordonneeX + "," + this.coordonneeY + ")" ;
    }

    /* TEST UNITAIRE */
    public static void main(String[] args)
    {
        int x = 1;
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

        Symbole s1 = new Symbole(1, 2) ;
        System.out.println(s1);

        for (int i = 0 ; i < x; i++)
        {
            s1.ajouterVoisin( new Symbole(i, i) );
            System.out.println("Voisin " + i + " : " + s1.ensVoisin.get(i));
        }

        System.out.println("Voisins : " + s1.ensVoisin);
    }
}