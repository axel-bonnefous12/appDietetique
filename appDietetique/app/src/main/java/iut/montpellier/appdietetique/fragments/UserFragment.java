package iut.montpellier.appdietetique.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import iut.montpellier.appdietetique.R;

import static android.content.Context.MODE_PRIVATE;

public class UserFragment extends Fragment{

    private TextView nom;
    private TextView prenom;
    private TextView age;
    private TextView poids;

    View view;
    Button button_change_fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view= inflater.inflate(R.layout.fragment_user_profil,container, false);
        nom = view.findViewById(R.id.champNom);
        prenom = view.findViewById(R.id.champPrenom);
        age = view.findViewById(R.id.champAge);
        poids = view.findViewById(R.id.champPoids);

        Button button =(Button) view.findViewById(R.id.btn_profile);
        Button button2 =(Button) view.findViewById(R.id.btn_graphique_fragment); // Bouton GraphiqueFragment

        SharedPreferences prefs = getContext().getSharedPreferences("MY_DATA",MODE_PRIVATE);
        String name=prefs.getString("MON_NOM","pas de nom");
        String surname=prefs.getString("MON_PRENOM","pas de Prenom");
        int monAge=prefs.getInt("MON_AGE", 0);
        int monPoids=prefs.getInt("MON_POIDS", 0);


        this.nom.setText(name);
        this.prenom.setText(surname);
        this.age.setText(monAge+"");
        this.poids.setText(monPoids+"");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                EditProfileFragment editProfileFragment = new EditProfileFragment();

                fragmentTransaction.replace(R.id.fragment_container, editProfileFragment); // remplacer le fragment actuel avec le fragment repas
                fragmentTransaction.commit(); //commit du fragment
            }
        });
        //spinner choix sexe
        Spinner sexeSpinner = (Spinner)view.findViewById(R.id.spinnerSexe);
        ArrayAdapter<String> sexeSpinnerAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.sexe));
        sexeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sexeSpinner.setAdapter(sexeSpinnerAdapter);

       //spinner choix profil activité
        /*Spinner profilSpinner = (Spinner)view.findViewById(R.id.spinnerActivite);
        ArrayAdapter<String> profilSpinnerAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.profil));
        profilSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        profilSpinner.setAdapter(profilSpinnerAdapter);*/

        //Se rendre sur le Fragment Graphique
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                allerGraphiqueFragment();
            }
        });


        return view;
    }



    private void allerGraphiqueFragment()
    {
        GraphiqueFragment graphiqueFragment = new GraphiqueFragment();

        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, graphiqueFragment);
        fragmentTransaction.commit();
    }



}

