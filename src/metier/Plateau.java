package metier ;

import java.util.ArrayList ;

public class Plateau 
{
    private int taille ;

    private int nbCouleur ;

    private int nbSymbole ;

    private Zone[] ensZone ;

    /* CONSTRUCTEUR */
    public Plateau ( int taille , int nbCouleur , int nbSymbole ) 
    {
        this.taille      = taille ;
        this.nbCouleur   = nbCouleur ;
        this.nbSymbole   = nbSymbole ;
        this.ensZone     = new Zone[taille * taille]  ;
    }

    /* GETTERS */
    public int getTaille    () { return this.taille    ; }
    public int getNbCouleur () { return this.nbCouleur ; }
    public int getNbSymbole () { return this.nbSymbole ; }

    /* SETTERS */
    public void setTaille    ( int taille    ) { this.taille    = taille    ; }
    public void setNbCouleur ( int nbCouleur ) { this.nbCouleur = nbCouleur ; }
    public void setNbSymbole ( int nbSymbole ) { this.nbSymbole = nbSymbole ; }

    /* METHODES */
   

}