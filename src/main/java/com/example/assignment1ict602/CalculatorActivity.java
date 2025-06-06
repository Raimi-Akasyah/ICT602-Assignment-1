package com.example.assignment1ict602;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private EditText etInvestment, etDividendRate, etMonths;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialize views
        etInvestment = findViewById(R.id.et_investment);
        etDividendRate = findViewById(R.id.et_dividend_rate);
        etMonths = findViewById(R.id.et_months);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        drawer = findViewById(R.id.drawer_layout);

        // Setup Toolbar with Hamburger Icon
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);

        // Setup Navigation Drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Intent MainIntent = new Intent(CalculatorActivity.this, MainActivity.class);
                startActivity(MainIntent);
            }
            else if (id == R.id.nav_about) {
                Intent aboutIntent = new Intent(CalculatorActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
            else if (id == R.id.nav_calculator) {
                // Already in calculator, just close drawer
                drawer.closeDrawer(GravityCompat.START);
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        // Calculate button click listener
        btnCalculate.setOnClickListener(v -> calculateDividend());
    }

    private void calculateDividend() {
        try {
            // Get input values
            double investment = Double.parseDouble(etInvestment.getText().toString());
            double dividendRate = Double.parseDouble(etDividendRate.getText().toString());
            int months = Integer.parseInt(etMonths.getText().toString());

            // Calculate
            double annualDividend = investment * (dividendRate / 100);
            double monthlyDividend = annualDividend / 12;
            double totalDividend = monthlyDividend * months;

            // Format result
            DecimalFormat df = new DecimalFormat("#,##0.00");
            String result = "Monthly Dividend: RM" + df.format(monthlyDividend) + "\n" +
                    "Total Dividend for " + months + " months: RM" + df.format(totalDividend);

            // Display result
            tvResult.setText(result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
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