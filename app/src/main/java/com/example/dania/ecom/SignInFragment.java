package com.example.dania.ecom;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SignInFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signin, container, false);


            mAuth = FirebaseAuth.getInstance();
            userRegistrationBtn = (Button) v.findViewById(R.id.registrationActivityBtn);
            passwrodTxt = (TextView) v.findViewById(R.id.password);
            emailTxt = (TextView) v.findViewById(R.id.emailEditText);
            loginBtn = (Button) v.findViewById(R.id.singinButton);
            mAuth = FirebaseAuth.getInstance();

//
            forgotPassword = (TextView) v.findViewById(R.id.tvForgotPassword);


            forgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), forgotPasswordActivity.class));
                }
            });

            userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), registrationActivity.class));

                }
            });
//
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userLogin();
                }
            });

            // Locator
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            }
//
//            //gets address automatically
//            //  getLocation();
//
//
//
//
//            //newly added
//
//            //////
//            mAuth = FirebaseAuth.getInstance();
//            firebaseAuth = FirebaseAuth.getInstance();
//            //logout = (Button) findViewById(R.id.logoutBtn);
//            //  FacebookSdk.sdkInitialize(getApplicationContext());
//            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//            FirebaseApp.initializeApp(getActivity());
//            myRef = firebaseDatabase.getInstance().getReference().child("Users");
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        return v;
//    }
//    private void userLogin() {
//        String email = emailTxt.getText().toString().trim();
//        String password = passwrodTxt.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            emailTxt.setError("Email is required");
//            emailTxt.requestFocus();
//            return;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailTxt.setError("Please enter a valid email");
//            emailTxt.requestFocus();
//            return;
//        }
//
//        if (password.isEmpty()) {
//            passwrodTxt.setError("Password is required");
//            passwrodTxt.requestFocus();
//            return;
//        }
//
//        if (password.length() < 6) {
//            passwrodTxt.setError("Minimum lenght of password should be 6");
//            passwrodTxt.requestFocus();
//            return;
//        }
//
//
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if (task.isSuccessful()) {
//                    checkEmailVerification();
//                } else {
//                    Toast.makeText(getActivity().getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private  void checkEmailVerification(){
//        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
//        mAuth.getCurrentUser().getUid();
//        Boolean emailflag = firebaseUser.isEmailVerified();
//        if(emailflag){
//            startActivity(new Intent(getActivity(), MainActivity.class));
//        }
//        else{
//            Toast.makeText(getActivity(), "Please verify your email", Toast.LENGTH_SHORT).show();
//            mAuth.signOut();
//        }
//    }
        return inflater.inflate(R.layout.fragment_signin, container, false);
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
//                    checkEmailVerification();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
