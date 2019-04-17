package com.example.dania.ecom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dania.ecom.Classes.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class NavHeaderFragment extends Fragment  {
    private DatabaseReference myRef;
    TextView profileName;
    TextView profileEmail;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String userId;



    UserProfile uInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.nav_header, container, false);


        uInfo = new UserProfile();
        profileName = (TextView) v.findViewById(R.id.tName);
        profileEmail = (TextView) v.findViewById(R.id.tEmail);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();


        profileName.setText("Danial Saeed");
//
//        myRef.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                profileName.setText(dataSnapshot.child(userId).child("userName").getValue().toString()  + " " + dataSnapshot.child(userId).child("userSureName").getValue().toString()  );
//                profileEmail.setText(dataSnapshot.child(userId).child("userEmail").getValue().toString() );
//
//                //Toast.makeText(getApplicationContext(), uInfo.getUserPhone(),Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        return v;

    }
}
