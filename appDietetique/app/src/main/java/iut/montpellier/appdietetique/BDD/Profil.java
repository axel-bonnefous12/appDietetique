package iut.montpellier.appdietetique.BDD;

public class Profil
{
    private Integer id;
    private String nom_alim_fr;

    public Profil(Integer id, String nom_alim_fr)
    {
        this.id = id;
        this.nom_alim_fr = nom_alim_fr;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String get_nom_alim_fr()
    {
        return nom_alim_fr;
    }

    public void set_nom_alim_fr(String nom_alim_fr)
    {
        this.nom_alim_fr = nom_alim_fr;
    }


    public void creerUnProfil(Integer id, String _nom_alim_fr)
    {
        Profil profil = new Profil(id, nom_alim_fr);
    }


}
