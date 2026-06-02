package metier;

import java.awt.Color;

public enum Enums {
    BLEU("Bleu", Color.BLUE);

    private final String libelle;
    private final Color couleur;

    Enums(String libelle, Color couleur) {
        this.libelle = libelle;
        this.couleur = couleur;
    }

    public String getLibelle() {
        return libelle;
    }

    public Color getCouleur() {
        return couleur;
    }
}

