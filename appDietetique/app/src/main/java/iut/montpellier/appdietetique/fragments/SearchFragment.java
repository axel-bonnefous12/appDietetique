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

import java.util.ArrayList;
import java.util.Objects;
import java.util.Calendar;

import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;

import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

public class SearchFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

       View view = inflater.inflate(R.layout.fragment_search,container, false);

        PlatManager platRecherche = new PlatManager(this.getContext());
            platRecherche.open();

        Plat plat = platRecherche.getPlatNom("Coq au vin");
            platRecherche.close();

        ArrayList<Plat> listPlatsAfficher = new ArrayList<>();
            listPlatsAfficher.add(plat);




            return view;
    }
}
