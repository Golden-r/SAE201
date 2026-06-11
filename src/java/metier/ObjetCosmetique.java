package metier;

/* SAE 2.01 | Développement d'une application 
* ObjetCosmetique
* 
* Date     : 11/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class ObjetCosmetique extends Objet
{
    public ObjetCosmetique(String nom, int prix)
    {
        super(nom, prix);
    }

    public String getCategorie()
    {
        return "Cosmetique";
    }
}
