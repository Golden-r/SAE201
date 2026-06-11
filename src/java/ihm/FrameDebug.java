package ihm;

import controleur.Controleur;

import java.awt.Panel;

import javax.swing.JFrame;

public class FrameDebug extends JFrame
{
    public FrameDebug(Controleur ctrl)
    {
        this.setTitle("Debug");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

        this.add(new PanelDebug(ctrl));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
