package com.usama.ridekaro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.froyo.usama.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class drivermap extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Marker marker;
    private FirebaseDatabase database;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivermap);

        database = FirebaseDatabase.getInstance();

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set up a listener to fetch data from Firebase and update the marker
        database.getReference("/locations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = dataSnapshot.child("latitude").getValue(Double.class);
                Double longitude = dataSnapshot.child("longitude").getValue(Double.class);

                if (latitude != null && longitude != null) {
                    LatLng location = new LatLng(latitude, longitude);

                    // Show a dialog to accept or decline the ride
                    AlertDialog.Builder builder = new AlertDialog.Builder(drivermap.this);
                    builder.setTitle("New Ride Request");
                    builder.setMessage("Do you want to accept the ride?");
                    builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(drivermap.this);
                            builder.setTitle("Thank You");
                            builder.setMessage("For Accepting The Ride");

                            // Show the AlertDialog
                            AlertDialog thankYouDialog = builder.create();
                            thankYouDialog.show();

                            // Create a Handler to dismiss the dialog after 2 seconds
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    thankYouDialog.dismiss();
                                }
                            }, 2000);

                            // Dismiss the original ride request dialog
                            dialog.dismiss();
                        }

                    });
                    builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Delete the data from Firebase
                            dataSnapshot.getRef().removeValue();

                        }
                    });
                    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            // Handle cancel action if needed
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    if (marker == null) {
                        marker = mMap.addMarker(new MarkerOptions().position(location).title("Location Marker"));
                    } else {
                        marker.setPosition(location);
                    }

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database read error
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
