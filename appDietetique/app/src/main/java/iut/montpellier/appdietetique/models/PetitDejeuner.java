package iut.montpellier.appdietetique.models;

import java.util.ArrayList;

public class PetitDejeuner extends Repas{
    private String typeDuRepas = "Petit déjeuner";

    public PetitDejeuner(float totalProteines, float totalGraisses, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super(totalProteines, totalGraisses, totalGlucides, totalCalories, plats);
    }

    public PetitDejeuner(){
        super();
    }

    public String getTypeDuRepas() {
        return typeDuRepas;
    }
}
