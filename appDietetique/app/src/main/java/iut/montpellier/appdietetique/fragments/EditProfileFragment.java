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

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import iut.montpellier.appdietetique.MainActivity;
import iut.montpellier.appdietetique.R;

import static android.content.Context.MODE_PRIVATE;


public class EditProfileFragment extends Fragment {
    View view;
    //private EditText inputName;
    private EditText inputSurname;
    private EditText inputAge;
    private EditText inputPoids;
    private EditText inputTaille;
    private TextView inputSexe;
    private TextView inputActivite;

    private SharedPreferences prefs;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        this.button = view.findViewById(R.id.btn_sauvegarder);
        Button btn_changer_sexe = (Button)view.findViewById(R.id.BtnsexeChanger);//bouton de changement du sexe
        Button btn_changer_activite = (Button)view.findViewById(R.id.BtnActiviteChanger);//bouton de changement activité

        //selection du sexe pour pouvoir les sauvegarder
        inputSexe =(TextView) view.findViewById(R.id.sexeInput);
        btn_changer_sexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder changerDesexe = new AlertDialog.Builder(view.getContext());
                changerDesexe.setMessage("vous etes?");
                changerDesexe.setPositiveButton("une femme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputSexe.setText("féminin");
                        Toast.makeText(view.getContext(), "changement fait", Toast.LENGTH_SHORT).show();
                    }
                });
                changerDesexe.setNegativeButton("un homme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputSexe.setText("masculin");
                        Toast.makeText(view.getContext(), "changement fait", Toast.LENGTH_SHORT).show();
                    }
                });
                changerDesexe.show();
            }
        });
        //selection de l'activite pour pouvoir les sauvegarder
        inputActivite =(TextView) view.findViewById(R.id.activiteInput);
        btn_changer_activite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final String[] items = {"sédentaire", "légèrement actif", "actif", "très actif"};
                    final ArrayList<Integer> selectedList = new ArrayList<>();
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setTitle("veuillez faire UN choix");
                    builder.setMultiChoiceItems(items, null,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    if (isChecked) {
                                        selectedList.add(which);

                                    } else if (selectedList.contains(which)) {
                                        selectedList.remove(which);
                                    }
                                }
                            });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ArrayList<String> selectedStrings = new ArrayList<>();

                            for (int j = 0; j < selectedList.size(); j++) {
                                selectedStrings.add(items[selectedList.get(j)]);
                                inputActivite.setText(selectedStrings.get(j).toString());
                            }
                            //Toast.makeText(view.getContext(), "Items selected are: " + Arrays.toString(selectedStrings.toArray()), Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.show();
            }
        });


        prefs = getContext().getSharedPreferences("MY_DATA",MODE_PRIVATE);
        //inutile on le ne veut plus //String nom = prefs.getString("MON_NOM","");
        String prenom = prefs.getString("MON_PRENOM","");
        int age = prefs.getInt("MON_AGE",1);
        int poids = prefs.getInt("MON_POIDS", 1);
        int taille = prefs.getInt("MA_TAILLE",100);
        String monSexe = prefs.getString("MON_SEXE","homme/femme");
        String activite = prefs.getString("MON_ACTIVITE","activité");


        //inputName =(EditText) view.findViewById(R.id.inputName);
        inputSurname =(EditText) view.findViewById(R.id.inputSurname);
        inputAge =(EditText) view.findViewById(R.id.inputAge);
        inputPoids =(EditText) view.findViewById(R.id.inputPoids);
        inputTaille = (EditText) view.findViewById(R.id.inputTaille);


        //on set les sharedPref
        //inputName.setText(nom);
        inputSurname.setText(prenom);
        inputAge.setText(age+"");
        inputPoids.setText(poids+"");
        inputTaille.setText(taille+"");
        inputSexe.setText(monSexe);
        inputActivite.setText(activite);

        //bouton de validation et sauvegarde
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on get les input Text
                //String nom=inputName.getText().toString();
                String prenom=inputSurname.getText().toString();
                int age=Integer.parseInt(inputAge.getText().toString());
                int poids=Integer.parseInt(inputPoids.getText().toString());
                int taille = Integer.parseInt(inputTaille.getText().toString());
                String monSexe = prefs.getString("MON_SEXE","féminin/masculin");
                String activite = prefs.getString("MON_ACTIVITE","pas d'activité");

                //sauvegarde des datas
                SharedPreferences.Editor editor = prefs.edit();
                //editor.putString("MON_NOM",nom);
                editor.putString("MON_PRENOM",prenom);
                editor.putInt("MON_AGE",age);
                editor.putInt("MON_POIDS",poids);
                editor.putInt("MA_TAILLE",taille);
                editor.putString("MON_SEXE",inputSexe.getText().toString());
                editor.putString("MON_ACTIVITE",inputActivite.getText().toString());
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