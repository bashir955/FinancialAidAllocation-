package com.example.financialaidallocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDashboard extends AppCompatActivity implements View.OnClickListener {
    public CardView c1, c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_dashboard);
        c1 = (CardView) findViewById(R.id.apply_scholarship);
        c2 = (CardView) findViewById(R.id.needbase_criteria);
        c3 = (CardView) findViewById(R.id.meritbase_criteria);
        c4 = (CardView) findViewById(R.id.need_help);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;
        int id = v.getId();

        if (id == R.id.apply_scholarship) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.needbase_criteria) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.meritbase_criteria) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.need_help) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);

        }
    }
}
