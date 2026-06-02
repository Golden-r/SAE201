package metier;

import java.awt.Color;

public enum TypeSymbole
{
    BLEU("Eau potable", Color.BLUE);

    private final String libelle;
    private final Color couleur;

    TypeSymbole(String libelle, Color couleur)
    {
        this.libelle = libelle;
        this.couleur = couleur;
    }

    public String getLibelle(){ return this.libelle; }

    public Color  getCouleur(){ return this.couleur; }
}

