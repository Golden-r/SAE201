package metier;

import java.util.ArrayList;

public class Partie
{
    /*----------------------------*/ 
	/* Attributs de la classe     */
	/*----------------------------*/
    
    private Plateau             plateau         ;
    private ArrayList<Joueur>   ensJoueur       ;
    private Pioche              pioche          ;
    private Carte               carteCourante   ;
    private ArrayList<ECouleur> reseauxJouables ;
    
    private int     mancheCourante      ;
    private int     nbMancheMax         ;
    private int     indiceJoueurCourant ;

    /*----------------------------*/
	/* Constructeur de la classe  */
	/*----------------------------*/

    public Partie(Plateau plateau, int nbJoueur)
    {
        /*----------------*/
		/* Données        */
		/*----------------*/

        ArrayList<Integer>  lstCouleurPlateau ;

        /*----------------*/
		/* Instructions   */
		/*----------------*/

        this.plateau             = plateau ;
        this.mancheCourante      = 1       ;
        this.nbMancheMax         = 0       ;
        this.indiceJoueurCourant = 0       ;

        this.ensJoueurs    = new ArrayList<Joueur>() ;
        this.pioche        = new Pioche()            ;
        this.carteCourante = null                    ;

        lstCouleurPlateau = this.plateau.getLstCouleur();

        if(lstCouleurPlateau != null)
        {
            for(int cpt = 0; cpt < lstCouleurPlateau.size(); cpt++)
            {
                if(lstCouleurPlateau.get(cpt) == 1)
                {
                    this.reseauxJouables.add(ECouleur.getCouleur(cpt));
                    this.nbMancheMax++;
                }
            }
        }

        if(reseaux.size() < nbJoueur)
            throw new IllegalArgumentException("Impossible de lancer la partie le nombre de réseau est inférieur au nombre de joueur.");
        
        for(int cpt = 0; cpt < nbJoueur; cpt++)
            this.ensJoueurs.add(new Joueur("Joueur " + (cpt + 1)));

        this.attribuerReseauxManche();
        
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