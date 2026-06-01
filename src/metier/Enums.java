package metier;

import java.awt.Color;

public enum Enums
{
    BLEU("Eau potable", Color.BLUE),

    private String libelle;
    private Color  couleur;

    Enums ( String libelle, Color couleur )
    {
        this.libelle = libelle;
        this.couleur = couleur;
    }

    public String getLibelle(){ return this.libelle; }
    public Color  getCouleur(){ return this.couleur; }
}
