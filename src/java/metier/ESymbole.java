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
	MAISONS     ("Maisons"     , 'm' ),
	IMMEUBLES   ("Immeubles"   , 'i' ),
	HOTELS      ("Hôtels"      , 'h' ),
	RESTAURANTS ("Restaurants" , 'r' ),
	ECOLES      ("Écoles"      , 'e' ),
	USINES      ("Usines"      , 'u' );

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
