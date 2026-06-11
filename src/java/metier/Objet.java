package metier ;

/* SAE 2.01 | Développement d'une application 
* Objet
* 
* Date     : 11/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public abstract class Objet 
{
    private String nom;
    private int    prix;

    public Objet (String nom, int prix)
    {
        this.nom  = nom;
        this.prix = prix;
    }


    public String getNom()  { return this.nom  ;}
    public int    getPrix() { return this.prix ;}

    public abstract String getCategorie();
}