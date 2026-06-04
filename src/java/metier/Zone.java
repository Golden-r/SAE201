package metier ;

import java.util.ArrayList ;
import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* Zone
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Zone 
{
	private static final String[] TAB_LIBELLE_ZONE = new String[] { "Le Pôle Industriel"        , 
																	"Les Docks Marchands"       , 
																	"Le Hub Logistique"         , 
																	"Le Centre de Données"      ,
																	"L'Écoquartier"             , 
																	"Le Centre Historique"      , 
																	"Les Coteaux Résidentiels"  , 
																	"Le Quartier Ouvrier"       ,
																	"Le Nouveau Lotissement"    , 
																	"Le Centre d'Affaires"      , 
																	"Les Halles Marchandes"     , 
																	"Le Technopôle"             ,
																	"La Promenade des Arts"     , 
																	"Le Pôle Hospitalier"       , 
																	"Le Campus Universitaire"   , 
																	"Le Pôle Multimodal"        ,
																	"Le Quartier Administratif" , 
																	"Le Complexe Énergétique"   , 
																	"Le Val de l'Eau"           , 
																	"Le Grand Parc Urbain"        };

	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private Color couleur;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Zone () 
    {
        this.couleur = new Color(200, 200, 200);
    }


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public Color  getCouleur () { return this.couleur  ;}
	public String[]

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	public void setCouleur ( Color couleur ) {  this.couleur = couleur ;}

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

}