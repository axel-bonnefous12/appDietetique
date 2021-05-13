package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.adapters.PlatAfficherAdapter;
import iut.montpellier.appdietetique.models.Plat;


public class AddRepasSearchFragment extends Fragment {

    View view;
    PlatManager bddPlat;

    String typeRepas;
    Date date;

    ImageView backButton;
    EditText editTextSearch;
    ListView listViewPlats;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_repas_search, container, false);

        Bundle bundle = this.getArguments(); // Récupération du bundle passé en argument
        date = new Date(bundle.getLong("date")); // recuperation de la date
        typeRepas = bundle.getString("typeRepas"); // recuperation du type de repas

        bddPlat = new PlatManager(getContext());

        initBackButton();
        initListView();
        initEditText();

        return view;
    }

    private void initBackButton() {
        backButton = view.findViewById(R.id.back_icon);
        backButton.setClickable(true);
        backButton.setOnClickListener(view1 -> {

            // Création du bundle d'un bundle de données avec l'objet que l'on veut passé en argument du fragment repas
            Bundle bundle = new Bundle();
            bundle.putString("typeRepas", typeRepas);
            bundle.putLong("date", date.getTime());

            RepasFragment repasFragment = new RepasFragment();
            repasFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, repasFragment);
            fragmentTransaction.commit();
        });
    }

    private void initListView(){

        listViewPlats = view.findViewById(R.id.liste_de_plat);
    }

    private void initEditText(){
        editTextSearch = view.findViewById(R.id.edit_text_search);
        editTextSearch.addTextChangedListener(new TextWatcher() {
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
                bddPlat.open();
                ArrayList<Plat> plats = bddPlat.getPlatNom("" + editTextSearch.getText());
                actualiseListView(plats);
                bddPlat.close();
            }
        });
    }

    private void actualiseListView(ArrayList<Plat> plats) {
        listViewPlats.setAdapter(new PlatAfficherAdapter(getContext(), plats));
        listViewPlats.invalidateViews();
        listViewPlats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Plat plat = (Plat) listViewPlats.getAdapter().getItem(i);

                // Création du bundle d'un bundle de données avec l'objet que l'on veut passé en argument du fragment repas
                Bundle bundle = new Bundle();
                bundle.putString("typeRepas", typeRepas);
                bundle.putLong("date", date.getTime());
                bundle.putInt("idPlat", plat.getId());

                AddPlatFragment addPlatFragment = new AddPlatFragment();
                addPlatFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, addPlatFragment);
                fragmentTransaction.commit();
            }
        });
    }
}