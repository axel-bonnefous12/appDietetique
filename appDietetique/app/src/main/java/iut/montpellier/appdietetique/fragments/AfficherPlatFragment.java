package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Objects;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.models.Plat;


public class AfficherPlatFragment extends Fragment
{


    View view;
    int idPlat;
    DbUserManager dbUserManager;
    PlatManager platManager;
    Plat plat;

    TextView textView_afficher_nomPlat;
    TextView textView_totalProteines;
    TextView textView_totalGlucides;
    TextView textView_totalCalories;
    ImageView button_back;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_afficher_plat, container, false);

        Bundle bundle = this.getArguments(); // Récupération du bundle passé en argument

        idPlat = bundle.getInt("idPlat");

        dbUserManager = new DbUserManager(getContext());
        platManager = new PlatManager(getContext());

        trouverPlatDansBdd();
        initText();
        initBackButton();

        return view;
    }

    private void initText()
    {
        textView_afficher_nomPlat = view.findViewById(R.id.afficher_nomDuPlat);
        textView_afficher_nomPlat.setText(plat.getNom());

        // Mise à jour du champ de valeur de proteines
        textView_totalProteines = view.findViewById(R.id.afficher_proteine);
        textView_totalProteines.setText("" + plat.getProteines());

        // Mise à jour du champ de valeur de glucides
        textView_totalGlucides = view.findViewById(R.id.afficher_glucides);
        textView_totalGlucides.setText("" + plat.getGlucides());

        // Mise à jour du champ de valeur de calories
        textView_totalCalories = view.findViewById(R.id.afficher_calories);
        textView_totalCalories.setText("" + plat.getCalories());
    }

    private void trouverPlatDansBdd()
    {
        platManager.open();
        plat = platManager.getPlatId(idPlat);
        platManager.close();
    }

    private void initBackButton()
    {
        button_back = view.findViewById(R.id.back_icon);
        button_back.setClickable(true);
        button_back.setOnClickListener(view1 -> {
            allerSearchFragment();
        });
    }

    private void allerSearchFragment(){
        SearchFragment searchFragment = new SearchFragment();

        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, searchFragment);
        fragmentTransaction.commit();
    }



}