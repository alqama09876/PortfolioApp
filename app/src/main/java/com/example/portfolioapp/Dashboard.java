package com.example.portfolioapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.example.portfolioapp.Fragment.AboutFragment;
import com.example.portfolioapp.Fragment.CertificateFragment;
import com.example.portfolioapp.Fragment.ContactFragment;
import com.example.portfolioapp.Fragment.ExperienceFragment;
import com.example.portfolioapp.Fragment.HomeFragment;
import com.example.portfolioapp.Fragment.LogoutFragment;
import com.example.portfolioapp.Fragment.ProjectFragment;
import com.example.portfolioapp.Fragment.SkillFragment;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    public DrawerLayout my_drawer_layout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        my_drawer_layout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, my_drawer_layout, toolbar, R.string.nav_open, R.string.nav_close);

        my_drawer_layout.addDrawerListener(toggle);

        toggle.syncState();

        loadFragment(new HomeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.nav_home) {
                    loadFragment(new HomeFragment());
                } else if (id == R.id.about) {
                    loadFragment(new AboutFragment());
                } else if (id == R.id.nav_experience) {
                    loadFragment(new ExperienceFragment());
                } else if (id == R.id.nav_skill) {
                    loadFragment(new SkillFragment());
                } else if (id == R.id.nav_certificate) {
                    loadFragment(new CertificateFragment());
                } else if (id == R.id.nav_project) {
                    loadFragment(new ProjectFragment());
                } else if (id == R.id.nav_contact) {
                    loadFragment(new ContactFragment());
                } else {
                    loadFragment(new LogoutFragment());
                }


                my_drawer_layout.closeDrawer(GravityCompat.START);

                return true;
            }

        });
    }

    @Override
    public void onBackPressed() {
        if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            my_drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}