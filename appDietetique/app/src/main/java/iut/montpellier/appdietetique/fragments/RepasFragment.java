package iut.montpellier.appdietetique.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.models.Repas;

public class RepasFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repas,container, false);

        // Récupération des data passé en argument
        Bundle bundle = this.getArguments();
        Repas data = bundle.getParcelable("data");

        // Mise à jour du champ de text titre
        TextView titre = view.findViewById(R.id.titrePage);
        titre.setText(data.getTypeDuRepas());

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
}
