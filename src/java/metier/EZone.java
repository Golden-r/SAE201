package metier;

/* SAE 2.01 | Développement d'une application 
* EZone
*	Description : Enumération des différentes zones de la ville
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public enum EZone
{
	ZONE1  ("Le Pôle Industriel"       ),
	ZONE2  ("Les Docks Marchands"      ),
	ZONE3  ("Le Hub Logistique"        ),
	ZONE4  ("Le Centre de Données"     ),
	ZONE5  ("L'Écoquartier"            ),
	ZONE6  ("Le Centre Historique"     ),
	ZONE7  ("Les Coteaux Résidentiels" ),
	ZONE8  ("Le Quartier Ouvrier"      ),
	ZONE9  ("Le Nouveau Lotissement"   ),
	ZONE10 ("Le Centre d'Affaires"     ),
	ZONE11 ("Les Halles Marchandes"    ),
	ZONE12 ("Le Technopôle"            ),
	ZONE13 ("La Promenade des Arts"    ),
	ZONE14 ("Le Pôle Hospitalier"      ),
	ZONE15 ("Le Campus Universitaire"  ),
	ZONE16 ("Le Pôle Multimodal"       ),
	ZONE17 ("Le Quartier Administratif"),
	ZONE18 ("Le Complexe Énergétique"  ),
	ZONE19 ("Le Val de l'Eau"          ),
	ZONE20 ("Le Grand Parc Urbain"     );

	private String libelle;

	EZone ( String libelle )
	{
		this.libelle = libelle;
	}

	public static String[] getZonesLibelles()
	{
		String[] zones = new String[ EZone.values().length ];

		for (int cpt = 0; cpt < EZone.values().length; cpt++)
			zones[cpt] = EZone.values()[cpt].getLibelle();
		
		
		return zones;
	}
	
	public String getLibelle(){ return this.libelle; }
}