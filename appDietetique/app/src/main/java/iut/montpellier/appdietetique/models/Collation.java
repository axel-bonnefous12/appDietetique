package iut.montpellier.appdietetique.models;

import java.util.ArrayList;

public class Collation extends Repas{
    private String typeDuRepas = "Collation";

    public Collation(float totalProteines, float totalGraisses, float totalGlucides, float totalCalories, ArrayList<Plat> plats) {
        super(totalProteines, totalGraisses, totalGlucides, totalCalories, plats);
    }

    public Collation(){
        super();
    }

    public String getTypeDuRepas() {
        return typeDuRepas;
    }
}
