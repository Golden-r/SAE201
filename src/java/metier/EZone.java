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
	ZONE1  ("Le Pôle Industriel"       , new Color(220, 50, 50) , "Pôle_Industriel"        ), 
	ZONE2  ("Les Docks Marchands"      , new Color( 50, 50,220) , "Docks_Marchand"         ), 
	ZONE3  ("Le Hub Logistique"        , new Color( 50,180, 50) , "Hub_Logistique"         ), 
	ZONE4  ("Le Centre de Données"     , new Color(230,140, 10) , "Centre_de_Données"      ), 
	ZONE5  ("L'Écoquartier"            , new Color( 30,140,100) , "Écoquartier"            ), 
	ZONE6  ("Le Centre Historique"     , new Color(140, 40,140) , "Centre_Historique"      ), 
	ZONE7  ("Les Coteaux Résidentiels" , new Color(230,100,160) , "Coteaux_Résidentiels"   ), 
	ZONE8  ("Le Quartier Ouvrier"      , new Color(140, 80, 40) , "Quartier_Ouvrier"       ), 
	ZONE9  ("Le Nouveau Lotissement"   , new Color(220,220, 40) , "Nouveau_Lotissement"    ), 
	ZONE10 ("Le Centre d'Affaires"     , new Color( 40,200,220) , "Centre_Affaire"         ), 
	ZONE11 ("Les Halles Marchandes"    , new Color(200,130,230) , "Halles_Marchandes"      ), 
	ZONE12 ("Le Technopôle"            , new Color(100,100,100) , "Technopôle"             ), 
	ZONE13 ("La Promenade des Arts"    , new Color(240,120,120) , "Promenade_des_Arts"     ), 
	ZONE14 ("Le Pôle Hospitalier"      , new Color(160,230,180) , "Pôle_Hospitalier"       ), 
	ZONE15 ("Le Campus Universitaire"  , new Color(200,180,120) , "Campus_Universitaire"   ), 
	ZONE16 ("Le Pôle Multimodal"       , new Color(130,160,210) , "Pôle_Multimodal"        ), 
	ZONE17 ("Le Quartier Administratif", new Color(160,170,190) , "Quartier_Administratif" ), 
	ZONE18 ("Le Complexe Énergétique"  , new Color(255, 90, 40) , "Complexe_Énergétique"   ), 
	ZONE19 ("Le Val de l'Eau"          , new Color(100,200,255) , "Val_de_l_Eau"           ), 
	ZONE20 ("Le Grand Parc Urbain"     , new Color(100,220,100) , "Grand_Parc_Urbain"      ); 

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
	
	EZone ( String libelle, Color couleur, String nomImage)
	{
		this.libelle      = libelle              ;
		this.nomImage     = nomImage + ".png"    ;
		this.nomImageBase = nomImage +"_Base.png";
		this.couleur      = couleur              ;
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