package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Repas implements Parcelable {
    private String typeDuRepas;
    private float totalProteines;
    private float totalGlucides;
    private float totalCalories;
    private ArrayList<Plat> plats;

    // ----- Constructeurs ----- //
    public Repas(String typeDuRepas, float totalProteines, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        this.typeDuRepas = typeDuRepas;
        this.totalProteines = totalProteines;
        this.totalGlucides = totalGlucides;
        this.totalCalories = totalCalories;
        this.plats = plats;
    }

    public Repas(String typeDuRepas, ArrayList<Plat> plats){
        this.typeDuRepas = typeDuRepas;
        this.plats = plats;
        calculerTotaux();
    }

    public Repas(String typeDuRepas){
        this.typeDuRepas = typeDuRepas;
        this.totalProteines = 0;
        this.totalGlucides = 0;
        this.totalCalories = 0;
        this.plats = new ArrayList<>();
    }


    // ----- Utils methods ----- //
    public void calculerTotaux(){
        totalProteines = 0;
        totalGlucides = 0;
        totalCalories = 0;

        for (Plat plat: plats) {
            float quantite = plat.getQuantite();
            totalProteines = totalProteines + produitEnCroix(plat.getProteines(),quantite);
            totalGlucides = totalGlucides + produitEnCroix(plat.getGlucides(),quantite);
            totalCalories = totalCalories + produitEnCroix(plat.getCalories(),quantite);
        }
    }

    public float produitEnCroix(float apport, float quantite){
        return quantite * apport / 100;
    }


    // ----- Getter methods ----- //
    public String getTypeDuRepas() {
        return typeDuRepas;
    }

    public float getTotalProteines() {
        return totalProteines;
    }

    public float getTotalGlucides() {
        return totalGlucides;
    }

    public float getTotalCalories() {
        return totalCalories;
    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }


    // ----- Parcelable interface methods ----- //

    // Désérialisation des attributs
    protected Repas(Parcel in){
        plats = new ArrayList<Plat>();
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
        parcel.writeString(typeDuRepas);
        parcel.writeFloat(totalProteines);
        parcel.writeFloat(totalGlucides);
        parcel.writeFloat(totalCalories);
        parcel.writeParcelableArray(plats.toArray(new Plat[plats.size()]), i);
    }

    // Désérialisation des attributs
    public void readFromParcel(Parcel in){
        typeDuRepas = in.readString();
        totalProteines = in.readFloat();
        totalGlucides = in.readFloat();
        totalCalories = in.readFloat();
        in.readTypedList(this.plats, Plat.CREATOR);
    }


}
