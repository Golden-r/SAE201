package metier ;

import java.util.ArrayList ;
import java.awt.Color;

/* SAE 2.01 | Développement d'une application 
* Zone
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Zone 
{
	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private Color couleur;
	private EZone typeZone;
	private int   id;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Zone ()// EZone typeZone  )
    {
        this.couleur  = new Color(200,200,200);
		this.typeZone = typeZone;
		this.id       = 0;
	}


	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public        Color    getCouleur     (){ return this.couleur  ;}
	public        EZone    getTypeZone    (){ return this.typeZone ;}
	public        int      getId          (){ return this.id       ;}
	

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	public void setCouleur ( Color couleur ) 
	{
		this.couleur = couleur ;

	}

	public void setCouleur ( EZone typeZone, int id, int occurrence )
	{
		this.typeZone = typeZone;
		this.id       = id;

		Color base = typeZone.getCouleur();
		int variation = occurrence * 25;

		int r = Math.max(0, Math.min(255, base.getRed()   + variation));
		int g = Math.max(0, Math.min(255, base.getGreen() + variation));
		int b = Math.max(0, Math.min(255, base.getBlue()  + variation));

		this.couleur = new Color(r, g, b);
	}

	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

}