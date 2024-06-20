package com.example.financialaidallocation.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.financialaidallocation.R;

public class NeedHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_need_help);

        // Find TextViews
        TextView emailTextView = findViewById(R.id.emaail);
        TextView facebookTextView = findViewById(R.id.fab);
        TextView instagramTextView = findViewById(R.id.insta);

        // Make TextViews clickable
        emailTextView.setMovementMethod(LinkMovementMethod.getInstance());
        facebookTextView.setMovementMethod(LinkMovementMethod.getInstance());
        instagramTextView.setMovementMethod(LinkMovementMethod.getInstance());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}