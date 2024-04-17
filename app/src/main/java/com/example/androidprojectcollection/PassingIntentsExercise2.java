package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassingIntentsExercise2 extends AppCompatActivity {

    TextView tFname, tLname, tGender, tStatus, tBdate, tPnum, tEmail, tNation, tField, tHobby, tEmergency;

    Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);

        tFname = findViewById(R.id.txtPI2Fname);
        tLname = findViewById(R.id.txtPI2Lname);
        tGender = findViewById(R.id.txtPI2Gender);
        tStatus = findViewById(R.id.txtPI2Status);
        tBdate = findViewById(R.id.txtPI2Bdate);
        tPnum = findViewById(R.id.txtPI2Pnumber);
        tEmail = findViewById(R.id.txtPI2Eaddress);
        tNation = findViewById(R.id.txtPI2Nation);
        tField = findViewById(R.id.txtPI2Field);
        tHobby = findViewById(R.id.txtPI2Hobby);
        tEmergency = findViewById(R.id.txtPI2Emergency);

        buttonReturn = (Button) findViewById(R.id.btnReturn);

        Intent intent = getIntent();

        String fname = intent.getStringExtra("fname_key");
        if (fname.equals("First Name")) {
            fname = "";
        }
        String lname = intent.getStringExtra("lname_key");
        if (lname.equals("Last Name")) {
            lname = "";
        }
        String gender = intent.getStringExtra("gender_key");
        String status = intent.getStringExtra("status_key");
        String bdate = intent.getStringExtra("bdate_key");
        String pnum = intent.getStringExtra("pnum_key");
        String email = intent.getStringExtra("eadd_key");
        String nation = intent.getStringExtra("nation_key");
        String field = intent.getStringExtra("pfield_key");
        String hobby = intent.getStringExtra("fhobby_key");
        String emergency = intent.getStringExtra("econtact_key");

        tFname.setText(fname);
        tLname.setText(lname);
        tGender.setText(gender);
        tStatus.setText(status);
        tBdate.setText(bdate);
        tPnum.setText(pnum);
        tEmail.setText(email);
        tNation.setText(nation);
        tField.setText(field);
        tHobby.setText(hobby);
        tEmergency.setText(emergency);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(PassingIntentsExercise2.this, PassingIntentsExercise.class);
                startActivity(intent2);
            }
        });
    }
}