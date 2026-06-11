package metier;

import javax.sound.sampled.*;
import java.io.File;

/* SAE 2.01 | Développement d'une application 
* ManageurMusique
*
* Date     : 11/06/2026
* @author  : AZAANOUNE Rayan , BASSAM YOUSSIF Youssif , FERRIER Mathys , LARBI Timothe 
* Groupe   : 4
*/

public class ManageurMusique 
{
    /*----------------------------*/
	/*  Attributs de la classe    */
	/*----------------------------*/

    private Clip         clip;
    private FloatControl controleVolume;
    private int          memoireVolume ;


    /*----------------------------*/
	/*  Constructeur de la classe */
	/*----------------------------*/

    public ManageurMusique(String cheminAudio) 
    {
        this.memoireVolume = 50 ;

        try 
        {
            File fichierAudio = new File(cheminAudio);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fichierAudio);
            
            this.clip = AudioSystem.getClip();
            this.clip.open(audioStream);
            
            this.controleVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } 
        catch (Exception e) 
        {
            System.out.println("Erreur de chargement de la musique : " + e.getMessage());
        }
    }

    /*----------------------------*/
	/*  Accesseur                 */
	/*----------------------------*/

    public int  getVolumeMusique()           { return this.memoireVolume   ;}

    /*----------------------------*/
	/*  Modificateur              */
	/*----------------------------*/

    public void setVolumeMusique(int volumeSlider) 
    {
        this.memoireVolume = volumeSlider; 
        
        if (this.controleVolume != null) 
        {
            if (volumeSlider <= 0) 
            {
                this.controleVolume.setValue(this.controleVolume.getMinimum());
            } 
            else 
            {
                float pourcentage = volumeSlider / 100f;
                float decibels = (float) (Math.log(pourcentage) / Math.log(10.0) * 20.0);
                this.controleVolume.setValue(decibels);
            }
        }
    }

    public void setMute(boolean estMute) 
    {
        if (this.controleVolume != null) 
        {
            if (estMute) 
            {
                this.controleVolume.setValue(this.controleVolume.getMinimum());
            } 
            else 
            {
                if (this.memoireVolume <= 0) 
                {
                    this.controleVolume.setValue(this.controleVolume.getMinimum());
                } 
                else 
                {
                    float pourcentage = this.memoireVolume / 100f;
                    float decibels = (float) (Math.log(pourcentage) / Math.log(10.0) * 20.0);
                    this.controleVolume.setValue(decibels);
                }
            }
        }
    }


    /*----------------------------*/
	/*  Méthodes                  */
	/*----------------------------*/

    public void jouerEnBoucle() 
    {
        if (this.clip != null) 
        {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY); 
            this.clip.start();
        }
    }

   


    
   
}
