package com.example.samu.poolmii;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.samu.poolmii.Auth.Login;
import com.example.samu.poolmii.Preferencias.BusquedaTrayectoFragment;
import com.example.samu.poolmii.Preferencias.BusquedasFragment;
import com.example.samu.poolmii.Servicios.ServicioTrayectoFragment;
import com.example.samu.poolmii.Servicios.ServiciosFragment;
import com.example.samu.poolmii.listadoServicios.ListadoTrayectos;
import com.google.firebase.auth.FirebaseAuth;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity implements BusquedasFragment.onClickDiaFromFrListener, ServiciosFragment.onClickDiaFromFrListener {


    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);


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
            case R.id.menu_busquedas:
                BusquedasFragment fragment = new BusquedasFragment();
                transaction.replace(R.id.fragment_container, new BusquedasFragment());
                break;
            case R.id.menu_perfil:
                transaction.replace(R.id.fragment_container, new PerfilFragment());
                break;
            case R.id.menu_mis_servicios:
                transaction.replace(R.id.fragment_container, new ServiciosFragment());
                break;
            case R.id.menu_mis_trayectos:
                transaction.replace(R.id.fragment_container, new ListadoTrayectos());
                break;
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
            default:
                //transaction.replace(R.id.fragment_container, new ListadoTrayectos());
        }
        transaction.commit();
    }




    @Override
    public void onClickDiaFromFr(int frag, String dia) {
        Bundle bundle = new Bundle();
        bundle.putString("arg0", dia);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment=null;
        if(frag==1){
            fragment = new BusquedaTrayectoFragment();

        }else if(frag==2){
            fragment = new ServicioTrayectoFragment();
        }else
        {
            return;
        }
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null).commit();

        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
