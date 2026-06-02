package metier;

import java.util.Objects;

public class Sommet
{
    private final int x;
    private final int y;

    private TypeSymbole symbole;

    private boolean estBase;
    private Enums couleurBase;

    public Sommet(int x, int y, TypeSymbole symbole)
    {
        this.x = x;
        this.y = y;

        this.symbole = symbole;
        
        this.estBase = false;
        this.couleurBase = null;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public TypeSymbole getSymbole() { return symbole; }
    public void setSymbole(TypeSymbole symbole) { this.symbole = symbole; }

    public boolean isBase() { return estBase; }

    public void setBase(boolean estBase, Enums couleurBase)
    {
        this.estBase = estBase;
        this.couleurBase = estBase ? Objects.requireNonNull(couleurBase, "couleurBase") : null;
    }

    public Enums getCouleurBase() { return couleurBase; }
}

