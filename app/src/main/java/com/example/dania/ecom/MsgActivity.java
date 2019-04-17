package com.example.dania.ecom;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cenkgun.chatbar.ChatBarView;
import com.example.dania.ecom.Classes.AddWorkClass;
import com.example.dania.ecom.Classes.MsgClass;
import com.example.dania.ecom.Classes.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgActivity extends AppCompatActivity {

    String senderName;
    String recieverID;
    String recieverName;
    String workerPhone;
    String workerType;
    String workerPreviosRating;
    String workerPayment;
    TextView msgWorkerTv;
    ChatBarView chatBarView;
    FirebaseUser firebaseUser;
    DatabaseReference ref;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String senderID = firebaseUser.getUid().toString();




      //  pName + " , " + pEmail + " , " + pPhone + " , " + pType + " , " + pRating + " , " + payment +"$"
        Bundle bundle = getIntent().getExtras();
        String pnEmail  = bundle.getString("WorkerProfile");
        List<String> myList = new ArrayList<String>(Arrays.asList(pnEmail.split(",")));
        msgWorkerTv = (TextView) findViewById(R.id.chatView);
        chatBarView =(ChatBarView) findViewById(R.id.chatBar);
        chatBarView.setMessageBoxHint(" ");
        chatBarView.setMessageBoxHint("Enter your message");

        recieverID = myList.get(6).toString().trim();
        recieverName = myList.get(0).toString().trim();
//        workerPhone = myList.get(2).toString().trim();
//        workerType = myList.get(3).toString().trim();
//        workerPreviosRating = myList.get(4).toString().trim();
//        workerPayment = myList.get(5).toString().trim();
////        counter = myList.get(6).toString().trim();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();


        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserProfile userProfile = new UserProfile();
                    userProfile = ds.getValue(UserProfile.class);
                    String tempID = userProfile.getId();
                    if(senderID.equals(tempID)){
                        senderName = userProfile.getUserName() + " " + userProfile.getUserSureName();
                        Toast.makeText(getApplicationContext(), senderName , Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Toast.makeText(getApplicationContext(), recieverID , Toast.LENGTH_LONG).show();
//
        chatBarView.setSendClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                msgWorkerTv.setText(chatBarView.getMessageText());
                String temStr = chatBarView.getMessageText();
                MsgClass msgClass = new MsgClass(senderID, senderName, recieverID, recieverName, temStr);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String time = dtf.format(now);
                Toast.makeText(getApplicationContext(), time , Toast.LENGTH_LONG).show();
                ref.child("Chats").child(recieverID).child(time).setValue(msgClass);

//                chatBarView.
//                chatBarView.
            }
        });
    }
}
