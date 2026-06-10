package metier ;

import java.util.ArrayList ;


public class Manche 
{
    /*----------------------------*/ 
	/* Attributs de la classe     */
	/*----------------------------*/

    private ArrayList<Joueur> ensJoueur ;
    private Pioche pioche               ;
    private Carte  carteCourante        ;
    private int    idJoueur             ;
    private int    numManche            ;

    /*----------------------------*/
	/* Constructeur de la classe  */
	/*----------------------------*/
    
    public Manche (int numManche, ArrayList<Joueur> ensJoueur, Pioche pioche, ArrayList<ECouleur> reseaux)
    {
        boolean             bAssignement ;
        ArrayList<ECouleur> reseauxDispo ;

        this.numManche = numManche ;
        this.ensJoueur = ensJoueur ;
        this.pioche    = pioche    ;
        this.idJoueur  = 0         ;

        bAssignement = false;
        reseauxDispo = null;

        while(!bAssignement)
        {
            bAssignement = true;
            reseauDispo  = new ArrayList<ECouleur>(this.reseaux);

            Collections.shuffle(reseauxDispo);

            for(int cpt = 0; cpt < this.ensJoueur.size(); cpt++)
            {
                
                if(this.enJoueur.get(cpt).aDejaJoue(reseauxDispo.get(cpt)))
                {
                    bAssignement = false;
                    break;
                }
            }
        }

        for(int cpt = 0; cpt < this.ensJoueur.size(); cpt++)
        {
            this.ensJoueur.get(cpt).setReseau(reseauDispo.get(cpt));
            this.ensJoueur.get(cpt).ajouterECouleurVisite();
        }

        this.carteCourante = this.pioche.tirerCarte();
    }

    /*----------------------------*/
	/* Accesseurs                 */
	/*----------------------------*/

    public int    getNumManche()       { return this.numManche     ;}
    public Pioche getPioche()          { return this.pioche        ;}
    public Carte  getCarteCourante()   { return this.carteCourante ;}
    public Joueur getJoueurCourant()
    {
        if(this.ensJoueurs.isEmpty())
            return null;
        
        return this.ensJoueurs.get(this.idJoueur);
    }

    /*----------------------------*/
	/* Méthodes                   */
	/*----------------------------*/

    public void piocherCarte()
    {
        this.carteCourante = this.pioche.tirerCarte();
    }

    public void passerTour()
    {
        if(this.ensJoueurs.isEmpty())
            return;

        this.indiceJoueurCourant++;

        if(this.indiceJoueurCourant >= this.ensJoueurs.size())
            this.indiceJoueurCourant = 0;       
    }


}