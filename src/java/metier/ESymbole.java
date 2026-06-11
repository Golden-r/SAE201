package metier;

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

    /*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private String libelle ;
    private String nomImage;
    private char   nom     ;
    

    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    ESymbole ( String libelle, char nom )
    {
        this.libelle  = libelle;
        this.nomImage = libelle;
        this.nom      = nom    ;
        
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public String getLibelle()  { return this.libelle  ;}
    public String getNomImage() { return this.nomImage ;}
    public char   getNom()      { return this.nom      ;}
    
}
