package iut.montpellier.appdietetique.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import iut.montpellier.appdietetique.BDD.DbUserManager;
import iut.montpellier.appdietetique.BDD.PlatManager;
import iut.montpellier.appdietetique.R;
import iut.montpellier.appdietetique.models.Journee;
import iut.montpellier.appdietetique.models.Plat;


public class GraphiqueFragment extends Fragment
{

    View view;
    int idPlat;
    DbUserManager dbUserManager;
    PlatManager platManager;
    Plat plat;
    Journee journee;

    TextView textView_afficher_nomPlat;
    TextView textView_totalProteines;
    TextView textView_totalGlucides;
    TextView textView_totalCalories;
    ImageView button_back;

    ValueDataEntry proteines;
    ValueDataEntry glucides;
    ValueDataEntry calories;

    DbUserManager userDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_graphique, container, false);

        initBackButton();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userDb = new DbUserManager(getContext());

        //Objects.requireNonNull(getActivity()). ------> Car on utilise un fragment
        AnyChartView anyChartView = Objects.requireNonNull(getActivity()).findViewById(R.id.any_chart_view);
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();

        Journee journee = new Journee(Calendar.getInstance().getTime(), userDb);

        proteines = new ValueDataEntry("Total Calories", journee.getTotalProteines());
        glucides = new ValueDataEntry("Total Glucides", journee.getTotalGlucides());
        calories = new ValueDataEntry("Total Calories", journee.getTotalCalories());

        data.add(proteines);
        data.add(glucides);
        data.add(calories);

        pie.data(data);


        anyChartView.setChart(pie);
    }





    private void initBackButton()
    {
        button_back = view.findViewById(R.id.back_icon);
        button_back.setClickable(true);
        button_back.setOnClickListener(view1 -> {
            allerUserFragment();
        });
    }

    private void allerUserFragment(){
        UserFragment userFragment = new UserFragment();

        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, userFragment);
        fragmentTransaction.commit();
    }


}