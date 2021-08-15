package com.example.prototipo2tt;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class invitado_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout dl;
    NavigationView nv;
    Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitado_home);
        dl = findViewById(R.id.drawer_layout);
        nv = findViewById(R.id.nav_view);
        toolb = findViewById(R.id.toolbar);

        setSupportActionBar(toolb);

        //hide and show items
        Menu menu = nv.getMenu();
        menu.findItem(R.id.id_menulogin).setVisible(false);

        nv.bringToFront();
        ActionBarDrawerToggle tg = new ActionBarDrawerToggle(this, dl, toolb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(tg);
        tg.syncState();

        nv.setNavigationItemSelectedListener(this);
        nv.setCheckedItem(R.id.id_menuhorario);
    }

    @Override
    public void onBackPressed(){
        if(dl.isDrawerOpen(GravityCompat.START)){
            dl.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.id_menuhorario:
                break;
            case R.id.id_menuagendar:
                /*
                Intent i = new Intent(MainActivity.this, Bus.class);
                startActivity(i);
                */
                break;
            case R.id.id_menureserva:
                Toast.makeText(this, "hola mundo bonito uwu", Toast.LENGTH_SHORT).show();
                break;

        }
        dl.closeDrawer(GravityCompat.START);
        return true;
    }
}