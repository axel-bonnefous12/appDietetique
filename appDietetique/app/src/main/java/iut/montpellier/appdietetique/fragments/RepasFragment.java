package iut.montpellier.appdietetique.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.adapters.PlatItemAdapter;
import iut.montpellier.appdietetique.models.Plat;
import iut.montpellier.appdietetique.models.Repas;

public class RepasFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repas,container, false);

        // Récupération des data passé en argument
        Bundle bundle = this.getArguments();
        Repas data = bundle.getParcelable("data");

        // Initialise les text view avec les veleurs du repas
        initTextViews(view, data);

        // associer a l'image back arrow l'action de retourner a la journee
        ImageView backButton = view.findViewById(R.id.back_icon);
        backButton.setClickable(true);
        backButton.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
            fragmentTransaction.commit();
        });
        return view;
    }

    private void initTextViews(View view, Repas data){
        // Mise à jour de la listView avec les plats
        ListView listPlats = view.findViewById(R.id.liste_de_plat);

        listPlats.setAdapter(new PlatItemAdapter(getContext(),data.getPlats()));



        // Mise à jour du champ de text titre
        TextView titre = view.findViewById(R.id.titrePage);
        titre.setText(data.getTypeDuRepas());

        // Mise à jour du champ de valeur total de proteines
        TextView totalProteines = view.findViewById(R.id.valeur_proteines);
        totalProteines.setText("" + data.getTotalProteines());

        // Mise à jour du champ de valeur total de proteines
        TextView totalGlucides = view.findViewById(R.id.valeur_glucides);
        totalGlucides.setText("" + data.getTotalGlucides());

        // Mise à jour du champ de valeur total de calories
        TextView totalCalories = view.findViewById(R.id.valeur_calories);
        totalCalories.setText("" + data.getTotalCalories());
    }
}
