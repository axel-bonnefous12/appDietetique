package iut.montpellier.appdietetique.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import iut.montpellier.appdietetique.R;

public class UserFragment extends Fragment {
     View view;
    private TextView nom;
    private TextView prenom;
    private TextView age;
    private TextView poids;
    private TextView sexe;
    private String m_text="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_user_profil,container, false);
        nom = view.findViewById(R.id.champNom);
        prenom = view.findViewById(R.id.champPrenom);
        age = view.findViewById(R.id.champAge);
        poids = view.findViewById(R.id.champPoids);
        sexe = view.findViewById(R.id.champSexe);
        Button button =(Button) view.findViewById(R.id.btn_modif_profil);
        
        // changer un champs texte de l'user Pour l'instant j'ai trouvé que le prénom
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final EditText inputNom= new EditText(view.getContext());
                inputNom.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(inputNom);
                builder.setView(inputNom);
                builder.setMessage("changer le nom?");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_text= inputNom.getText().toString();
                        nom.setText(m_text);
                        Toast.makeText(view.getContext(), "changement fait", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        return view;
    }
    /*public void changerInfo(View v){
        final EditText inputNom= new EditText(getContext());
        inputNom.setInputType(InputType.TYPE_CLASS_TEXT);
        //nom.setText(inputNom.getText());
        //AlertDialog.Builder popUp = new AlertDialog.Builder(new AlertDialog().Builder(view));
    }*/
}
