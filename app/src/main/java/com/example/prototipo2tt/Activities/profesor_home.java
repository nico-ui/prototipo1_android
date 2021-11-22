package com.example.prototipo2tt.Activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prototipo2tt.R;

public class profesor_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout dl;
    NavigationView nv;
    Toolbar toolb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profesor_home);
        dl = (DrawerLayout) findViewById(R.id.drawer_layout_profesor);
        nv = (NavigationView) findViewById(R.id.nav_view_profesor);
        toolb = (Toolbar) findViewById(R.id.toolbar_profesor);

        setSupportActionBar(toolb);

        //hide and show items
        Menu menu = nv.getMenu();
        menu.findItem(R.id.id_menuloginprofesor).setVisible(false);

        nv.bringToFront();
        ActionBarDrawerToggle tg = new ActionBarDrawerToggle(this, dl, toolb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(tg);
        tg.syncState();

        nv.setNavigationItemSelectedListener(this);
        nv.setCheckedItem(R.id.id_menuhorarioprofesor);

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
            case R.id.id_menuhorarioprofesor:
                break;
            case R.id.id_menubuzon:
                /*
                Intent i = new Intent(MainActivity.this, Bus.class);
                startActivity(i);
                */
                break;
            case R.id.id_menubitacora:
                Toast.makeText(this, "Nada que mostrar", Toast.LENGTH_SHORT).show();
                break;

        }
        dl.closeDrawer(GravityCompat.START);
        return true;
    }
}
