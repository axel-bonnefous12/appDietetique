package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import iut.montpellier.appdietetique.models.Plat;

public class AddPlatFragment extends Fragment {

    View view;
    Date date;
    String typeRepas;
    int idPlat;
    DbUserManager dbUserManager;
    PlatManager platManager;
    Plat plat;
    float quantite = 100;

    TextView textView_nomDuPlat;
    TextView textView_totalProteines;
    TextView textView_totalGlucides;
    TextView textView_totalCalories;
    EditText editText_quantite;
    Button button_ajouter_plat;
    ImageView button_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_afficher_plat, container, false);

        Bundle bundle = this.getArguments(); // Récupération du bundle passé en argument
        date = new Date(bundle.getLong("date")); // recuperation de la date
        typeRepas = bundle.getString("typeRepas"); // recuperation du type de repas
        idPlat = bundle.getInt("idPlat");

        dbUserManager = new DbUserManager(getContext());
        platManager = new PlatManager(getContext());

        trouverPlatDansBdd();

        initText();
        initAjouterBouton();
        initBackButton();

        return view;
    }

    private void initText() {
        textView_nomDuPlat = view.findViewById(R.id.nom_du_plat);
        textView_nomDuPlat.setText(plat.getNom());

        // Mise à jour du champ de valeur de proteines
        textView_totalProteines = view.findViewById(R.id.valeur_proteines);
        textView_totalProteines.setText("" + plat.getProteines());

        // Mise à jour du champ de valeur de glucides
        textView_totalGlucides = view.findViewById(R.id.valeur_glucides);
        textView_totalGlucides.setText("" + plat.getGlucides());

        // Mise à jour du champ de valeur de calories
        textView_totalCalories = view.findViewById(R.id.valeur_calories);
        textView_totalCalories.setText("" + plat.getCalories());

        editText_quantite = view.findViewById(R.id.edit_text_quantite);
        editText_quantite.setText("" + quantite);
        editText_quantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Float.parseFloat("" + editable) > 1){
                    plat.setQuantite(Float.parseFloat("" + editable));
                    updateText();
                }
            }
        });
    }

    private void initAjouterBouton(){
        button_ajouter_plat = view.findViewById(R.id.button_ajouter_plat);
        button_ajouter_plat.setClickable(true);
        button_ajouter_plat.setOnClickListener(view1 -> {
            ajouterPlatDansBdd();
            allerRepasFragment();
        });

    }

    private void initBackButton() {
        button_back = view.findViewById(R.id.back_icon);
        button_back.setClickable(true);
        button_back.setOnClickListener(view1 -> {
            allerRepasSearchFragment();
        });
    }

    private void updateText(){
        textView_totalProteines.setText("" + plat.getProteines());
        textView_totalGlucides.setText("" + plat.getGlucides());
        textView_totalCalories.setText("" + plat.getCalories());
    }

    private void ajouterPlatDansBdd(){
        quantite = Float.parseFloat(String.valueOf(editText_quantite.getText()));
        dbUserManager.insertPlat(typeRepas,date,idPlat,quantite);
    }

    private void trouverPlatDansBdd(){
        platManager.open();
        plat = platManager.getPlatId(idPlat);
        platManager.close();
    }

    private void allerRepasFragment(){
        // Création du bundle d'un bundle de données avec l'objet que l'on veut passé en argument du fragment repas
        Bundle bundle = new Bundle();
        bundle.putString("typeRepas", typeRepas);
        bundle.putLong("date", date.getTime());

        RepasFragment repasFragment = new RepasFragment();
        repasFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, repasFragment);
        fragmentTransaction.commit();
    }

    private void allerRepasSearchFragment(){
        Bundle bundle = new Bundle();
        bundle.putString("typeRepas", typeRepas);
        bundle.putLong("date", date.getTime());

        AddRepasSearchFragment addRepasSearchFragment = new AddRepasSearchFragment();
        addRepasSearchFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, addRepasSearchFragment);
        fragmentTransaction.commit();
    }
}
