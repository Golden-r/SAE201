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
	private static int NB_ZONES = 0;

	/*----------------------------*/ 
	/*  Attributs de la classe    */
	/*----------------------------*/

    private Color couleur;
	private EZone typeZone;
	private int   id;

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public Zone ( EZone typeZone  )
    {
        this.couleur  = new Color(200,200,200);
		this.typeZone = typeZone;
		this.id       = Zone.NB_ZONES++;
	}

    public Zone ( EZone typeZone, int id  )
    {
        this.couleur  = new Color(200,200,200) ;
		this.typeZone = typeZone ;
		this.id       = id ;

		if ( id >= Zone.NB_ZONES )
		{
			Zone.NB_ZONES = id + 1 ;
		}
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

	public void setCouleur ( EZone typeZone, int occurrence )
	{
		this.id       = id;

		Color base      = typeZone.getCouleur();
		int   variation = occurrence * 25;

		int r = base.getRed()   + variation;
		int g = base.getGreen() + variation;
		int b = base.getBlue()  + variation;

		while (r > 255 || g > 255 || b > 255 || r + g + b > 650)
		{
			r -= 85;
			g -= 85;
			b -= 85;
		}

		r = Math.max(0, r);
		g = Math.max(0, g);
		b = Math.max(0, b);

		this.couleur = new Color(r, g, b);
	}
}