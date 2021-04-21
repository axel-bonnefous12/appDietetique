package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import iut.montpellier.appdietetique.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container, false);

        //passage au fragment repas lorsqu'on clique sur le bouton petit dej
        Button boutonPetitDej = view.findViewById(R.id.button_petit_dej); // associer variable du bouton avec bouton graphique
        boutonPetitDej.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new RepasFragment());
            fragmentTransaction.commit();
        });

        //passage au fragment repas lorsqu'on clique sur le bouton collation
        Button boutonCollation = view.findViewById(R.id.button_collation); // associer variable du bouton avec bouton graphique
        boutonCollation.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new RepasFragment());
            fragmentTransaction.commit();
        });

        //passage au fragment repas lorsqu'on clique sur le bouton dejeuner
        Button boutonDejeuner = view.findViewById(R.id.button_dejeuner); // associer variable du bouton avec bouton graphique
        boutonDejeuner.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new RepasFragment());
            fragmentTransaction.commit();
        });

        //passage au fragment repas lorsqu'on clique sur le bouton diner
        Button boutonDiner = view.findViewById(R.id.button_diner); // associer variable du bouton avec bouton graphique
        boutonDiner.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new RepasFragment());
            fragmentTransaction.commit();
        });

        return view;
    }




}
