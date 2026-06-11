package metier;

public class ObjetSon extends Objet
{
    public ObjetSon(String nom, int prix)
    {
        super(nom, prix);
    }

    public String getCategorie()
    {
        return "Son";
    }
}
