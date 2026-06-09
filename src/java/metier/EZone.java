package metier;

import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* EZone
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public enum EZone
{
	ZONE1  ("Pôle Industriel"        , new Color(220, 50, 50)), 
	ZONE2  ("Docks Marchands"       , new Color( 50, 50,220)), 
	ZONE3  ("Hub Logistique"         , new Color( 50,180, 50)), 
	ZONE4  ("Centre de Données"      , new Color(230,140, 10)), 
	ZONE5  ("Écoquartier"            , new Color( 30,140,100)), 
	ZONE6  ("Centre Historique"      , new Color(140, 40,140)), 
	ZONE7  ("Coteaux Résidentiels"   , new Color(230,100,160)), 
	ZONE8  ("Quartier Ouvrier"       , new Color(140, 80, 40)), 
	ZONE9  ("Nouveau Lotissement"    , new Color(220,220, 40)), 
	ZONE10 ("Centre d'Affaires"      , new Color( 40,200,220)), 
	ZONE11 ("Halles Marchandes"      , new Color(200,130,230)), 
	ZONE12 ("Technopôle"             , new Color(100,100,100)), 
	ZONE13 ("Promenade des Arts"     , new Color(240,120,120)), 
	ZONE14 ("Pôle Hospitalier"       , new Color(160,230,180)), 
	ZONE15 ("Campus Universitaire"   , new Color(200,180,120)), 
	ZONE16 ("Pôle Multimodal"        , new Color(130,160,210)), 
	ZONE17 ("Quartier Administratif" , new Color(160,170,190)), 
	ZONE18 ("Complexe Énergétique"   , new Color(255, 90, 40)), 
	ZONE19 ("Val de l'Eau"           , new Color(100,200,255)), 
	ZONE20 ("Grand Parc Urbain"      , new Color(100,220,100));

	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

	private String libelle      ;
	private String nomImage     ;
	private String nomImageBase ;
	private Color  couleur      ;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
	
	EZone ( String libelle, Color couleur )
	{
		this.libelle      = libelle              ;
		this.couleur      = couleur              ;

		this.nomImage     = libelle.replace(" ", "_") + ".png" ;
		this.nomImageBase = libelle.replace(" ", "_") + "_Base.png" ;
	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public static String[] getZonesLibelles()
	{
		String[] zones = new String[ EZone.values().length ];

		for (int cpt = 0; cpt < EZone.values().length; cpt++)
			zones[cpt] = EZone.values()[cpt].getLibelle();
		
		
		return zones;
	}
	
	public String getLibelle     () { return this.libelle      ; }
	public String getNomImage    () { return this.nomImage     ; }
	public String getNomImageBase() { return this.nomImageBase ; }
	public Color  getCouleur     () { return this.couleur      ; }
}