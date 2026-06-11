package controleur;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import ihm.FrameJeu;
import ihm.FrameMenu;
import ihm.FramePrevisuPlateau;
import ihm.ManageurFont;
import java.awt.Color;

import metier.*;

/* SAE 2.01 | Développement d'une application 
* Controleur
*
* Date     : 10/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class Controleur
{
	final static int WIDTH_MENU  = 1000;
	final static int HEIGHT_MENU =  500;
	
	/*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

	private FrameJeu     frameJeu ;
	private FrameMenu    frameMenu;
	private FramePrevisuPlateau framePrevisu;

	private Partie         partie   ;
	

	/*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

	public Controleur()
	{

	}

	/*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

	public int getTailleLargeur() { 
        return (this.partie != null && this.partie.getPlateau() != null) ? this.partie.getPlateau().getTailleLargeur() : 0;
    }
    
    public int getTailleLongueur() { 
        return (this.partie != null && this.partie.getPlateau() != null) ? this.partie.getPlateau().getTailleLongueur() : 0;
    }
    
    public int getTailleCellule() { 
        return (this.partie != null && this.partie.getPlateau() != null) ? this.partie.getPlateau().getTailleCellule() : 0;
    }
	public Cellule getCellule(int col, int lig) { 
        if (this.partie == null || this.partie.getPlateau() == null) return null;
        return this.partie.getPlateau().getCellule(col, lig);
    }
    
    public boolean getPrevisu() { 
        if (this.partie == null || this.partie.getPlateau() == null) return false;
        return this.partie.getPlateau().getPrevisu();
    }
    
    public int getNbLiaisons() { 
        if (this.partie == null || this.partie.getPlateau() == null) return 0;
        return this.partie.getPlateau().getEnsLiaison().size();
    }
    
    public Color getCouleurLiaison(int indice) {
        if (this.partie == null || this.partie.getPlateau() == null) return Color.BLACK;
        
        Liaison l = this.partie.getPlateau().getEnsLiaison().get(indice);

        if (l != null && l.getReseau() != null)
            return l.getReseau().getCouleur();

        return Color.BLACK;
    }
	public int[][] getCheminLiaison(int indice) { 
        if (this.partie == null || this.partie.getPlateau() == null) return null;
        return this.partie.getPlateau().getCheminLiaison(indice);
    }

	public String[]   getNomJeu          () { return EModes.getNomModes        ()           ; }
	public String[]   getDescriptionModes() { return EModes.getDescriptionModes()           ; }

	public Dimension  getSizeMenu        () { return new Dimension(WIDTH_MENU, HEIGHT_MENU) ; }

	public Partie     getPartie          () { return this.partie                            ; }
	public String[] getListeSauvegardes() { return GestionFichier.getListeSauvegardes(); }
	public Font       retourneFont       (String nom, float size) { return ManageurFont.getFont(nom, size); }

	public String[] getLibellesSymboles()
	{
		ESymbole[] tabSymboles = ESymbole.values();
		String[] tabLibelles = new String[tabSymboles.length];

		for (int i = 0; i < tabSymboles.length; i++)
		{
			tabLibelles[i] = tabSymboles[i].getLibelle();
		}

		return tabLibelles;
	}


	/*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

	
	/*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/
	public void lancerMenu()
	{
		this.frameMenu = new FrameMenu(this);
	}

	public void LancerFramePrevisu(String nomFichier)
	{
		File fichier;
		PlateauData proprietes;
		Plateau plateauPrevisu;


		fichier = new File("./src/ressource/data/" + nomFichier);
		if ( fichier == null || !fichier.exists() ) return;
		
		
		proprietes = GestionFichier.lireFichier(fichier);
		if(proprietes == null) return;
		
		plateauPrevisu = new Plateau(proprietes.tailleLongueur, proprietes.tailleLargeur, proprietes.tailleCellule, proprietes.lstCouleur, proprietes.lstSymbole);
		
		if(proprietes.lstCellules != null){ plateauPrevisu.chargerDonnees(proprietes.lstCellules);}
		
		if (this.framePrevisu == null || !this.framePrevisu.isDisplayable()) 
		{
			this.framePrevisu = new FramePrevisuPlateau(this, plateauPrevisu);
		} 
		else { this.framePrevisu.majPlateau(plateauPrevisu);}
	}


	public void lancerPartie(File fichier, int nbJoueur, EModes mode, boolean modeDebug)
	{
		PlateauData proprietes ;
		Plateau     plateau    ;

		if(fichier == null || !fichier.exists())
			return;
		
		proprietes = GestionFichier.lireFichier(fichier) ;

		if(proprietes == null)
			return;
		
		if (this.framePrevisu != null && this.framePrevisu.isDisplayable()) 
		{
			this.framePrevisu.dispose();
		}

		plateau = new Plateau(proprietes.tailleLongueur, proprietes.tailleLargeur, proprietes.tailleCellule, proprietes.lstCouleur, proprietes.lstSymbole);
		
		if(proprietes.lstCellules != null)
			plateau.chargerDonnees(proprietes.lstCellules);
		
		this.partie   = new Partie(plateau, nbJoueur, mode, modeDebug);
		this.frameJeu = new FrameJeu(this);
	}

	public void passerALaMancheSuivante()
	{
		if (this.partie == null) return;

		this.partie.passerManche();
	}

	public void allerALaManche(int numManche)
	{
		if (this.partie == null) return;
		if (numManche < 1) return;

		while (this.partie.getManche() != null && this.partie.getMancheCourante() < numManche)
		{
			this.partie.passerManche();
		}
	}

	public void piocherCarteCourante()
	{
		if (this.partie == null) return;
		if (this.partie.getManche() == null) return;

		this.partie.getManche().piocherCarte();
	}

	public void selectionnerCarte(String symbole, boolean estSombre, boolean estJoker)
	{
		if (this.partie == null) return;
		if (this.partie.getManche() == null) return;

		ESymbole symboleChoisi = null;

		if (!estJoker && symbole != null && !symbole.isEmpty())
			symboleChoisi = ESymbole.valueOf(symbole);

		this.partie.getManche().piocherCarteSpecifique(
			new Carte(symboleChoisi, estSombre, estJoker)
		);
	}

	public String getTexteEtatPartie()
	{
		if (this.partie == null)
			return "Aucune partie en cours.";

		if (this.partie.getManche() == null)
			return "Fin de partie.\nManche courante : " + this.partie.getMancheCourante();

		String texte = "";
		texte += "Mode : " + this.partie.getMode() + "\n";
		texte += "Manche : " + this.partie.getMancheCourante() + " / " + this.partie.getNbMancheMax() + "\n";

		if (this.partie.getManche().getCarteCourante() == null)
		{
			texte += "Carte : aucune\n";
		}
		else
		{
			texte += "Carte symbole : " + this.partie.getManche().getCarteCourante().getSymbole() + "\n";
			texte += "Carte sombre : " + this.partie.getManche().getCarteCourante().estSombre() + "\n";
			texte += "Carte joker : " + this.partie.getManche().getCarteCourante().estJoker() + "\n";
		}

		return texte;
	}

	public int getNumeroMancheCourante()
	{
		if (this.partie == null) return 0;

		return this.partie.getMancheCourante();
	}

	

	/*----------------------------*/
	/*   Main                     */
	/*----------------------------*/

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		
		ctrl.lancerMenu() ;
	}

}