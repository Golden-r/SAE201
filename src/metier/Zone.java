package metier ;

import java.util.ArrayList ;

public class Zone 
{
    private ArrayList<Symbole> ensSymbole ;

    public Zone () 
    {
        this.ensSymbole = new ArrayList<Symbole>() ;
    }

    public boolean ajouterSymbole ( Symbole symbole )
    {
        this.ensSymbole.add( symbole ) ;
        return true ;
    }

    public boolean supprimerSymbole ( Symbole symbole )
    {
        if (symbole == null) return false;
        
        this.ensSymbole.remove( symbole ) ;
        return true ;
    }

}