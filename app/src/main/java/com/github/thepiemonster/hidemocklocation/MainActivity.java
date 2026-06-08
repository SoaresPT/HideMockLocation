package com.github.thepiemonster.hidemocklocation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.github.thepiemonster.hidemocklocation.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // inflating our xml layout in our activity main binding
        setModuleState(binding);

        binding.txtVersion.setText(BuildConfig.VERSION_NAME);

        binding.menuAbout.setOnClickListener(view -> {
            Log.v(TAG, "Starting About Activity");
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        });
        setContentView(binding.getRoot()); // set content view for our layout
    }

    /**
     * Check if this module is enabled in LSPosed
     *
     * @param binding Pass ActivityMainBinding object as parameter
     */
    private void setModuleState(ActivityMainBinding binding) {
        if (isModuleEnabled()) {
            binding.moduleStatusCard.setCardBackgroundColor(getColor(R.color.purple_500));
            binding.moduleStatusIcon.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.baseline_check_circle_24));
            binding.moduleStatusText.setText(getString(R.string.card_title_activated));
            binding.serviceStatusText.setText(getString(R.string.card_detail_activated));
            binding.serveTimes.setText(getString(R.string.card_serve_time));
        } else {
            binding.moduleStatusCard.setCardBackgroundColor(getColor(R.color.red_500));
            binding.moduleStatusIcon.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.baseline_error_24));
            binding.moduleStatusText.setText(getText(R.string.card_title_not_activated));
            binding.serviceStatusText.setText(getText(R.string.card_detail_not_activated));
            binding.serveTimes.setVisibility(View.GONE);
        }
    }

    /**
     * Self-hook method.
     * Logging and Boolean object are present to avoid ART optimization.
     */
    @SuppressWarnings("all")
    private static boolean isModuleEnabled() {
        Log.i(TAG, "Xposed module not active.");
        return Boolean.valueOf(false);
    }
}
