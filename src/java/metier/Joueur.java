package metier ;

import java.util.ArrayList ;

public class Joueur 
{
    /*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    private String pseudo ;
    private ArrayList<Cellule> ensBaseVisite ;
    private ArrayList<Liaison> ensLiaisonVisite    ;

    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Joueur ( String pseudo ) 
    {
        this.pseudo           = pseudo ;
        this.ensLiaisonVisite = new ArrayList<Liaison> () ;
        this.ensBaseVisite    = new ArrayList<Cellule> () ;

    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public String             getPseudo           () { return this.pseudo              ; }
    public ArrayList<Liaison> getensLiaisonVisite () { return this.ensLiaisonVisite    ; }
    public ArrayList<Cellule> getEnsBaseVisite    () { return this.ensBaseVisite       ; }

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void   setPseudo           ( String             pseudo           ) { this.pseudo           = pseudo           ;}
    public void   setensLiaisonVisite ( ArrayList<Liaison> ensLiaisonVisite ) { this.ensLiaisonVisite = ensLiaisonVisite ;}
    public void   setEnsBaseVisite    ( ArrayList<Cellule> ensBaseVisite    ) { this.ensBaseVisite    = ensBaseVisite    ;}

    /*----------------------------*/
	/*  Test                      */
	/*----------------------------*/


    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public void ajouterLiaison ( Liaison liaison ) { this.ensLiaisonVisite.add   ( liaison ) ; }
    public void retirerLiaison ( Liaison liaison ) { this.ensLiaisonVisite.remove( liaison ) ; }

    public void ajouterBaseVisite ( Cellule Base ) { this.ensBaseVisite.add   ( Base ) ; }
    public void retirerBaseVisite ( Cellule Base ) { this.ensBaseVisite.remove( Base ) ; }

}