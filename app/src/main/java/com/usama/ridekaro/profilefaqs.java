package com.usama.ridekaro;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.froyo.usama.R;

public class profilefaqs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilefaqs);

        CardView cardView1 = findViewById(R.id.card1);
        CardView cardView2 = findViewById(R.id.card2);
        CardView cardView3 = findViewById(R.id.card3);
        CardView cardView4 = findViewById(R.id.card4);
        CardView cardView5 = findViewById(R.id.card5);


        // Load the animation from XML
        Animation slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.animation1);

        // Apply the animation to the CardView
        cardView1.startAnimation(slideInAnimation);
        cardView2.startAnimation(slideInAnimation);
        cardView3.startAnimation(slideInAnimation);
        cardView4.startAnimation(slideInAnimation);
        cardView5.startAnimation(slideInAnimation);

    }
}
