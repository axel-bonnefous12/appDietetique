package iut.montpellier.appdietetique.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import iut.montpellier.appdietetique.MainActivity;
import iut.montpellier.appdietetique.R;

import static android.content.Context.MODE_PRIVATE;


public class EditProfileFragment extends Fragment {
    View view;
    private EditText inputName;
    private EditText inputSurname;
    private EditText inputAge;
    private EditText inputPoids;
    private EditText inputTaille;

    private SharedPreferences prefs;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        this.button = view.findViewById(R.id.btn_sauvegarder);

        prefs = getContext().getSharedPreferences("MY_DATA",MODE_PRIVATE);
        String nom = prefs.getString("MON_NOM","");
        String prenom = prefs.getString("MON_PRENOM","");
        int age = prefs.getInt("MON_AGE",1);
        int poids = prefs.getInt("MON_POIDS", 1);
        //String sexe = prefs.getString("MON_SEXE","");
        int taille = prefs.getInt("MA_TAILLE",100);


        inputName =(EditText) view.findViewById(R.id.inputName);
        inputSurname =(EditText) view.findViewById(R.id.inputSurname);
        inputAge =(EditText) view.findViewById(R.id.inputAge);
        inputPoids =(EditText) view.findViewById(R.id.inputPoids);
        inputTaille = (EditText) view.findViewById(R.id.inputTaille);

        //on set les sharedPref
        inputName.setText(nom);
        inputSurname.setText(prenom);
        inputAge.setText(age+"");
        inputPoids.setText(poids+"");
        inputTaille.setText(taille+"");


        //bouton de validation et sauvegarde
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on get les input Text
                String nom=inputName.getText().toString();
                String prenom=inputSurname.getText().toString();
                int age=Integer.parseInt(inputAge.getText().toString());
                int poids=Integer.parseInt(inputPoids.getText().toString());
                int taille = Integer.parseInt(inputTaille.getText().toString());


                //sauvegarde des datas
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("MON_NOM",nom);
                editor.putString("MON_PRENOM",prenom);
                editor.putInt("MON_AGE",age);
                editor.putInt("MON_POIDS",poids);
                editor.putInt("MA_TAILLE",taille);
                //editor.putString("MON_SEXE",sexe);
                editor.apply();
                //return to profile
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                UserFragment userFragment = new UserFragment();//

                fragmentTransaction.replace(R.id.fragment_container, userFragment); // remplacer le fragment actuel avec le fragment repas
                fragmentTransaction.commit(); //commit du fragment

            }
        });
        return  view;
    }
}