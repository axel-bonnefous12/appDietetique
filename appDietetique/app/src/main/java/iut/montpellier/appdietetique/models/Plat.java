package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Plat implements Parcelable {
    private int id;
    private String nom;
    private float proteines;
    private float glucides;
    private float calories;
    private float quantite;

    // Attribut for parcelable interface
    private Parcel parcel;
    private int i;

    // Constructeur
    public Plat(int id, String nom, float proteines, float glucides, float calories, float quantite) {
        this.id = id;
        this.nom = nom;
        this.proteines = proteines;
        this.glucides = glucides;
        this.calories = calories;
        this.quantite = quantite;
    }

    // ----- Getter methods ----- //
    public int getId() { return id; }

    public String getNom() {
        return nom;
    }

    public float getProteines() {
        return proteines;
    }

    public float getGlucides() {
        return glucides;
    }

    public float getCalories() {
        return calories;
    }

    public float getQuantite() {
        return quantite;
    }

    // ----- Setter methods ----- //
    public void setId(int id) { this.id = id; }

    public void setNom(String nom) { this.nom = nom; }

    public void setProteines(float proteines) {
        this.proteines = proteines;
    }

    public void setGlucides(float glucides) {
        this.glucides = glucides;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    // ----- Parcelable interface methods ----- //

    // Désérialisation des attributs
    protected Plat(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        proteines = in.readFloat();
        glucides = in.readFloat();
        calories = in.readFloat();
        quantite = in.readFloat();
        i = in.readInt();
    }

    // Serialisation des attributs
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeFloat(proteines);
        dest.writeFloat(glucides);
        dest.writeFloat(calories);
        dest.writeFloat(quantite);
        dest.writeInt(i);
    }

    // description des objets de types complexes
    @Override
    public int describeContents() {
        return 0;
    }

    // créer une instance parcelable de la class
    public static final Creator<Plat> CREATOR = new Creator<Plat>() {
        @Override
        public Plat createFromParcel(Parcel in) {
            return new Plat(in);
        }

        @Override
        public Plat[] newArray(int size) {
            return new Plat[size];
        }
    };


}
