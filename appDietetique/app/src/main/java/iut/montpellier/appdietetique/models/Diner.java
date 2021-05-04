package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import iut.montpellier.appdietetique.BDD.DbUserManager;

public class Diner extends Repas{

    // ----- Constructeurs ----- //
    public Diner(float totalProteines, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super("Diner",totalProteines, totalGlucides, totalCalories, plats);
    }

    public Diner(ArrayList<Plat> plats){
        super("Diner", plats);
    }

    public Diner(){
        super("Diner");
    }

    // ----- Utils Methods ----- //
    public ArrayList<Plat> getUserBddRepasPlats(DbUserManager dbUserManager, Date date) {
        return super.getUserBddRepasPlats(dbUserManager, date, "Diner");
    }

    public void updatePlatsWithBdd(DbUserManager dbUserManager, Date date){
        setPlats(getUserBddRepasPlats(dbUserManager,date));
    }

    public void addPlat(DbUserManager dbUserManager, Date date, Plat plat){
        super.addPlat(dbUserManager, "Diner", date, plat);
    }

    // ----- Parcelable interface methods ----- //
    // Désérialisation des attributs
    public Diner(Parcel in){
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
    public static final Parcelable.Creator<Diner> CREATOR = new Parcelable.Creator<Diner>() {
        @Override
        public Diner createFromParcel(Parcel parcel) {
            return new Diner(parcel);
        }

        @Override
        public Diner[] newArray(int i) {
            return new Diner[i];
        }
    };
}
