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
public class PlatAfficherAdapter extends BaseAdapter {

    private Context context;
    private List<Plat> platList;
    private LayoutInflater inflater;

    public PlatAfficherAdapter(Context context, List<Plat> platList){
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
    public Plat getItem(int position) // Nom de l'utilisateur pour un certain item
    {
        return platList.get(position);
    }

    @Override
    public long getItemId(int position) // la position de l'item
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) // attribuer le plat au textView
    {

        convertView = inflater.inflate(R.layout.item_afficher_plat, null); // association du layout item_list_plat_afficher avec la view

        TextView affichage_text_view = convertView.findViewById(R.id.text_afficher_plat);
        // recuperation des informations par rapport au plat
        Plat currentPlat = getItem(position);
        String nomDuPlat = currentPlat.getNom();
        Float poidsDuPlat = currentPlat.getQuantite();

        // mise à jour de la TextView du nom du plat
        TextView nomDuPlatView = convertView.findViewById(R.id.text_afficher_plat);
        nomDuPlatView.setText(nomDuPlat);

        return convertView;
    }
}
