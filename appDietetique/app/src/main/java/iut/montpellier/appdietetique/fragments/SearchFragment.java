package iut.montpellier.appdietetique.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Calendar;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.MySQLite;
import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;

import iut.montpellier.appdietetique.models.Journee;
import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

import static iut.montpellier.appdietetique.BDD.DbUserManager.KEY_DATE;

public class SearchFragment extends Fragment
{
    MySQLite affichageDb;

    ListView listView_affichageBDD;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search,container, false);

        affichageDb = new MySQLite(this.getContext()); // Ouvre la bdd des plats

        initListViews(view, affichageDb);

        return view;
    }

    private void initListViews(View view, MySQLite affichageDb)
    {
        listView_affichageBDD = view.findViewById(R.id.affichageBDD);
        ArrayList<String> listPlat = affichageDb.findPlat("Plats");
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getContext(), );
        //listView_affichageBDD.setAdapter(listPlat);

    }
}
