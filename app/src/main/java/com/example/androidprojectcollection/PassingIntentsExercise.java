package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PassingIntentsExercise extends AppCompatActivity {

    Button submit, clear;
    TextView firstName, lastName, birthDate, phoneNumber, emailAddress ,nationality,
        primary, favoriteHobby, emergency;
    RadioButton male, female, others, single, married, divorced, widow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);

        submit = (Button) findViewById(R.id.btnSubmit);
        clear = (Button) findViewById(R.id.btnClear);

        firstName = (TextView) findViewById(R.id.eFName);
        lastName = (TextView) findViewById(R.id.eLName);
        birthDate = (TextView) findViewById(R.id.txtBDate);
        birthDate = (TextView) findViewById(R.id.txtBDate);
        phoneNumber = (TextView) findViewById(R.id.txtPN);
        emailAddress = (TextView) findViewById(R.id.txtEA);
        nationality = (TextView) findViewById(R.id.txtNationality);
        primary = (TextView) findViewById(R.id.txtField);
        favoriteHobby = (TextView) findViewById(R.id.txtHobby);
        emergency = (TextView) findViewById(R.id.txtEmergency);

        male = (RadioButton) findViewById(R.id.btnMale);
        female = (RadioButton) findViewById(R.id.btnFemale);
        others = (RadioButton) findViewById(R.id.btnOthers);
        single = (RadioButton) findViewById(R.id.btnSingle);
        married = (RadioButton) findViewById(R.id.btnMarried);
        divorced = (RadioButton) findViewById(R.id.btnDivorced);
        widow = (RadioButton) findViewById(R.id.btnWidow);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();

                String gender;
                if (male.isChecked()) {
                    gender = "Male";
                }else if(female.isChecked()) {
                    gender = "Female";
                }else if(others.isChecked()) {
                    gender = "Others";
                }else {
                    gender = "Unknown";
                }

                String status;
                if (single.isChecked()) {
                    status = "Single";
                }else if(married.isChecked()) {
                    status = "Married";
                }else if(divorced.isChecked()) {
                    status = "Divorced";
                }else if(widow.isChecked()) {
                    status = "Widow";
                }else {
                    status = "Unknown";
                }

                String bDate = birthDate.getText().toString();
                String pNumber = phoneNumber.getText().toString();
                String emailAdd = emailAddress.getText().toString();
                String nation = nationality.getText().toString();
                String pField = primary.getText().toString();
                String fHobby = favoriteHobby.getText().toString();
                String eContact = emergency.getText().toString();

                if (fName.isEmpty() || lName.isEmpty() || bDate.isEmpty() || pNumber.isEmpty() || emailAdd.isEmpty() ||
                        nation.isEmpty() || pField.isEmpty() || fHobby.isEmpty() || eContact.isEmpty() ||
                        gender.equals("Unknown") || status.equals("Unknown")) {
                    Toast.makeText(getApplicationContext(), "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);
                    intent.putExtra("fname_key", fName);
                    intent.putExtra("lname_key", lName);
                    intent.putExtra("gender_key", gender);
                    intent.putExtra("status_key", status);
                    intent.putExtra("bdate_key", bDate);
                    intent.putExtra("pnum_key", pNumber);
                    intent.putExtra("eadd_key", emailAdd);
                    intent.putExtra("nation_key", nation);
                    intent.putExtra("pfield_key", pField);
                    intent.putExtra("fhobby_key", fHobby);
                    intent.putExtra("econtact_key", eContact);

                    startActivity(intent);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Clear all text fields
                firstName.setText("First Name");
                lastName.setText("Last Name");
                birthDate.setText("");
                phoneNumber.setText("");
                emailAddress.setText("");
                nationality.setText("");
                primary.setText("");
                favoriteHobby.setText("");
                emergency.setText("");

                // Clear radio button selections
                male.setChecked(false);
                female.setChecked(false);
                others.setChecked(false);
                single.setChecked(false);
                married.setChecked(false);
                divorced.setChecked(false);
                widow.setChecked(false);
            }
        });
    }
}