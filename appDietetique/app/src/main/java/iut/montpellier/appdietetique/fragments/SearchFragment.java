package iut.montpellier.appdietetique.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.security.PKCS12Attribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Calendar;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.MySQLite;
import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;

import iut.montpellier.appdietetique.adapters.PlatAfficherAdapter;
import iut.montpellier.appdietetique.models.Journee;
import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

import static iut.montpellier.appdietetique.BDD.DbUserManager.KEY_DATE;

public class SearchFragment extends Fragment
{
    PlatManager affichageDb; // Déclaré comme variable globale

    ListView listView_affichageBDD;
    EditText editText_search_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search,container, false);

        affichageDb = new PlatManager(this.getContext()); // Instance la bdd des plats
        listView_affichageBDD = view.findViewById(R.id.affichageBDD);

        initEditTexte(view);

        return view;
    }


    private void initEditTexte(View view)
    {
        editText_search_id = view.findViewById(R.id.edit_text_search_id);
        editText_search_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                affichageDb.open();
                actualiseListView(view, affichageDb.getPlatNom("" + editText_search_id.getText()));
                affichageDb.close();
            }
        });
    }

    private void actualiseListView(View view, ArrayList<Plat> plats) // Actualiser la View
    {
        listView_affichageBDD.setAdapter(new PlatAfficherAdapter(getContext(), plats));
        listView_affichageBDD.invalidateViews();
    }










}
