package com.example.dania.ecom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPasswordActivity extends AppCompatActivity {

    private EditText resetPassEmail;
    private Button resetPasswordBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hides the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enables full screen

//h

        //automatic keyboard popup
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        resetPassEmail = (EditText) findViewById(R.id.etPasswordEmail);
        resetPasswordBtn = (Button) findViewById(R.id.resetBtn);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = resetPassEmail.getText().toString().trim();
                if(userEmail.equals("")){
                    Toast.makeText(forgotPasswordActivity.this, "Please Enter your Registered Email.", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(forgotPasswordActivity.this, "Password reset Email has been sent! ", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotPasswordActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(forgotPasswordActivity.this, "Password reset Email failed to be sent! ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });

    }
}
