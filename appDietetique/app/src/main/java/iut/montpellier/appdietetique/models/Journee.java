package iut.montpellier.appdietetique.models;

import java.util.Date;

public class Journee {
    private Date date;
    private float totalProteines;
    private float totalGraisses;
    private float totalGlucides;
    private float totalCalories;
    private PetitDejeuner petitDejeuner;
    private Collation collation;
    private Dejeuner dejeuner;
    private Diner diner;

    public Journee(Date date, float totalProteines, float totalGraisses, float totalGlucides, float totalCalories, PetitDejeuner petitDejeuner, Collation collation, Dejeuner dejeuner, Diner diner) {
        this.date = date;
        this.totalProteines = totalProteines;
        this.totalGraisses = totalGraisses;
        this.totalGlucides = totalGlucides;
        this.totalCalories = totalCalories;
        this.petitDejeuner = petitDejeuner;
        this.collation = collation;
        this.dejeuner = dejeuner;
        this.diner = diner;
    }

    public Journee(Date date){
        this.date = date;
        this.totalProteines = 0;
        this.totalGraisses = 0;
        this.totalGlucides = 0;
        this.totalCalories = 0;
        this.petitDejeuner = new PetitDejeuner();
        this.collation = new Collation();
        this.dejeuner = new Dejeuner();
        this.diner = new Diner();
    }



}
