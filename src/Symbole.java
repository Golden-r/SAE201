import java.util.ArrayList ;

public class Symbole
{
    private int                 coordonneeX , coordonneeY   ;
    private ArrayList<Symbole>  ensVoisin                   ;
    private ArrayList<>         voisinValide                ;


    public Symbole ( int coordonneeX , int coordonneeY ) 
    {
        this.coordonneeX = coordonneeX ;
        this.coordonneeY = coordonneeY ;

        this.ensVoisin   = new ArrayList<Symbole> ;
        //this.ensVoisin   = new ArrayList<>        ;

    }

    public getX () { return this.coordonneeX ; }
    public getY () { return this.coordonneeY ; }

    public setX ( int coordonneeX ) { this.coordonneeX = coordonneeX ; }
    public setY ( int coordonneeY ) { this.coordonneeY = coordonneeY ; }

    public boolean ajouterVoisin ( Symbole voisin )
    {
        this.ensVoisin.add( voisin ) ;
        this.voisin   .add( this   ) ;

        return true ;
    }

    
}