package metier;

import java.awt.Color;


/* SAE 2.01 | Développement d'une application 
* ESymbole
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public enum ESymbole

{
	MAISONS     ("Maisons"     , 'M' ),
	IMMEUBLES   ("Immeubles"   , 'I' ),
	HOTELS      ("Hôtels"      , 'H' ),
	RESTAURANTS ("Restaurants" , 'R' ),
	ECOLES      ("Écoles"      , 'E' ),
	USINES      ("Usines"      , 'U' );

    private String libelle;
    private char   nom ;

    ESymbole ( String libelle, char nom )
    {
        this.libelle = libelle;
        this.nom     = nom    ;
    }

    public String getLibelle(){ return this.libelle ;}
    public char   getNom()    { return this.nom ;    }
}
