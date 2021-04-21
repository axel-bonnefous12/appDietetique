package iut.montpellier.appdietetique.models;

import java.util.ArrayList;

public class Dejeuner extends Repas{
    private String typeDuRepas = "Déjeuner";

    public Dejeuner(float totalProteines, float totalGraisses, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super(totalProteines, totalGraisses, totalGlucides, totalCalories, plats);
    }

    public Dejeuner(){
        super();
    }

    public String getTypeDuRepas() {
        return typeDuRepas;
    }
}
