package com.usama.ridekaro;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.froyo.usama.R;

public class driverDash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dash);


        CardView card1 = findViewById(R.id.card1);
        CardView card2 = findViewById(R.id.card2);
        CardView card3 = findViewById(R.id.card3);
        CardView card4 = findViewById(R.id.card4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(driverDash.this, drivermap.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(driverDash.this, driverinfo.class);
                startActivity(intent);
            }
        });


//        card3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(driverDash.this, driverinfo.class);
//                startActivity(intent);
//            }
//        });
//
//
//        card4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(driverDash.this, dashbordrides.class);
//                startActivity(intent);
//            }
//        });
    }
}
