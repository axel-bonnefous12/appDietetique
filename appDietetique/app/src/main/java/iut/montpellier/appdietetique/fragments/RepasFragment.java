package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Date;
import java.util.Objects;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.adapters.PlatItemAdapter;
import iut.montpellier.appdietetique.models.Journee;
import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

public class RepasFragment extends Fragment {

    View view;

    ImageView backButton;
    Button goToAddPlatSearchButton;

    ListView listPlats;
    TextView textView_titre;
    TextView textView_totalProteines;
    TextView textView_totalGlucides;
    TextView textView_totalCalories;

    Journee journee;
    String typeRepas;

    DbUserManager dbUserManager;
    PlatManager platManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repas,container, false);

        dbUserManager = new DbUserManager(getContext()); // ouverture de la bdd des données utilisateur
        platManager = new PlatManager(getContext()); // ouverture de la bdd plat


        Bundle bundle = this.getArguments(); // Récupération du bundle passé en argument
        Date date = new Date(bundle.getLong("date")); // recuperation de la date
        typeRepas = bundle.getString("typeRepas"); // recuperation du type de repas

        journee = new Journee(date, dbUserManager); // creation de l'objet journee initialiser avec les données de la bdd

        //switch case permetant d'afficher le type de repas passé en argument
        switch (typeRepas) {
            case "PetitDejeuner":
                initTextViews(journee.getPetitDejeuner()); // Initialise les text view avec les veleurs du repas
                initListView(journee.getPetitDejeuner());
                break;
            case "Collation":
                initTextViews(journee.getCollation()); // Initialise les text view avec les veleurs du repas
                initListView(journee.getCollation());
                break;
            case "Dejeuner":
                initTextViews(journee.getDejeuner()); // Initialise les text view avec les veleurs du repas
                initListView(journee.getDejeuner());
                break;
            case "Diner":
                initTextViews(journee.getDiner()); // Initialise les text view avec les veleurs du repas
                initListView(journee.getDiner());
                break;
            default:
                break;

        }

        // associer a l'image back arrow l'action de retourner a la journee
        initBackButton();
        initgoToAddPlatSearchButton();

        return view;
    }

    private void initTextViews(Repas repas){

        // Mise à jour du champ de text titre
        textView_titre = view.findViewById(R.id.titrePage);
        textView_titre.setText(repas.getTypeDuRepas());

        // Mise à jour du champ de valeur total de proteines
        textView_totalProteines = view.findViewById(R.id.valeur_proteines);
        textView_totalProteines.setText("" + repas.getTotalProteines());

        // Mise à jour du champ de valeur total de proteines
        textView_totalGlucides = view.findViewById(R.id.valeur_glucides);
        textView_totalGlucides.setText("" + repas.getTotalGlucides());

        // Mise à jour du champ de valeur total de calories
        textView_totalCalories = view.findViewById(R.id.valeur_calories);
        textView_totalCalories.setText("" + repas.getTotalCalories());

    }


    private void initListView(Repas repas){
        // Mise à jour de la listView avec les plats
        listPlats = view.findViewById(R.id.liste_de_plat);
        listPlats.setAdapter(new PlatItemAdapter(getContext(),repas.getPlats())); //utilisation de l'adapteur pour l'ajout d'item plat dans la liste
    }

    private void initBackButton() {
        backButton = view.findViewById(R.id.back_icon);
        backButton.setClickable(true);
        backButton.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
            fragmentTransaction.commit();
        });
    }

    private void initgoToAddPlatSearchButton() {
       goToAddPlatSearchButton = view.findViewById(R.id.button_add_plat_search);
       goToAddPlatSearchButton.setClickable(true);
       goToAddPlatSearchButton.setOnClickListener(view1 -> {

           Bundle bundle = new Bundle();
           bundle.putString("typeRepas", typeRepas);
           bundle.putLong("date", journee.getDate().getTime());

           AddRepasSearchFragment addRepasSearchFragment = new AddRepasSearchFragment();
           addRepasSearchFragment.setArguments(bundle);

           FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
           fragmentTransaction.replace(R.id.fragment_container, addRepasSearchFragment);
           fragmentTransaction.commit();
       });
    }
}
