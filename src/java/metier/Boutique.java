package metier ;

import java.util.ArrayList ;


/* SAE 2.01 | Développement d'une application 
* Boutique
* 
* Date     : 11/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/


public class Boutique 
{

    /*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    private ArrayList<Objet> ensObjets ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/
    
    public Boutique () 
    {
        this.ensObjets = new ArrayList<Objet>() ;
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public ArrayList<Objet> getObjets () { return this.ensObjets ;}
    public Objet            getObjet  (String objet )  
    {
        for(Objet obj : this.ensObjets)
        {
            if(obj.getNom().equals(objet))
                return obj;
        }
        return null;
    }
    

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public ArrayList<Objet> setObjets ( ArrayList<Objet> ensObjets ) { return this.ensObjets = ensObjets ;}

    /*----------------------------*/
	/*  Test                      */
	/*----------------------------*/


    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public void     ajouterObjets ( Objet objet ) { this.ensObjets.add( objet ) ;}

    public boolean  achatObjet    ( Objet objet , Joueur j) 
    {
        if( j == null       || objet == null      ) { return false ;}
        if( objet.getPrix() > j.getMonaieJoueur() ) { return false ;}

        j.ajouterObjets(objet) ;
        j.retirerMonaisJoueur(objet.getPrix() ) ;

        return true ;
    }

    public boolean  VendreObjet    ( Objet objet , Joueur j) 
    {
        if( j == null       || objet == null      ) { return false ;}
        
        j.retirerObjets      (objet) ;
        j.ajouterMonaisJoueur(objet.getPrix() ) ;

        return true ;
    }
}