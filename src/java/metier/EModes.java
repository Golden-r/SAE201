package metier;

public enum EModes
{
    SOLO ( "Solo"         , "Jouer à Reseaux Urbains en solo !"                        ),
    POSTE( "2 Joueurs"    , "Jouer à deux joueurs à Reseaux Urbains en Pass n' Play ! "),
    MULTI( "Multi Joueurs", "Joeur à 2+ joueurs à Reseaux Urbains en ligne !"          );

    private String libelle    ;
    private String description;

    EModes(String libelle, String description)
    {
        this.libelle     = libelle;
        this.description = description;
    }

    
	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
	public String getLibelle    () { return this.libelle    ;}
    public String getDescription() { return this.description;}    

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
