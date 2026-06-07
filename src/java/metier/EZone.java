package metier;

import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* EZone
*	Description : Enumération des différentes zones de la ville
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public enum EZone
{
	ZONE1  ("Le Pôle Industriel"       , new Color(220, 50, 50) ), 
	ZONE2  ("Les Docks Marchands"      , new Color( 50, 50,220) ), 
	ZONE3  ("Le Hub Logistique"        , new Color( 50,180, 50) ), 
	ZONE4  ("Le Centre de Données"     , new Color(230,140, 10) ), 
	ZONE5  ("L'Écoquartier"            , new Color( 30,140,100) ), 
	ZONE6  ("Le Centre Historique"     , new Color(140, 40,140) ), 
	ZONE7  ("Les Coteaux Résidentiels" , new Color(230,100,160) ), 
	ZONE8  ("Le Quartier Ouvrier"      , new Color(140, 80, 40) ), 
	ZONE9  ("Le Nouveau Lotissement"   , new Color(220,220, 40) ), 
	ZONE10 ("Le Centre d'Affaires"     , new Color( 40,200,220) ), 
	ZONE11 ("Les Halles Marchandes"    , new Color(200,130,230) ), 
	ZONE12 ("Le Technopôle"            , new Color(100,100,100) ), 
	ZONE13 ("La Promenade des Arts"    , new Color(240,120,120) ), 
	ZONE14 ("Le Pôle Hospitalier"      , new Color(160,230,180) ), 
	ZONE15 ("Le Campus Universitaire"  , new Color(200,180,120) ), 
	ZONE16 ("Le Pôle Multimodal"       , new Color(130,160,210) ), 
	ZONE17 ("Le Quartier Administratif", new Color(160,170,190) ), 
	ZONE18 ("Le Complexe Énergétique"  , new Color(255, 90, 40) ), 
	ZONE19 ("Le Val de l'Eau"          , new Color(100,200,255) ), 
	ZONE20 ("Le Grand Parc Urbain"     , new Color(100,220,100) ); 

	private String libelle  ;
	private String nomImage ;
	private Color  couleur  ;

	EZone ( String libelle, Color couleur)
	{
		this.libelle = libelle;
		this.couleur = couleur;

		this.nomImage = libelle.toLowerCase().replace(" ", "_") + ".png";
	}

	public static String[] getZonesLibelles()
	{
		String[] zones = new String[ EZone.values().length ];

		for (int cpt = 0; cpt < EZone.values().length; cpt++)
			zones[cpt] = EZone.values()[cpt].getLibelle();
		
		
		return zones;
	}
	
	public String getLibelle (){ return this.libelle ; }
	public String getNomImage(){ return this.nomImage; }
	public Color  getCouleur() { return this.couleur ; }
}