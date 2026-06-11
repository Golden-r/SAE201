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
    private JButton btnPiocherCarte;

    private JButton btnRond;
    private JButton btnRondSombre;
    private JButton btnCarre;
    private JButton btnCarreSombre;
    private JButton btnTriangle;
    private JButton btnTriangleSombre;
    private JButton btnEtoile;
    private JButton btnEtoileSombre;
    private JButton btnJoker;
    private JButton btnJokerSombre;

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

        JPanel panelCentre = new JPanel(new GridLayout(2, 5));

        this.btnRond = new JButton("ROND");
        this.btnRondSombre = new JButton("ROND SOMBRE");
        this.btnCarre = new JButton("CARRE");
        this.btnCarreSombre = new JButton("CARRE SOMBRE");
        this.btnTriangle = new JButton("TRIANGLE");
        this.btnTriangleSombre = new JButton("TRIANGLE SOMBRE");
        this.btnEtoile = new JButton("ETOILE");
        this.btnEtoileSombre = new JButton("ETOILE SOMBRE");
        this.btnJoker = new JButton("JOKER");
        this.btnJokerSombre = new JButton("JOKER SOMBRE");
        this.btnPiocherCarte = new JButton("PIOCHER ALEATOIRE");

        this.styliserBoutonCarte(this.btnRond);
        this.styliserBoutonCarte(this.btnRondSombre);
        this.styliserBoutonCarte(this.btnCarre);
        this.styliserBoutonCarte(this.btnCarreSombre);
        this.styliserBoutonCarte(this.btnTriangle);
        this.styliserBoutonCarte(this.btnTriangleSombre);
        this.styliserBoutonCarte(this.btnEtoile);
        this.styliserBoutonCarte(this.btnEtoileSombre);
        this.styliserBoutonCarte(this.btnJoker);
        this.styliserBoutonCarte(this.btnJokerSombre);

        panelCentre.add(this.btnRond);
        panelCentre.add(this.btnRondSombre);
        panelCentre.add(this.btnCarre);
        panelCentre.add(this.btnCarreSombre);
        panelCentre.add(this.btnTriangle);
        panelCentre.add(this.btnTriangleSombre);
        panelCentre.add(this.btnEtoile);
        panelCentre.add(this.btnEtoileSombre);
        panelCentre.add(this.btnJoker);
        panelCentre.add(this.btnJokerSombre);

        this.add(panelCentre, BorderLayout.CENTER);

        this.txtEtat = new JTextArea();
        this.txtEtat.setEditable(false);

        JPanel panelBas = new JPanel(new BorderLayout());
        panelBas.add(this.btnPiocherCarte, BorderLayout.NORTH);
        panelBas.add(new JScrollPane(this.txtEtat), BorderLayout.CENTER);

        this.add(panelBas, BorderLayout.SOUTH);

        this.btnAllerManche.addActionListener(this);
        this.btnMancheSuivante.addActionListener(this);
        this.btnPiocherCarte.addActionListener(this);

        this.btnRond.addActionListener(this);
        this.btnRondSombre.addActionListener(this);
        this.btnCarre.addActionListener(this);
        this.btnCarreSombre.addActionListener(this);
        this.btnTriangle.addActionListener(this);
        this.btnTriangleSombre.addActionListener(this);
        this.btnEtoile.addActionListener(this);
        this.btnEtoileSombre.addActionListener(this);
        this.btnJoker.addActionListener(this);
        this.btnJokerSombre.addActionListener(this);

        this.mettreAJourAffichage();
    }

    private void styliserBoutonCarte(JButton btn)
    {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
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

        if (e.getSource() == this.btnPiocherCarte)
        {
            this.ctrl.piocherCarteCourante();
        }

        if (e.getSource() == this.btnRond)
            this.ctrl.selectionnerCarte("ROND", false, false);

        if (e.getSource() == this.btnRondSombre)
            this.ctrl.selectionnerCarte("ROND", true, false);

        if (e.getSource() == this.btnCarre)
            this.ctrl.selectionnerCarte("CARRE", false, false);

        if (e.getSource() == this.btnCarreSombre)
            this.ctrl.selectionnerCarte("CARRE", true, false);

        if (e.getSource() == this.btnTriangle)
            this.ctrl.selectionnerCarte("TRIANGLE", false, false);

        if (e.getSource() == this.btnTriangleSombre)
            this.ctrl.selectionnerCarte("TRIANGLE", true, false);

        if (e.getSource() == this.btnEtoile)
            this.ctrl.selectionnerCarte("ETOILE", false, false);

        if (e.getSource() == this.btnEtoileSombre)
            this.ctrl.selectionnerCarte("ETOILE", true, false);

        if (e.getSource() == this.btnJoker)
            this.ctrl.selectionnerCarte(null, false, true);

        if (e.getSource() == this.btnJokerSombre)
            this.ctrl.selectionnerCarte(null, true, true);

        this.mettreAJourAffichage();
    }

    private void mettreAJourAffichage()
    {
        this.lblManche.setText("Manche : " + this.ctrl.getNumeroMancheCourante());
        this.txtEtat.setText(this.ctrl.getTexteEtatPartie());
    }
}