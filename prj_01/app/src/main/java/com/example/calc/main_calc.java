package com.example.calc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;


public class main_calc extends AppCompatActivity {

    TextView textView;
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calc);
        textView = (TextView)findViewById(R.id.textView1);
    }

    public void addChar ( View v ) {
        String current_string = textView.getText().toString();
        String new_char = ( (Button)v ).getText().toString();

        textView.setText( String.format("%s%s", current_string, new_char) );
    }


    public void addOperation ( View v ) {
        if (operation == "") {
            addChar( v );
            operation = ( (Button) v ).getText().toString();
        }
    }

    public void clearField (View v) {
        textView.setText("");
        operation = "";
    }

    public void calculate ( View v ) {
        String[] s_parts = textView.getText().toString().split( Pattern.quote(operation) );
        if (s_parts.length == 2) {
            double[] parts = { Double.parseDouble(s_parts[0]), Double.parseDouble(s_parts[1]) };

            double answer = 1;
            switch ( operation ) {
                case "+":
                    answer = parts[0] + parts[1];
                    break;
                case "-":
                    answer = parts[0] - parts[1];
                    break;
                case "*":
                    answer = parts[0] * parts[1];
                    break;
                case "/":
                    answer = parts[0] / (double) parts[1];
                    break;
            }

            if ( (long) answer == answer ) {
                textView.setText( String.format("%d", (int) answer) );
            } else {
                textView.setText( String.format("%f", answer) );
            }

            operation = "";
        }
    }

    public void showAbout ( View v ) {
        Intent intent = new Intent(main_calc.this, AboutInfo.class);
        startActivity(intent);
    }



}
