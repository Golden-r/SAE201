package metier;

import java.util.ArrayList;

public class PlateauData
{
    public int                tailleLargeur ;
    public int                tailleLongueur;
    public int                tailleCellule ;
    
    public ArrayList<Integer> lstCouleur    ;
    public ArrayList<Integer> lstSymbole    ;

    public PlateauData()
    {
        this.lstCouleur = new ArrayList<Integer>();
        this.lstSymbole = new ArrayList<Integer>();
    }

    public String toString()
    {
        return "PlateauData{" +
                "tailleLargeur=" + tailleLargeur +
                ", tailleLongueur=" + tailleLongueur +
                ", tailleCellule=" + tailleCellule +
                ", lstCouleur=" + lstCouleur +
                ", lstSymbole=" + lstSymbole +
                '}';
    }
}
