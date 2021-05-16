package iut.montpellier.appdietetique.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private TextView taille;
    private TextView sexeUser;
    private TextView activiteUser;



    View view;
    Button button_change_fragment;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view= inflater.inflate(R.layout.fragment_user_profil,container, false);
        nom = view.findViewById(R.id.champNom);
        prenom = view.findViewById(R.id.champPrenom);
        age = view.findViewById(R.id.champAge);
        poids = view.findViewById(R.id.champPoids);
        taille = view.findViewById(R.id.champTaille);
        sexeUser = view.findViewById(R.id.champSexe);
        activiteUser = view.findViewById(R.id.champActivite);

        Button button =(Button) view.findViewById(R.id.btn_profile);//bouton de modification profile
        Button button2 =(Button) view.findViewById(R.id.btn_graphique_fragment); // Bouton GraphiqueFragment

        //sharedPreferences
        SharedPreferences prefs = getContext().getSharedPreferences("MY_DATA",MODE_PRIVATE);
        String name=prefs.getString("MON_NOM","pas de nom");
        String surname=prefs.getString("MON_PRENOM","pas de Prenom");
        int monAge=prefs.getInt("MON_AGE", 1);
        int monPoids=prefs.getInt("MON_POIDS", 1);
        int maTaille=prefs.getInt("MA_TAILLE",100);
        String monSexe=prefs.getString("MON_SEXE","féminin/masculin");
        String monActivite = prefs.getString("MON_ACTIVITE","activité");

        this.nom.setText(name);
        this.prenom.setText(surname);
        this.age.setText(monAge+"");
        this.poids.setText(monPoids+"");
        this.taille.setText(maTaille+"");
        this.sexeUser.setText(monSexe);
        this.activiteUser.setText(monActivite);

        //on va sur la page de l'edition profile pour remplir les champs
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                EditProfileFragment editProfileFragment = new EditProfileFragment();

                fragmentTransaction.replace(R.id.fragment_container, editProfileFragment); // remplacer le fragment actuel avec le fragment repas
                fragmentTransaction.commit(); //commit du fragment
            }
        });

       //spinner choix profil activité
        /*Spinner profilSpinner = (Spinner)view.findViewById(R.id.spinnerActivite);
        ArrayAdapter<String> profilSpinnerAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.profil));
        profilSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        profilSpinner.setAdapter(profilSpinnerAdapter);

        profilSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String txt = parent.getItemAtPosition(position).toString();
            }
        });*/

        //calcul IMC poids/(taille en cm * taille en cm) on cast et on arrondi au supérieur pour l'indice Imc en valeur
        if(maTaille!=0 || monPoids !=0) {
            TextView imcText = view.findViewById(R.id.imcText);
            TextView Imc = view.findViewById(R.id.ValeurImc);
            float T1 = ((float) (maTaille) / 100);
            float imcFloat = (float) Math.ceil(monPoids / (T1 * T1));
            Imc.setText(imcFloat + "");
            if (imcFloat < 17) {
                imcText.setText("dénutrition");
            } else if (imcFloat >= 17 && imcFloat <= 18) {
                imcText.setText("maigreur");
            } else if (imcFloat >= 19 && imcFloat <= 25) {
                imcText.setText("corpulence normale");
            } else if (imcFloat >= 26 && imcFloat <= 30) {
                imcText.setText("surpoids");
            } else if (imcFloat >= 31 && imcFloat <= 35) {
                imcText.setText("obésité modéré");
            } else if (imcFloat >= 36 && imcFloat <= 40) {
                imcText.setText("obésité sévère");
            } else if (imcFloat > 40) {
                imcText.setText("obésité morbide");
            }
        }

         //besoin énergétique selon une femme ou un homme
        if(monAge !=0 && monSexe!="") {
            TextView valeurApport = view.findViewById(R.id.valeurColorie);
            float calculEnergieKj = 0;
             String sexe = monSexe ;

            if ( sexe.equals("féminin")) {
                if (monAge >= 18 && monAge <= 29) {
                    calculEnergieKj = (float) ((0.062*monPoids) + 2.036)*239;
                }
                else if (monAge >= 30 && monAge <= 60) {
                    calculEnergieKj = (float) ((0.034*monPoids) + 3.538)*239;
                }
                else {
                    calculEnergieKj = (float) ((0.038*monPoids) + 3.538)*239;
                }
                Math.floor(calculEnergieKj);
                valeurApport.setText(calculEnergieKj + "");
            }
          else{
                if (monAge >= 18 && monAge <= 29) {
                    calculEnergieKj = (float) ((0.063*monPoids) + 2.896)*239;
                }
                else if (monAge >= 30 && monAge <= 60) {
                    calculEnergieKj = (float) (0.048*monPoids + 3.653)*239;
                }
                else {
                    calculEnergieKj = (float) ((0.049*monPoids) + 2.459)*239;
                }
                Math.floor(calculEnergieKj);
                valeurApport.setText(calculEnergieKj + "");
            }
        }

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

