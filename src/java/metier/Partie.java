package metier;

import java.util.ArrayList;
import java.util.Collections;

/* SAE 2.01 | Développement d'une application 
* Manche
*
* Date     : 10/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Partie
{
    /*----------------------------*/ 
	/* Attributs de la classe     */
	/*----------------------------*/
    
    private Plateau             plateau         ;
    private ArrayList<Joueur>   ensJoueurs       ;
    private Pioche              pioche          ;
    private Carte               carteCourante   ;
    private ArrayList<ECouleur> reseauxJouables ;
    private ArrayList<ESymbole> symboles        ;
    private Manche              manche          ;
    private EModes              mode            ;
    
    private int     mancheCourante      ;
    private int     nbMancheMax         ;
    private boolean modeDebug           ;

    /*----------------------------*/
	/* Constructeur de la classe  */
	/*----------------------------*/

    public Partie(Plateau plateau, int nbJoueur, EModes mode, boolean modeDebug)
    {
        /*----------------*/
		/* Données        */
		/*----------------*/

        ArrayList<Integer>  lstCouleurPlateau ;
        ArrayList<Integer>  lstSymbolePlateau ;

        /*----------------*/
		/* Instructions   */
		/*----------------*/

        if (mode == EModes.SOLO && nbJoueur != mode.getLimiteJoueur() )
			throw new IllegalArgumentException("Le mode Solo nécessite exactement 1 joueur.");
			
		if (mode == EModes.POSTE && nbJoueur != mode.getLimiteJoueur())
			throw new IllegalArgumentException("Le mode 2 Joueurs nécessite exactement 2 joueurs.");
			
		if (mode == EModes.MULTI && nbJoueur > mode.getLimiteJoueur())
			throw new IllegalArgumentException("Le mode Multi Joueurs nécessite au moins 2 joueurs.");

        this.plateau             = plateau   ;
        this.mancheCourante      = 1         ;
        this.nbMancheMax         = 0         ;
        this.mode                = mode      ;
        this.modeDebug           = modeDebug ;

        this.ensJoueurs      = new ArrayList<Joueur>()  ;
        this.reseauxJouables = new ArrayList<ECouleur>();
        this.symboles        = new ArrayList<ESymbole>();

        lstCouleurPlateau = this.plateau.getLstCouleur();

        if(lstCouleurPlateau != null)
        {
            for(int cpt = 0; cpt < lstCouleurPlateau.size(); cpt++)
            {
                if(lstCouleurPlateau.get(cpt) == 1)
                {
                    this.reseauxJouables.add(ECouleur.values()[cpt]);
                    this.nbMancheMax++;
                }
            }
        }

        Collections.shuffle(this.reseauxJouables);

        lstSymbolePlateau = this.plateau.getLstSymbole();

		if (lstSymbolePlateau != null)
			for (int cpt = 0; cpt < lstSymbolePlateau.size(); cpt++)
				if (lstSymbolePlateau.get(cpt) == 1)
					this.symboles.add(ESymbole.values()[cpt]);

		if (this.reseauxJouables.size() < nbJoueur)
			throw new IllegalArgumentException("Impossible de lancer la partie le nombre de réseau est inférieur au nombre de joueur.");
		
		for (int cpt = 0; cpt < nbJoueur; cpt++)
			this.ensJoueurs.add(new Joueur("Joueur " + (cpt + 1)));

		this.pioche = new Pioche(this.symboles, this.modeDebug);
		this.manche = new Manche(this.mancheCourante, this.ensJoueurs, this.pioche, this.reseauxJouables, this.modeDebug);
        
    }

    /*----------------------------*/
	/* Accesseurs                 */
	/*----------------------------*/

    public Plateau             getPlateau()         { return this.plateau         ;}
    public ArrayList<Joueur>   getEnsJoueur()       { return this.ensJoueurs      ;}
    public int                 getMancheCourante()  { return this.mancheCourante  ;}
    public int                 getNbMancheMax()     { return this.nbMancheMax     ;}
    public ArrayList<ECouleur> getReseauxJouables() { return this.reseauxJouables ;}
    public EModes              getMode()            { return this.mode            ;}
    public boolean             getModeDebug()       { return this.modeDebug       ;}
    public ArrayList<Joueur>   getGagnant()
    {
        if (this.ensJoueurs == null || this.ensJoueurs.isEmpty()) return null;

        ArrayList<Joueur>  ensGagnant = new ArrayList<Joueur> () ;

        int scoreMax = Integer.MIN_VALUE; 

        for (Joueur j : this.ensJoueurs) {
            if (j.getScore() > scoreMax) {
                scoreMax = j.getScore();
                ensGagnant.clear(); 
                ensGagnant.add(j);
            } 
            else if (j.getScore() == scoreMax) {
                ensGagnant.add(j);
            }
        }
        return ensGagnant ;
    }
    

    /*----------------------------*/
	/* Méthodes                   */
	/*----------------------------*/

    public void ajouterJoueur(Joueur joueur)
    {
        this.ensJoueurs.add(joueur);
    }

    public void passerManche()
    {
        this.mancheCourante++;
    }

    public boolean estFinDePartie()
    {
        return this.mancheCourante > this.nbMancheMax;
    }
}