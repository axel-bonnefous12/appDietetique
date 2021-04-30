package iut.montpellier.appdietetique.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import iut.montpellier.appdietetique.models.Plat;


public class PlatManager {
    private static final String TABLE_NAME = "Plats";
    public static final String KEY_ID_PLAT ="id_plat";
    public static final String KEY_NOM_PLAT ="nom_plat";
    public static final String KEY_NB_PROTEINES = "nb_proteines";
    public static final String KEY_NB_GLUCIDES = "nb_glucides";
    public static final String KEY_NB_CALORIES = "nb_calories";

    public static final String CREATE_TABLE_ANIMAL = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID_PLAT + " INTEGER primary key," +
            " " + KEY_NOM_PLAT + " VARCHAR(100)," +
            " " + KEY_NB_PROTEINES + " REAL," +
            " " + KEY_NB_GLUCIDES + " REAL," +
            " " + KEY_NB_CALORIES + " REAL" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    public PlatManager(Context context) {
        this.maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public Plat getPlatId(int id)
    {
        // Retourne le plat dont l'id est passé en paramètre

        Plat p = new Plat(0, "", 0, 0, 0,100);
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_PLAT+"="+id, null);
        if (c.moveToFirst()) {
            p.setId(c.getInt(c.getColumnIndex(KEY_ID_PLAT)));
            p.setNom(c.getString(c.getColumnIndex(KEY_NOM_PLAT)));
            p.setProteines(c.getFloat(c.getColumnIndex(KEY_NB_PROTEINES)));
            p.setGlucides(c.getFloat(c.getColumnIndex(KEY_NB_PROTEINES)));
            p.setCalories(c.getFloat(c.getColumnIndex(KEY_NB_CALORIES)));
            c.close();
        }

        return p;
    }

    public Plat getPlatNom(String nom_Plat)
    {
        // Retourne le plat dont le nom est passé en paramètre

        Plat p = new Plat(0, "", 0, 0, 0,0);
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_NOM_PLAT+"="+nom_Plat, null);
        if (c.moveToFirst()) {
            p.setId(c.getInt(c.getColumnIndex(KEY_ID_PLAT)));
            p.setNom(c.getString(c.getColumnIndex(KEY_NOM_PLAT)));
            p.setProteines(c.getFloat(c.getColumnIndex(KEY_NB_PROTEINES)));
            p.setGlucides(c.getFloat(c.getColumnIndex(KEY_NB_PROTEINES)));
            p.setCalories(c.getFloat(c.getColumnIndex(KEY_NB_CALORIES)));
            c.close();
        }

        return p;
    }
}
