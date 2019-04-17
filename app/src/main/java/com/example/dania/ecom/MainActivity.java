package com.example.dania.ecom;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dania.ecom.Classes.AddWorkClass;
import com.example.dania.ecom.Classes.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private DatabaseReference myRef;
    TextView profileName;
    TextView profileEmail;
    ArrayAdapter<String> adapter;
    ListView listView;
    ArrayList<String> arrayList;
    DatabaseReference ref;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String userId;
    AddWorkClass addWorkClass;

    private DrawerLayout drawer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //#########################################################
        addWorkClass = new AddWorkClass();
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        listView = (ListView) findViewById(R.id.listView);
        //  Toast.makeText(getApplicationContext(), panditTypeSelected, Toast.LENGTH_SHORT).show();


        adapter = new ArrayAdapter<String>(this, R.layout.activity_main_temp, R.id.workerInfo, arrayList);

        ref.child("Jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    addWorkClass = ds.getValue(AddWorkClass.class);
                    String pName = addWorkClass.getName().toString();
                    String pEmail = addWorkClass.getEmail().toString();
                    String pPhone = addWorkClass.getPhone().toString();
                    String pType = addWorkClass.getType().toString();
                    float pRating = addWorkClass.getRating();
                    String payment = addWorkClass.getPayment().toString();
//                                    String[] sm = ds.getRef().toString().split("/");
//                                    String panditID = sm[sm.length - 1];

                    arrayList.add(pName + " , " + pEmail + " , " + pPhone + " , " + pType + " , " + pRating + " , " + payment +"$");
                    //panditEmail.add(pEmail);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
// kkkk
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( MainActivity.this, MsgActivity.class);
                intent.putExtra("WorkerProfile", listView.getItemAtPosition(position).toString());
                startActivity(intent) ;

            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MsgFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CartFragment()).commit();
                break;
            case R.id.nav_additem:
                Toast.makeText(getApplicationContext(), "add item", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_work:
                startActivity(new Intent(MainActivity.this, AddWork.class));
                break;
            case R.id.nav_exit:
                finish();
                break;
            case R.id.nav_logIn:
                startActivity(new Intent(MainActivity.this, logInActivity.class));

                break;
            case R.id.nav_home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));

                break;
        }
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }

    private void setActionBar(android.support.v7.widget.Toolbar toolbar) {
    }

}