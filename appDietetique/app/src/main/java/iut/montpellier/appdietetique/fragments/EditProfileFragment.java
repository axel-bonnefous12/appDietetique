package iut.montpellier.appdietetique.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    private TextView inputSexe;
    private String sexe="";

    private SharedPreferences prefs;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        this.button = view.findViewById(R.id.btn_sauvegarder);

        //selection du sexe pour pouvoir les sauvegarder
        inputSexe =(TextView) view.findViewById(R.id.sexeInput);
        inputSexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder changerDesexe = new AlertDialog.Builder(view.getContext());
                changerDesexe.setTitle("définir mon sexe");
                changerDesexe.setMessage("Etes vous une femme?");
                changerDesexe.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sexe="femme";
                        inputSexe.setText(sexe);
                    }
                });
                changerDesexe.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sexe="homme";
                        inputSexe.setText(sexe);
                    }
                });
                changerDesexe.show();
            }
        });

        prefs = getContext().getSharedPreferences("MY_DATA",MODE_PRIVATE);
        String nom = prefs.getString("MON_NOM","");
        String prenom = prefs.getString("MON_PRENOM","");
        int age = prefs.getInt("MON_AGE",1);
        int poids = prefs.getInt("MON_POIDS", 1);
        int taille = prefs.getInt("MA_TAILLE",100);
        String monSexe = prefs.getString("MON_SEXE","homme/femme");


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
        inputSexe.setText(monSexe);

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
                String monSexe = prefs.getString("MON_SEXE","homme/femme");

                //sauvegarde des datas
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("MON_NOM",nom);
                editor.putString("MON_PRENOM",prenom);
                editor.putInt("MON_AGE",age);
                editor.putInt("MON_POIDS",poids);
                editor.putInt("MA_TAILLE",taille);
                editor.putString("MON_SEXE",sexe);
                editor.apply();

                //return to profile
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                UserFragment userFragment = new UserFragment();//
                fragmentTransaction.replace(R.id.fragment_container, userFragment); // remplacer le fragment actuel avec le fragment UserFragment
                fragmentTransaction.commit(); //commit du fragment

            }
        });
        return  view;
    }
}