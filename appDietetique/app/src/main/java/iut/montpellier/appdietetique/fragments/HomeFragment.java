package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Calendar;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.models.Collation;
import iut.montpellier.appdietetique.models.Dejeuner;
import iut.montpellier.appdietetique.models.Diner;
import iut.montpellier.appdietetique.models.Journee;
import iut.montpellier.appdietetique.models.PetitDejeuner;
import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

public class HomeFragment extends Fragment {

    DbUserManager userDb;

    Button boutonPetitDej;
    Button boutonCollation;
    Button boutonDejeuner;
    Button boutonDiner;

    TextView textView_titreDateDuJour;

    TextView textView_totalProteines;
    TextView textView_totalGlucides;
    TextView textView_totalCalories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false);

        // ----- creation de repas test (a retirer!) ----- //

        //userDb.insertPlat("Dejeuner", Calendar.getInstance().getTime(), 1, 50); //ajouter plat à la bdd de l'utilisateur

        userDb = new DbUserManager(this.getContext());
        Journee journee = new Journee(Calendar.getInstance().getTime(),userDb);
        userDb.close();

        // ----- ----- //

        //initialisation du bouton petit dej
        boutonPetitDej = initBoutonsRepas(view, R.id.button_petit_dej, journee.getDate(), "PetitDejeuner");

        //initialisation du bouton collation
        boutonCollation = initBoutonsRepas(view, R.id.button_collation, journee.getDate(), "Collation");

        //initialisation du bouton dejeuner
        boutonDejeuner = initBoutonsRepas(view, R.id.button_dejeuner, journee.getDate(), "Dejeuner");

        //initialisation du bouton diner
        boutonDiner = initBoutonsRepas(view, R.id.button_diner, journee.getDate(), "Diner");

        initTextViews(view, journee);

        return view;
    }


    /**
     * @param view view de la page fragment_home
     * @param idBouton id du bouton au quelle on veut associer l'action
     * @param date
     * @return un bouton qui change le fragment actuel par le fragment repas, avec les données du repas passé en parametre
     */
    private Button initBoutonsRepas(View view, int idBouton, Date date, String typeRepas){
        //Récupération du bouton sur la view
        Button bouton = view.findViewById(idBouton);

        // Associer d'une action lorque le bouton est cliqué
        bouton.setOnClickListener(v -> {
            // Création du bundle d'un bundle de données avec l'objet que l'on veut passé en argument du fragment repas
            Bundle bundle = new Bundle();
            bundle.putString("typeRepas", typeRepas);
            bundle.putLong("date", date.getTime());

            // Initialisation de la transaction de fragment
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            RepasFragment repasFragment = new RepasFragment();// creation de l'entite repasFragement
            repasFragment.setArguments(bundle); //associer le bundle de données
            fragmentTransaction.replace(R.id.fragment_container, repasFragment); // remplacer le fragment actuel avec le fragment repas
            fragmentTransaction.commit(); //commit du fragment
        });
        return bouton;
    }

    private void initTextViews(View view, Journee journee) {

        textView_titreDateDuJour = view.findViewById(R.id.date_du_jour);
        textView_titreDateDuJour.setText("" + new SimpleDateFormat("EEEE d MMMM").format(journee.getDate()));

        textView_totalProteines = view.findViewById(R.id.valeur_proteines);
        textView_totalProteines.setText("" + journee.getTotalProteines());

        textView_totalGlucides = view.findViewById(R.id.valeur_glucides);
        textView_totalGlucides.setText("" + journee.getTotalGlucides());

        textView_totalCalories = view.findViewById(R.id.valeur_calories);
        textView_totalCalories.setText("" + journee.getTotalCalories());
    }


}
