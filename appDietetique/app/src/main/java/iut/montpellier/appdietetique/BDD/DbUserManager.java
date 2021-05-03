package iut.montpellier.appdietetique.BDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import iut.montpellier.appdietetique.models.Plat;

/**
 * Class d'utilisation de la base de donnée qui stock tout les plats manger par l'utilisateur par jour
 */
public class DbUserManager extends SQLiteOpenHelper {

    private static final String TABLE_NAME_PETIT_DEJ = "PetitDejeuner";
    private static final String TABLE_NAME_COLLATION = "Collation";
    private static final String TABLE_NAME_DEJEUNER = "Dejeuner";
    private static final String TABLE_NAME_DINER = "Diner";
    public static final String KEY_DATE="date";
    public static final String KEY_ID_PLAT="nom_plat";
    public static final String KEY_NB_QUANTITE = "nb_quantite";


    public static final String CREATE_TABLE_PETIT_DEJ = "CREATE TABLE " + TABLE_NAME_PETIT_DEJ +
            " (" +
            " " + KEY_DATE + " VARCHAR(50)," +
            " " + KEY_ID_PLAT + " INT," +
            " " + KEY_NB_QUANTITE + " REAL NOT NULL," +
            " " + "PRIMARY KEY(" + KEY_DATE + "," + KEY_ID_PLAT + ")" +
            ");";

    public static final String CREATE_TABLE_COLLATION = "CREATE TABLE " + TABLE_NAME_COLLATION +
            " (" +
            " " + KEY_DATE + " VARCHAR(50)," +
            " " + KEY_ID_PLAT + " INT," +
            " " + KEY_NB_QUANTITE + " REAL NOT NULL," +
            " " + "PRIMARY KEY(" + KEY_DATE + "," + KEY_ID_PLAT + ")" +
            ");";

    public static final String CREATE_TABLE_DEJEUNER = "CREATE TABLE " + TABLE_NAME_DEJEUNER +
            " (" +
            " " + KEY_DATE + " VARCHAR(50)," +
            " " + KEY_ID_PLAT + " INT," +
            " " + KEY_NB_QUANTITE + " REAL NOT NULL," +
            " " + "PRIMARY KEY(" + KEY_DATE + "," + KEY_ID_PLAT + ")" +
            ");";

    public static final String CREATE_TABLE_DINER = "CREATE TABLE " + TABLE_NAME_DINER +
            " (" +
            " " + KEY_DATE + " VARCHAR(50)," +
            " " + KEY_ID_PLAT + " INT," +
            " " + KEY_NB_QUANTITE + " REAL NOT NULL," +
            " " + "PRIMARY KEY(" + KEY_DATE + "," + KEY_ID_PLAT + ")" +
            ");";

    private static final String DATABASE_NAME = "userData.sqlite";
    private static  final int DATABASE_VERSION = 2;

    Context context;


    /**
     * Constructeur de la base de donnée
     * @param context context de l'activity
     */
    public DbUserManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.getWritableDatabase();
        this.context = context;
    }

    /**
     * Method appelé lors de la première utilisation de la base qui va créer les tables qui vont stocker les 4 repas de la journée
     * @param sqLiteDatabase base de donnée
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i( "DATABASE", "onCreate invoked");
        sqLiteDatabase.execSQL(CREATE_TABLE_PETIT_DEJ);
        sqLiteDatabase.execSQL(CREATE_TABLE_COLLATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_DEJEUNER);
        sqLiteDatabase.execSQL(CREATE_TABLE_DINER);

    }

    /**
     * Method appelé lors de l'incrémentation de la variable version de la base de donnée. Elle met à jour les tables de la bdd.
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * Method qui ajoute un plat mangé dans une des tables repas
     * @param nomTable PetitDejeuner / Collation / Dejeuner / Diner
     * @param date date de la journée du plat mangé
     * @param idPlat id du plat dans la bdd data.sql
     * @param quantite quantité mangé
     */
    public void insertPlat(String nomTable, Date date, int idPlat, float quantite){
        String dateFormate = "" + new SimpleDateFormat("EEEE d MMMM").format(date); //Met la date de la journée dans le format ex:"Dimanche 2 Mai"

        try {
            //execute la requette dans la table
            this.getWritableDatabase().execSQL(
                    "INSERT INTO " + nomTable + "(" + "'" + KEY_DATE + "'" + "," + KEY_ID_PLAT + "," + "'" + KEY_NB_QUANTITE + "'" + ")" +
                            " VALUES" + "(" + "'" + dateFormate + "'" + "," + idPlat + "," + "'" + quantite + "'" + ");"
            );
        } catch (SQLiteException e) {
            // le tuple existe deja
        }

        Log.i( "DATABASE", "insertPlat invoked"); // Affichage de debugage dans les logs
    }

    public void updatePlat(String nomTable, Date date, int idPlat, float quantite){
        //a faire
    }

    /**
     * Method qui va chercher les plats manger un certain jour et pendant un certain repas
     * @param nomTable repas PetitDejeuner / Collation / Dejeuner / Diner
     * @param date date à la quelle on veut chercher
     * @return liste de plat
     */
    public ArrayList<Plat> findPlat(String nomTable, Date date){
        ArrayList<Plat> listPlat = new ArrayList<>(); // stock les plats trouvé

        String dateFormate = "" + new SimpleDateFormat("EEEE d MMMM").format(date); //Met la date de la journée dans le format ex:"Dimanche 2 Mai"

        String strSql = "SELECT * FROM " + nomTable + " WHERE " + KEY_DATE + " = '" + dateFormate + "'"; // requette sql

        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null); // récupération du resultat de la requette dans un curseur

        PlatManager platManager = new PlatManager(context); // instance de la bdd de tout les plats
        platManager.open(); // ouverture de la bdd

        cursor.moveToFirst(); // on place le curseur au premier resultat
        
        // Boucle qui ajoute chaque plat en resultat de la requette à la liste
        while ( !cursor.isAfterLast()) {
            Plat p = platManager.getPlatId(cursor.getInt(1));
            p.setQuantite(cursor.getInt(2));
            listPlat.add(p);
            cursor.moveToNext();
        }
        cursor.close();
        platManager.close();

        return listPlat;
    }
}
