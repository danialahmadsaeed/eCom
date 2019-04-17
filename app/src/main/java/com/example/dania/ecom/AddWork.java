package com.example.dania.ecom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dania.ecom.Classes.AddWorkClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWork extends AppCompatActivity {



    private DatabaseReference mDatabase;
    private EditText etName, etEmail,etPhone, etPayment, etLocation, etWorktype;
    private Button addWork;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    public String sName, sEmail, sPhone,  sLocation, sWork,  sPayment;
    float rate = (float) 0.0;
    int counter;
    Button btn;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid().toString();

        btn = (Button) findViewById(R.id.regiterJob);


        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPhone = (EditText) findViewById(R.id.phone);
        etPayment = (EditText) findViewById(R.id.payment);
        etLocation = (EditText) findViewById(R.id.location);
        etWorktype = (EditText) findViewById(R.id.workType);

        addWork = (Button) findViewById(R.id.regiterJob);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_LONG).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sName = etName.getText().toString();
                sEmail = etEmail.getText().toString();
                sPhone = etPhone.getText().toString().trim();
                sLocation = etLocation.getText().toString();
                sWork = etWorktype.getText().toString().trim();
                sPayment = etPayment.getText().toString();

                String userId =mAuth.getUid();



                AddWorkClass addWorkClass = new AddWorkClass(sName, sEmail, sPhone, sLocation, sPayment, sWork, rate, counter, userID);

                mDatabase.child("Jobs").child(userId).setValue(addWorkClass);
                finish();

            }
        });

    }

    public void onClick(View view) {





    }

}
