package iut.montpellier.appdietetique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import iut.montpellier.appdietetique.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // remplacement du fragment_container par le fragment home
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).addToBackStack(null).commit();
    }

}