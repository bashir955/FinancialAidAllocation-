package com.example.financialaidallocation.Activities;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.financialaidallocation.R;

public class AddPolicies extends AppCompatActivity {
    private Spinner policyspinner;
    private RadioGroup meritbaseRg,meritbase;
    private LinearLayout cgpaViews, strengthViews, needbaseViews, topstd, actionButtons;
    private RadioButton cgpa, strength;
    private EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_policies);
        meritbaseRg = findViewById(R.id.meritbase_rg);
        cgpaViews = findViewById(R.id.cgpaViews);
        strengthViews = findViewById(R.id.strengthViews);
        topstd = findViewById(R.id.topstd);
        description = findViewById(R.id.description);
        policyspinner=findViewById(R.id.policyForSpinner);
//        policyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 1) {
//                    cgpaViews.setVisibility(View.VISIBLE);
//                    actionButtons.setVisibility(View.VISIBLE);
//                    description.setVisibility(View.VISIBLE);
//                }
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        meritbaseRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cgpa) {
                    cgpaViews.setVisibility(View.VISIBLE);
                    strengthViews.setVisibility(View.GONE);
                    topstd.setVisibility(View.GONE);
                    description.setVisibility(View.GONE);
                } else if (checkedId == R.id.strength) {
                    cgpaViews.setVisibility(View.GONE);
                    strengthViews.setVisibility(View.VISIBLE);
                    topstd.setVisibility(View.VISIBLE);
                    description.setVisibility(View.VISIBLE);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}