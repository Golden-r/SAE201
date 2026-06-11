package ihm;

import controleur.Controleur;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
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

    private JButton btnMaisons;
    private JButton btnMaisonsSombre;
    private JButton btnImmeubles;
    private JButton btnImmeublesSombre;
    private JButton btnHotels;
    private JButton btnHotelsSombre;
    private JButton btnRestaurants;
    private JButton btnRestaurantsSombre;
    private JButton btnEcoles;
    private JButton btnEcolesSombre;
    private JButton btnUsines;
    private JButton btnUsinesSombre;
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

        String[] libelles = this.ctrl.getLibellesSymboles();

        JPanel panelCentre = new JPanel(new GridLayout(2, 7));

        this.btnMaisons = new JButton(libelles[0]);
        this.btnMaisonsSombre = new JButton(libelles[0] + " sombre");
        this.btnImmeubles = new JButton(libelles[1]);
        this.btnImmeublesSombre = new JButton(libelles[1] + " sombre");
        this.btnHotels = new JButton(libelles[2]);
        this.btnHotelsSombre = new JButton(libelles[2] + " sombre");
        this.btnRestaurants = new JButton(libelles[3]);
        this.btnRestaurantsSombre = new JButton(libelles[3] + " sombre");
        this.btnEcoles = new JButton(libelles[4]);
        this.btnEcolesSombre = new JButton(libelles[4] + " sombre");
        this.btnUsines = new JButton(libelles[5]);
        this.btnUsinesSombre = new JButton(libelles[5] + " sombre");
        this.btnJoker = new JButton("Joker");
        this.btnJokerSombre = new JButton("Joker sombre");
        this.btnPiocherCarte = new JButton("PIOCHER ALEATOIRE");

        this.styliserBoutonCarte(this.btnMaisons);
        this.styliserBoutonCarte(this.btnMaisonsSombre);
        this.styliserBoutonCarte(this.btnImmeubles);
        this.styliserBoutonCarte(this.btnImmeublesSombre);
        this.styliserBoutonCarte(this.btnHotels);
        this.styliserBoutonCarte(this.btnHotelsSombre);
        this.styliserBoutonCarte(this.btnRestaurants);
        this.styliserBoutonCarte(this.btnRestaurantsSombre);
        this.styliserBoutonCarte(this.btnEcoles);
        this.styliserBoutonCarte(this.btnEcolesSombre);
        this.styliserBoutonCarte(this.btnUsines);
        this.styliserBoutonCarte(this.btnUsinesSombre);
        this.styliserBoutonCarte(this.btnJoker);
        this.styliserBoutonCarte(this.btnJokerSombre);

        this.mettreImageBouton(this.btnMaisons,           "src/java/ressource/images/Cartes/Maison_Carte_B.png");
        this.mettreImageBouton(this.btnMaisonsSombre,     "src/java/ressource/images/Cartes/Maison_Carte_N.png");

        this.mettreImageBouton(this.btnImmeubles,         "src/java/ressource/images/Cartes/Immeuble_Carte_B.png");
        this.mettreImageBouton(this.btnImmeublesSombre,   "src/java/ressource/images/Cartes/Immeuble_Carte_N.png");

        this.mettreImageBouton(this.btnHotels,            "src/java/ressource/images/Cartes/Hôtel_Carte_B.png");
        this.mettreImageBouton(this.btnHotelsSombre,      "src/java/ressource/images/Cartes/Hôtel_Carte_N.png");

        this.mettreImageBouton(this.btnRestaurants,       "src/java/ressource/images/Cartes/Restaurant_Carte_B.png");
        this.mettreImageBouton(this.btnRestaurantsSombre, "src/java/ressource/images/Cartes/Restaurant_Carte_N.png");

        this.mettreImageBouton(this.btnEcoles,            "src/java/ressource/images/Cartes/École_Carte_B.png");
        this.mettreImageBouton(this.btnEcolesSombre,      "src/java/ressource/images/Cartes/École_Carte_N.png");

        this.mettreImageBouton(this.btnUsines,            "src/java/ressource/images/Cartes/Usine_Carte_B.png");
        this.mettreImageBouton(this.btnUsinesSombre,      "src/java/ressource/images/Cartes/Usine_Carte_N.png");

        this.mettreImageBouton(this.btnJoker,             "src/java/ressource/images/Cartes/Joker_Carte_B.png");
        this.mettreImageBouton(this.btnJokerSombre,       "src/java/ressource/images/Cartes/Joker_Carte_N.png");

        panelCentre.add(this.btnMaisons);
        panelCentre.add(this.btnMaisonsSombre);
        panelCentre.add(this.btnImmeubles);
        panelCentre.add(this.btnImmeublesSombre);
        panelCentre.add(this.btnHotels);
        panelCentre.add(this.btnHotelsSombre);
        panelCentre.add(this.btnRestaurants);
        panelCentre.add(this.btnRestaurantsSombre);
        panelCentre.add(this.btnEcoles);
        panelCentre.add(this.btnEcolesSombre);
        panelCentre.add(this.btnUsines);
        panelCentre.add(this.btnUsinesSombre);
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

        this.btnMaisons.addActionListener(this);
        this.btnMaisonsSombre.addActionListener(this);
        this.btnImmeubles.addActionListener(this);
        this.btnImmeublesSombre.addActionListener(this);
        this.btnHotels.addActionListener(this);
        this.btnHotelsSombre.addActionListener(this);
        this.btnRestaurants.addActionListener(this);
        this.btnRestaurantsSombre.addActionListener(this);
        this.btnEcoles.addActionListener(this);
        this.btnEcolesSombre.addActionListener(this);
        this.btnUsines.addActionListener(this);
        this.btnUsinesSombre.addActionListener(this);
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

    private void mettreImageBouton(JButton btn, String chemin)
    {
        ImageIcon icon = new ImageIcon(chemin);

        if (icon.getIconWidth() == -1)
        {
            System.out.println("Image introuvable : " + chemin);
            return;
        }

        Image img = icon.getImage().getScaledInstance(90, 140, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(img));
        btn.setText("");
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

        if (e.getSource() == this.btnMaisons)
            this.ctrl.selectionnerCarte("MAISONS", false, false);

        if (e.getSource() == this.btnMaisonsSombre)
            this.ctrl.selectionnerCarte("MAISONS", true, false);

        if (e.getSource() == this.btnImmeubles)
            this.ctrl.selectionnerCarte("IMMEUBLES", false, false);

        if (e.getSource() == this.btnImmeublesSombre)
            this.ctrl.selectionnerCarte("IMMEUBLES", true, false);

        if (e.getSource() == this.btnHotels)
            this.ctrl.selectionnerCarte("HOTELS", false, false);

        if (e.getSource() == this.btnHotelsSombre)
            this.ctrl.selectionnerCarte("HOTELS", true, false);

        if (e.getSource() == this.btnRestaurants)
            this.ctrl.selectionnerCarte("RESTAURANTS", false, false);

        if (e.getSource() == this.btnRestaurantsSombre)
            this.ctrl.selectionnerCarte("RESTAURANTS", true, false);

        if (e.getSource() == this.btnEcoles)
            this.ctrl.selectionnerCarte("ECOLES", false, false);

        if (e.getSource() == this.btnEcolesSombre)
            this.ctrl.selectionnerCarte("ECOLES", true, false);

        if (e.getSource() == this.btnUsines)
            this.ctrl.selectionnerCarte("USINES", false, false);

        if (e.getSource() == this.btnUsinesSombre)
            this.ctrl.selectionnerCarte("USINES", true, false);

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