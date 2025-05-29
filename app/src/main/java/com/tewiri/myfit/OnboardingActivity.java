package com.tewiri.myfit;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnNext;
    private int[] layouts;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Initialize layouts array
        layouts = new int[]{
                R.layout.onboarding_page1,
                R.layout.onboarding_page2,
                R.layout.onboarding_page3};

        // Initialize views
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.dotsLayout);
        btnNext = findViewById(R.id.btn_next);

        // Set up ViewPager
        OnboardingAdapter adapter = new OnboardingAdapter(this, layouts);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        // Set up dots indicator
        addDotsIndicator(0);

        // Set up next button click listener
        btnNext.setOnClickListener(v -> {
            if (currentPage < layouts.length - 1) {
                viewPager.setCurrentItem(currentPage + 1);
            } else {
                launchMainActivity();
            }
        });
    }

    private void addDotsIndicator(int position) {
        if (dotsLayout == null) return;

        dotsLayout.removeAllViews();
        int dotSize = (int) getResources().getDimension(R.dimen.dot_size);

        for (int i = 0; i < layouts.length; i++) {
            View dot = new View(this);
            dot.setBackgroundResource(i == position ? R.drawable.dot_active : R.drawable.dot_inactive);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotSize, dotSize);
            params.setMargins(8, 0, 8, 0);
            dotsLayout.addView(dot, params);
        }
    }

    private final ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // Not needed for this implementation
        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            addDotsIndicator(position);

            // Update button text based on position
            btnNext.setText(position == layouts.length - 1 ?
                    R.string.get_started : R.string.next);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // Not needed for this implementation
        }
    };

    private void launchMainActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}