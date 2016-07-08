package com.example.samu.poolmii;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout =
                (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(android.R.drawable.btn_star_big_on);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setFragment(-1);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        setFragment(item.getItemId());
                        // Cerrar el drawer al hacer click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setFragment(int item){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (item){
            case R.id.menu_preferencias_viaje:
                transaction.replace(R.id.fragment_container, new PreferenciasViajeFragment());
                break;
            case R.id.menu_perfil:
                transaction.replace(R.id.fragment_container, new PerfilFragment());
                break;
            case R.id.menu_mis_rutas:
                transaction.replace(R.id.fragment_container, new MisRutasFragment());
                break;
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
            default:
                transaction.replace(R.id.fragment_container, new MisRutasFragment());
        }

        transaction.commit();
    }

}
