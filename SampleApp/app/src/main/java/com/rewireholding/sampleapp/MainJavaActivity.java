package com.rewireholding.sampleapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rewireholding.onboarding.RHOnBoarding;
import com.rewireholding.onboarding.RHOnBoardingCallBack;
import com.rewireholding.onboarding.utils.RHOnBoardingException;
import com.rewireholding.sampleapp.databinding.ActivityMainJavaBinding;

public class MainJavaActivity extends AppCompatActivity {

    private ActivityMainJavaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLaunchSdk.setOnClickListener(v -> launchSdkByApplicantId());
        binding.btnLaunchByUserData.setOnClickListener(v -> launchSdkByUserData());
    }

    private void launchSdkByUserData() {
        String journey = binding.etJourney.getText().toString().trim();
        String name = binding.etUserName.getText().toString().trim();
        String lastName = binding.etUserLastName.getText().toString().trim();
        String dob = binding.etBirthDate.getText().toString().trim();

        if (journey != null && journey.length() > 0 && name != null && name.length() > 0
                && lastName != null && lastName.length() > 0 && dob != null && dob.length() > 0) {
            initSdkByUsingUserDataID(journey, name, lastName, dob);
        }
    }

    private void initSdkByUsingUserDataID(String journey, String name, String lastName, String dob) {
        RHOnBoarding rhOnBoarding = new RHOnBoarding();
        rhOnBoarding.setAppLicense("test", "test", true, this, this);
        rhOnBoarding.initOnBoardingFlow(
                journey,
                name,
                lastName,
                dob,
                this,
                this, new RHOnBoardingCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Successful process", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserQuitProcess() {
                        Toast.makeText(getApplicationContext(), "User pressed quit.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@Nullable Exception e, @Nullable String s) {
                        Toast.makeText(getApplicationContext(), "Unsuccessful process: ${message}", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void launchSdkByApplicantId() {
        String applicantId = binding.etApplicantId.getText().toString().trim();
        if (applicantId != null && applicantId.length() > 0) {
            initSdkByApplicantID(applicantId);
        }
    }

    private void initSdkByApplicantID(String applicantId) {
        RHOnBoarding rhOnBoarding = new RHOnBoarding();
        rhOnBoarding.setAppLicense("test", "test", true, this, this);
        rhOnBoarding.initOnBoardingFlow(applicantId, this, this, new RHOnBoardingCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "Successful process", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUserQuitProcess() {
                Toast.makeText(getApplicationContext(), "User pressed quit.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@Nullable Exception e, @Nullable String s) {
                Toast.makeText(getApplicationContext(), "Unsuccessful process: " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}