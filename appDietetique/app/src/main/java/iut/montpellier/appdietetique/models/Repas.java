package iut.montpellier.appdietetique.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.PlatManager;

/**
 * la class repas: objet qui va contenir toutes les informations manger pendant le repas, apports totaux, plats mange
 */
public abstract class Repas implements Parcelable {
    private String typeDuRepas; // type du repas: Petit déjeuner, Collation...
    private float totalProteines; // total de proteines du repas
    private float totalGlucides; // total de glucides du repas
    private float totalCalories; // total de calories du repas
    private ArrayList<Plat> plats; // liste de plat manger durant le repas

    /**
     * Constructeur qui va construire le repas avec le nom du type et la liste de plats
     * @param typeDuRepas nom du type de repas
     * @param plats liste de plat mange
     */
    // ----- Constructeurs ----- //
    public Repas(String typeDuRepas, ArrayList<Plat> plats){
        this.typeDuRepas = typeDuRepas;
        this.plats = plats;
        updateTotaux(); // calculs des apports totaux du repas
    }

    /**
     * Constructeur qui va construire un repas vide
     * @param typeDuRepas nom du type de repas
     */
    public Repas(String typeDuRepas){
        this.typeDuRepas = typeDuRepas;
        this.totalProteines = 0;
        this.totalGlucides = 0;
        this.totalCalories = 0;
        this.plats = new ArrayList<>();
    }

    // ----- Utils methods ----- //
    /**
     * calcul les apport totaux du repas en prenant en compte tout les apports de chaques plat
     */
    public void updateTotaux(){
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

    /**
     * method qui calcul l'apport en fonction de la quantite
     * @param apport apport a modifier
     * @param quantite nouvelle quantite
     * @return apport addapter a la nouvelle quantite
     */
    public float produitEnCroix(float apport, float quantite){
        return quantite * apport / 100;
    }

    /**
     * method qui va chercher les plats dans la bbd user avec la table du nom du type du repas a la date donné
     * @param dbUserManager bdd dans la quelle est stocke les plats mange par l'utilisateur
     * @param date date a la quelle on veut retrouver le plat mange
     * @param nomTableRepas nom du type de repas
     * @return ArrayList<Plat> liste de plat manger a la date et au type de repas indique en param
     */
    public static ArrayList<Plat> getUserBddRepasPlats(DbUserManager dbUserManager, Date date, String nomTableRepas){
        return dbUserManager.findPlat(nomTableRepas, date);
    }

    /**
     * @param dbUserManager
     * @param nomTable
     * @param date
     * @param plat
     */
    public void addPlat(DbUserManager dbUserManager, String nomTable, Date date, Plat plat){
        plats.add(plat);
        updateTotaux();
        dbUserManager.insertPlat(nomTable, date, plat.getId(), plat.getQuantite());
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

    // ----- Setter methods ----- //
    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
        updateTotaux();
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
