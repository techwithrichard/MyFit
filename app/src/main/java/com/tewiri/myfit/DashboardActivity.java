package com.tewiri.myfit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class DashboardActivity extends AppCompatActivity {
    // Class fields to store views for efficiency and reuse
    private MaterialCardView heartRateCard, stepsCard, caloriesCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Step 2: Set up system bars
        setupSystemBars();

        // Step 3: Initialize UI components
        initUI();

        // Step 4: Load data
        loadDashboardData();
    }

    private void setupSystemBars() {
        Window window = getWindow();

        // Set status bar color
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.dark_space));

        // Set navigation bar color
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_space));

        // Make status bar icons light (for dark background)
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() |
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initUI() {
        // Try to find MaterialCardView directly via include IDs
        heartRateCard = findViewById(R.id.card_heart_rate);
        stepsCard = findViewById(R.id.card_steps);
        caloriesCard = findViewById(R.id.card_calories);

        // Log and check for null, set stroke colors if found
        if (heartRateCard == null) {
            Log.e("DashboardActivity", "heartRateCard is null! Check ID card_heart_rate and card_stat.xml");
            // Fallback: Try finding MaterialCardView within the included layout
            View heartRateView = findViewById(R.id.card_heart_rate);
            if (heartRateView != null) {
                heartRateCard = heartRateView.findViewById(R.id.card_view);
                if (heartRateCard != null) {
                    heartRateCard.setStrokeColor(ContextCompat.getColor(this, R.color.electric_blue));
                } else {
                    Log.e("DashboardActivity", "heartRateCard still null! Check card_view ID in card_stat.xml");
                }
            }
        } else {
            heartRateCard.setStrokeColor(ContextCompat.getColor(this, R.color.electric_blue));
        }

        if (stepsCard == null) {
            Log.e("DashboardActivity", "stepsCard is null! Check ID card_steps and card_stat.xml");
            View stepsView = findViewById(R.id.card_steps);
            if (stepsView != null) {
                stepsCard = stepsView.findViewById(R.id.card_view);
                if (stepsCard != null) {
                    stepsCard.setStrokeColor(ContextCompat.getColor(this, R.color.neon_green));
                } else {
                    Log.e("DashboardActivity", "stepsCard still null! Check card_view ID in card_stat.xml");
                }
            }
        } else {
            stepsCard.setStrokeColor(ContextCompat.getColor(this, R.color.neon_green));
        }

        if (caloriesCard == null) {
            Log.e("DashboardActivity", "caloriesCard is null! Check ID card_calories and card_stat.xml");
            View caloriesView = findViewById(R.id.card_calories);
            if (caloriesView != null) {
                caloriesCard = caloriesView.findViewById(R.id.card_view);
                if (caloriesCard != null) {
                    caloriesCard.setStrokeColor(ContextCompat.getColor(this, R.color.coral_red));
                } else {
                    Log.e("DashboardActivity", "caloriesCard still null! Check card_view ID in card_stat.xml");
                }
            }
        } else {
            caloriesCard.setStrokeColor(ContextCompat.getColor(this, R.color.coral_red));
        }
    }

    private void loadDashboardData() {
        // Initialize progress bars
        LinearProgressIndicator workoutProgress = findViewById(R.id.workout_progress);
        if (workoutProgress == null) {
            Log.e("DashboardActivity", "workoutProgress is null! Check ID workout_progress in activity_dashboard.xml");
        } else {
            workoutProgress.setProgressCompat(75, true); // Example value
        }

        // You'll replace these with actual data from your database
        updateHeartRateCard(72); // Example heart rate
        updateStepsCard(4582); // Example steps
        updateCaloriesCard(1240); // Example calories
    }

    private void updateHeartRateCard(int bpm) {
        if (heartRateCard == null) {
            Log.e("DashboardActivity", "heartRateCard is null in updateHeartRateCard");
            return;
        }
        TextView value = heartRateCard.findViewById(R.id.stat_value);
        ImageView icon = heartRateCard.findViewById(R.id.stat_icon);
        if (value == null || icon == null) {
            Log.e("DashboardActivity", "stat_value or stat_icon is null in heartRateCard");
            return;
        }
        value.setText(String.valueOf(bpm));

        // Change color based on heart rate
        int colorRes = (bpm > 100) ? R.color.coral_red :
                (bpm > 80) ? R.color.electric_blue : R.color.neon_green;
        value.setTextColor(ContextCompat.getColor(this, colorRes));
        icon.setColorFilter(ContextCompat.getColor(this, colorRes));
    }

    private void updateStepsCard(int steps) {
        if (stepsCard == null) {
            Log.e("DashboardActivity", "stepsCard is null in updateStepsCard");
            return;
        }
        TextView value = stepsCard.findViewById(R.id.stat_value);
        if (value == null) {
            Log.e("DashboardActivity", "stat_value is null in stepsCard");
            return;
        }
        value.setText(String.format("%,d", steps));
    }

    private void updateCaloriesCard(int calories) {
        if (caloriesCard == null) {
            Log.e("DashboardActivity", "caloriesCard is null in updateCaloriesCard");
            return;
        }
        TextView value = caloriesCard.findViewById(R.id.stat_value);
        if (value == null) {
            Log.e("DashboardActivity", "stat_value is null in caloriesCard");
            return;
        }
        value.setText(String.format("%,d", calories));
    }
}