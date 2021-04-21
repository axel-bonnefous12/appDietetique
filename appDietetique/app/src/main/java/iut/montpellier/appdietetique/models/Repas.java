package iut.montpellier.appdietetique.models;

import java.util.ArrayList;

public abstract class Repas {
    private float totalProteines;
    private float totalGraisses;
    private float totalGlucides;
    private float totalCalories;
    private ArrayList<Plat> plats;

    public Repas(float totalProteines, float totalGraisses, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        this.totalProteines = totalProteines;
        this.totalGraisses = totalGraisses;
        this.totalGlucides = totalGlucides;
        this.totalCalories = totalCalories;
        this.plats = plats;
    }

    public Repas(){
        this.totalProteines = 0;
        this.totalGraisses = 0;
        this.totalGlucides = 0;
        this.totalCalories = 0;
        this.plats = new ArrayList<>();
    }

    public void calculerTotaux(){
        totalProteines = 0;
        totalGraisses = 0;
        totalGlucides = 0;
        totalCalories = 0;

        for (Plat plat: plats) {
            totalProteines = plat.getProteines();
            totalGraisses = plat.getGraisses();
            totalGlucides = plat.getGlucides();
            totalCalories = plat.getCalories();
        }
    }

    public float getTotalProteines() {
        return totalProteines;
    }

    public float getTotalGraisses() {
        return totalGraisses;
    }

    public float getTotalGlucides() {
        return totalGlucides;
    }

    public float getTotalCalories() {
        return totalCalories;
    }


}
