package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import iut.montpellier.appdietetique.BDD.DbUserManager;

public class PetitDejeuner extends Repas{

    // ----- Constructeurs ----- //
    public PetitDejeuner(float totalProteines, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super("Petit déjeuner", totalProteines, totalGlucides, totalCalories, plats);
    }

    public PetitDejeuner(ArrayList<Plat> plats){
        super("Petit déjeuner", plats);
    }

    public PetitDejeuner(){
        super("Petit déjeuner");
    }

    // ----- Utils Methods ----- //
    public ArrayList<Plat> getUserBddRepasPlats(DbUserManager dbUserManager, Date date) {
        return super.getUserBddRepasPlats(dbUserManager, date, "PetitDejeuner");
    }

    public void updatePlatsWithBdd(DbUserManager dbUserManager, Date date){
        setPlats(getUserBddRepasPlats(dbUserManager,date));
    }

    public void addPlat(DbUserManager dbUserManager, Date date, Plat plat){
        super.addPlat(dbUserManager, "PetitDejeuner", date, plat);
    }

    // ----- Parcelable interface methods ----- //
    // Désérialisation des attributs
    public PetitDejeuner(Parcel in){
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
    public static final Parcelable.Creator<PetitDejeuner> CREATOR = new Parcelable.Creator<PetitDejeuner>() {
        @Override
        public PetitDejeuner createFromParcel(Parcel parcel) {
            return new PetitDejeuner(parcel);
        }

        @Override
        public PetitDejeuner[] newArray(int i) {
            return new PetitDejeuner[i];
        }
    };
}
