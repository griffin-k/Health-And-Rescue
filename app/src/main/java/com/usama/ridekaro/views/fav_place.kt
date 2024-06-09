package com.usama.ridekaro.views;

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.froyo.usama.R
import com.google.firebase.database.*

class fav_place : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var submitButton: Button
    private lateinit var inputField1: EditText
    private lateinit var inputField2: EditText
    private lateinit var inputField3: EditText
    private lateinit var inputField4: EditText
    private lateinit var inputField5: EditText
    private lateinit var inputField6: EditText
    private lateinit var inputField7: EditText
    private lateinit var inputField8: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_place)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().reference

        // Initialize UI components
        inputField1 = findViewById(R.id.inputField1)
        inputField2 = findViewById(R.id.inputField2)
        inputField3 = findViewById(R.id.inputField3)
        inputField4 = findViewById(R.id.inputField4)
        inputField5 = findViewById(R.id.inputField5)
        inputField6 = findViewById(R.id.inputField6)
        inputField7 = findViewById(R.id.inputField7)
        inputField8 = findViewById(R.id.inputField8)

        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            // Get data from input fields
            val name = inputField1.text.toString()
            val bloodGroup = inputField2.text.toString()
            val allergies = inputField3.text.toString()
            val medicalCondition = inputField4.text.toString()
            val emergencyContact = inputField5.text.toString()
            val guardianPhone = inputField6.text.toString()
            val physicianName = inputField7.text.toString()
            val physicianContact = inputField8.text.toString()

            // Create a unique key for the data entry
            val entryKey = database.child("medicalData").push().key

            // Create a data object to be sent to Firebase
            val data = MedicalData(
                name,
                bloodGroup,
                allergies,
                medicalCondition,
                emergencyContact,
                guardianPhone,
                physicianName,
                physicianContact
            )

            // Send data to Firebase
            if (entryKey != null) {
                database.child("medicalData").child(entryKey).setValue(data)
                    .addOnSuccessListener {
                        // Data sent successfully
                        // You can show a success message here if needed
                        showToast("Data Saved")

                        // Hide the button permanently
                        submitButton.visibility = View.GONE

                        // Populate input fields with data
                        populateInputFields(data)
                    }
                    .addOnFailureListener {
                        // Failed to send data
                        // You can show an error message here if needed
                        showToast("Error Saving Data")
                    }
            }

        }

        // Retrieve and populate data from Firebase
        retrieveDataFromFirebase()
    }

    private fun retrieveDataFromFirebase() {
        val latestEntryRef = database.child("medicalData").limitToLast(1)
        latestEntryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val data = childSnapshot.getValue(MedicalData::class.java)
                    data?.let {
                        // Populate input fields with retrieved data
                        populateInputFields(it)

                        // Hide the button permanently
                        submitButton.visibility = View.GONE
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if needed
            }
        })
    }

    private fun populateInputFields(data: MedicalData) {
        inputField1.setText(data.name)
        inputField2.setText(data.bloodGroup)
        inputField3.setText(data.allergies)
        inputField4.setText(data.medicalCondition)
        inputField5.setText(data.emergencyContact)
        inputField6.setText(data.guardianPhone)
        inputField7.setText(data.physicianName)
        inputField8.setText(data.physicianContact)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

data class MedicalData(
    val name: String = "",
    val bloodGroup: String = "",
    val allergies: String = "",
    val medicalCondition: String = "",
    val emergencyContact: String = "",
    val guardianPhone: String = "",
    val physicianName: String = "",
    val physicianContact: String = ""
)
