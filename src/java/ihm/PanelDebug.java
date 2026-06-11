package ihm;

import controleur.Controleur;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDebug extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JLabel lblManche;
    private JTextField txtManche;
    private JButton btnAllerManche;
    private JButton btnMancheSuivante;

    private JPanel panelCartes;
    private JButton[] tabBtnCartes;

    private JTextArea txtEtat;

    public PanelDebug(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());

        JPanel panelHaut = new JPanel(new GridLayout(2, 2));

        this.lblManche = new JLabel("Manche : 0");
        this.txtManche = new JTextField("1");
        this.btnAllerManche = new JButton("Aller a la manche");
        this.btnMancheSuivante = new JButton("Manche suivante");

        panelHaut.add(this.lblManche);
        panelHaut.add(this.txtManche);
        panelHaut.add(this.btnAllerManche);
        panelHaut.add(this.btnMancheSuivante);

        this.add(panelHaut, BorderLayout.NORTH);

        String[] tabCartes = this.ctrl.getLibellesCartes();

        this.panelCartes = new JPanel(new GridLayout(0, 4));
        this.tabBtnCartes = new JButton[tabCartes.length];

        for (int cpt = 0; cpt < tabCartes.length; cpt++)
        {
            this.tabBtnCartes[cpt] = new JButton(tabCartes[cpt]);
            this.tabBtnCartes[cpt].addActionListener(this);
            this.panelCartes.add(this.tabBtnCartes[cpt]);
        }

        this.add(new JScrollPane(this.panelCartes), BorderLayout.CENTER);

        this.txtEtat = new JTextArea();
        this.txtEtat.setEditable(false);

        this.add(new JScrollPane(this.txtEtat), BorderLayout.SOUTH);

        this.btnAllerManche.addActionListener(this);
        this.btnMancheSuivante.addActionListener(this);

        this.mettreAJourAffichage();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnAllerManche)
        {
            try
            {
                int numManche = Integer.parseInt(this.txtManche.getText());
                this.ctrl.allerALaManche(numManche);
            }
            catch (Exception ex)
            {
            }
        }

        if (e.getSource() == this.btnMancheSuivante)
        {
            this.ctrl.passerALaMancheSuivante();
        }

        for (int cpt = 0; cpt < this.tabBtnCartes.length; cpt++)
        {
            if (e.getSource() == this.tabBtnCartes[cpt])
            {
                this.ctrl.selectionnerCarte(cpt);
            }
        }

        this.mettreAJourAffichage();
    }

    private void mettreAJourAffichage()
    {
        this.lblManche.setText("Manche : " + this.ctrl.getNumeroMancheCourante());
        this.txtEtat.setText(this.ctrl.getTexteEtatPartie());
    }
}