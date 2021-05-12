package iut.montpellier.appdietetique.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import iut.montpellier.appdietetique.R;

import static android.content.Context.MODE_PRIVATE;

public class UserFragment extends Fragment {
     View view;
    private TextView nom;
    private TextView prenom;
    private TextView age;
    private TextView poids;
    private TextView sexe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_user_profil,container, false);
        nom = view.findViewById(R.id.champNom);
        prenom = view.findViewById(R.id.champPrenom);
        age = view.findViewById(R.id.champAge);
        poids = view.findViewById(R.id.champPoids);
        sexe = view.findViewById(R.id.champSexe);
        Button button =(Button) view.findViewById(R.id.btn_profile);

        SharedPreferences prefs = getContext().getSharedPreferences("MY_DATA",MODE_PRIVATE);
        String name=prefs.getString("MON_NOM","pas de nom");
        String surname=prefs.getString("MON_PRENOM","pas de Prenom");
        int monAge=prefs.getInt("MON_AGE", 0);
        int monPoids=prefs.getInt("MON_POIDS", 0);
        String monSexe=prefs.getString("MON_SEXE","votre sexe");

        this.nom.setText(name);
        this.prenom.setText(surname);
        this.age.setText(monAge+"");
        this.poids.setText(monPoids+"");
        this.sexe.setText(monSexe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                EditProfileFragment editProfileFragment = new EditProfileFragment();

                fragmentTransaction.replace(R.id.fragment_container, editProfileFragment); // remplacer le fragment actuel avec le fragment repas
                fragmentTransaction.commit(); //commit du fragment
            }
        });
        return view;
    }

}
