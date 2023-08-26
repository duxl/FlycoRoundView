package com.flyco.RoundViewSample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flyco.roundview.RoundTextView;
import com.flyco.roundview.RoundViewDelegate;

public class MainActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void tt(View v) {
        Toast.makeText(this, "tt", Toast.LENGTH_SHORT).show();
    }
}
