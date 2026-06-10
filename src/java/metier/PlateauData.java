package metier;

import java.util.ArrayList;

public class PlateauData
{

    /*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    public int                tailleLargeur ;
    public int                tailleLongueur;
    public int                tailleCellule ;
    
    public ArrayList<Integer> lstCouleur    ;
    public ArrayList<Integer> lstSymbole    ;
    public ArrayList<Cellule> lstCellules   ;

    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public PlateauData()
    { 
        this.lstCouleur  = new ArrayList<Integer>();
        this.lstSymbole  = new ArrayList<Integer>();
        this.lstCellules = new ArrayList<Cellule>();
    }
    
    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public String toString()
    {
        return "PlateauData{"       +
                "tailleLargeur="    + tailleLargeur  +
                ", tailleLongueur=" + tailleLongueur +
                ", tailleCellule="  + tailleCellule  +
                ", lstCouleur="     + lstCouleur     +
                ", lstSymbole="     + lstSymbole     +
                '}';
    }
}
