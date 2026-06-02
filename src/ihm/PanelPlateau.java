package ihm;

import metier.Enums;
import metier.Plateau;


import metier.Sommet;
import metier.TypeSymbole;
import metier.Zone;

import javax.swing.*;
import java.awt.*;

public class PanelPlateau extends JPanel {

    public enum Mode {
        EMPLACER_SOMMET,
        TOGGLE_BASE,
        DESSINER_ZONE
    }

    private final Plateau plateau;

    private Mode mode = Mode.EMPLACER_SOMMET;

    private TypeSymbole symboleCourant = TypeSymbole.BLEU;

    private Enums couleurBaseCourante = Enums.BLEU;

    // utilisé pour modifier le poids de la zone récemment dessinée
    private Zone zoneActuelle = null;

    public PanelPlateau(Plateau plateau) {
        this.plateau = plateau;

        setPreferredSize(new Dimension(plateau.getLargeur() * plateau.getTailleCase(),
                plateau.getHauteur() * plateau.getTailleCase()));

        setBackground(Color.WHITE);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int t = plateau.getTailleCase();
                int x = e.getX() / t;
                int y = e.getY() / t;

                if (!plateau.dansGrille(x, y)) return;

                switch (mode) {
                    case EMPLACER_SOMMET -> {
                        plateau.poserSommet(x, y, symboleCourant);
                    }
                    case TOGGLE_BASE -> {
                        plateau.toggleBase(x, y, couleurBaseCourante);
                    }
                    case DESSINER_ZONE -> {
                        Zone zone = plateau.getZoneDeCellule(x, y);
                        if (zone == null && !plateau.getZones().isEmpty()) {
                            zone = plateau.getZones().get(0);
                        }
                        plateau.assignerCelluleAZone(x, y, zone);
                        zoneActuelle = zone;
                    }
                }

                repaint();
            }
        });
    }

    public void setMode(Mode mode) { this.mode = mode; }

    public void setSymboleCourant(TypeSymbole symboleCourant) { this.symboleCourant = symboleCourant; }

    public void setCouleurBaseCourante(Enums couleurBaseCourante) { this.couleurBaseCourante = couleurBaseCourante; }

    public Zone getZoneActuelle() { return zoneActuelle; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int t = plateau.getTailleCase();

        // Dessin des zones
        for (Zone z : plateau.getZones()) {
            for (long cellKey : z.getCellules()) {
                int x = (int) (cellKey >> 32);
                int y = (int) cellKey;
                g.setColor(new Color(z.getCouleur().getRed(), z.getCouleur().getGreen(), z.getCouleur().getBlue(), 60));
                g.fillRect(x * t, y * t, t, t);
            }
        }

        // quadrillage + sommets
        for (int x = 0; x < plateau.getLargeur(); x++) {
            for (int y = 0; y < plateau.getHauteur(); y++) {
                int px = x * t;
                int py = y * t;

                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(px, py, t, t);

                Sommet s = plateau.getSommet(x, y);
                if (s != null) {
                    drawSymbole(g, s, px, py, t);
                }
            }
        }
    }

    private void handleSymbole(Graphics g, Sommet s, int px, int py, int t) {
        int pad = t / 6;
        int w = t - 2 * pad;

        switch (s.getSymbole())
        {
            case BLEU -> {
                // Dessine une onde/triangle pour représenter l'eau
                g.setColor(TypeSymbole.BLEU.getCouleur());
                g.fillOval(px + pad, py + pad, w, w);

                // Petit motif de vague
                g.setColor(new Color(255, 255, 255, 160));
                for (int i = 0; i < 3; i++) {
                    int r = w / 6 + i * w / 12;
                    int cx = px + pad + w / 2;
                    int cy = py + pad + w / 2;
                    g.drawArc(cx - r, cy - r, 2 * r, 2 * r, 200, 120);
                }
            }
            default -> {
                // Aucun dessin spécifique
            }
        }
    }


    private void drawSymbole(Graphics g, Sommet s, int px, int py, int t) {
        int pad = t / 6;
        int w = t - 2 * pad;

        // fond
        if (s.isBase()) {
            g.setColor(s.getCouleurBase().getCouleur());
            g.fillOval(px + pad, py + pad, w, w);
        }

        g.setColor(Color.BLACK);

        handleSymbole(g, s, px, py, t);
    }
}

