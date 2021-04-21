package iut.montpellier.appdietetique.models;

public class Plat {
    private String nom;
    private float proteines;
    private float graisses;
    private float glucides;
    private float calories;
    private float quantite;

    public Plat(String nom, float proteines, float graisses, float glucides, float calories, float quantite) {
        this.nom = nom;
        this.proteines = proteines;
        this.graisses = graisses;
        this.glucides = glucides;
        this.calories = calories;
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public float getProteines() {
        return proteines;
    }

    public float getGraisses() {
        return graisses;
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
}
