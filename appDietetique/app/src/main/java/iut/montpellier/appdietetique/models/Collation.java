package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import iut.montpellier.appdietetique.BDD.DbUserManager;

public class Collation extends Repas{

    private static final String NOM_TYPE_REPAS = "Collation";
    private static final String NOM_TABLE_REPAS = "Collation";

    // ----- Constructeurs ----- //
    public Collation(float totalProteines, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super(NOM_TYPE_REPAS, totalProteines, totalGlucides, totalCalories, plats);
    }

    public Collation(ArrayList<Plat> plats){
        super(NOM_TYPE_REPAS, plats);
    }

    public Collation(){
        super(NOM_TYPE_REPAS);
    }

    // ----- Utils Methods ----- //
    public ArrayList<Plat> getUserBddRepasPlats(DbUserManager dbUserManager, Date date) {
        return super.getUserBddRepasPlats(dbUserManager, date, NOM_TABLE_REPAS);
    }

    public void updatePlatsWithBdd(DbUserManager dbUserManager, Date date){
        setPlats(getUserBddRepasPlats(dbUserManager,date));
    }

    public void addPlat(DbUserManager dbUserManager, Date date, Plat plat){
        super.addPlat(dbUserManager, NOM_TABLE_REPAS, date, plat);
    }

    // ----- Parcelable interface methods ----- //
    // Désérialisation des attributs
    public Collation(Parcel in){
        this();
        readFromParcel(in);
    }

    // description des objets de types complexes
    @Override
    public int describeContents() {
        return 0;
    }

    // Serialisation des attributs
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    // Désérialisation des attributs
    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
    }

    // créer une instance parcelable de la class
    public static final Parcelable.Creator<Collation> CREATOR = new Parcelable.Creator<Collation>() {
        @Override
        public Collation createFromParcel(Parcel parcel) {
            return new Collation(parcel);
        }

        @Override
        public Collation[] newArray(int i) {
            return new Collation[i];
        }
    };
}
