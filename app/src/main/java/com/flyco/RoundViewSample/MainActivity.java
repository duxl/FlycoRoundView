package com.flyco.RoundViewSample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
        findViewById(R.id.rtv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click--->RoundTextView_1", Toast.LENGTH_SHORT).show();
            }
        });

        RoundTextView rtv_2 = (RoundTextView) findViewById(R.id.rtv_2);
        rtv_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "LongClick--->RoundTextView_2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        final RoundTextView rtv_3 = (RoundTextView) findViewById(R.id.rtv_3);
        rtv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundViewDelegate delegate = rtv_3.getDelegate();
                delegate.setBackgroundColor(
                        delegate.getBackgroundColor() == Color.parseColor("#ffffff")
                                ? Color.parseColor("#F6CE59") : Color.parseColor("#ffffff"));
            }
        });

        findViewById(R.id.v_selected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                ((TextView) v).setText("是否选中---" + v.isSelected());
            }
        });

        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
            }
        });
    }
}
