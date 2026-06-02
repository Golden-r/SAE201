package ihm;

import metier.Enums;
import metier.Plateau;
import metier.TypeSymbole;

import javax.swing.*;
import java.awt.*;

public class FrameEditeur extends JFrame
{

    public FrameEditeur()
    {
        super("Editeur - Réseaux urbains");

        Plateau plateau = demanderCreationPlateau();

        PanelPlateau panel = new PanelPlateau(plateau);

        JComboBox<TypeSymbole> cbSymbole = new JComboBox<>(TypeSymbole.values());
        cbSymbole.setSelectedIndex(0);

        JComboBox<Enums> cbCouleurBase = new JComboBox<>(new Enums[]{Enums.BLEU});

        JToggleButton btnAjouterSommet = new JToggleButton("Placer sommet");
        JToggleButton btnBase = new JToggleButton("Base");
        JToggleButton btnZone = new JToggleButton("Dessiner zones");

        ButtonGroup group = new ButtonGroup();
        group.add(btnAjouterSommet);
        group.add(btnBase);
        group.add(btnZone);
        btnAjouterSommet.setSelected(true);

        cbSymbole.addActionListener(e -> panel.setSymboleCourant((TypeSymbole) cbSymbole.getSelectedItem()));
        cbCouleurBase.addActionListener(e -> panel.setCouleurBaseCourante((Enums) cbCouleurBase.getSelectedItem()));

        btnAjouterSommet.addActionListener(e -> panel.setMode(PanelPlateau.Mode.EMPLACER_SOMMET));
        btnBase.addActionListener(e -> panel.setMode(PanelPlateau.Mode.TOGGLE_BASE));
        btnZone.addActionListener(e -> panel.setMode(PanelPlateau.Mode.DESSINER_ZONE));

        panel.setSymboleCourant((TypeSymbole) cbSymbole.getSelectedItem());
        panel.setCouleurBaseCourante((Enums) cbCouleurBase.getSelectedItem());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Symbole"));
        top.add(cbSymbole);
        top.add(btnAjouterSommet);
        top.add(btnBase);
        top.add(btnZone);
        top.add(new JLabel("Couleur base"));
        top.add(cbCouleurBase);



        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(panel), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private Plateau demanderCreationPlateau()
    {
        JTextField tfL = new JTextField("7");
        JTextField tfH = new JTextField("7");
        JTextField tfT = new JTextField("40");

        JPanel p = new JPanel(new GridLayout(3, 2));
        p.add(new JLabel("Largeur"));
        p.add(tfL);
        p.add(new JLabel("Hauteur"));
        p.add(tfH);
        p.add(new JLabel("Taille case (px)"));
        p.add(tfT);

        int res = JOptionPane.showConfirmDialog(this, p, "Taille du plateau", JOptionPane.OK_CANCEL_OPTION);
        if (res != JOptionPane.OK_OPTION) {
            return new Plateau(7, 7, 40);
        }

        int l = Integer.parseInt(tfL.getText().trim());
        int h = Integer.parseInt(tfH.getText().trim());
        int t = Integer.parseInt(tfT.getText().trim());
        Plateau plateau = new Plateau(l, h, t);

        // zone initiale pour permettre le dessin
        plateau.creerZoneSiInexistante("Zone 1", new Color(220, 220, 220));
        return plateau;
    }

}