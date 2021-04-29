package iut.montpellier.appdietetique.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;


public class MySQLiteOpenHelper extends SQLiteOpenHelper // Outils qui permet d'accéder à la base de données
{
    private String create = "CREATE TABLE Profil (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, nom_alim_fr VARCHAR(30) NOT NULL);";
    private Integer DATABASE_VERSION = 1;


    public MySQLiteOpenHelper(Context contexte, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(contexte, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }







}
