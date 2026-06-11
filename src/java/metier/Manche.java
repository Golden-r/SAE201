package metier ;

import java.util.ArrayList ;
import java.util.Collections;


/* SAE 2.01 | Développement d'une application 
* Manche
*
* Date     : 10/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/


public class Manche 
{
    /*----------------------------*/ 
	/* Attributs de la classe     */
	/*----------------------------*/

    private ArrayList<Joueur> ensJoueurs;
    private Pioche pioche               ;
    private Carte  carteCourante        ;
    private int    idJoueur             ;
    private int    numManche            ;

    /*----------------------------*/
	/* Constructeur de la classe  */
	/*----------------------------*/
    
    public Manche (int numManche, ArrayList<Joueur> ensJoueurs, Pioche pioche, ArrayList<ECouleur> reseaux, boolean modeDebug)
    {
        ECouleur reseau;

        this.numManche  = numManche  ;
        this.ensJoueurs = ensJoueurs ;
        this.pioche     = pioche     ;
        this.idJoueur   = 0          ;
 

        for(int cpt = 0; cpt < this.ensJoueurs.size(); cpt++)
        {
            reseau = reseaux.get((cpt + numManche - 1) % reseaux.size());

            this.ensJoueurs.get(cpt).setReseau(reseau);
            this.ensJoueurs.get(cpt).ajouterECouleurVisite(reseau);
        }

        if(!modeDebug)
            this.carteCourante = this.pioche.piocher();
        else
            this.carteCourante = null;
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

    public void passerTour()
    {
        if(this.ensJoueurs.isEmpty())
            return;

        this.idJoueur++;

        if(this.idJoueur >= this.ensJoueurs.size())
            this.idJoueur = 0;       
    }

    public void piocherCarte()
    {
        this.carteCourante = this.pioche.piocher();
    }

    public void piocherCarteSpecifique(Carte carte)
    {
        this.carteCourante = this.pioche.piocherSpecifique(carte);
    }


}