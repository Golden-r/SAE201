package metier;

import java.lang.reflect.Array;
import java.util.ArrayList;

import metier.*;
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
    
    private int     mancheCourante      ;
    private int     nbMancheMax         ;

    /*----------------------------*/
	/* Constructeur de la classe  */
	/*----------------------------*/

    public Partie(Plateau plateau, int nbJoueur)
    {
        /*----------------*/
		/* Données        */
		/*----------------*/

        ArrayList<Integer>  lstCouleurPlateau ;
        ArrayList<Integer>  lstSymbolePlateau ;

        /*----------------*/
		/* Instructions   */
		/*----------------*/

        this.plateau             = plateau ;
        this.mancheCourante      = 1       ;
        this.nbMancheMax         = 0       ;

        this.ensJoueurs      = new ArrayList<Joueur>() ;
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

        

        lstSymbolePlateau = this.plateau.getLstSymbole();

		if (lstSymbolePlateau != null)
			for (int cpt = 0; cpt < lstSymbolePlateau.size(); cpt++)
				if (lstSymbolePlateau.get(cpt) == 1)
					this.symboles.add(ESymbole.values()[cpt]);

		if (this.reseauxJouables.size() < nbJoueur)
			throw new IllegalArgumentException("Impossible de lancer la partie le nombre de réseau est inférieur au nombre de joueur.");
		
		for (int cpt = 0; cpt < nbJoueur; cpt++)
			this.ensJoueurs.add(new Joueur("Joueur " + (cpt + 1)));

		this.pioche = new Pioche(this.symboles);
		this.manche = new Manche(this.mancheCourante, this.ensJoueurs, this.pioche, this.reseauxJouables);
        
    }

    /*----------------------------*/
	/* Accesseurs                 */
	/*----------------------------*/

    public Plateau             getPlateau()         { return this.plateau         ;}
    public ArrayList<Joueur>   getEnsJoueur()       { return this.ensJoueurs      ;}
    public int                 getMancheCourante()  { return this.mancheCourante  ;}
    public int                 getNbMancheMax()     { return this.nbMancheMax     ;}
    public ArrayList<ECouleur> getReseauxJouables() { return this.reseauxJouables ;}
    

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