
import ihm.FrameEditeur;

public class Controleur
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usage : java Controleur [jeu|editeur]");
            return;
        }

        if (args[0].equals("jeu"))
        {
            System.out.println("Lancement du jeu");
        }
        else // editeur
        {
            FrameEditeur frame = new FrameEditeur();
            frame.setVisible(true);
        }
    }
}

