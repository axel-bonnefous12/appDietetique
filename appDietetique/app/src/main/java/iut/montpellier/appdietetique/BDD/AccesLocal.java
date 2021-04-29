package iut.montpellier.appdietetique.BDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class AccesLocal // Accéder à la base de données
{
    // Attributs
    private String nomBase = "profil.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd; // Canaux pour pouvoir lire/écrire dans la bdd

    public AccesLocal(Context contexte)
    {
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
    }

    public void ajout(Profil profil) // ajout d'un profil dans la bdd
    {
        bd = accesBD.getWritableDatabase();
        String requete = "INSERT INTO Profil(id, nom_alim_fr) VALUES ";
        requete += "(\"" + profil.getId() + "\"," + profil.get_nom_alim_fr() + ")";
        bd.execSQL(requete);
    }

    public Profil recupDernier() // récupération du dernier profil de la bdd
    {
        bd = accesBD.getReadableDatabase();
        Profil profil = null;
        String requete = "SELECT * FROM Profil";
        Cursor curseur = bd.rawQuery(requete, null);
        curseur.moveToLast(); // On se positionne sur la dernnière ligne
        if(!curseur.isAfterLast()) // Si on est pas après le dernier
        {
            Integer id = curseur.getInt(1);
            String nom_alim_fr = curseur.getString(2); // On récup la data sur la colonne nom_alim_fr
            profil = new Profil(id, nom_alim_fr);
        }
        curseur.close();
        return profil;
    }
}
