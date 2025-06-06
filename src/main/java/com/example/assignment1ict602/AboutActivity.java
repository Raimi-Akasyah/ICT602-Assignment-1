package com.example.assignment1ict602;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // 1. Setup Toolbar with Hamburger Icon
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);

        // 2. Setup Navigation Drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Intent MainIntent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(MainIntent);
            }
            else if (id == R.id.nav_about) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else if (id == R.id.nav_calculator) {
                // Handle calculator navigation
                Intent calculatorIntent = new Intent(AboutActivity.this, CalculatorActivity.class);
                startActivity(calculatorIntent);
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        // 3. Set your personal information (REPLACE THESE VALUES)
        TextView developerName = findViewById(R.id.developer_name);
        TextView studentId = findViewById(R.id.student_id);
        TextView classGroup = findViewById(R.id.class_group);
        TextView additionalLink = findViewById(R.id.additional_link);

        developerName.setText("Raimi Akasyah Bin Abdul Halim"); // Replace with your name
        studentId.setText("2020105261"); // Replace with your student ID
        classGroup.setText("RCDCS2405A"); // Replace with your class
        additionalLink.setText("Github Repository"); // Replace with your link text

        // 4. Handle link click
        additionalLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with your actual link
                String url = "https://github.com/Raimi-Akasyah/ICT602-Assignment-1";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
        return true;
    }
}