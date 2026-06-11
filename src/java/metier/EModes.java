package metier;

/* SAE 2.01 | Développement d'une application 
* EModes
*
* Date     : 10/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public enum EModes
{
    SOLO ( "Solo"         , "Jouer à Reseaux Urbains en solo !"                        ,   1),
    POSTE( "2 Joueurs"    , "Jouer à deux joueurs à Reseaux Urbains en Pass n' Play ! ",   2),
    MULTI( "Multi Joueurs", "Joeur à 2+ joueurs à Reseaux Urbains en ligne !"          ,  16);

    private String  libelle    ;
    private String  description;
    private int     limite     ;

    EModes(String libelle, String description, int limite)
    {
        this.libelle     = libelle;
        this.description = description;
        this.limite      = limite;
    }

    
	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	public String getLibelle     () { return this.libelle    ;}
    public String getDescription () { return this.description;}   

    public int    getLimiteJoueur() { return this.limite;}   

    public static String[] getNomModes()
	{
		String[] tabModes = new String[EModes.values().length];

        for (int lig = 0; lig < tabModes.length; lig++)
        {
            tabModes[lig] = EModes.values()[lig].getLibelle();
        }


		return tabModes;
	}

    public static String[] getDescriptionModes()
	{
		String[] tabModes = new String[EModes.values().length];

        for (int lig = 0; lig < tabModes.length; lig++)
        {
            tabModes[lig] = EModes.values()[lig].getDescription();
        }

		return tabModes;
	}

}
