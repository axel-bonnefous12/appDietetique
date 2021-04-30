package iut.montpellier.appdietetique.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.models.Plat;

/**
 * Class permettant d'adapter les plats dans la listView du fragment ReapasFragment
 */
public class PlatItemAdapter extends BaseAdapter {

    private Context context;
    private List<Plat> platList;
    private LayoutInflater inflater;

    public PlatItemAdapter(Context context, List<Plat> platList){
        this.context = context;
        this.platList = platList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() // Nombre d'élément dans la liste
    {
        return platList.size();
    }

    @Override
    public Plat getItem(int i) // Nom de l'utilisateur pour un certain item
    {
        return platList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) // attribuer le plat au textView
    {

        view = inflater.inflate(R.layout.item_list_plat, null); // association du layout item_list_plat avec la view

        // recuperation des informations par rapport au plat
        Plat currentPlat = getItem(i);
        String nomDuPlat = currentPlat.getNom();
        Float poidsDuPlat = currentPlat.getQuantite();

        // mise à jour de la TextView du nom du plat
        TextView nomDuPlatView = view.findViewById(R.id.text_nom_du_plat);
        nomDuPlatView.setText(nomDuPlat);

        // mise à jour de la TextView du poids du plat
        TextView PoidsDuPlatView = view.findViewById(R.id.text_value_poids);
        PoidsDuPlatView.setText("" + poidsDuPlat + "g");

        return view;
    }
}
