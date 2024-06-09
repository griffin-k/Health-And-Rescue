package com.usama.ridekaro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.froyo.usama.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class driverinfo extends AppCompatActivity {

    private EditText nameEditText, cnicEditText, vehicleTypeEditText, vehicleNoEditText,
            acStatusEditText, phoneEditText, drivingExperienceEditText, drivingLicenseEditText;
    private Button submitButton;

    private DatabaseReference driversDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverinfo);

        // Initialize the Firebase Database reference
        driversDatabaseReference = FirebaseDatabase.getInstance().getReference().child("drivers");

        // Initialize UI components
        nameEditText = findViewById(R.id.inputField1);
        cnicEditText = findViewById(R.id.inputField2);
        vehicleTypeEditText = findViewById(R.id.inputField3);
        vehicleNoEditText = findViewById(R.id.inputField4);
        acStatusEditText = findViewById(R.id.inputField5);
        phoneEditText = findViewById(R.id.inputField6);
        drivingExperienceEditText = findViewById(R.id.inputField7);
        drivingLicenseEditText = findViewById(R.id.inputField8);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDriverInfo();
            }
        });
    }

    private void saveDriverInfo() {
        String name = nameEditText.getText().toString();
        String cnic = cnicEditText.getText().toString();
        String vehicleType = vehicleTypeEditText.getText().toString();
        String vehicleNo = vehicleNoEditText.getText().toString();
        String acStatus = acStatusEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String drivingExperience = drivingExperienceEditText.getText().toString();
        String drivingLicense = drivingLicenseEditText.getText().toString();

        HashMap<String, Object> driverInfo = new HashMap<>();
        driverInfo.put("Name", name);
        driverInfo.put("CNIC", cnic);
        driverInfo.put("VehicleType", vehicleType);
        driverInfo.put("VehicleNo", vehicleNo);
        driverInfo.put("ACStatus", acStatus);
        driverInfo.put("Phone", phone);
        driverInfo.put("DrivingExperience", drivingExperience);
        driverInfo.put("DrivingLicense", drivingLicense);

        // Save the data to Firebase
        driversDatabaseReference.child(name).setValue(driverInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Data saved successfully, now fetch and display it
                            fetchAndDisplayDriverInfo(name);
                        } else {
                            // Handle any errors here
                        }
                    }
                });
    }

    private void fetchAndDisplayDriverInfo(String driverName) {
        driversDatabaseReference.child(driverName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Data retrieved from Firebase
                    String name = dataSnapshot.child("Name").getValue(String.class);
                    String cnic = dataSnapshot.child("CNIC").getValue(String.class);
                    String vehicleType = dataSnapshot.child("VehicleType").getValue(String.class);
                    String vehicleNo = dataSnapshot.child("VehicleNo").getValue(String.class);
                    String acStatus = dataSnapshot.child("ACStatus").getValue(String.class);
                    String phone = dataSnapshot.child("Phone").getValue(String.class);
                    String drivingExperience = dataSnapshot.child("DrivingExperience").getValue(String.class);
                    String drivingLicense = dataSnapshot.child("DrivingLicense").getValue(String.class);

                    // Update the UI fields with the retrieved data
                    nameEditText.setText(name);
                    cnicEditText.setText(cnic);
                    vehicleTypeEditText.setText(vehicleType);
                    vehicleNoEditText.setText(vehicleNo);
                    acStatusEditText.setText(acStatus);
                    phoneEditText.setText(phone);
                    drivingExperienceEditText.setText(drivingExperience);
                    drivingLicenseEditText.setText(drivingLicense);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors here
            }
        });
    }
}
