package iut.montpellier.appdietetique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import iut.montpellier.appdietetique.fragments.HomeFragment;
import iut.montpellier.appdietetique.fragments.SearchFragment;
import iut.montpellier.appdietetique.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creation des boutons de navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        bottomNav.setSelectedItemId(R.id.nav_home);// set nav_hom selectionné à la creation
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selecterFragment=null;
            switch(item.getItemId()){
                case R.id.nav_search_loup: selecterFragment = new SearchFragment();
                break;

                case R.id.nav_home: selecterFragment = new HomeFragment();
                break;

                case R.id.nav_user_profil: selecterFragment = new UserFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selecterFragment).commit();
            return true;
        }
    };

}//end MainActivity