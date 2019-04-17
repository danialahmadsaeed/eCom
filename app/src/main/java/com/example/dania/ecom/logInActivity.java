package com.example.dania.ecom;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class logInActivity extends AppCompatActivity {

    String takeEmail;
    String takeName;
    String takeLastName;
    String takePhone = " ";
    FirebaseDatabase pdata;
    DatabaseReference pRef;
    private FirebaseAuth pAuth;

    //newly added
    private FirebaseAuth firebaseAuth;
    DatabaseReference myRef;
    private FacebookAuthProvider facebookAuthProvider;
    private FirebaseAuth mAuth;
    ////

    LocationManager locationManager;
    private Button userRegistrationBtn;
    private TextView emailTxt;
    private TextView passwrodTxt;
    private Button loginBtn;
    //FirebaseAuth mAuth;
//    LoginButton fbLogin;
    TextView forgotPassword;
    //    CallbackManager callbackManager;
    public static String userLocation = " ";
    PackageInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        userRegistrationBtn = (Button) findViewById(R.id.registrationActivityBtn);
        passwrodTxt = (TextView) findViewById(R.id.password);
        emailTxt = (TextView) findViewById(R.id.emailEditText);
        loginBtn = (Button) findViewById(R.id.singinButton);
        mAuth = FirebaseAuth.getInstance();

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        fbLogin = (LoginButton)findViewById(R.id.login_button);
//        callbackManager = CallbackManager.Factory.create();
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
//        fbLogin.setReadPermissions(Arrays.asList("email"));

//        //login state
//
//        if (mAuth.getCurrentUser() == null) {
//            //Toast.makeText(getApplicationContext(), "not regd", Toast.LENGTH_SHORT).show();
//        }
//
//        else {
//            Intent intent = new Intent(logInActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(logInActivity.this, forgotPasswordActivity.class));
            }
        });

        userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(logInActivity.this, registrationActivity.class));

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        // Locator
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        //gets address automatically
        //  getLocation();




        //newly added

        //////
        mAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        //logout = (Button) findViewById(R.id.logoutBtn);
        //  FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseApp.initializeApp(this);
        myRef = firebaseDatabase.getInstance().getReference().child("Users");










    }
    private void userLogin() {
        String email = emailTxt.getText().toString().trim();
        String password = passwrodTxt.getText().toString().trim();

        if (email.isEmpty()) {
            emailTxt.setError("Email is required");
            emailTxt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTxt.setError("Please enter a valid email");
            emailTxt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwrodTxt.setError("Password is required");
            passwrodTxt.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwrodTxt.setError("Minimum lenght of password should be 6");
            passwrodTxt.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
//                    checkEmailVerification();    // while umcommenting this delete the next line of code "start activity one
                    startActivity(new Intent(logInActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void checkEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        mAuth.getCurrentUser().getUid();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag){
            startActivity(new Intent(logInActivity.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }





}
