# RHOnboardingSDK-Android by Rewire Holding LTD
---
## Overview
---
Our RHOnboardingSDK is a specialized software development kit designed to streamline and enhance the onboarding process for mobile through efficient and user-friendly photo upload features. This SDK is tailored for apps where the primary onboarding requirement involves users uploading photographs, such as in identity verification, profile creation, or document submission scenarios.
## Download
---
To integrate the RHOnboardingSDK into your project, ensure that **mavenCentral()** has already been added to your *settings.gradle* file.
```groovy
pluginManagement {
    repositories {
        ...
        mavenCentral()
    }
}
dependencyResolutionManagement {
    ...
    repositories {
        ...
        mavenCentral()
    }
}
```
After downloading the RHOnboardingSDK zip file, decompress it and place the contents in *project/app/libs* folder.  The next step is to set the SDK URL in your *settings.gradle* as follows:
```groovy
pluginManagement {
    repositories {
        ...
        maven{
            url("$rootDir/app/libs")
        }
        ...
    }
}
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven {
            url("$rootDir/app/libs")
        }
    }
}
```
The final step for implementing the library is to add the dependency in your build.gradle (Module:app):
```groovy
dependencies {
    ...
    implementation("com.rewireholding:rhonboardingsdk:(insert latest version)@aar") {  transitive = true }
}
```
RHOnboardingSDK requires at minimun Android API 23+.

## Getting started
---
You only need to create an instance of the **RHOnBoarding** class, which contains the following methods:
```kotlin
fun setAppLicense(user: String, password: String, isSandBox: Boolean, context: Context, lifecycleOwner: LifecycleOwner)
    
fun initOnBoardingFlow(applicantId: String,  context: Context, lifecycleOwner: LifecycleOwner, callBack: RHOnBoardingCallBack)
    
fun initOnBoardingFlow(journey: String,  firstName: String, lastName: String, dateOfBirth: String, context: Context, lifecycleOwner: LifecycleOwner, callBack: RHOnBoardingCallBack )

```
In your *initOnBoardingFlow* you will need to implement the *RHOnBoardingCallBack*. You will receive notifications of the SDK result by overriding the following methods in your callback implementation:
```kotlin
fun onSuccess()
fun onUserQuitProcess()
fun onError(exception: Exception?, message:String?)
```


## How do I use RHOnboardingSDK?
---
### 1. Obtain your Credentials
To begin the integration process, you'll need credentials to connect to the SDK. Please get in touch with the Rewire Holding Team.

### 2. Create a instance
Fristly, you will need to create a instance of the **RHOnBoarding** class.
```kotlin
//Kotlin
val rHOnBoarding = RHOnBoarding()

//Java
RHOnBoarding rhOnBoarding = new RHOnBoarding();
```
### 3. Create a instance
To verify your identity as client, you need to set your credentials using the *".setAppLicense()"* method.
```kotlin
//Kotlin
class YourActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            ...
        val rHOnBoarding = RHOnBoarding()
        rHOnBoarding.setAppLicense("your user", "your password", true, this, this)
    }
}

//Java
public class YourActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            ...
        RHOnBoarding rhOnBoarding = new RHOnBoarding();
        rhOnBoarding.setAppLicense("your user", "your password", true, this, this);
    }
}
```
### 4. Start the flow
You will have two variants, depending on whether you already have an **ApplicantID** or not. In both cases, you will need to implement a **RHOnBoardingCallBack** as parameter to handle the SDK result.
1. You already have an **ApplicantID**.

```kotlin
//Kotlin
class YourActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
        ...
        rhOnBoarding.initOnBoardingFlow(applicantId, this, this, object : RHOnBoardingCallBack {
            override fun onSuccess() { /* Your sucess handling code */}

            override fun onUserQuitProcess() { /* Your quitted process handling code */ }
            
            override fun onError(exception: Exception?, message: String?) { 
                /* Your error handling code */ 
            }
        })
    }
}

//Java
public class YourActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        rhOnBoarding.initOnBoardingFlow(applicantId, this, this, new RHOnBoardingCallBack() {
            @Override
            public void onSuccess() {/* Your sucess handling code */}

            @Override
            public void onUserQuitProcess() {/* Your quitted process handling code */ }

            @Override
            public void onError(@Nullable Exception e, @Nullable String s) {
                /* Your error handling code */
            }
        });
    }
}

```
2. If you don't have an **ApplicantID**, you will need to send your *journey* String and the user information as parameter.
```kotlin
//Kotlin
class YourActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
        ...
        rhOnBoarding.initOnBoardingFlow("journey", "UserFirstName", "UserLastName","YYYY-MM-dd", this, this,
        object : RHOnBoardingCallBack {
                override fun onSuccess() { /* Your sucess handling code */ }

                override fun onUserQuitProcess() { /* Your quitted process handling code */ }

                override fun onError(exception: Exception?, message: String?) { /* Your error handling code */ }
            }
        )
    }
}
//Java
public class YourActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        rhOnBoarding.initOnBoardingFlow("journey", 
            "UserFirstName", 
            "UserLastName",
            "YYYY-MM-dd", 
            this, 
            this, 
            new RHOnBoardingCallBack() {
                @Override
                public void onSuccess() {/* Your sucess handling code */}
    
                @Override
                public void onUserQuitProcess() {/* Your quitted process handling code */ }
    
                @Override
                public void onError(@Nullable Exception e, @Nullable String s) {
                    /* Your error handling code */
                }
        });
    }
}
```
## More information
---
### Sample App
We have included sample apps to show how to integrate with the RHOnboarding Android SDK using Kotlin and Java. See the `SampleApp` directory for more information.
### Support
Should you encounter any technical issues during integration, please contact Rewire Holding's Customer Support team
via [email](mailto:support@rewireholding.com), including the word ISSUE: at the start of the subject line.


Copyright 2023 Rewire Holding, Ltd. All rights reserved.
