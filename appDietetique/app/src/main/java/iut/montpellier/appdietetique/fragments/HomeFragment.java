package iut.montpellier.appdietetique.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Calendar;

import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.models.Collation;
import iut.montpellier.appdietetique.models.Dejeuner;
import iut.montpellier.appdietetique.models.Diner;
import iut.montpellier.appdietetique.models.Journee;
import iut.montpellier.appdietetique.models.PetitDejeuner;
import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false);

        // ----- creation de repas test (a retirer!) ----- //
        Plat plat1 = new Plat("test",11,9,150,100);
        Plat plat2 = new Plat("test2",8,9,200,100);

        ArrayList<Plat> listPlats = new ArrayList<>();
        listPlats.add(plat1);
        listPlats.add(plat2);

        PetitDejeuner petitDejeuner = new PetitDejeuner(listPlats);
        Collation collation = new Collation(listPlats);
        Dejeuner dejeuner = new Dejeuner(listPlats);
        Diner diner = new Diner(listPlats);

        Journee journee = new Journee(Calendar.getInstance().getTime(),petitDejeuner,collation,dejeuner,diner);

        // ----- ----- //


        //initialisation du bouton petit dej
        Button boutonPetitDej = initBoutonsRepas(view, R.id.button_petit_dej, journee.getPetitDejeuner());

        //initialisation du bouton collation
        Button boutonCollation = initBoutonsRepas(view, R.id.button_collation, journee.getCollation());

        //initialisation du bouton dejeuner
        Button boutonDejeuner = initBoutonsRepas(view, R.id.button_dejeuner, journee.getDejeuner());

        //initialisation du bouton diner
        Button boutonDiner = initBoutonsRepas(view, R.id.button_diner, journee.getDiner());

        return view;
    }


    /**
     * @param view view de la page fragment_home
     * @param idBouton id du bouton au quelle on veut associer l'action
     * @param repas repas que l'ont veut afficher sur la page repas (PetitDejeuner, Collation, Dejeuner ou Diner)
     * @return un bouton qui change le fragment actuel par le fragment repas, avec les données du repas passé en parametre
     */
    private Button initBoutonsRepas(View view, int idBouton, Repas repas){
        //Récupération du bouton sur la view
        Button bouton = view.findViewById(idBouton);

        // Associer d'une action lorque le bouton est cliqué
        bouton.setOnClickListener(v -> {
            // Création du bundle d'un bundle de données avec l'objet que l'on veut passé en argument du fragment repas
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", repas);

            // Initialisation de la transaction de fragment
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            RepasFragment repasFragment = new RepasFragment();// creation de l'entite repasFragement
            repasFragment.setArguments(bundle); //associer le bundle de données
            fragmentTransaction.replace(R.id.fragment_container, repasFragment); // remplacer le fragment actuel avec le fragment repas
            fragmentTransaction.commit(); //commit du fragment
        });
        return bouton;
    }



}
