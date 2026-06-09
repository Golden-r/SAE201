package ihm;

import controleur.Controleur;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Rectangle;

import metier.ESymbole;
import metier.ECouleur;

 
/* SAE 2.01 | Développement d'une application 
* PanelModification
*
* Date     : 02/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class PanelModification extends JPanel 
{
	private Controleur ctrl ;

	private PanelPlateau      			panelPlateau ;
	private PanelPlateauNord  			panelPlateauNord ;
	private PanelPlateauOuestZone 		panelPlateauOuestZone ; 
	private PanelPlateauOuestSymbole 	panelPlateauOuestSymbole ;
	private PanelPlateauOuestBase       panelPlateauOuestBase;
	private PanelPlateauSud 			panelSud ;
	private JPanel 			  			panelEst ;


	public PanelModification( Controleur ctrl ) 
	{
		this.ctrl = ctrl ;

        this.setLayout( new BorderLayout() );
		
		/* ----------------------------- */
		/* Création des composants       */
		/* ----------------------------- */

		this.panelPlateau 		= new PanelPlateau( this.ctrl, this );

		this.panelSud   		= new PanelPlateauSud( this.ctrl ) ;
		this.panelPlateauNord   = new PanelPlateauNord( this.ctrl , this , this.panelSud ) ;
		this.panelEst   		= new JPanel() ;
		this.panelPlateauOuestZone 		= new PanelPlateauOuestZone( this.ctrl ) ;
		this.panelPlateauOuestSymbole 	= new PanelPlateauOuestSymbole	( this.ctrl ) ;
		this.panelPlateauOuestBase      = new PanelPlateauOuestBase( this.ctrl );
        
		this.panelPlateauNord .setBackground( Color.LIGHT_GRAY );
		this.panelSud  .setBackground( Color.LIGHT_GRAY  );
		this.panelEst  .setBackground( Color.LIGHT_GRAY  );

		/* ----------------------------- */
		/* Positionnement des composants */
		/* ----------------------------- */

		this.add( this.panelPlateau , BorderLayout.CENTER );	

		this.add( this.panelPlateauNord      , BorderLayout.NORTH );
		this.add( this.panelSud              , BorderLayout.SOUTH );
		this.add( this.panelEst              , BorderLayout.EAST  );
		this.add( this.panelPlateauOuestZone , BorderLayout.WEST  );

		/*---------------------------*/
		/* Activation des composants */ 
		/*---------------------------*/

        
		this.mettreAJourNbZones();
	}
	
	public void passerEtapeZone()
	{

		if ( this.panelPlateauOuestZone    != null){ this.remove(this.panelPlateauOuestZone);  }
		if ( this.panelPlateauOuestSymbole != null){ this.remove(this.panelPlateauOuestSymbole) ;}
		if ( this.panelPlateauOuestBase    != null){ this.remove(this.panelPlateauOuestBase)    ;}

		this.ctrl.setEtapeConception( 1 );
		this.add(this.panelPlateauOuestZone, BorderLayout.WEST);

		this.panelPlateauNord.setTextBtnSuivant() ;
		

		this.revalidate();
		this.repaint();
	}

	public void passerEtapeSymbole()
	{
		if ( this.panelPlateauOuestZone    != null){ this.remove(this.panelPlateauOuestZone)    ;}
		if ( this.panelPlateauOuestSymbole != null){ this.remove(this.panelPlateauOuestSymbole) ;}
		if ( this.panelPlateauOuestBase    != null){ this.remove(this.panelPlateauOuestBase)    ;}
		
		this.ctrl.setEtapeConception( 2 );
		this.add(this.panelPlateauOuestSymbole, BorderLayout.WEST);

		this.panelPlateauNord.setTextBtnSuivant() ;
		
		this.revalidate();
		this.repaint();
	}

	public void passerEtapeBase()
	{
		if ( this.panelPlateauOuestZone    != null){ this.remove(this.panelPlateauOuestZone)    ;}
		if ( this.panelPlateauOuestSymbole != null){ this.remove(this.panelPlateauOuestSymbole) ;}
		if ( this.panelPlateauOuestBase    != null){ this.remove(this.panelPlateauOuestBase)    ;}

		this.ctrl.setEtapeConception( 3 );
		this.add(this.panelPlateauOuestBase, BorderLayout.WEST);

		this.panelPlateauNord.setTextBtnEnregistrer() ;
		this.ctrl.afficherLiasons();
		
		this.revalidate();
		this.repaint();
	}


	public void mettreAJourNbZones()
	{	
		this.panelPlateauNord.setNbZone( this.ctrl.getNbZonesDistinctes() ) ;

		if ( this.panelPlateauOuestZone != null ){ this.panelPlateauOuestZone.mettreAJourPrevisu() ;}
	}

	public void majBtnEnregistrer()
	{
		this.panelPlateauNord.majBtnEnregistrer() ;
	}

	public void majBtnSuivant()
	{
		this.panelPlateauNord.majBtnSuivant() ;
	}
	
	

	public boolean  getModeSelection()        { return this.panelPlateauOuestZone   .getModeSelection     ()     ;}
	public int      getZoneSelectioner()      { return this.panelPlateauOuestZone   .getZoneSelectioner   ()     ;}
	public boolean  getModePlacementSymbole() { return this.panelPlateauOuestSymbole.getModePlacement     ()     ;}
	public ESymbole getSymboleSelectionne ()  { return this.panelPlateauOuestSymbole.getSymboleSelectionne()     ;}
	public ECouleur getBaseSelectione      () { return this.panelPlateauOuestBase   .getBaseSelectionne   ()     ;}
	public boolean  getModePlacementBase()    { return this.panelPlateauOuestBase   .getModePlacement     ()     ;}
}
