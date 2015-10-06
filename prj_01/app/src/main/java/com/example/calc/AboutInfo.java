package com.example.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AboutInfo extends AppCompatActivity {

    public Animation animation;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_one);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void startAnimation ( View v ) {
        textView.startAnimation( animation );

    }

}
