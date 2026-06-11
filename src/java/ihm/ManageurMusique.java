package ihm;

import javax.sound.sampled.*;
import java.io.File;

public class ManageurMusique 
{
    private Clip clip;
    private FloatControl controleVolume;

    public ManageurMusique(String cheminAudio) 
    {
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

    public void jouerEnBoucle() 
    {
        if (this.clip != null) 
        {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY); 
            this.clip.start();
        }
    }

    public void setVolume(int volumeSlider) 
    {
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
}
