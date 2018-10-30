package com.dankook.tagme.view.store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dankook.tagme.R;

public class VReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton)findViewById(R.id.radioButton4);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
    }
}
