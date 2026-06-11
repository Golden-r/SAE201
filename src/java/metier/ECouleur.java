package metier;

import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* ECouleur
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public enum ECouleur
{
    BLEU    ("Eau potable"           , Color.BLUE           ),
	JAUNE   ("Électricité"           , Color.YELLOW         ),
	ROUGE   ("Gaz de ville"          , Color.RED            ),
	VERT    ("Fibre optique"         , Color.GREEN          ),
	MARRON  ("Égouts et eaux usées"  , new Color (137,81,41)),
	NOIR    ("Ramassage des déchets" , Color.BLACK          ),

    LIAISON ("LIAISON"               , Color.GRAY           );

    /*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private String libelle;
    private Color  couleur;

    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    ECouleur ( String libelle, Color couleur )
    {
        this.libelle = libelle;
        this.couleur = couleur;
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
    
    public String getLibelle(){ return this.libelle; }
    public Color  getCouleur(){ return this.couleur; }

}
