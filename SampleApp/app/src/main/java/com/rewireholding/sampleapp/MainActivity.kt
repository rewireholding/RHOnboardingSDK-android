package com.rewireholding.sampleapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rewireholding.onboarding.RHOnBoarding
import com.rewireholding.onboarding.RHOnBoardingCallBack
import com.rewireholding.sampleapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunchSdk.setOnClickListener {
            launchSdkByApplicantId()
        }

        binding.btnLaunchByUserData.setOnClickListener {
            launchSdkByUserData()
        }
    }

    private fun launchSdkByUserData() {
        val journey = binding.etJourney.text.toString().trim()
        val name = binding.etUserName.text.toString().trim()
        val lastName = binding.etUserLastName.text.toString().trim()
        val dob = binding.etBirthDate.text.toString().trim()
        if (!journey.isNullOrEmpty() && !name.isNullOrEmpty() && !lastName.isNullOrEmpty() && !dob.isNullOrEmpty()) {
            initSdkByUsingUserDataID(journey, name, lastName, dob)
        }
    }

    private fun launchSdkByApplicantId() {
        val applicantID = binding.etApplicantId.text.toString().trim()
        if (!applicantID.isNullOrEmpty()) {
            initSdkByApplicantID(applicantID)
        }
    }

    private fun initSdkByApplicantID(applicantId: String) {
        val rhOnBoarding = RHOnBoarding()
        rhOnBoarding.setAppLicense("test", "test", true, this, this)
        rhOnBoarding.initOnBoardingFlow(applicantId, this, this, object : RHOnBoardingCallBack {
            override fun onSuccess() {
                Toast.makeText(this@MainActivity, "Successful process", Toast.LENGTH_SHORT).show()
            }

            override fun onUserQuitProcess() {
                Toast.makeText(this@MainActivity, "User pressed quit.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onError(exception: Exception?, message: String?) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful process: ${message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun initSdkByUsingUserDataID(
        journey: String,
        name: String,
        lastName: String,
        dob: String
    ) {
        val rhOnBoarding = RHOnBoarding()
        rhOnBoarding.setAppLicense("test", "test", true, this, this)
        rhOnBoarding.initOnBoardingFlow(
            journey,
            name,
            lastName,
            dob,
            this,
            this,
            object : RHOnBoardingCallBack {
                override fun onSuccess() {
                    Toast.makeText(this@MainActivity, "Successful process", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onUserQuitProcess() {
                    Toast.makeText(this@MainActivity, "User pressed quit.", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onError(exception: Exception?, message: String?) {
                    Toast.makeText(
                        this@MainActivity,
                        "Unsuccessful process: ${message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}