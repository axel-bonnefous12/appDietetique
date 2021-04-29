package iut.montpellier.appdietetique.BDD;

import android.content.Context;
import android.service.controls.Control;

public final class Controle
{
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesLocal accesLocal;

    public Controle()
    {
        super();
    }

    public static final Controle getInstance(Context contexte)
    {
        if(Controle.instance == null)
        {
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(contexte);
            profil = accesLocal.recupDernier();
        }
        return Controle.instance;
    }


    public void creerProfil(Integer id, String nom_alim_fr)
    {
         profil = new Profil(id, nom_alim_fr);
         accesLocal.ajout(profil);
    }
}

