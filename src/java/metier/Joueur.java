package metier ;

import java.util.ArrayList ;

public class Joueur 
{
    /*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    private String   pseudo ;
    private int      score  ;
    //private ECouleur reseau ;
    private ArrayList<Liaison > ensLiaisonVisite  ;
    private ArrayList<ECouleur> ensECouleurVisite ;

    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Joueur ( String pseudo ) 
    {
        this.pseudo            = pseudo ;
        this.score             = 0      ;
        //this.reseau            = null   ;
        this.ensLiaisonVisite  = new ArrayList<Liaison> () ;
        this.ensECouleurVisite = new ArrayList<ECouleur>() ;
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/
 
    public String              getPseudo               () { return this.pseudo              ;}
    public int                 getScore                () { return this.score               ;}
    //public ECouleur            getreseau               () { return this.reseau              ;}
    public ArrayList<Liaison>  getEnsLiaisonVisite     () { return this.ensLiaisonVisite    ;}
    public ArrayList<ECouleur> getEnsECouleurVisite    () { return this.ensECouleurVisite   ;}

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void     setPseudo            ( String             pseudo                ) { this.pseudo            = pseudo            ;}
    public void     setScore             ( int                score                 ) { this.score             = score             ;}
    //public ECouleur setreseau            ( ECouleur           reseau                ) { this.reseau            = reseau            ;}
    public void     setEnsLiaisonVisite  ( ArrayList<Liaison> ensLiaisonVisite      ) { this.ensLiaisonVisite  = ensLiaisonVisite  ;}
    public void     setEnsECouleurVisite ( ArrayList<ECouleur> ensECouleurVisite    ) { this.ensECouleurVisite = ensECouleurVisite ;}

    /*----------------------------*/
	/*  Test                      */
	/*----------------------------*/


    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public void ajouterLiaison ( Liaison liaison ) { this.ensLiaisonVisite.add   ( liaison ) ; }
    public void retirerLiaison ( Liaison liaison ) { this.ensLiaisonVisite.remove( liaison ) ; }

    public void ajouterScore ( int score ) { this.score += score ;}
    public void retirerScore ( int score ) { this.score -= score ;}

    public void ajouterECouleurVisite ( ECouleur couleur ) { this.ensECouleurVisite.add   ( couleur  ) ;}
    public void retirerECouleurVisite ( ECouleur couleur ) { this.ensECouleurVisite.remove( couleur  ) ;}

    public void ajouterEnsLiaisonVisite ( Liaison liaison ) { this.ensLiaisonVisite.add   ( liaison  ) ;}
    public void retirerensLiaisonVisite ( Liaison liaison ) { this.ensLiaisonVisite.remove( liaison  ) ;}

    public boolean aDejaJoue(ECouleur couleur) { return this.ensECouleurVisite.contains(couleur) ;}

    
    public int  calculerScore         () 
    {

        if (this.ensLiaisonVisite == null || this.ensLiaisonVisite.isEmpty()) 
        {
            return 0;
        }

        // 1. Identifier toutes les zones uniques visitées
        ArrayList<Zone> zonesVisitees = new ArrayList<Zone>();

        for (Liaison liaison : this.ensLiaisonVisite) 
        {
            Cellule depart  = liaison.getDepart();
            Cellule arrivee = liaison.getArrivee();

            if (depart != null && depart.getZone() != null) 
            {
                if (!zonesVisitees.contains(depart.getZone())) {
                    zonesVisitees.add(depart.getZone());
                }
            }

            if (arrivee != null && arrivee.getZone() != null) 
            {
                if (!zonesVisitees.contains(arrivee.getZone())) {
                    zonesVisitees.add(arrivee.getZone());
                }
            }
        }
        // 2. Trouver le nombre maximum de cellules visitées dans une SEULE zone
        int nbMaxCelluleVisiteUneZone = 0;

        for (Zone zone : zonesVisitees) 
        {
            // Pour chaque zone, on va lister les cellules uniques que le joueur a touchées
            ArrayList<Cellule> cellulesDuJoueurDansCetteZone = new ArrayList<Cellule>();

            for (Liaison liaison : this.ensLiaisonVisite) 
            {
                Cellule depart  = liaison.getDepart();
                Cellule arrivee = liaison.getArrivee();

                // Vérification de la cellule de départ
                if (depart != null && depart.getZone() == zone) 
                {
                    if (!cellulesDuJoueurDansCetteZone.contains(depart)) {
                        cellulesDuJoueurDansCetteZone.add(depart);
                    }
                }

                // Vérification de la cellule d'arrivée
                if (arrivee != null && arrivee.getZone() == zone) 
                {
                    if (!cellulesDuJoueurDansCetteZone.contains(arrivee)) {
                        cellulesDuJoueurDansCetteZone.add(arrivee);
                    }
                }
            }

            // On regarde si cette zone contient plus de cellules visitées que le maximum actuel
            if (cellulesDuJoueurDansCetteZone.size() > nbMaxCelluleVisiteUneZone) 
            {
                nbMaxCelluleVisiteUneZone = cellulesDuJoueurDansCetteZone.size();
            }
        }


        // 3. Application de ta formule mathématique
        int scoreManche = nbZoneVisite * nbMaxCelluleVisiteUneZone;

        // Cumul dans le score global du joueur
        this.ajouterScore(scoreManche);

        return scoreManche;
    }       

}
