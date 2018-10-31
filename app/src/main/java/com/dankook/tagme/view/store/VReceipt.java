package com.dankook.tagme.view.store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dankook.tagme.R;

public class VReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        ImageView iv = (ImageView)findViewById(R.id.imageView);
        TextView tv = (TextView)findViewById(R.id.textView);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        TextView tv5 = (TextView)findViewById(R.id.textView5);
        TextView tv7 = (TextView)findViewById(R.id.textView7);
        TextView tv8 = (TextView)findViewById(R.id.textView8);
        TextView tv9 = (TextView)findViewById(R.id.textView9);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton)findViewById(R.id.radioButton4);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
    }
}
