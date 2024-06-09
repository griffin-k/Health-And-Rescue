package com.usama.ridekaro;




import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.froyo.usama.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class profile : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var submitButton: Button
    private lateinit var inputName: EditText
    private lateinit var inputPhone: EditText
    private lateinit var inputMail: EditText
    private lateinit var inputHome: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().reference

        // Initialize UI components
        inputName = findViewById(R.id.inputname)
        inputPhone = findViewById(R.id.inputphone)
        inputMail = findViewById(R.id.inputmail)
        inputHome = findViewById(R.id.inputhome)

        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            // Get data from input fields
            val name = inputName.text.toString()
            val phone = inputPhone.text.toString()
            val mail = inputMail.text.toString()
            val home = inputHome.text.toString()

            // Create a unique key for the data entry
            val entryKey = database.child("userInput").push().key

            // Create a timestamp
            val timestamp = System.currentTimeMillis()

            // Create a data object to be sent to Firebase
            val data = UserData(name, phone, mail, home, timestamp)

            // Send data to Firebase
            if (entryKey != null) {
                database.child("userInput").child(entryKey).setValue(data)
                    .addOnSuccessListener {
                        // Data sent successfully
                        // You can show a success message here if needed
                        showToast("Data Saved")

                        // Hide the button permanently
                        submitButton.visibility = View.GONE
                    }
                    .addOnFailureListener {
                        // Failed to send data
                        // You can show an error message here if needed
                        showToast("Error Saving Data")
                    }
            }
        }

        // Retrieve and populate data from Firebase
        retrieveLatestEntry()
    }

    private fun retrieveLatestEntry() {
        val latestEntryQuery = database.child("userInput").orderByChild("timestamp").limitToLast(1)
        latestEntryQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val data = childSnapshot.getValue(UserData::class.java)
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

    private fun populateInputFields(data: UserData) {
        inputName.setText(data.name)
        inputPhone.setText(data.phone)
        inputMail.setText(data.mail)
        inputHome.setText(data.home)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

data class UserData(
    val name: String = "",
    val phone: String = "",
    val mail: String = "",
    val home: String = "",
    val timestamp: Long = 0
)
