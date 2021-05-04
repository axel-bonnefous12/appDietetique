package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import iut.montpellier.appdietetique.BDD.DbUserManager;

public class Dejeuner extends Repas{

    // ----- Constructeurs ----- //
    public Dejeuner(float totalProteines, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super("Déjeuner", totalProteines, totalGlucides, totalCalories, plats);
    }

    public Dejeuner(ArrayList<Plat> plats){
        super("Déjeuner", plats);
    }

    public Dejeuner(){
        super("Déjeuner");
    }

    // ----- Utils Methods ----- //
    public ArrayList<Plat> getUserBddRepasPlats(DbUserManager dbUserManager, Date date) {
        return super.getUserBddRepasPlats(dbUserManager, date, "Dejeuner");
    }

    public void updatePlatsWithBdd(DbUserManager dbUserManager, Date date){
        setPlats(getUserBddRepasPlats(dbUserManager,date));
    }

    public void addPlat(DbUserManager dbUserManager, Date date, Plat plat){
        super.addPlat(dbUserManager, "Dejeuner", date, plat);
    }

    // ----- Parcelable interface methods ----- //
    // Désérialisation des attributs
    public Dejeuner(Parcel in){
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
    public static final Parcelable.Creator<Dejeuner> CREATOR = new Parcelable.Creator<Dejeuner>() {
        @Override
        public Dejeuner createFromParcel(Parcel parcel) {
            return new Dejeuner(parcel);
        }

        @Override
        public Dejeuner[] newArray(int i) {
            return new Dejeuner[i];
        }
    };
}
