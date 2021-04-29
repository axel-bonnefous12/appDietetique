/*package iut.montpellier.appdietetique.BDD;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;


public class DietetiqueManager {

    private static final String TABLE_NAME = "data.sqlite";
    public static final String KEY_ID_ALIMENT="id";
    public static final String KEY_NOM_ALIMENT="alim_nom_fr";
    public static final String CREATE_TABLE_DIETETIQUE = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID_ALIMENT + " INTEGER primary key," +
            " " + KEY_NOM_ALIMENT + " TEXT" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public DietetiqueManager(MySQLite context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
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

      public long addAnimal(Animal animal) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ANIMAL, animal.getNom_animal());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

      public int modAnimal(Animal animal) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ANIMAL, animal.getNom_animal());

        String where = KEY_ID_ANIMAL+" = ?";
        String[] whereArgs = {animal.getId_animal()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

      public int supAnimal(Animal animal) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_ANIMAL+" = ?";
        String[] whereArgs = {animal.getId_animal()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Dietetique getDietetique(int id) {
        // Retourne l'aliment dont l'id est passé en paramètre

        Dietetique a = new Dietetique(0,"");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_ALIMENT+"="+id, null);
        if (c.moveToFirst()) {
            a.setId(c.getInt(c.getColumnIndex(KEY_ID_ALIMENT)));
            a.setAlim_nom_fr(c.getString(c.getColumnIndex(KEY_NOM_ALIMENT)));
            c.close();
        }

        return a;
    }

    public Cursor getDietetique()
    {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }



} // class DietetiqueManager
*/