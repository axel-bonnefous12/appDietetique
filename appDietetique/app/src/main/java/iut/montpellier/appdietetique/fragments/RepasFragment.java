package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import iut.montpellier.appdietetique.models.Repas;

public class RepasFragment extends Fragment {

    ImageView backButton;
    ListView listPlats;
    TextView textView_titre;
    TextView textView_totalProteines;
    TextView textView_totalGlucides;
    TextView textView_totalCalories;

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
        intBackButton(view);

        return view;
    }

    private void initTextViews(View view, Repas data){
        // Mise à jour de la listView avec les plats
        listPlats = view.findViewById(R.id.liste_de_plat);
        listPlats.setAdapter(new PlatItemAdapter(getContext(),data.getPlats())); //utilisation de l'adapteur pour l'ajout d'item plat dans la liste


        // Mise à jour du champ de text titre
        textView_titre = view.findViewById(R.id.titrePage);
        textView_titre.setText(data.getTypeDuRepas());

        // Mise à jour du champ de valeur total de proteines
        textView_totalProteines = view.findViewById(R.id.valeur_proteines);
        textView_totalProteines.setText("" + data.getTotalProteines());

        // Mise à jour du champ de valeur total de proteines
        textView_totalGlucides = view.findViewById(R.id.valeur_glucides);
        textView_totalGlucides.setText("" + data.getTotalGlucides());

        // Mise à jour du champ de valeur total de calories
        textView_totalCalories = view.findViewById(R.id.valeur_calories);
        textView_totalCalories.setText("" + data.getTotalCalories());
    }

    private void intBackButton(View view) {
        backButton = view.findViewById(R.id.back_icon);
        backButton.setClickable(true);
        backButton.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
            fragmentTransaction.commit();
        });
    }
}
