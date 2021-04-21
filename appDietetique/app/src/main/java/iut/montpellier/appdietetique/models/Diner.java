package iut.montpellier.appdietetique.models;

import java.util.ArrayList;

public class Diner extends Repas{
    private String typeDuRepas = "Diner";

    public Diner(float totalProteines, float totalGraisses, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super(totalProteines, totalGraisses, totalGlucides, totalCalories, plats);
    }

    public Diner(){
        super();
    }

    public String getTypeDuRepas() {
        return typeDuRepas;
    }
}
